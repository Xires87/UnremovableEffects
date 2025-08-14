package net.fryc.unremovableeffects.json;

import com.google.gson.*;
import net.fryc.unremovableeffects.UnremovableEffects;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.io.InputStream;
import java.util.Collection;

public class FrycJsonHelper {

    public static StatusEffect asStatusEffect(JsonElement element, String name) {
        if (element.isJsonPrimitive()) {
            String string = element.getAsString();
            return (StatusEffect) Registries.STATUS_EFFECT.getOrEmpty(new Identifier(string)).orElseThrow(() -> {
                return new JsonSyntaxException("Expected " + name + " to be a status effect, was unknown string '" + string + "'");
            });
        } else {
            throw new JsonSyntaxException("Expected " + name + " to be a status effect, was " + JsonHelper.getType(element));
        }
    }

    public static <T> void manageReloadingWithReplacing(ResourceManager manager, String resourcesPath, String arrayElement, Collection<T> collection, JsonSupplier<T> jsonSupplier){
        ReplaceManager replaceManager = new ReplaceManager();
        for(Identifier id : manager.findResources(resourcesPath, path -> path.getPath().endsWith(".json")).keySet()) {
            try(InputStream stream = manager.getResource(id).get().getInputStream()) {
                JsonObject jsonObject = JsonParser.parseString(new String(stream.readAllBytes())).getAsJsonObject();
                manageReloadingWithReplacing(id.getPath(), jsonObject, arrayElement, collection, jsonSupplier, replaceManager);
            } catch(Exception e) {
                UnremovableEffects.LOGGER.error("Error occurred while loading resource json" + id.toString(), e);
            }
        }
    }

    public static <T> void manageReloadingWithReplacing(String filePath, JsonObject jsonObject, String arrayElement, Collection<T> collection, JsonSupplier<T> jsonSupplier, ReplaceManager replaceManager){
        boolean replace = JsonHelper.getBoolean(jsonObject, "replace", false);
        boolean shouldGo = !replaceManager.isReplacePresent() || replace;

        if(replace){
            int replacePriority = JsonHelper.getInt(jsonObject, "replace_priority", 0);
            if(replaceManager.isReplacePresent()){
                if(replacePriority > replaceManager.getCurrentReplacePriority()){
                    replaceManager.setCurrentReplacePriority(replacePriority);
                    collection.clear();
                }
                else {
                    if(replaceManager.getCurrentReplacePriority() == replacePriority){
                        UnremovableEffects.LOGGER.warn("Found two Json files with the same replace priority! " +
                                "Consider changing priority in one of the files");
                        UnremovableEffects.LOGGER.warn("'" + filePath + "' was ignored - other file was loaded first");
                    }
                    shouldGo = false;
                }
            }
            else {
                collection.clear();
                replaceManager.setReplacePresent(true);
                replaceManager.setCurrentReplacePriority(replacePriority);
            }
        }

        if(shouldGo){
            JsonArray array = JsonHelper.getArray(jsonObject, arrayElement);
            for (JsonElement jsonElement : array) {
                T effect = jsonSupplier.get(jsonElement, "array_element");
                collection.add(effect);
            }
        }
    }

    @FunctionalInterface
    public interface JsonSupplier<T> {
        T get(JsonElement element, String name);
    }

}
