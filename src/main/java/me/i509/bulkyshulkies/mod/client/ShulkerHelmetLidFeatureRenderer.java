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

package me.i509.bulkyshulkies.mod.client;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PiglinEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.Identifier;

import me.i509.bulkyshulkies.mod.BulkyShulkies;
import me.i509.bulkyshulkies.mod.client.model.AbstractShulkerLidHelmetModel;
import me.i509.bulkyshulkies.mod.client.model.HumanoidShulkerLidHelmetModel;
import me.i509.bulkyshulkies.mod.client.model.PiglinShulkerLidHelmetModel;
import me.i509.bulkyshulkies.mod.registry.ShulkerItems;

public class ShulkerHelmetLidFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
	private static final Identifier TEXTURE = BulkyShulkies.id("textures/armor/shulker_helmet_lid.png");
	private final AbstractShulkerLidHelmetModel<T, M> helmetLidModel;

	public ShulkerHelmetLidFeatureRenderer(FeatureRendererContext<T, M> context) {
		super(context);

		if (context instanceof PiglinEntityRenderer) {
			this.helmetLidModel = new PiglinShulkerLidHelmetModel<>(context);
		} else {
			this.helmetLidModel = new HumanoidShulkerLidHelmetModel<>(context);
		}
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		if (entity.getEquippedStack(EquipmentSlot.HEAD).getItem() == ShulkerItems.SHULKER_HELMET) {
			matrices.push();

			if (entity instanceof ArmorStandEntity) {
				matrices.translate(0.0D, 0.065D, 0.0D);
			}

			this.getContextModel().copyStateTo(this.helmetLidModel);
			this.helmetLidModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
			// TODO: Use Armor vertex consumer
			this.helmetLidModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(ShulkerHelmetLidFeatureRenderer.TEXTURE)), light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
			matrices.pop();
		}
	}
}
