package net.fryc.unremovableeffects;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fryc.unremovableeffects.json.ItemsRemoveEffectResourceReloadListener;
import net.fryc.unremovableeffects.json.UnremovableStatusEffectsResourceReloadListener;
import net.minecraft.resource.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnremovableEffects implements ModInitializer {
	public static final String MOD_ID = "unremovableeffects";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new UnremovableStatusEffectsResourceReloadListener());
		ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new ItemsRemoveEffectResourceReloadListener());

	}
}
