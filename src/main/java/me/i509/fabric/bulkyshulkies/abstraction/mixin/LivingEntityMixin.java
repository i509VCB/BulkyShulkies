/*
 * MIT License
 *
 * Copyright (c) 2019 i509VCB
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.i509.fabric.bulkyshulkies.abstraction.mixin;

import java.util.Iterator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

import me.i509.fabric.bulkyshulkies.abstraction.DurabilityBasedProtection;

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

			if (stack.getItem() instanceof DurabilityBasedProtection) {
				if (DurabilityBasedProtection.canDamage(stack)) {
					realArmorPoints -= ((ArmorItem) stack.getItem()).getProtection(); // Here we remove the armor points logically supplied by the server. // TODO Maybe change armor point icon in that context.
				}
			}
		} while (armorItems.hasNext());

		return DamageUtil.getDamageLeft(damageTaken, realArmorPoints, v);
	}
}
