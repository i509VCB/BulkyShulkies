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

package me.i509.bulkyshulkies.mod.client.model;

import java.util.Collections;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;

public abstract class AbstractShulkerLidHelmetModel<T extends LivingEntity, M extends EntityModel<T>> extends AnimalModel<T> {
	private final FeatureRendererContext<T, M> context;
	public final ShulkerModelPart lid;
	private float leaningPitch;

	public AbstractShulkerLidHelmetModel(FeatureRendererContext<T, M> context, ShulkerModelPart lid) {
		super(false, 5.0F, 2.0F);
		this.context = context;
		this.lid = lid;
	}

	@Override
	public void animateModel(T livingEntity, float limbAngle, float limbDistance, float tickDelta) {
		this.leaningPitch = livingEntity.getLeaningPitch(tickDelta);
		super.animateModel(livingEntity, limbAngle, limbDistance, tickDelta);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		super.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float customAngle, float headYaw, float headPitch) {
		// Ah yes, magic values
		if (entity instanceof ArmorStandEntity) {
			ArmorStandEntity armorStandEntity = (ArmorStandEntity) entity;
			this.lid.pitch = 0.017453292F * armorStandEntity.getHeadRotation().getPitch();
			this.lid.yaw = 0.017453292F * armorStandEntity.getHeadRotation().getYaw();
			this.lid.roll = 0.017453292F * armorStandEntity.getHeadRotation().getRoll();
			this.lid.setPivot(0.0F, -1.0F, 0.0F);
		} else {
			this.lid.yaw = 0.017453292F * headYaw;
			final boolean rolling = entity.getRoll() > 4;
			final boolean inSwimmingPose = entity.isInSwimmingPose();
			this.lid.yaw = headYaw * 0.017453292F;

			if (rolling) {
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

		// Lower the helmet if an entity is sneaking
		this.lid.pivotY = entity.isSneaking() ? 4.2F : 0.0F;
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

	@Override
	protected Iterable<ModelPart> getHeadParts() {
		return Collections.singleton(this.lid);
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return Collections.emptySet();
	}
}
