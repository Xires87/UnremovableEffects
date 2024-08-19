package net.fryc.unremovableeffects.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class StatusEffectJsonHelper {

    public static RegistryEntry<StatusEffect> asStatusEffect(JsonElement element, String name) {
        if (element.isJsonPrimitive()) {
            String string = element.getAsString();
            return (RegistryEntry<StatusEffect>) Registries.STATUS_EFFECT.getEntry(Identifier.of(string)).orElseThrow(() -> {
                return new JsonSyntaxException("Expected " + name + " to be a status effect, was unknown string '" + string + "'");
            });
        } else {
            throw new JsonSyntaxException("Expected " + name + " to be a status effect, was " + JsonHelper.getType(element));
        }
    }

}
