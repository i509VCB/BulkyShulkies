/*
 * MIT License
 *
 * Copyright (c) 2019, 2020 i509VCB
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

package me.i509.bulkyshulkies.mod.mixin.core.block.entity;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import me.i509.bulkyshulkies.api.block.old.entity.inventory.LootableBlockEntity;

@Mixin(LootableContainerBlockEntity.class)
abstract class LootableContainerBlockEntityMixin {
	@Inject(at = @At("TAIL"), method = "setLootTable(Lnet/minecraft/world/BlockView;Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/Identifier;)V")
	private static void setCustomBlockEntityTables(BlockView world, Random random, BlockPos pos, Identifier id, CallbackInfo ci) {
		final BlockEntity blockEntity = world.getBlockEntity(pos);

		if (blockEntity instanceof LootableContainerBlockEntity) {
			return; // Don't need to handle
		}

		if (blockEntity instanceof LootableBlockEntity) {
			((LootableBlockEntity) blockEntity).setLootTable(id, random.nextLong());
		}
	}
}
