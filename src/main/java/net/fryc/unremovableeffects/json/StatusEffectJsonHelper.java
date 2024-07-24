package net.fryc.unremovableeffects.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class StatusEffectJsonHelper {

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

}
