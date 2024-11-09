package net.fryc.unremovableeffects.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fryc.unremovableeffects.interfaces.MilkUser;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.MilkBucketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MilkBucketItem.class)
abstract class MilkBucketMixin extends Item {

    public MilkBucketMixin(Settings settings) {
        super(settings);
    }

    /* replaced with WrapOperation to let other mods inject
    @Inject(method = "finishUsing(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;)Lnet/minecraft/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    private void overrideFinishUsingMethod(ItemStack stack, World world, LivingEntity user , CallbackInfoReturnable<ItemStack> ret) {
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat((MilkBucketItem)(Object)this));
        }

        if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
            stack.decrement(1);
        }

        if (!world.isClient()) {
            ((MilkUser) user).clearStatusEffectsExceptUnremovable();
        }

        ret.setReturnValue(stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack);
    }

     */

    @WrapOperation(
            method = "finishUsing(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/LivingEntity;)Lnet/minecraft/item/ItemStack;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;clearStatusEffects()Z")
    )
    private boolean overrideClearStatusEffectsMethod(LivingEntity instance, Operation<Boolean> original) {
        return ((MilkUser) instance).clearStatusEffectsExceptUnremovable();
    }
}
