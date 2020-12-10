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

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.BlockPlacementDispenserBehavior;

import me.i509.bulkyshulkies.api.block.old.slab.SlabBlockPlacementDispenserBehavior;

public final class ShulkerDispenserBehaviors {
	static {
		copper();
		iron();
		silver();
		gold();
		diamond();
		obsidian();
		platinum();
		clear();
		missing();
		slab();
	}

	static void init() {
	}

	private static void copper() {
		DispenserBlock.registerBehavior(ShulkerBlockSettings.COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.WHITE_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.ORANGE_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.MAGENTA_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_BLUE_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.YELLOW_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIME_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PINK_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GRAY_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_GRAY_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.CYAN_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PURPLE_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLUE_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BROWN_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GREEN_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.RED_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLACK_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
	}

	private static void iron() {
		DispenserBlock.registerBehavior(ShulkerBlockSettings.IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.WHITE_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.ORANGE_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.MAGENTA_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_BLUE_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.YELLOW_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIME_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PINK_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GRAY_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_GRAY_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.CYAN_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PURPLE_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLUE_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BROWN_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GREEN_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.RED_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLACK_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
	}

	private static void silver() {
		DispenserBlock.registerBehavior(ShulkerBlockSettings.SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.WHITE_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.ORANGE_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.MAGENTA_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_BLUE_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.YELLOW_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIME_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PINK_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GRAY_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_GRAY_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.CYAN_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PURPLE_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLUE_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BROWN_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GREEN_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.RED_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLACK_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
	}

	private static void gold() {
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.WHITE_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.ORANGE_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.MAGENTA_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_BLUE_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.YELLOW_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIME_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PINK_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GRAY_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_GRAY_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.CYAN_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PURPLE_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLUE_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BROWN_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GREEN_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.RED_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLACK_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
	}

	private static void diamond() {
		DispenserBlock.registerBehavior(ShulkerBlockSettings.DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.WHITE_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.ORANGE_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.MAGENTA_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_BLUE_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.YELLOW_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIME_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PINK_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GRAY_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_GRAY_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.CYAN_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PURPLE_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLUE_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BROWN_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GREEN_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.RED_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLACK_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
	}

	private static void obsidian() {
		DispenserBlock.registerBehavior(ShulkerBlockSettings.OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.WHITE_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.ORANGE_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.MAGENTA_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_BLUE_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.YELLOW_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIME_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PINK_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GRAY_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_GRAY_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.CYAN_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PURPLE_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLUE_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BROWN_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GREEN_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.RED_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLACK_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
	}

	private static void platinum() {
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.WHITE_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.ORANGE_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.MAGENTA_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_BLUE_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.YELLOW_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIME_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PINK_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GRAY_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_GRAY_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.CYAN_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PURPLE_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLUE_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BROWN_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GREEN_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.RED_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLACK_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
	}

	private static void clear() {
		// TODO Add Clear Boxes
	}

	private static void missing() {
		DispenserBlock.registerBehavior(ShulkerBlockSettings.MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.WHITE_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.ORANGE_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.MAGENTA_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_BLUE_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.YELLOW_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIME_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PINK_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GRAY_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_GRAY_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.CYAN_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PURPLE_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLUE_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BROWN_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GREEN_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.RED_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLACK_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
	}

	private static void slab() {
		DispenserBlock.registerBehavior(ShulkerBlockSettings.SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.WHITE_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.ORANGE_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.MAGENTA_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_BLUE_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.YELLOW_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIME_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PINK_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GRAY_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.LIGHT_GRAY_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.CYAN_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.PURPLE_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLUE_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BROWN_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.GREEN_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.RED_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlockSettings.BLACK_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
	}
}
