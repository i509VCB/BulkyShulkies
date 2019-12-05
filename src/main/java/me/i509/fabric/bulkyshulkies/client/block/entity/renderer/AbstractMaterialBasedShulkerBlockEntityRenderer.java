/*
 * MIT License
 *
 * Copyright (c) 2019 i509VCB
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

import me.i509.fabric.bulkyshulkies.BulkyShulkiesMod;
import me.i509.fabric.bulkyshulkies.client.ShulkerRenderLayers;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.model.ShulkerEntityModel;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.bulkyshulkies.api.block.base.AbstractShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.api.block.base.BaseShulkerBlock;
import me.i509.fabric.bulkyshulkies.api.block.material.AbstractMaterialBasedShulkerBoxBlockEntity;

@Environment(EnvType.CLIENT)
public abstract class AbstractMaterialBasedShulkerBlockEntityRenderer<BE extends AbstractMaterialBasedShulkerBoxBlockEntity> extends BlockEntityRenderer<BE> {
	protected static final ShulkerEntityModel<ShulkerEntity> MODEL = new ShulkerEntityModel<>();
	protected final String type;

	public AbstractMaterialBasedShulkerBlockEntityRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, String type) {
		super(blockEntityRenderDispatcher);
		this.type = type;
	}

	public SpriteIdentifier getSprite() {
		return new SpriteIdentifier(ShulkerRenderLayers.SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("be/shulker/" + type +"/shulker"));
	}

	public SpriteIdentifier getSprite(DyeColor color) {
		return new SpriteIdentifier(ShulkerRenderLayers.SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("be/shulker/" + type +"/shulker_" + color.getName()));
	}

	@Override
	public void render(AbstractMaterialBasedShulkerBoxBlockEntity blockEntity, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int defaultUV) {
		Direction direction = Direction.UP;

		if (blockEntity.hasWorld()) {
			BlockState blockState = blockEntity.getCachedState();

			if (blockState.getBlock() instanceof AbstractShulkerBoxBlock) {
				direction = blockState.get(BaseShulkerBlock.FACING);
			}
		}

		DyeColor dyeColor = blockEntity.getColor();
		SpriteIdentifier spriteIdentifier;

		if (dyeColor == null) {
			spriteIdentifier = getSprite();
		} else {
			spriteIdentifier = getSprite(dyeColor);
		}

		matrixStack.push();
		matrixStack.translate(0.5D, 0.5D, 0.5D);
		float g = 0.9995F;
		matrixStack.scale(0.9995F, 0.9995F, 0.9995F);
		matrixStack.multiply(direction.getRotationQuaternion());
		matrixStack.scale(1.0F, -1.0F, -1.0F);
		matrixStack.translate(0.0D, -1.0D, 0.0D);
		VertexConsumer vertexConsumer = spriteIdentifier.getVertexConsumer(vertexConsumerProvider, RenderLayer::getEntityCutoutNoCull);
		MODEL.getBottomShell().render(matrixStack, vertexConsumer, i, defaultUV);
		matrixStack.translate(0.0D, (-blockEntity.getAnimationProgress(tickDelta) * 0.5F), 0.0D);
		matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(270.0F * blockEntity.getAnimationProgress(tickDelta)));

		MODEL.getTopShell().render(matrixStack, vertexConsumer, i, defaultUV);
		matrixStack.pop();
	}
}
