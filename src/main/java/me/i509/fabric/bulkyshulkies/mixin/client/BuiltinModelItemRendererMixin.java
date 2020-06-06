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

package me.i509.fabric.bulkyshulkies.mixin.client;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.bulkyshulkies.api.block.AbstractShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.api.block.colored.ColoredShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.api.block.base.BasicShulkerBlock;
import me.i509.fabric.bulkyshulkies.client.BulkyShulkiesClientMod;

@Environment(EnvType.CLIENT)
@Mixin(BuiltinModelItemRenderer.class)
public class BuiltinModelItemRendererMixin {
	// TODO: Move to callback
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BlockItem;getBlock()Lnet/minecraft/block/Block;", shift = At.Shift.AFTER), method = "render")
	private void bulkyshulkies_onRender(ItemStack stack, ModelTransformation.Mode mode, MatrixStack matrix, VertexConsumerProvider vertexConsumerProvider, int light, int overlay, CallbackInfo ci) {
		Block block = ((BlockItem) stack.getItem()).getBlock();

		if (block instanceof BasicShulkerBlock) {
			@Nullable DyeColor color = null;

			if (block instanceof ColoredShulkerBoxBlock) {
				color = ((ColoredShulkerBoxBlock) block).getColor();
			}

			BlockEntity blockEntity = BulkyShulkiesClientMod.getRenderBlockEntity((AbstractShulkerBoxBlock) block, color);
			BlockEntityRenderDispatcher.INSTANCE.renderEntity(blockEntity, matrix, vertexConsumerProvider, light, overlay);
		}
	}
}
