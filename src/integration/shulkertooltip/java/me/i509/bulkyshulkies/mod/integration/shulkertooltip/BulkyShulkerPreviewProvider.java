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

package me.i509.bulkyshulkies.mod.integration.shulkertooltip;

import com.misterpemodder.shulkerboxtooltip.api.PreviewContext;
import com.misterpemodder.shulkerboxtooltip.api.provider.BlockEntityPreviewProvider;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;

import me.i509.bulkyshulkies.api.block.old.colored.ColoredShulkerBoxBlock;

class BulkyShulkerPreviewProvider extends BlockEntityPreviewProvider {
	private static float[] SHULKER_BOX_COLOR = new float[] {0.592f, 0.403f, 0.592f};

	BulkyShulkerPreviewProvider(int maxInvSize) {
		super(maxInvSize, true);
	}

	@Override
	public float[] getWindowColor(PreviewContext context) {
		Item item = context.getStack().getItem();

		if (item instanceof BlockItem && ((BlockItem) item).getBlock() instanceof ColoredShulkerBoxBlock) {
			DyeColor dye = ((ColoredShulkerBoxBlock) Block.getBlockFromItem(item)).getColor();

			if (dye != null) {
				float[] components = dye.getColorComponents();
				return new float[] {
						Math.max(0.15f, components[0]),
						Math.max(0.15f, components[1]),
						Math.max(0.15f, components[2])
				};
			}
		}

		return SHULKER_BOX_COLOR;
	}

	@Override
	public boolean showTooltipHints(PreviewContext context) {
		return true;
	}
}
