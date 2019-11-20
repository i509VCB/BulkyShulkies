package me.i509.fabric.cursedshulkerboxes.api.item;

import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.item.ArmorItem;

public class AbstractShulkerHelmet extends ArmorItem implements DurabilityBasedProtection {
    public static final TrackedData<ShulkerBoxBlockEntity.AnimationStage> SHULKER_HELMET_STAGE = DataTracker.registerData(LivingEntity.class, HelmetTrackedDataStage.INSTANCE);

    public AbstractShulkerHelmet(Settings settings) {
        super(ShulkerArmorMaterials.SHULKER, EquipmentSlot.HEAD, settings);
    }
}
