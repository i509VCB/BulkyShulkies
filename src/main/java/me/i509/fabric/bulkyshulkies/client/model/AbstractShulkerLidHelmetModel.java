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

package me.i509.fabric.bulkyshulkies.client.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;

import me.i509.fabric.bulkyshulkies.api.item.ShulkerHelmetStage;

public abstract class AbstractShulkerLidHelmetModel extends EntityModel<LivingEntity> {
	public final ModelPart lid;
	private float leaningPitch;

	public AbstractShulkerLidHelmetModel(ModelPart lid) {
		super(RenderLayer::getEntityCutoutNoCull);
		this.lid = lid;
	}

	@Override
	public void animateModel(LivingEntity livingEntity, float f, float g, float h) {
		this.leaningPitch = livingEntity.getLeaningPitch(h);
		super.animateModel(livingEntity, f, g, h);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		this.lid.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(LivingEntity entity, float limbAngle, float limbDistance, float customAngle, float headYaw, float headPitch) {
		// Ah yes, radical mathematics
		if (entity instanceof ArmorStandEntity) {
			ArmorStandEntity armorStandEntity = (ArmorStandEntity) entity;
			this.lid.pitch = 0.017453292F * armorStandEntity.getHeadRotation().getPitch();
			this.lid.yaw = 0.017453292F * armorStandEntity.getHeadRotation().getYaw();
			this.lid.roll = 0.017453292F * armorStandEntity.getHeadRotation().getRoll();
		} else {
			this.lid.yaw = 0.017453292F * headYaw;
			boolean roll = entity.getRoll() > 4;
			boolean inSwimmingPose = entity.isInSwimmingPose();
			this.lid.yaw = headYaw * 0.017453292F;

			if (roll) {
				this.lid.pitch = -0.7853982F;
			} else if (this.leaningPitch > 0.0F) {
				if (inSwimmingPose) {
					this.lid.pitch = this.lerpAngle(this.lid.pitch, -0.7853982F, this.leaningPitch);
				} else {
					this.lid.pitch = this.lerpAngle(this.lid.pitch, headPitch * 0.017453292F, this.leaningPitch);
				}
			} else {
				this.lid.pitch = headPitch * 0.017453292F;
			}
		}

		// Now actual lid movement magic
		ShulkerHelmetStage stage = ShulkerHelmetStage.getStageFromEntity(entity);
		this.lid.pivotY -= stage.getAnimationProgress() * 1.75F;

		// Now rotate it upon the way up TODO
	}

	protected float lerpAngle(float from, float to, float position) {
		float f = (to - from) % 6.2831855F;

		if (f < -3.1415927F) {
			f += 6.2831855F;
		}

		if (f >= 3.1415927F) {
			f -= 6.2831855F;
		}

		return from + position * f;
	}
}
