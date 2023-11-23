package net.fryc.unremovableeffects.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;

@Config(name = "unremovableeffects")
public class UnremovableEffectsConfig implements ConfigData {

    @Comment("All options affect only Milk Bucket. Other items and commands can still remove them")
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableSpeed = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableSlowness = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableHaste = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableMiningFatigue = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableStrength = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableInstantHealth = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableInstantDamage = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableJumpBoost = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableNausea = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableRegeneration = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableResistance = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableFireResistance = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableWaterBreathing = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableInvisibility = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableBlindness = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableNightVision = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableHunger = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableWeakness = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovablePoison = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableWither = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableHealthBoost = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableAbsorption = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableSaturation = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableGlowing = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableLevitation = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableLuck = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableUnluck = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableSlowFalling = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableConduitPower = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableDolphinsGrace = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableBadOmen = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableHeroOfTheVillage = false;
    @ConfigEntry.Gui.RequiresRestart
    public boolean unremovableDarkness = false;

}
