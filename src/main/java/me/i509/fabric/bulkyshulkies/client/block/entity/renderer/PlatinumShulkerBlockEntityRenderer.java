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

package me.i509.fabric.bulkyshulkies.client.block.entity.renderer;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.ShulkerBoxKeys;
import me.i509.fabric.bulkyshulkies.api.block.entity.colored.ColoredFacing1X1ShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.block.material.platinum.PlatinumShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.platinum.PlatinumShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.item.ShulkerItemTags;

@Environment(EnvType.CLIENT)
public class PlatinumShulkerBlockEntityRenderer extends Facing1x1ShulkerBlockEntityRenderer<PlatinumShulkerBoxBlockEntity> {
	public PlatinumShulkerBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
		super(dispatcher, ShulkerBoxKeys.PLATINUM);
	}

	@Override
	public void render(ColoredFacing1X1ShulkerBoxBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int i, int defaultUV) {
		super.render(blockEntity, tickDelta, matrices, vertexConsumerProvider, i, defaultUV);

		if (blockEntity.hasWorld()) {
			final ClientPlayerEntity player = MinecraftClient.getInstance().player;

			if (player != null) {
				final Tag<Item> tag = ShulkerItemTags.SHULKER_MAGNET_WAND;

				if (player.getMainHandStack().getItem().isIn(tag) || player.getOffHandStack().getItem().isIn(tag)) {
					final BlockPos blockEntityPos = blockEntity.getPos();
					final BlockState state = player.world.getBlockState(blockEntityPos);

					if (state.getBlock() instanceof PlatinumShulkerBoxBlock) {
						final Direction facing = state.get(PlatinumShulkerBoxBlock.FACING);

						int size = BulkyShulkies.getInstance().getConfig().getPlatinumMagnetMaxRange();
						final Box box = new Box(0.0D, 0.0D, 0.0D, size, size, size);

						PlatinumShulkerBlockEntityRenderer.renderMagneticBox(matrices, vertexConsumerProvider, box, facing, 1.0F, 0.5F, 0.5F, 0.5F);
					}
				}
			}
		}
	}

	public static void renderMagneticBox(MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, Box box, Direction facing, float red, float green, float blue, float alpha) {
		matrices.push();

		final VertexConsumer buffer = vertexConsumerProvider.getBuffer(RenderLayer.getLines());
		final Vec3d offset = PlatinumShulkerBoxBlockEntity.getDirectionalBoxOffset(facing);

		matrices.translate(offset.getX(), offset.getY(), offset.getZ());
		WorldRenderer.drawBox(matrices, buffer, box, red, green, blue, alpha);

		matrices.pop();
	}
}
