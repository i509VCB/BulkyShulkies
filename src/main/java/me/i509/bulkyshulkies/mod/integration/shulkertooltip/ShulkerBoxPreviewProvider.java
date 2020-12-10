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

package me.i509.bulkyshulkies.mod.integration.shulkertooltip;

import com.misterpemodder.shulkerboxtooltip.api.PreviewContext;
import com.misterpemodder.shulkerboxtooltip.api.provider.BlockEntityPreviewProvider;
import me.i509.bulkyshulkies.api.block.ShulkerBoxColor;
import me.i509.bulkyshulkies.mod.registry.ShulkerComponents;

import net.minecraft.util.DyeColor;

final class ShulkerBoxPreviewProvider extends BlockEntityPreviewProvider {
	private static final float[] SHULKER_BOX_COLOR = new float[]{ 0.592F, 0.403F, 0.592F };

	ShulkerBoxPreviewProvider() {
		super(0, true);
	}

	@Override
	public float[] getWindowColor(PreviewContext context) {
		return ShulkerComponents.SHULKER_BOX_COLOR.maybeGet(context.getStack()).map(component -> {
			DyeColor color;

			if (component.getColor() == ShulkerBoxColor.NONE) {
				color = DyeColor.PURPLE;
			} else {
				color = component.getColor().toDyeColor();
			}

			//noinspection ConstantConditions
			final float[] components = color.getColorComponents();
			return new float[]{
					Math.max(0.15f, components[0]),
					Math.max(0.15f, components[1]),
					Math.max(0.15f, components[2])
			};
		}).orElse(SHULKER_BOX_COLOR);
	}

	@Override
	public int getInventoryMaxSize(PreviewContext context) {
		// TODO: Impl
		return super.getInventoryMaxSize(context);
	}

	@Override
	public int getMaxRowSize(PreviewContext context) {
		// TODO: Impl
		return super.getMaxRowSize(context);
	}

	@Override
	public boolean showTooltipHints(PreviewContext context) {
		return true;
	}
}
