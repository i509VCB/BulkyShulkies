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

package me.i509.fabric.bulkyshulkies.client.model;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.math.MathHelper;

public class SlabShulkerModel<T extends ShulkerEntity> extends CompositeEntityModel<T> {
	public SlabShulkerModel() {
		this.topShell.addCuboid(-8, -12, -8, 16, 6, 16);
		this.topShell.setPivot(0, 24, 0);
		this.bottomShell.addCuboid(-8.0F, -8.0F, -8.0F, 16, 4, 16);
		this.bottomShell.setPivot(0.0F, 24.0F, 0.0F);
	}

	private final ModelPart topShell = new ModelPart(64, 64, 0, 0);
	private final ModelPart bottomShell = new ModelPart(64, 64, 0, 28);

	@Override
	public Iterable<ModelPart> getParts() {
		return ImmutableList.of(this.topShell, this.bottomShell);
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float customAngle, float headYaw, float headPitch) {
		float k = customAngle - (float) entity.age;
		float l = (0.5F + entity.method_7116(k)) * 3.1415927F;
		float m = -1.0F + MathHelper.sin(l);
		float n = 0.0F;

		if (l > 3.1415927F) {
			n = MathHelper.sin(customAngle * 0.1F) * 0.7F;
		}

		this.topShell.setPivot(0.0F, 16.0F + MathHelper.sin(l) * 8.0F + n, 0.0F);

		if (entity.method_7116(k) > 0.3F) {
			this.topShell.yaw = m * m * m * m * 3.1415927F * 0.125F;
		} else {
			this.topShell.yaw = 0.0F;
		}
	}

	public ModelPart getBottomShell() {
		return this.bottomShell;
	}

	public ModelPart getTopShell() {
		return this.topShell;
	}
}
