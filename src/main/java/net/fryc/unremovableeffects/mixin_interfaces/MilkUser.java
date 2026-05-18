package net.fryc.unremovableeffects.mixin_interfaces;

/**
 * Implemented in LivingEntity
 */
public interface MilkUser {

    /**
     *  clearStatusEffects() from LivingEntity with "isUnremovable()" check.
     *  Used to keep unremovable effects after drinking milk
     */
    boolean clearStatusEffectsExceptUnremovable();
}
