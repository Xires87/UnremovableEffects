package net.fryc.unremovableeffects.mixin;

import net.fryc.unremovableeffects.interfaces.MilkUser;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MilkBucketItem.class)
abstract class MilkBucketMixin extends Item {

    public MilkBucketMixin(Settings settings) {
        super(settings);
    }

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
}
