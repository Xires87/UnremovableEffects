package net.fryc.unremovableeffects.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HoneyBottleItem.class)
abstract class HoneyBottleMixin extends Item {

    public HoneyBottleMixin(Settings settings) {
        super(settings);
    }

    @WrapWithCondition(
            method = "finishUsing(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;)Lnet/minecraft/item/ItemStack;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;removeStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z")
    )
    private boolean preventPoisonRemoval(LivingEntity instance, StatusEffect effect) {
        return false;
    }
}
