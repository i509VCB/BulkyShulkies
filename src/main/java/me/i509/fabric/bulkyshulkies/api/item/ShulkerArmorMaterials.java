/*
 * MIT License
 *
 * Copyright (c) 2019-2020 i509VCB
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

package me.i509.fabric.bulkyshulkies.api.item;

import java.util.function.Supplier;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public enum ShulkerArmorMaterials implements ArmorMaterial {
	SHULKER("shulker", 20, new int[]{2, 5, 6, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 0.0F, () -> Ingredient.ofItems(Items.GOLD_INGOT));

	private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
	private final String name;
	private final int durabilityMultiplier;
	private final int[] protectionAmounts;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final Lazy<Ingredient> repairIngredientSupplier;

	ShulkerArmorMaterials(String string_1, int int_1, int[] ints_1, int int_2, SoundEvent soundEvent_1, float float_1, Supplier<Ingredient> supplier_1) {
		this.name = string_1;
		this.durabilityMultiplier = int_1;
		this.protectionAmounts = ints_1;
		this.enchantability = int_2;
		this.equipSound = soundEvent_1;
		this.toughness = float_1;
		this.repairIngredientSupplier = new Lazy<>(supplier_1);
	}

	@Override
	public int getDurability(EquipmentSlot equipmentSlot_1) {
		return BASE_DURABILITY[equipmentSlot_1.getEntitySlotId()] * this.durabilityMultiplier;
	}

	@Override
	public int getProtectionAmount(EquipmentSlot equipmentSlot_1) {
		return this.protectionAmounts[equipmentSlot_1.getEntitySlotId()];
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
		return this.equipSound;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return this.repairIngredientSupplier.get();
	}

	@Override
	@Environment(EnvType.CLIENT)
	public String getName() {
		return this.name;
	}

	@Override
	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return 0.0F;
	}
}
