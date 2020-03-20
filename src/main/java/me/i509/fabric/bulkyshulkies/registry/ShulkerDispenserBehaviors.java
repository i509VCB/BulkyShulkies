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

package me.i509.fabric.bulkyshulkies.registry;

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.BlockPlacementDispenserBehavior;

import me.i509.fabric.bulkyshulkies.api.block.slab.SlabBlockPlacementDispenserBehavior;

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

	public static void init() {
		// NO-OP
	}

	private static void copper() {
		DispenserBlock.registerBehavior(ShulkerBlocks.COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.WHITE_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.ORANGE_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.MAGENTA_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_BLUE_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.YELLOW_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIME_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PINK_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GRAY_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_GRAY_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.CYAN_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PURPLE_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLUE_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BROWN_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GREEN_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.RED_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLACK_COPPER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
	}

	private static void iron() {
		DispenserBlock.registerBehavior(ShulkerBlocks.IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.WHITE_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.ORANGE_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.MAGENTA_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_BLUE_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.YELLOW_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIME_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PINK_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GRAY_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_GRAY_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.CYAN_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PURPLE_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLUE_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BROWN_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GREEN_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.RED_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLACK_IRON_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
	}

	private static void silver() {
		DispenserBlock.registerBehavior(ShulkerBlocks.SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.WHITE_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.ORANGE_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.MAGENTA_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_BLUE_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.YELLOW_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIME_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PINK_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GRAY_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_GRAY_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.CYAN_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PURPLE_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLUE_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BROWN_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GREEN_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.RED_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLACK_SILVER_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
	}

	private static void gold() {
		DispenserBlock.registerBehavior(ShulkerBlocks.GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.WHITE_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.ORANGE_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.MAGENTA_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_BLUE_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.YELLOW_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIME_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PINK_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GRAY_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_GRAY_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.CYAN_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PURPLE_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLUE_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BROWN_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GREEN_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.RED_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLACK_GOLD_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
	}

	private static void diamond() {
		DispenserBlock.registerBehavior(ShulkerBlocks.DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.WHITE_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.ORANGE_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.MAGENTA_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_BLUE_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.YELLOW_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIME_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PINK_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GRAY_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_GRAY_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.CYAN_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PURPLE_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLUE_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BROWN_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GREEN_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.RED_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLACK_DIAMOND_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
	}

	private static void obsidian() {
		DispenserBlock.registerBehavior(ShulkerBlocks.OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.WHITE_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.ORANGE_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.MAGENTA_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_BLUE_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.YELLOW_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIME_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PINK_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GRAY_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_GRAY_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.CYAN_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PURPLE_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLUE_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BROWN_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GREEN_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.RED_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLACK_OBSIDIAN_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
	}

	private static void platinum() {
		DispenserBlock.registerBehavior(ShulkerBlocks.PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.WHITE_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.ORANGE_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.MAGENTA_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_BLUE_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.YELLOW_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIME_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PINK_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GRAY_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_GRAY_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.CYAN_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PURPLE_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLUE_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BROWN_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GREEN_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.RED_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLACK_PLATINUM_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
	}

	private static void clear() {
		// TODO Add Clear Boxes
	}

	private static void missing() {
		DispenserBlock.registerBehavior(ShulkerBlocks.MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.WHITE_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.ORANGE_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.MAGENTA_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_BLUE_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.YELLOW_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIME_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PINK_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GRAY_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_GRAY_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.CYAN_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PURPLE_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLUE_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BROWN_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GREEN_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.RED_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLACK_MISSING_TEX_SHULKER_BOX.asItem(), new BlockPlacementDispenserBehavior());
	}

	private static void slab() {
		DispenserBlock.registerBehavior(ShulkerBlocks.SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.WHITE_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.ORANGE_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.MAGENTA_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_BLUE_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.YELLOW_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIME_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PINK_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GRAY_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.LIGHT_GRAY_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.CYAN_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.PURPLE_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLUE_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BROWN_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.GREEN_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.RED_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
		DispenserBlock.registerBehavior(ShulkerBlocks.BLACK_SLAB_SHULKER_BOX.asItem(), new SlabBlockPlacementDispenserBehavior());
	}
}
