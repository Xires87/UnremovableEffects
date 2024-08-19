package net.fryc.unremovableeffects.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fryc.unremovableeffects.UnremovableEffects;
import net.fryc.unremovableeffects.interfaces.Unremovable;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.io.InputStream;
import java.util.HashSet;

public class UnremovableStatusEffectsResourceReloadListener implements SimpleSynchronousResourceReloadListener {

    private static final String UNREMOVABLE_STATUS_EFFECTS_PATH = "unremovable_status_effects";
    public static final HashSet<StatusEffect> UNREMOVABLE_STATUS_EFFECTS = new HashSet<>();

    @Override
    public Identifier getFabricId() {
        return new Identifier(UnremovableEffects.MOD_ID, UNREMOVABLE_STATUS_EFFECTS_PATH);
    }

    @Override
    public void reload(ResourceManager manager) {
        UNREMOVABLE_STATUS_EFFECTS.clear();
        int currentReplacePriority = 0;
        boolean isReplacePresent = false;
        for(Identifier id : manager.findResources(UNREMOVABLE_STATUS_EFFECTS_PATH, path -> path.getPath().endsWith(".json")).keySet()) {
            try(InputStream stream = manager.getResource(id).get().getInputStream()) {

                JsonObject jsonObject = JsonParser.parseString(new String(stream.readAllBytes())).getAsJsonObject();
                boolean replace = JsonHelper.getBoolean(jsonObject, "replace", false);
                boolean shouldGo = !isReplacePresent || replace;

                if(replace){
                    int replacePriority = JsonHelper.getInt(jsonObject, "replace_priority", 0);
                    if(isReplacePresent){
                        if(replacePriority > currentReplacePriority){
                            currentReplacePriority = replacePriority;
                            UNREMOVABLE_STATUS_EFFECTS.clear();
                        }
                        else {
                            if(currentReplacePriority == replacePriority){
                                UnremovableEffects.LOGGER.warn("Found two Json files with the same replace priority! " +
                                        "Consider changing priority in one of the files");
                                UnremovableEffects.LOGGER.warn("'" + id.getPath() + "' was ignored - other file was loaded first");
                            }
                            shouldGo = false;
                        }
                    }
                    else {
                        UNREMOVABLE_STATUS_EFFECTS.clear();
                        isReplacePresent = true;
                        currentReplacePriority = replacePriority;
                    }
                }

                if(shouldGo){
                    JsonArray array = JsonHelper.getArray(jsonObject, "status_effects");
                    for (JsonElement jsonElement : array) {
                        StatusEffect effect = StatusEffectJsonHelper.asStatusEffect(jsonElement, "status_effects_array_element");
                        UNREMOVABLE_STATUS_EFFECTS.add(effect);
                    }
                }


            } catch(Exception e) {
                UnremovableEffects.LOGGER.error("Error occurred while loading resource json" + id.toString(), e);
            }
        }

        for(StatusEffect effect : Registries.STATUS_EFFECT){
            ((Unremovable) effect).setUnremovable(UNREMOVABLE_STATUS_EFFECTS.contains(effect));
        }
    }
}
