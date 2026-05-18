package net.fryc.unremovableeffects.mixin;

import net.fryc.unremovableeffects.mixin_interfaces.Unremovable;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(StatusEffect.class)
abstract class StatusEffectMixin implements Unremovable {

    private boolean unremovable = false;

    @Override
    public boolean isUnremovable() {
        return unremovable;
    }

    @Override
    public void setUnremovable(boolean unremovable) {
        this.unremovable = unremovable;
    }
}
