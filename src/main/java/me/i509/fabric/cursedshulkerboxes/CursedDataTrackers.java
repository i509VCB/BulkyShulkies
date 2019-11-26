package me.i509.fabric.cursedshulkerboxes;

import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;

import me.i509.fabric.cursedshulkerboxes.api.item.HelmetTrackedDataStage;

public class CursedDataTrackers {
	private CursedDataTrackers() {
		// NO-OP
	}

	static {
		TrackedDataHandlerRegistry.register(HelmetTrackedDataStage.INSTANCE);
	}

	public static final TrackedDataHandler<ShulkerBoxBlockEntity.AnimationStage> SHULKER_ANIMATION_STAGE = HelmetTrackedDataStage.INSTANCE;
}
