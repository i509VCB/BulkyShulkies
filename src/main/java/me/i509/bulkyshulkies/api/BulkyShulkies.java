/*
 * MIT License
 *
 * Copyright (c) 2020 i509VCB
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

package me.i509.bulkyshulkies.api;

import java.util.Objects;

import me.i509.bulkyshulkies.api.item.ShulkerHelmetStage;
import me.i509.bulkyshulkies.mod.BulkyShulkiesImpl;
import me.i509.bulkyshulkies.mod.Uninstantiable;
import me.i509.bulkyshulkies.mod.registry.ShulkerItems;
import me.i509.bulkyshulkies.mod.screen.ScreenHandlerKeys;

import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;

public final class BulkyShulkies {
	/**
	 * Checks if an item can be inserted into a shulker box.
	 *
	 * @param stack the item stack
	 * @return true if the item can be inserted, otherwise false
	 */
	public static boolean canInsertItem(ItemStack stack) {
		Objects.requireNonNull(stack, "Item stack cannot be null");

		return BulkyShulkiesImpl.getInstance().canInsertItem(stack);
	}

	/**
	 * Opens the current equiped shulker box helmet a player is wearing.
	 *
	 * @param player the player
	 * @return true if the helmet was opened, false if the shulker helmet was not equipped.
	 */
	public static boolean openShulkerBoxHelmet(ServerPlayerEntity player) {
		Objects.requireNonNull(player, "Player cannot be null");

		if (player.getEquippedStack(EquipmentSlot.HEAD).isEmpty()) {
			return false;
		}

		final ItemStack equippedStack = player.getEquippedStack(EquipmentSlot.HEAD);

		if (equippedStack.isOf(ShulkerItems.SHULKER_HELMET)) {
			// TODO: Screen handler
			ContainerProviderRegistry.INSTANCE.openContainer(ScreenHandlerKeys.SHULKER_HELMET, player, buf -> {
				buf.writeText(player.getEquippedStack(EquipmentSlot.HEAD).getName());
			});

			ShulkerHelmetStage.bulkyshulkies$getStageFromEntity(player).bulkyshulkies$setStage(ShulkerBoxBlockEntity.AnimationStage.OPENING);
			return true;
		}

		return false;
	}

	private BulkyShulkies() {
		Uninstantiable.whyDoIHearBossMusic(BulkyShulkies.class);
	}
}
