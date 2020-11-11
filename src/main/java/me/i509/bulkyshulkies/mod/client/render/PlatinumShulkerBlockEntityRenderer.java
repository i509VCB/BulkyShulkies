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

package me.i509.bulkyshulkies.mod.client.render;

import java.util.Objects;
import java.util.function.IntSupplier;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.bulkyshulkies.mod.BulkyShulkies;
import me.i509.bulkyshulkies.api.Magnetism;
import me.i509.bulkyshulkies.api.ShulkerBoxType;
import me.i509.bulkyshulkies.api.block.old.entity.colored.ColoredFacing1X1ShulkerBoxBlockEntity;
import me.i509.bulkyshulkies.mod.block.old.PlatinumShulkerBoxBlock;
import me.i509.bulkyshulkies.mod.block.old.PlatinumShulkerBoxBlockEntity;
import me.i509.bulkyshulkies.mod.item.ShulkerItemTags;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class PlatinumShulkerBlockEntityRenderer extends Facing1x1ShulkerBlockEntityRenderer<PlatinumShulkerBoxBlockEntity> {
	private final boolean forceBox;
	@Nullable
	private final Direction forcedDirection;
	@Nullable
	private final IntSupplier boxSize;

	public PlatinumShulkerBlockEntityRenderer(ShulkerBoxType type, BlockEntityRendererFactory.Arguments arguments) {
		this(type, arguments, false, null, null);
	}

	public PlatinumShulkerBlockEntityRenderer(ShulkerBoxType type, BlockEntityRendererFactory.Arguments arguments, boolean forceBox, @Nullable Direction forcedDirection, @Nullable IntSupplier boxSize) {
		super(type, arguments);
		this.forceBox = forceBox;
		this.forcedDirection = forcedDirection;
		this.boxSize = boxSize;

		if (forceBox) {
			Objects.requireNonNull(this.forcedDirection);
			Objects.requireNonNull(this.boxSize);
		}
	}

	@Override
	public void render(ColoredFacing1X1ShulkerBoxBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int light, int defaultUV) {
		super.render(blockEntity, tickDelta, matrices, vertexConsumerProvider, light, defaultUV);

		if (this.forceBox) {
			//noinspection ConstantConditions
			final int size = this.boxSize.getAsInt();
			final Box box = new Box(0.0D, 0.0D, 0.0D, size, size, size);

			PlatinumShulkerBlockEntityRenderer.renderMagneticBox(matrices, vertexConsumerProvider, box, this.forcedDirection, 1.0F, 0.5F, 0.5F, 0.5F);
		} else if (blockEntity.hasWorld()) {
			final MinecraftClient client = MinecraftClient.getInstance();
			final ClientPlayerEntity player = client.player;

			if (player != null) {
				final Tag<Item> tag = ShulkerItemTags.SHULKER_MAGNET_WAND;

				if (tag.contains(player.getMainHandStack().getItem()) || tag.contains(player.getOffHandStack().getItem())) {
					final BlockPos blockEntityPos = blockEntity.getPos();
					final BlockState state = player.world.getBlockState(blockEntityPos);

					if (client.crosshairTarget.getType() == HitResult.Type.BLOCK) {
						final BlockHitResult blockHitResult = (BlockHitResult) client.crosshairTarget;
						final BlockEntity hitResultBlockEntity = player.world.getBlockEntity(blockHitResult.getBlockPos());

						if (hitResultBlockEntity instanceof PlatinumShulkerBoxBlockEntity) {
							if (hitResultBlockEntity.getPos().equals(blockEntity.getPos())) {
								// If the block entity at the crosshair is this block entity, render the box only for that block entity.
								if (state.getBlock() instanceof PlatinumShulkerBoxBlock) {
									final Direction facing = state.get(PlatinumShulkerBoxBlock.FACING);

									int size = BulkyShulkies.getInstance().getConfig().getPlatinumMagnetMaxRange();
									final Box box = new Box(0.0D, 0.0D, 0.0D, size, size, size);

									PlatinumShulkerBlockEntityRenderer.renderMagneticBox(matrices, vertexConsumerProvider, box, facing, 1.0F, 0.5F, 0.5F, 0.5F);
								}
							} else {
								return;
							}
						}
					}

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
		final Vec3d offset = Magnetism.getDirectionalBoxOffset(facing);

		matrices.translate(offset.getX(), offset.getY(), offset.getZ());
		WorldRenderer.drawBox(matrices, buffer, box, red, green, blue, alpha);

		matrices.pop();
	}
}
