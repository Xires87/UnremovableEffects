package net.fryc.unremovableeffects.mixin_interfaces;

/**
 * Implemented in StatusEffect
 */
public interface Unremovable {

    /**
     *  Getter for "unremovable" variable
     */
    boolean isUnremovable();

    /**
     *  Setter for "unremovable" variable
     */
    void setUnremovable(boolean unremovable);
}
