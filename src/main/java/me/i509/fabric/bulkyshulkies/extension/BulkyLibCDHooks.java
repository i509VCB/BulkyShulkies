package me.i509.fabric.bulkyshulkies.extension;

import io.github.cottonmc.libcd.api.LibCDInitializer;
import io.github.cottonmc.libcd.api.condition.ConditionManager;
import io.github.cottonmc.libcd.api.tweaker.TweakerManager;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;

public class BulkyLibCDHooks implements LibCDInitializer {
	@Override
	public void initTweakers(TweakerManager tweakerManager) {
		// NO-OP
	}

	@Override
	public void initConditions(ConditionManager conditionManager) {
		conditionManager.registerCondition(BulkyShulkies.id("uses_resources"), this::requiresResourcesCondition);
	}

	private boolean requiresResourcesCondition(Object o) {
		return BulkyShulkies.getInstance().getConfig().shouldUseResources();
	}
}
