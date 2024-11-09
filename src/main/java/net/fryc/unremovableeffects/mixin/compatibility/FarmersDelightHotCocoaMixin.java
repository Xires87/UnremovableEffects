package net.fryc.unremovableeffects.mixin.compatibility;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.fryc.unremovableeffects.interfaces.Unremovable;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;

@Pseudo
@Mixin(targets = "vectorwing.farmersdelight.common.item.HotCocoaItem")
abstract class FarmersDelightHotCocoaMixin {


    @WrapWithCondition(
            method = "affectConsumer(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;)V",
            at = @At(value = "INVOKE", target = "Ljava/util/ArrayList;add(Ljava/lang/Object;)Z")
    )
    private boolean checkIfEffectIsUnremovable(ArrayList<StatusEffect> instance, Object obj) {
        // same as in MilkBottle here
        if(obj instanceof StatusEffect effect){
            return !((Unremovable) effect).isUnremovable();
        }
        return true;
    }

}
