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

package me.i509.bulkyshulkies.api.block.old.slab;

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.AutomaticItemPlacementContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class SlabBlockPlacementDispenserBehavior extends FallibleItemDispenserBehavior {
	// TODO: Make this result in the actual orientation of the slab block.
	protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
		this.setSuccess(false);
		Item item = stack.getItem();

		if (item instanceof BlockItem) {
			Direction dispenserFacing = pointer.getBlockState().get(DispenserBlock.FACING);
			BlockPos blockPos = pointer.getBlockPos().offset(dispenserFacing);
			Direction direction2 = pointer.getWorld().isAir(blockPos.down()) ? dispenserFacing : Direction.UP;
			this.setSuccess(((BlockItem) item).place(new AutomaticItemPlacementContext(pointer.getWorld(), blockPos, dispenserFacing, stack, direction2)) == ActionResult.SUCCESS);
		}

		return stack;
	}
}
