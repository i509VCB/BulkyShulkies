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

package me.i509.bulkyshulkies.mod.block.old;

import me.i509.bulkyshulkies.mod.registry.ShulkerScreenHandlers;
import me.i509.bulkyshulkies.mod.registry.ShulkerTexts;
import me.i509.bulkyshulkies.mod.screen.GenericScreenHandler11x7;
import org.checkerframework.checker.nullness.qual.Nullable;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.ShulkerBoxSlot;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;

import me.i509.bulkyshulkies.api.block.old.entity.colored.ColoredFacing1X1ShulkerBoxBlockEntity;
import me.i509.bulkyshulkies.mod.registry.ShulkerBlockEntities;

public class DiamondShulkerBoxBlockEntity extends ColoredFacing1X1ShulkerBoxBlockEntity {
	public DiamondShulkerBoxBlockEntity(@Nullable DyeColor color) {
		super(ShulkerBlockEntities.DIAMOND_SHULKER_BOX, ShulkerBoxConstants.DIAMOND_SLOT_COUNT, color);
	}

	public DiamondShulkerBoxBlockEntity() {
		this(null);
	}

	@Override
	protected Text getDefaultName() {
		return ShulkerTexts.DIAMOND_CONTAINER;
	}

	@Override
	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
		return new GenericScreenHandler11x7(ShulkerScreenHandlers.SHULKER_11x7_SCREEN_HANDLER, syncId, playerInventory, this, ShulkerBoxSlot::new);
	}
}
