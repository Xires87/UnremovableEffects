package net.fryc.unremovableeffects.mixin;

import net.fryc.unremovableeffects.interfaces.MilkUser;
import net.fryc.unremovableeffects.interfaces.Unremovable;
import net.fryc.unremovableeffects.json.ItemsRemoveEffectResourceReloadListener;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
                if(!((Unremovable) effect.getEffectType().value()).isUnremovable()){
                    this.onStatusEffectRemoved(effect);
                    iterator.remove();
                }
            }

            return bl;
        }
    }

    @Inject(method = "consumeItem()V", at = @At(value = "INVOKE", target =
            "Lnet/minecraft/entity/LivingEntity;spawnConsumptionEffects(Lnet/minecraft/item/ItemStack;I)V", shift = At.Shift.AFTER))
    private void removeEffectsAfterUsingItem(CallbackInfo info) {
        LivingEntity dys = ((LivingEntity)(Object)this);
        if(!dys.getWorld().isClient()){
            if(ItemsRemoveEffectResourceReloadListener.ITEMS_REMOVE_STATUS_EFFECTS.containsKey(dys.getActiveItem().getItem())){
                ItemsRemoveEffectResourceReloadListener.ITEMS_REMOVE_STATUS_EFFECTS.get(dys.getActiveItem().getItem()).forEach(dys::removeStatusEffect);
            }
        }
    }
}
