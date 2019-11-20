package me.i509.fabric.cursedshulkerboxes.mixin;

import net.minecraft.enchantment.EnchantmentTarget;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "net/minecraft/enchantment/EnchantmentTarget$13")
public class EnchantmentTargetMixin {
    // TODO, prevent curse of binding from applying to the shulker box helmet at all.
}
