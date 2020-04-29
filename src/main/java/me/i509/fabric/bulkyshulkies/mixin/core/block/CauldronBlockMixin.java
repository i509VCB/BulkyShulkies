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

package me.i509.fabric.bulkyshulkies.mixin.core.block;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import me.i509.fabric.bulkyshulkies.api.block.base.AbstractShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.ender.EnderSlabBoxBlock;
import me.i509.fabric.bulkyshulkies.item.ShulkerBlockItem;

@Mixin(CauldronBlock.class)
public abstract class CauldronBlockMixin {
	/**
	 * @reason Redirect so we can use the exact same logic.
	 */
	@Redirect(
			at = @At(
				value = "CONSTANT",
				args = {
					"classValue=net.minecraft.block.ShulkerBoxBlock" // TODO Figure out why this fails in a real client
				}
			),
			method = "onUse"
	)
	private boolean bulkyshulkies_onUseCleanBulkyBox(Object reference, Class clazz) {
		return reference instanceof ShulkerBoxBlock || reference instanceof AbstractShulkerBoxBlock && !(reference instanceof EnderSlabBoxBlock);
	}

	@Redirect(
			at = @At(value = "NEW",
			target = "net/minecraft/item/ItemStack"
			),
			slice = @Slice(
				from = @At(value = "FIELD",
					opcode = Opcodes.GETSTATIC,
					target = "Lnet/minecraft/stat/Stats;CLEAN_BANNER:Lnet/minecraft/util/Identifier;"
				),
				to = @At(value = "FIELD",
					opcode = Opcodes.GETSTATIC,
					target = "Lnet/minecraft/stat/Stats;CLEAN_SHULKER_BOX:Lnet/minecraft/util/Identifier;"
				)
			),
			method = "onUse"
	)
	private ItemStack bulkyshulkies_createNewStack(ItemConvertible itemConvertible, int count, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		ItemStack handStack = player.getStackInHand(hand);
		BlockItem item = (BlockItem) handStack.getItem();

		if (item instanceof ShulkerBlockItem) { // This will not include the ender slab.
			ShulkerBlockItem shulkerBlockItem = (ShulkerBlockItem) item;
			return shulkerBlockItem.getBlock().getItemStack(null).copy();
		}

		// Hardwire to vanilla if it fails
		return new ItemStack(Blocks.SHULKER_BOX, 1);
	}
}
