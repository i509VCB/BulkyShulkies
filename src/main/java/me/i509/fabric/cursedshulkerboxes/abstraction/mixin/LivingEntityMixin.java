package me.i509.fabric.cursedshulkerboxes.abstraction.mixin;

import me.i509.fabric.cursedshulkerboxes.abstraction.DurabilityBasedProtection;
import me.i509.fabric.cursedshulkerboxes.api.item.AbstractShulkerHelmet;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Iterator;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract Iterable<ItemStack> getArmorItems();

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/DamageUtil;getDamageLeft(FFF)F"), method = "applyArmorToDamage")
    public float onApplyArmorToDamage(float damageTaken, float armorPoints, float v) {
        float realArmorPoints = armorPoints; // Originally this is the input armor points.
        Iterator<ItemStack> armorItems = this.getArmorItems().iterator();
        do {
            ItemStack stack = armorItems.next();
            if(stack.getItem() instanceof AbstractShulkerHelmet) {
                if(!DurabilityBasedProtection.canApplyNoDurability(stack)) {
                    realArmorPoints-=((ArmorItem) stack.getItem()).getProtection(); // Here we remove the armor points logically supplied by the server. TODO Maybe change armor point icon in that context.
                }
            }
        } while (armorItems.hasNext());

        return DamageUtil.getDamageLeft(damageTaken, realArmorPoints, v);
    }
}
