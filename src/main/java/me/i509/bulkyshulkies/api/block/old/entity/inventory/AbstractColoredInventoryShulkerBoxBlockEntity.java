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

package me.i509.bulkyshulkies.api.block.old.entity.inventory;

import me.i509.bulkyshulkies.api.block.old.colored.ColoredShulkerBoxBlock;
import org.checkerframework.checker.nullness.qual.Nullable;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.DyeColor;

public abstract class AbstractColoredInventoryShulkerBoxBlockEntity extends AbstractInventoryShulkerBoxBlockEntity implements ColoredShulkerBoxBlockEntity {
	@Nullable private DyeColor cachedColor;
	private boolean cachedColorUpdateNeeded;

	protected AbstractColoredInventoryShulkerBoxBlockEntity(BlockEntityType<? extends AbstractColoredInventoryShulkerBoxBlockEntity> blockEntityType, DirectionProperty directionProperty, int slots, @Nullable DyeColor color) {
		super(blockEntityType, directionProperty, slots);
		this.cachedColor = color;
	}

	@Nullable
	public DyeColor getColor() {
		if (this.cachedColorUpdateNeeded) {
			this.cachedColor = ColoredShulkerBoxBlock.getColor(this.getCachedState().getBlock());
			this.cachedColorUpdateNeeded = false;
		}

		return this.cachedColor;
	}
}
