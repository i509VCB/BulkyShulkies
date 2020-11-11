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

import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.math.Direction;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.bulkyshulkies.mod.BulkyShulkies;
import me.i509.bulkyshulkies.api.ShulkerBoxType;
import me.i509.bulkyshulkies.api.block.old.FacingShulkerBoxBlock;
import me.i509.bulkyshulkies.mod.block.old.ender.EnderSlabBoxBlockEntity;
import me.i509.bulkyshulkies.mod.client.ShulkerRenderLayers;
import me.i509.bulkyshulkies.mod.client.model.SlabShulkerModel;

@Environment(EnvType.CLIENT)
public class EnderSlabBlockEntityRenderer implements BlockEntityRenderer<EnderSlabBoxBlockEntity> {
	public EnderSlabBlockEntityRenderer(ShulkerBoxType type, BlockEntityRendererFactory.Arguments arguments) {
		this.type = type;
	}

	protected static final SlabShulkerModel<ShulkerEntity> MODEL = new SlabShulkerModel<>();
	protected final ShulkerBoxType type;

	@Override
	public void render(EnderSlabBoxBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int i, int defaultUV) {
		Direction direction = Direction.UP;

		if (blockEntity.hasWorld()) {
			BlockState blockState = blockEntity.getCachedState();

			if (blockState.contains(FacingShulkerBoxBlock.FACING)) {
				direction = blockState.get(FacingShulkerBoxBlock.FACING);
			}
		}

		SpriteIdentifier spriteIdentifier = new SpriteIdentifier(ShulkerRenderLayers.SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkies.id("be/shulker/" + type + "/shulker"));

		matrices.push();
		matrices.translate(0.5D, 0.5D, 0.5D); // Center the model
		float baseScale = 0.9995F;
		matrices.scale(baseScale, baseScale, baseScale); // Scale em up
		matrices.multiply(direction.getRotationQuaternion()); // Directionality
		matrices.scale(1.0F, -1.0F, -1.0F); // To real size
		matrices.translate(0.0D, -0.75D, 0.0D);
		VertexConsumer vertexConsumer = spriteIdentifier.getVertexConsumer(vertexConsumerProvider, RenderLayer::getEntityCutoutNoCull);
		MODEL.getBottomShell().render(matrices, vertexConsumer, i, defaultUV);
		matrices.translate(0.0D, (-blockEntity.getAnimationProgress(tickDelta) * 0.25F), 0.0D);
		matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(270.0F * blockEntity.getAnimationProgress(tickDelta)));

		MODEL.getTopShell().render(matrices, vertexConsumer, i, defaultUV);
		matrices.pop();
	}
}
