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

package me.i509.fabric.bulkyshulkies.registry;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;

import net.fabricmc.fabric.api.block.FabricBlockSettings;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.block.cursed.slab.CursedSlabShulkerBox;
import me.i509.fabric.bulkyshulkies.block.material.copper.CopperShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.diamond.DiamondShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.ender.EnderSlabBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.iron.IronShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.gold.GoldShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.missing.MissingTexBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.obsidian.ObsidianShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.platinum.PlatinumShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.silver.SilverShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.injector.ShulkerInjectorBlock;

public class ShulkerBlocks {
	// Dynamic bounds are VERY IMPORTANT. Otherwise your box will make entities suffocate since the game's cached block is exactly 1 full cube, so collisions act odd for this reason without the dynamic bounds.
	public static final Block.Settings SHULKER_BOX_SETTINGS = FabricBlockSettings.of(Material.SHULKER_BOX).hardness(2.0F).dynamicBounds().build().nonOpaque();
	public static final Block.Settings OBSIDIAN_SHULKER_BOX_SETTINGS = FabricBlockSettings.of(Material.SHULKER_BOX).hardness(2.0F).resistance(1200.0F).dynamicBounds().build().nonOpaque();

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

	/*
	 * ========================
	 *    Silver Shulker Boxes
	 * ========================
	 */

	public static final Block SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, null), "silver_shulker_box"); // Uncolored
	public static final Block WHITE_SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_silver_shulker_box");
	public static final Block ORANGE_SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_silver_shulker_box");
	public static final Block MAGENTA_SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_silver_shulker_box");
	public static final Block LIGHT_BLUE_SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_silver_shulker_box");
	public static final Block YELLOW_SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_silver_shulker_box");
	public static final Block LIME_SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_silver_shulker_box");
	public static final Block PINK_SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_silver_shulker_box");
	public static final Block GRAY_SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_silver_shulker_box");
	public static final Block LIGHT_GRAY_SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_silver_shulker_box");
	public static final Block CYAN_SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_silver_shulker_box");
	public static final Block PURPLE_SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_silver_shulker_box");
	public static final Block BLUE_SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_silver_shulker_box");
	public static final Block BROWN_SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_silver_shulker_box");
	public static final Block GREEN_SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_silver_shulker_box");
	public static final Block RED_SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.RED), "red_silver_shulker_box");
	public static final Block BLACK_SILVER_SHULKER_BOX = register(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_silver_shulker_box");

	/*
	 * ========================
	 *    Gold Shulker Boxes
	 * ========================
	 */

	public static final Block GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, null), "gold_shulker_box"); // Uncolored
	public static final Block WHITE_GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_gold_shulker_box");
	public static final Block ORANGE_GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_gold_shulker_box");
	public static final Block MAGENTA_GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_gold_shulker_box");
	public static final Block LIGHT_BLUE_GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_gold_shulker_box");
	public static final Block YELLOW_GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_gold_shulker_box");
	public static final Block LIME_GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_gold_shulker_box");
	public static final Block PINK_GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_gold_shulker_box");
	public static final Block GRAY_GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_gold_shulker_box");
	public static final Block LIGHT_GRAY_GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_gold_shulker_box");
	public static final Block CYAN_GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_gold_shulker_box");
	public static final Block PURPLE_GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_gold_shulker_box");
	public static final Block BLUE_GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_gold_shulker_box");
	public static final Block BROWN_GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_gold_shulker_box");
	public static final Block GREEN_GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_gold_shulker_box");
	public static final Block RED_GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.RED), "red_gold_shulker_box");
	public static final Block BLACK_GOLD_SHULKER_BOX = register(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_gold_shulker_box");

	/*
	 * ========================
	 *   Diamond Shulker Boxes
	 * ========================
	 */

	public static final Block DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, null), "diamond_shulker_box"); // Uncolored
	public static final Block WHITE_DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_diamond_shulker_box");
	public static final Block ORANGE_DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_diamond_shulker_box");
	public static final Block MAGENTA_DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_diamond_shulker_box");
	public static final Block LIGHT_BLUE_DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_diamond_shulker_box");
	public static final Block YELLOW_DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_diamond_shulker_box");
	public static final Block LIME_DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_diamond_shulker_box");
	public static final Block PINK_DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_diamond_shulker_box");
	public static final Block GRAY_DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_diamond_shulker_box");
	public static final Block LIGHT_GRAY_DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_diamond_shulker_box");
	public static final Block CYAN_DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_diamond_shulker_box");
	public static final Block PURPLE_DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_diamond_shulker_box");
	public static final Block BLUE_DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_diamond_shulker_box");
	public static final Block BROWN_DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_diamond_shulker_box");
	public static final Block GREEN_DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_diamond_shulker_box");
	public static final Block RED_DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.RED), "red_diamond_shulker_box");
	public static final Block BLACK_DIAMOND_SHULKER_BOX = register(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_diamond_shulker_box");

	/*
	 * ========================
	 *  Obsidian Shulker Boxes
	 * ========================
	 */

	public static final Block OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, null), "obsidian_shulker_box"); // Uncolored
	public static final Block WHITE_OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_obsidian_shulker_box");
	public static final Block ORANGE_OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_obsidian_shulker_box");
	public static final Block MAGENTA_OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_obsidian_shulker_box");
	public static final Block LIGHT_BLUE_OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_obsidian_shulker_box");
	public static final Block YELLOW_OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_obsidian_shulker_box");
	public static final Block LIME_OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_obsidian_shulker_box");
	public static final Block PINK_OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_obsidian_shulker_box");
	public static final Block GRAY_OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_obsidian_shulker_box");
	public static final Block LIGHT_GRAY_OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_obsidian_shulker_box");
	public static final Block CYAN_OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_obsidian_shulker_box");
	public static final Block PURPLE_OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_obsidian_shulker_box");
	public static final Block BLUE_OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_obsidian_shulker_box");
	public static final Block BROWN_OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_obsidian_shulker_box");
	public static final Block GREEN_OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_obsidian_shulker_box");
	public static final Block RED_OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, DyeColor.RED), "red_obsidian_shulker_box");
	public static final Block BLACK_OBSIDIAN_SHULKER_BOX = register(new ObsidianShulkerBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_obsidian_shulker_box");

	/*
	 * ========================
	 *  Platinum Shulker Boxes
	 * ========================
	 */

	public static final Block PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, null), "platinum_shulker_box"); // Uncolored
	public static final Block WHITE_PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_platinum_shulker_box");
	public static final Block ORANGE_PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_platinum_shulker_box");
	public static final Block MAGENTA_PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_platinum_shulker_box");
	public static final Block LIGHT_BLUE_PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_platinum_shulker_box");
	public static final Block YELLOW_PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_platinum_shulker_box");
	public static final Block LIME_PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_platinum_shulker_box");
	public static final Block PINK_PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_platinum_shulker_box");
	public static final Block GRAY_PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_platinum_shulker_box");
	public static final Block LIGHT_GRAY_PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_platinum_shulker_box");
	public static final Block CYAN_PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_platinum_shulker_box");
	public static final Block PURPLE_PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_platinum_shulker_box");
	public static final Block BLUE_PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_platinum_shulker_box");
	public static final Block BROWN_PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_platinum_shulker_box");
	public static final Block GREEN_PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_platinum_shulker_box");
	public static final Block RED_PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.RED), "red_platinum_shulker_box");
	public static final Block BLACK_PLATINUM_SHULKER_BOX = register(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_platinum_shulker_box");

	/*
	 * ========================
	 *  Missing Texture Shulker Boxes
	 * ========================
	 */

	public static final Block MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, null), "missing_tex_shulker_box"); // Uncolored
	public static final Block WHITE_MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_missing_tex_shulker_box");
	public static final Block ORANGE_MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_missing_tex_shulker_box");
	public static final Block MAGENTA_MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_missing_tex_shulker_box");
	public static final Block LIGHT_BLUE_MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_missing_tex_shulker_box");
	public static final Block YELLOW_MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_missing_tex_shulker_box");
	public static final Block LIME_MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_missing_tex_shulker_box");
	public static final Block PINK_MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_missing_tex_shulker_box");
	public static final Block GRAY_MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_missing_tex_shulker_box");
	public static final Block LIGHT_GRAY_MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_missing_tex_shulker_box");
	public static final Block CYAN_MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_missing_tex_shulker_box");
	public static final Block PURPLE_MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_missing_tex_shulker_box");
	public static final Block BLUE_MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_missing_tex_shulker_box");
	public static final Block BROWN_MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_missing_tex_shulker_box");
	public static final Block GREEN_MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_missing_tex_shulker_box");
	public static final Block RED_MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.RED), "red_missing_tex_shulker_box");
	public static final Block BLACK_MISSING_TEX_SHULKER_BOX = register(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_missing_tex_shulker_box");

	/*
	 * ========================
	 *   Cursed Shulker Boxes
	 *       Slab Edition
	 * ========================
	 */

	public static final Block SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, null), "slab_shulker_box");
	public static final Block WHITE_SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_slab_shulker_box");
	public static final Block ORANGE_SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_slab_shulker_box");
	public static final Block MAGENTA_SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_slab_shulker_box");
	public static final Block LIGHT_BLUE_SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_slab_shulker_box");
	public static final Block YELLOW_SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_slab_shulker_box");
	public static final Block LIME_SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_slab_shulker_box");
	public static final Block PINK_SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_slab_shulker_box");
	public static final Block GRAY_SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_slab_shulker_box");
	public static final Block LIGHT_GRAY_SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_slab_shulker_box");
	public static final Block CYAN_SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_slab_shulker_box");
	public static final Block PURPLE_SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_slab_shulker_box");
	public static final Block BLUE_SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_slab_shulker_box");
	public static final Block BROWN_SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_slab_shulker_box");
	public static final Block GREEN_SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_slab_shulker_box");
	public static final Block RED_SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, DyeColor.RED), "red_slab_shulker_box");
	public static final Block BLACK_SLAB_SHULKER_BOX = register(new CursedSlabShulkerBox(SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_slab_shulker_box");

	public static final Block ENDER_SLAB_BOX = register(new EnderSlabBoxBlock(OBSIDIAN_SHULKER_BOX_SETTINGS), "ender_slab");

	/*
	 * ========================
	 *  Shulker Injector blocks
	 * ========================
	 */
	public static final Block SHULKER_INJECTOR = register(new ShulkerInjectorBlock(FabricBlockSettings.of(Material.METAL).build()), "shulker_injector", ShulkerItemGroups.MATERIAL_SETTINGS);

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
		Block b = Registry.register(Registry.BLOCK, BulkyShulkies.id(path), block);
		Registry.register(Registry.ITEM, BulkyShulkies.id(path), new BlockItem(block, settings));
		return b;
	}
}
