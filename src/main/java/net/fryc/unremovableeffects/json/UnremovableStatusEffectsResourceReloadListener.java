package net.fryc.unremovableeffects.json;

import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.fryc.unremovableeffects.UnremovableEffects;
import net.fryc.unremovableeffects.interfaces.Unremovable;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

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
        FrycJsonHelper.manageReloading(manager, UNREMOVABLE_STATUS_EFFECTS_PATH, "status_effects", UNREMOVABLE_STATUS_EFFECTS, FrycJsonHelper::asStatusEffect);

        for(StatusEffect effect : Registries.STATUS_EFFECT){
            ((Unremovable) effect).setUnremovable(UNREMOVABLE_STATUS_EFFECTS.contains(effect));
        }
    }
}
