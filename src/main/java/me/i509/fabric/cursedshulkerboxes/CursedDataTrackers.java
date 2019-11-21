package me.i509.fabric.cursedshulkerboxes;

import me.i509.fabric.cursedshulkerboxes.api.item.HelmetTrackedDataStage;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;

public class CursedDataTrackers {
    private CursedDataTrackers() {}

    static {
        TrackedDataHandlerRegistry.register(HelmetTrackedDataStage.INSTANCE);
    }

    public static final TrackedDataHandler<ShulkerBoxBlockEntity.AnimationStage> SHULKER_ANIMATION_STAGE = HelmetTrackedDataStage.INSTANCE;
}
