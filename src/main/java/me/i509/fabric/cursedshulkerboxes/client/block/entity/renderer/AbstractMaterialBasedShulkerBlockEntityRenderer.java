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

package me.i509.fabric.cursedshulkerboxes.client.block.entity.renderer;

import net.minecraft.block.BlockState;
import net.minecraft.class_4722;
import net.minecraft.class_4730;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.model.ShulkerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.cursedshulkerboxes.api.block.base.AbstractShulkerBoxBlock;
import me.i509.fabric.cursedshulkerboxes.api.block.base.BaseShulkerBlock;
import me.i509.fabric.cursedshulkerboxes.api.block.material.AbstractMaterialBasedShulkerBoxBlockEntity;

// TODO Make this work with our actual textures.
@Environment(EnvType.CLIENT)
public abstract class AbstractMaterialBasedShulkerBlockEntityRenderer<BE extends AbstractMaterialBasedShulkerBoxBlockEntity> extends BlockEntityRenderer<BE> {
	protected ShulkerEntityModel<ShulkerEntity> model;

	public AbstractMaterialBasedShulkerBlockEntityRenderer(ShulkerEntityModel<ShulkerEntity> shulkerEntityModel, BlockEntityRenderDispatcher blockEntityRenderDispatcher) {
		super(blockEntityRenderDispatcher);
		this.model = shulkerEntityModel;
	}

	public abstract Identifier getShulkerTextureAtlas();

	public abstract class_4730 getWrappedSprite(DyeColor color);

	@Override
	public void render(AbstractMaterialBasedShulkerBoxBlockEntity blockEntity, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int defaultUV) {
		Direction direction = Direction.UP;

		if (blockEntity.hasWorld()) {
			BlockState blockState = blockEntity.getWorld().getBlockState(blockEntity.getPos());

			if (blockState.getBlock() instanceof AbstractShulkerBoxBlock) {
				direction = blockState.get(BaseShulkerBlock.FACING);
			}
		}

		DyeColor dyeColor = blockEntity.getColor();
		class_4730 lv2;

		if (dyeColor == null) {
			lv2 = class_4722.field_21710;
		} else {
			lv2 = class_4722.field_21711.get(dyeColor.getId());
		}

		matrixStack.push();
		matrixStack.translate(0.5D, 0.5D, 0.5D);
		float g = 0.9995F;
		matrixStack.scale(0.9995F, 0.9995F, 0.9995F);
		matrixStack.multiply(direction.getRotationQuaternion());
		matrixStack.scale(1.0F, -1.0F, -1.0F);
		matrixStack.translate(0.0D, -1.0D, 0.0D);
		VertexConsumer vertexConsumer = lv2.method_24145(vertexConsumerProvider, RenderLayer::getEntityCutoutNoCull);
		this.model.getBottomShell().render(matrixStack, vertexConsumer, i, defaultUV);
		matrixStack.translate(0.0D, (-blockEntity.getAnimationProgress(tickDelta) * 0.5F), 0.0D);
		matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(270.0F * blockEntity.getAnimationProgress(tickDelta)));

		this.model.getTopShell().render(matrixStack, vertexConsumer, i, defaultUV);
		matrixStack.pop();
	}
}
