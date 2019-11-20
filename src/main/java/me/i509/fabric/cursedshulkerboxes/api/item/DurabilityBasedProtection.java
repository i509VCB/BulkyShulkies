package me.i509.fabric.cursedshulkerboxes.api.item;

import net.minecraft.item.ItemStack;

public interface DurabilityBasedProtection {
    static boolean canApplyNoDurability(ItemStack stack) {
        return !(stack.getMaxDamage()-1 <= stack.getDamage());
    }
}
