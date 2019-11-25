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

package me.i509.fabric.cursedshulkerboxes.registry;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;

import net.fabricmc.fabric.api.block.FabricBlockSettings;

import me.i509.fabric.cursedshulkerboxes.CursedShulkerBoxMod;
import me.i509.fabric.cursedshulkerboxes.block.cursed.slab.CursedSlabShulkerBox;
import me.i509.fabric.cursedshulkerboxes.block.material.copper.CopperShulkerBoxBlock;
import me.i509.fabric.cursedshulkerboxes.block.material.copper.ExampleBlock;
import me.i509.fabric.cursedshulkerboxes.block.material.copper.ExampleTallBox;
import me.i509.fabric.cursedshulkerboxes.block.material.iron.IronShulkerBoxBlock;

public class ShulkerBlocks {
	public static final Block.Settings SHULKER_BOX_SETTINGS = FabricBlockSettings.of(Material.SHULKER_BOX).hardness(2.0F).dynamicBounds().build().nonOpaque();

	public static final Block TEST_TALL = register(new ExampleTallBox(SHULKER_BOX_SETTINGS, null), "test_tall_shulker_box");

	/*
	 * ========================
	 *   Copper Shulker Boxes
	 * ========================
	 */

	public static final Block COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, null), "copper_shulker_box"); // Uncolored
	public static final Block WHITE_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_copper_shulker_box");
	public static final Block ORANGE_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_copper_shulker_box");
	public static final Block MAGENTA_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_copper_shulker_box");
	public static final Block LIGHT_BLUE_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_copper_shulker_box");
	public static final Block YELLOW_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_copper_shulker_box");
	public static final Block LIME_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_copper_shulker_box");
	public static final Block PINK_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_copper_shulker_box");
	public static final Block GRAY_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_copper_shulker_box");
	public static final Block LIGHT_GRAY_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_copper_shulker_box");
	public static final Block CYAN_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_copper_shulker_box");
	public static final Block PURPLE_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_copper_shulker_box");
	public static final Block BLUE_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_copper_shulker_box");
	public static final Block BROWN_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_copper_shulker_box");
	public static final Block GREEN_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_copper_shulker_box");
	public static final Block RED_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.RED), "red_copper_shulker_box");
	public static final Block BLACK_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_copper_shulker_box");

	/*
	 * ========================
	 *    Iron Shulker Boxes
	 * ========================
	 */

	public static final Block IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, null), "iron_shulker_box"); // Uncolored
	public static final Block WHITE_IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_iron_shulker_box");
	public static final Block ORANGE_IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_iron_shulker_box");
	public static final Block MAGENTA_IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_iron_shulker_box");
	public static final Block LIGHT_BLUE_IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_iron_shulker_box");
	public static final Block YELLOW_IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_iron_shulker_box");
	public static final Block LIME_IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_iron_shulker_box");
	public static final Block PINK_IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_iron_shulker_box");
	public static final Block GRAY_IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_iron_shulker_box");
	public static final Block LIGHT_GRAY_IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_iron_shulker_box");
	public static final Block CYAN_IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_iron_shulker_box");
	public static final Block PURPLE_IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_iron_shulker_box");
	public static final Block BLUE_IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_iron_shulker_box");
	public static final Block BROWN_IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_iron_shulker_box");
	public static final Block GREEN_IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_iron_shulker_box");
	public static final Block RED_IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.RED), "red_iron_shulker_box");
	public static final Block BLACK_IRON_SHULKER_BOX = register(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_iron_shulker_box");

	public static final Block SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, null), "slab_shulker_box");

	public static final Block TR = register(new ExampleBlock(FabricBlockSettings.of(Material.SHULKER_BOX).build()), "tr");

	private ShulkerBlocks() {
		// NO-OP
	}

	public static void init() {
		// NO-OP
	}

	public static Block register(Block block, String path) {
		return register(block, path, ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS);
	}

	public static Block register(Block block, String path, Item.Settings settings) {
		Block b = Registry.register(Registry.BLOCK, CursedShulkerBoxMod.id(path), block);
		Registry.register(Registry.ITEM, CursedShulkerBoxMod.id(path), new BlockItem(block, settings));
		return b;
	}
}
