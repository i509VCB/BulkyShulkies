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

import java.util.function.Consumer;

import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;

import me.i509.fabric.bulkyshulkies.screen.ScreenHandlerKeys;

public class ShulkerHelmetItem extends ArmorItem {
	public ShulkerHelmetItem(Settings settings) {
		super(ShulkerArmorMaterials.SHULKER, EquipmentSlot.HEAD, settings);
	}

	@Override
	public boolean canRepair(ItemStack stack, ItemStack ingredient) {
		return this.type.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
	}

	public static void open(PlayerEntity playerEntity) {
		if (playerEntity instanceof ServerPlayerEntity) {
			// TODO: Screen handler
			ContainerProviderRegistry.INSTANCE.openContainer(ScreenHandlerKeys.SHULKER_HELMET, playerEntity, writer -> {
				writer.writeText(playerEntity.getEquippedStack(EquipmentSlot.HEAD).getName());
			});

			ShulkerHelmetStage.bulkyshulkies$getStageFromEntity(playerEntity).bulkyshulkies$setStage(ShulkerBoxBlockEntity.AnimationStage.OPENING);
		}
	}

	public static int handleItemDamage(ItemStack stack, int damageAmount, LivingEntity entity, Consumer<LivingEntity> breakCallback) {
		if (stack.getMaxDamage() - damageAmount <= stack.getDamage()) {
			final int remainingDurability = stack.getMaxDamage() - stack.getDamage();

			if (damageAmount > 1 && remainingDurability - damageAmount < 0) {
				// If all durability damage is applied, the item will break. Only apply the damage to reach 0
				// 3 remaining, 4 dmg = -1 durability -> breaks
				// subtract damage from remaining durability to get damage to actually apply.
				return damageAmount - remainingDurability;
			}

			// We are at 0 durability, do not damage the armor
			return 0;
		}

		return damageAmount;
	}
}
