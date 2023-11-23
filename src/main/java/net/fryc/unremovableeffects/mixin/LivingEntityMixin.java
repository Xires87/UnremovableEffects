package net.fryc.unremovableeffects.mixin;

import net.fryc.unremovableeffects.interfaces.MilkUser;
import net.fryc.unremovableeffects.interfaces.Unremovable;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;

@Mixin(LivingEntity.class)
abstract class LivingEntityMixin extends Entity implements Attackable, MilkUser {

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    protected void onStatusEffectRemoved(StatusEffectInstance effect){
    }


    @Override
    public boolean clearStatusEffectsExceptUnremovable() {
        LivingEntity dys = ((LivingEntity)(Object)this);
        if (dys.getWorld().isClient()) {
            return false;
        } else {
            Iterator<StatusEffectInstance> iterator = dys.getActiveStatusEffects().values().iterator();

            boolean bl;
            StatusEffectInstance effect;
            for(bl = false; iterator.hasNext(); bl = true) {
                effect = iterator.next();
                if(!((Unremovable) effect.getEffectType()).isUnremovable()){
                    this.onStatusEffectRemoved(effect);
                    iterator.remove();
                }
            }

            return bl;
        }
    }
}
