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

package me.i509.bulkyshulkies.mod.registry;

import me.i509.bulkyshulkies.mod.Uninstantiable;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public final class ShulkerTexts {
	public static final Text CURSED_SLAB_CONTAINER = new TranslatableText("container.slabShulkerBox");
	public static final Text CURSED_STAIR_CONTAINER = new TranslatableText("container.stairShulkerBox");

	public static final Text ENDER_SLAB = new TranslatableText("container.enderSlab");

	public static final Text COPPER_CONTAINER = new TranslatableText("container.copperShulkerBox");
	public static final Text DIAMOND_CONTAINER = new TranslatableText("container.diamondShulkerBox");
	public static final Text GOLD_CONTAINER = new TranslatableText("container.goldShulkerBox");
	public static final Text IRON_CONTAINER = new TranslatableText("container.ironShulkerBox");
	public static final Text OBSIDIAN_CONTAINER = new TranslatableText("container.obsidianShulkerBox");
	public static final Text PLATINUM_CONTAINER = new TranslatableText("container.platinumShulkerBox");
	public static final Text SILVER_CONTAINER = new TranslatableText("container.silverShulkerBox");
	public static final Text NETHERITE_CONTAINER = new TranslatableText("container.netheriteShulkerBox");

	public static final Text MISSING_CONTAINER = new TranslatableText("container.missingTexBox");
	public static final Text CLEAR_CONTAINER = new TranslatableText("container.clearShulkerBox");

	private ShulkerTexts() {
		Uninstantiable.whyDoIHearBossMusic(ShulkerTexts.class);
	}
}
