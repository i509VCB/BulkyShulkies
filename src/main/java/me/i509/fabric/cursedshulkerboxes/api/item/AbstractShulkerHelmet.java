package me.i509.fabric.cursedshulkerboxes.api.item;

import me.i509.fabric.cursedshulkerboxes.abstraction.DurabilityBasedProtection;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;

public class AbstractShulkerHelmet extends ArmorItem implements DurabilityBasedProtection {
    public AbstractShulkerHelmet(Settings settings) {
        super(ShulkerArmorMaterials.SHULKER, EquipmentSlot.HEAD, settings);
    }
}
