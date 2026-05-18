package net.fryc.unremovableeffects.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fryc.unremovableeffects.UnremovableEffects;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;

public class ItemsRemoveEffectResourceReloadListener implements SimpleSynchronousResourceReloadListener {

    private static final String ITEMS_REMOVE_STATUS_EFFECTS_PATH = "items_remove_status_effects";
    public static final HashMap<Item, HashMap<Identifier, HashSet<StatusEffect>>> ITEMS_REMOVE_STATUS_EFFECTS = new HashMap<>();

    @Override
    public Identifier getFabricId() {
        return Identifier.of(UnremovableEffects.MOD_ID, ITEMS_REMOVE_STATUS_EFFECTS_PATH);
    }

    @Override
    public void reload(ResourceManager manager) {
        ITEMS_REMOVE_STATUS_EFFECTS.clear();

        for(Identifier id : manager.findResources(ITEMS_REMOVE_STATUS_EFFECTS_PATH, path -> path.getPath().endsWith(".json")).keySet()) {
            try(InputStream stream = manager.getResource(id).get().getInputStream()) {
                JsonObject jsonObject = JsonParser.parseString(new String(stream.readAllBytes())).getAsJsonObject();
                JsonArray itemsArray = JsonHelper.getArray(jsonObject, "items");
                JsonArray effectArray = JsonHelper.getArray(jsonObject, "status_effects");

                HashSet<StatusEffect> effects = new HashSet<>();

                for (JsonElement jsonElement : effectArray) {
                    StatusEffect effect = FrycJsonHelper.asStatusEffect(jsonElement, "array_element");
                    effects.add(effect);
                }

                for (JsonElement jsonElement : itemsArray) {
                    boolean actionsEmpty = true;

                    if(jsonElement.isJsonObject()) {
                        RegistryEntry<Item> item = JsonHelper.asItem(jsonElement.getAsJsonObject().get("id"), "item_id");
                        JsonArray supportedActionsArray = JsonHelper.getArray(jsonElement.getAsJsonObject(), "supported_actions", new JsonArray());

                        if(!supportedActionsArray.isEmpty()) {
                            ITEMS_REMOVE_STATUS_EFFECTS.putIfAbsent(item.value(), new HashMap<>());

                            for (JsonElement supportedActionElement : supportedActionsArray) {
                                String action = JsonHelper.asString(supportedActionElement, "actions_array_element");
                                Identifier actionId = Identifier.of(action);
                                ITEMS_REMOVE_STATUS_EFFECTS.get(item.value()).putIfAbsent(actionId, new HashSet<>());
                                ITEMS_REMOVE_STATUS_EFFECTS.get(item.value()).get(actionId).addAll(effects);
                            }

                            actionsEmpty = false;
                        }
                    }

                    if(actionsEmpty) {
                        RegistryEntry<Item> item = JsonHelper.asItem(jsonElement, "array_element");
                        ITEMS_REMOVE_STATUS_EFFECTS.putIfAbsent(item.value(), new HashMap<>());
                        ITEMS_REMOVE_STATUS_EFFECTS.get(item.value()).putIfAbsent(null, new HashSet<>());
                        ITEMS_REMOVE_STATUS_EFFECTS.get(item.value()).get(null).addAll(effects);
                    }
                }

            } catch(Exception e) {
                UnremovableEffects.LOGGER.error("Error occurred while loading resource json" + id.toString(), e);
            }
        }
    }
}
