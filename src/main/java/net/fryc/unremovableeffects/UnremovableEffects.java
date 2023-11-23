package net.fryc.unremovableeffects;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fryc.unremovableeffects.config.UnremovableEffectsConfig;
import net.fryc.unremovableeffects.interfaces.Unremovable;
import net.minecraft.entity.effect.StatusEffects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnremovableEffects implements ModInitializer {
	public static final String MOD_ID = "unremovableeffects";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static UnremovableEffectsConfig config;

	@Override
	public void onInitialize() {
		AutoConfig.register(UnremovableEffectsConfig.class, JanksonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(UnremovableEffectsConfig.class).getConfig();

		((Unremovable) StatusEffects.SPEED).setUnremovable(UnremovableEffects.config.unremovableSpeed);
		((Unremovable) StatusEffects.SLOWNESS).setUnremovable(UnremovableEffects.config.unremovableSlowness);
		((Unremovable) StatusEffects.HASTE).setUnremovable(UnremovableEffects.config.unremovableHaste);
		((Unremovable) StatusEffects.MINING_FATIGUE).setUnremovable(UnremovableEffects.config.unremovableMiningFatigue);
		((Unremovable) StatusEffects.STRENGTH).setUnremovable(UnremovableEffects.config.unremovableStrength);
		((Unremovable) StatusEffects.INSTANT_HEALTH).setUnremovable(UnremovableEffects.config.unremovableInstantHealth);
		((Unremovable) StatusEffects.INSTANT_DAMAGE).setUnremovable(UnremovableEffects.config.unremovableInstantDamage);
		((Unremovable) StatusEffects.JUMP_BOOST).setUnremovable(UnremovableEffects.config.unremovableJumpBoost);
		((Unremovable) StatusEffects.NAUSEA).setUnremovable(UnremovableEffects.config.unremovableNausea);
		((Unremovable) StatusEffects.REGENERATION).setUnremovable(UnremovableEffects.config.unremovableRegeneration);
		((Unremovable) StatusEffects.RESISTANCE).setUnremovable(UnremovableEffects.config.unremovableResistance);
		((Unremovable) StatusEffects.FIRE_RESISTANCE).setUnremovable(UnremovableEffects.config.unremovableFireResistance);
		((Unremovable) StatusEffects.WATER_BREATHING).setUnremovable(UnremovableEffects.config.unremovableWaterBreathing);
		((Unremovable) StatusEffects.INVISIBILITY).setUnremovable(UnremovableEffects.config.unremovableInvisibility);
		((Unremovable) StatusEffects.BLINDNESS).setUnremovable(UnremovableEffects.config.unremovableBlindness);
		((Unremovable) StatusEffects.NIGHT_VISION).setUnremovable(UnremovableEffects.config.unremovableNightVision);
		((Unremovable) StatusEffects.HUNGER).setUnremovable(UnremovableEffects.config.unremovableHunger);
		((Unremovable) StatusEffects.WEAKNESS).setUnremovable(UnremovableEffects.config.unremovableWeakness);
		((Unremovable) StatusEffects.POISON).setUnremovable(UnremovableEffects.config.unremovablePoison);
		((Unremovable) StatusEffects.WITHER).setUnremovable(UnremovableEffects.config.unremovableWither);
		((Unremovable) StatusEffects.HEALTH_BOOST).setUnremovable(UnremovableEffects.config.unremovableHealthBoost);
		((Unremovable) StatusEffects.ABSORPTION).setUnremovable(UnremovableEffects.config.unremovableAbsorption);
		((Unremovable) StatusEffects.SATURATION).setUnremovable(UnremovableEffects.config.unremovableSaturation);
		((Unremovable) StatusEffects.GLOWING).setUnremovable(UnremovableEffects.config.unremovableGlowing);
		((Unremovable) StatusEffects.LEVITATION).setUnremovable(UnremovableEffects.config.unremovableLevitation);
		((Unremovable) StatusEffects.LUCK).setUnremovable(UnremovableEffects.config.unremovableLuck);
		((Unremovable) StatusEffects.UNLUCK).setUnremovable(UnremovableEffects.config.unremovableUnluck);
		((Unremovable) StatusEffects.SLOW_FALLING).setUnremovable(UnremovableEffects.config.unremovableSlowFalling);
		((Unremovable) StatusEffects.CONDUIT_POWER).setUnremovable(UnremovableEffects.config.unremovableConduitPower);
		((Unremovable) StatusEffects.DOLPHINS_GRACE).setUnremovable(UnremovableEffects.config.unremovableDolphinsGrace);
		((Unremovable) StatusEffects.BAD_OMEN).setUnremovable(UnremovableEffects.config.unremovableBadOmen);
		((Unremovable) StatusEffects.HERO_OF_THE_VILLAGE).setUnremovable(UnremovableEffects.config.unremovableHeroOfTheVillage);
		((Unremovable) StatusEffects.DARKNESS).setUnremovable(UnremovableEffects.config.unremovableDarkness);

	}
}
