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

import java.util.function.BiConsumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.api.block.AbstractShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.cursed.slab.ColoredSlabShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.cursed.stair.StairShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.copper.CopperShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.diamond.DiamondShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.ender.EnderSlabBlock;
import me.i509.fabric.bulkyshulkies.block.material.iron.IronShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.gold.GoldShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.netherite.NetheriteShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.obsidian.ObsidianShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.platinum.PlatinumShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.material.silver.SilverShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.missing.MissingTexBoxBlock;
import me.i509.fabric.bulkyshulkies.item.InventoryShulkerBlockItem;
import me.i509.fabric.bulkyshulkies.api.block.base.BasicShulkerBlock;

public final class ShulkerBlocks {
	private static final AbstractBlock.ContextPredicate FALSE_CTX = (state, world, pos) -> false;
	// Dynamic bounds are VERY IMPORTANT. Otherwise your box will make entities suffocate since the game's cached block is exactly 1 full cube, so collisions act odd for this reason without the dynamic bounds.
	public static final AbstractBlock.Settings SHULKER_BOX_SETTINGS = FabricBlockSettings.of(Material.SHULKER_BOX).breakByTool(FabricToolTags.PICKAXES).hardness(2.0F).dynamicBounds().nonOpaque();
	public static final AbstractBlock.Settings SHULKER_SLAB_BOX_SETTINGS = FabricBlockSettings.of(Material.SHULKER_BOX).breakByTool(FabricToolTags.PICKAXES).hardness(2.0F).dynamicBounds().nonOpaque().suffocates(ShulkerBlocks.FALSE_CTX);
	public static final AbstractBlock.Settings EXPLOSION_PROOF_SHULKER_BOX_SETTINGS = FabricBlockSettings.of(Material.SHULKER_BOX).breakByTool(FabricToolTags.PICKAXES).hardness(2.0F).resistance(1200.0F).dynamicBounds().nonOpaque();
	public static final AbstractBlock.Settings ENDER_SLAB_SETTINGS = FabricBlockSettings.of(Material.SHULKER_BOX).breakByTool(FabricToolTags.PICKAXES).hardness(2.0F).resistance(1200.0F).dynamicBounds().nonOpaque().lightLevel(7).suffocates(ShulkerBlocks.FALSE_CTX);

	/*
	 * ========================
	 *   Copper Shulker Boxes
	 * ========================
	 */

	public static final Block COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, null), "copper_shulker_box"); // Uncolored
	public static final Block WHITE_COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_copper_shulker_box");
	public static final Block ORANGE_COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_copper_shulker_box");
	public static final Block MAGENTA_COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_copper_shulker_box");
	public static final Block LIGHT_BLUE_COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_copper_shulker_box");
	public static final Block YELLOW_COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_copper_shulker_box");
	public static final Block LIME_COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_copper_shulker_box");
	public static final Block PINK_COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_copper_shulker_box");
	public static final Block GRAY_COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_copper_shulker_box");
	public static final Block LIGHT_GRAY_COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_copper_shulker_box");
	public static final Block CYAN_COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_copper_shulker_box");
	public static final Block PURPLE_COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_copper_shulker_box");
	public static final Block BLUE_COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_copper_shulker_box");
	public static final Block BROWN_COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_copper_shulker_box");
	public static final Block GREEN_COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_copper_shulker_box");
	public static final Block RED_COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.RED), "red_copper_shulker_box");
	public static final Block BLACK_COPPER_SHULKER_BOX = registerInventoryShulker(new CopperShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_copper_shulker_box");

	/*
	 * ========================
	 *    Iron Shulker Boxes
	 * ========================
	 */

	public static final Block IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, null), "iron_shulker_box"); // Uncolored
	public static final Block WHITE_IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_iron_shulker_box");
	public static final Block ORANGE_IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_iron_shulker_box");
	public static final Block MAGENTA_IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_iron_shulker_box");
	public static final Block LIGHT_BLUE_IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_iron_shulker_box");
	public static final Block YELLOW_IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_iron_shulker_box");
	public static final Block LIME_IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_iron_shulker_box");
	public static final Block PINK_IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_iron_shulker_box");
	public static final Block GRAY_IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_iron_shulker_box");
	public static final Block LIGHT_GRAY_IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_iron_shulker_box");
	public static final Block CYAN_IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_iron_shulker_box");
	public static final Block PURPLE_IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_iron_shulker_box");
	public static final Block BLUE_IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_iron_shulker_box");
	public static final Block BROWN_IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_iron_shulker_box");
	public static final Block GREEN_IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_iron_shulker_box");
	public static final Block RED_IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.RED), "red_iron_shulker_box");
	public static final Block BLACK_IRON_SHULKER_BOX = registerInventoryShulker(new IronShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_iron_shulker_box");

	/*
	 * ========================
	 *    Silver Shulker Boxes
	 * ========================
	 */

	public static final Block SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, null), "silver_shulker_box"); // Uncolored
	public static final Block WHITE_SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_silver_shulker_box");
	public static final Block ORANGE_SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_silver_shulker_box");
	public static final Block MAGENTA_SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_silver_shulker_box");
	public static final Block LIGHT_BLUE_SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_silver_shulker_box");
	public static final Block YELLOW_SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_silver_shulker_box");
	public static final Block LIME_SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_silver_shulker_box");
	public static final Block PINK_SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_silver_shulker_box");
	public static final Block GRAY_SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_silver_shulker_box");
	public static final Block LIGHT_GRAY_SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_silver_shulker_box");
	public static final Block CYAN_SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_silver_shulker_box");
	public static final Block PURPLE_SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_silver_shulker_box");
	public static final Block BLUE_SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_silver_shulker_box");
	public static final Block BROWN_SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_silver_shulker_box");
	public static final Block GREEN_SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_silver_shulker_box");
	public static final Block RED_SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.RED), "red_silver_shulker_box");
	public static final Block BLACK_SILVER_SHULKER_BOX = registerInventoryShulker(new SilverShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_silver_shulker_box");

	/*
	 * ========================
	 *    Gold Shulker Boxes
	 * ========================
	 */

	public static final Block GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, null), "gold_shulker_box"); // Uncolored
	public static final Block WHITE_GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_gold_shulker_box");
	public static final Block ORANGE_GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_gold_shulker_box");
	public static final Block MAGENTA_GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_gold_shulker_box");
	public static final Block LIGHT_BLUE_GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_gold_shulker_box");
	public static final Block YELLOW_GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_gold_shulker_box");
	public static final Block LIME_GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_gold_shulker_box");
	public static final Block PINK_GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_gold_shulker_box");
	public static final Block GRAY_GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_gold_shulker_box");
	public static final Block LIGHT_GRAY_GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_gold_shulker_box");
	public static final Block CYAN_GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_gold_shulker_box");
	public static final Block PURPLE_GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_gold_shulker_box");
	public static final Block BLUE_GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_gold_shulker_box");
	public static final Block BROWN_GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_gold_shulker_box");
	public static final Block GREEN_GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_gold_shulker_box");
	public static final Block RED_GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.RED), "red_gold_shulker_box");
	public static final Block BLACK_GOLD_SHULKER_BOX = registerInventoryShulker(new GoldShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_gold_shulker_box");

	/*
	 * ========================
	 *   Diamond Shulker Boxes
	 * ========================
	 */

	public static final Block DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, null), "diamond_shulker_box"); // Uncolored
	public static final Block WHITE_DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_diamond_shulker_box");
	public static final Block ORANGE_DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_diamond_shulker_box");
	public static final Block MAGENTA_DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_diamond_shulker_box");
	public static final Block LIGHT_BLUE_DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_diamond_shulker_box");
	public static final Block YELLOW_DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_diamond_shulker_box");
	public static final Block LIME_DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_diamond_shulker_box");
	public static final Block PINK_DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_diamond_shulker_box");
	public static final Block GRAY_DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_diamond_shulker_box");
	public static final Block LIGHT_GRAY_DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_diamond_shulker_box");
	public static final Block CYAN_DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_diamond_shulker_box");
	public static final Block PURPLE_DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_diamond_shulker_box");
	public static final Block BLUE_DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_diamond_shulker_box");
	public static final Block BROWN_DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_diamond_shulker_box");
	public static final Block GREEN_DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_diamond_shulker_box");
	public static final Block RED_DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.RED), "red_diamond_shulker_box");
	public static final Block BLACK_DIAMOND_SHULKER_BOX = registerInventoryShulker(new DiamondShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_diamond_shulker_box");

	/*
	 * ========================
	 *  Obsidian Shulker Boxes
	 * ========================
	 */

	public static final Block OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, null), "obsidian_shulker_box"); // Uncolored
	public static final Block WHITE_OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_obsidian_shulker_box");
	public static final Block ORANGE_OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_obsidian_shulker_box");
	public static final Block MAGENTA_OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_obsidian_shulker_box");
	public static final Block LIGHT_BLUE_OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_obsidian_shulker_box");
	public static final Block YELLOW_OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_obsidian_shulker_box");
	public static final Block LIME_OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_obsidian_shulker_box");
	public static final Block PINK_OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_obsidian_shulker_box");
	public static final Block GRAY_OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_obsidian_shulker_box");
	public static final Block LIGHT_GRAY_OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_obsidian_shulker_box");
	public static final Block CYAN_OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_obsidian_shulker_box");
	public static final Block PURPLE_OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_obsidian_shulker_box");
	public static final Block BLUE_OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_obsidian_shulker_box");
	public static final Block BROWN_OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_obsidian_shulker_box");
	public static final Block GREEN_OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_obsidian_shulker_box");
	public static final Block RED_OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.RED), "red_obsidian_shulker_box");
	public static final Block BLACK_OBSIDIAN_SHULKER_BOX = registerInventoryShulker(new ObsidianShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_obsidian_shulker_box");

	/*
	 * ========================
	 *  Obsidian Shulker Boxes
	 * ========================
	 */

	public static final Block NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, null), "netherite_shulker_box"); // Uncolored
	public static final Block WHITE_NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_netherite_shulker_box");
	public static final Block ORANGE_NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_netherite_shulker_box");
	public static final Block MAGENTA_NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_netherite_shulker_box");
	public static final Block LIGHT_BLUE_NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_netherite_shulker_box");
	public static final Block YELLOW_NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_netherite_shulker_box");
	public static final Block LIME_NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_netherite_shulker_box");
	public static final Block PINK_NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_netherite_shulker_box");
	public static final Block GRAY_NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_netherite_shulker_box");
	public static final Block LIGHT_GRAY_NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_netherite_shulker_box");
	public static final Block CYAN_NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_netherite_shulker_box");
	public static final Block PURPLE_NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_netherite_shulker_box");
	public static final Block BLUE_NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_netherite_shulker_box");
	public static final Block BROWN_NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_netherite_shulker_box");
	public static final Block GREEN_NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_netherite_shulker_box");
	public static final Block RED_NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.RED), "red_netherite_shulker_box");
	public static final Block BLACK_NETHERITE_SHULKER_BOX = registerInvulInventoryShulker(new NetheriteShulkerBoxBlock(EXPLOSION_PROOF_SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_netherite_shulker_box");

	/*
	 * ========================
	 *  Platinum Shulker Boxes
	 * ========================
	 */

	public static final Block PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, null), "platinum_shulker_box"); // Uncolored
	public static final Block WHITE_PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_platinum_shulker_box");
	public static final Block ORANGE_PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_platinum_shulker_box");
	public static final Block MAGENTA_PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_platinum_shulker_box");
	public static final Block LIGHT_BLUE_PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_platinum_shulker_box");
	public static final Block YELLOW_PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_platinum_shulker_box");
	public static final Block LIME_PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_platinum_shulker_box");
	public static final Block PINK_PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_platinum_shulker_box");
	public static final Block GRAY_PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_platinum_shulker_box");
	public static final Block LIGHT_GRAY_PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_platinum_shulker_box");
	public static final Block CYAN_PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_platinum_shulker_box");
	public static final Block PURPLE_PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_platinum_shulker_box");
	public static final Block BLUE_PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_platinum_shulker_box");
	public static final Block BROWN_PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_platinum_shulker_box");
	public static final Block GREEN_PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_platinum_shulker_box");
	public static final Block RED_PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.RED), "red_platinum_shulker_box");
	public static final Block BLACK_PLATINUM_SHULKER_BOX = registerInventoryShulker(new PlatinumShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_platinum_shulker_box");

	/*
	 * ========================
	 *  Missing Texture Shulker Boxes
	 * ========================
	 */

	public static final Block MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, null), "missing_tex_shulker_box"); // Uncolored
	public static final Block WHITE_MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_missing_tex_shulker_box");
	public static final Block ORANGE_MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_missing_tex_shulker_box");
	public static final Block MAGENTA_MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_missing_tex_shulker_box");
	public static final Block LIGHT_BLUE_MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_missing_tex_shulker_box");
	public static final Block YELLOW_MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_missing_tex_shulker_box");
	public static final Block LIME_MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_missing_tex_shulker_box");
	public static final Block PINK_MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_missing_tex_shulker_box");
	public static final Block GRAY_MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_missing_tex_shulker_box");
	public static final Block LIGHT_GRAY_MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_missing_tex_shulker_box");
	public static final Block CYAN_MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_missing_tex_shulker_box");
	public static final Block PURPLE_MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_missing_tex_shulker_box");
	public static final Block BLUE_MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_missing_tex_shulker_box");
	public static final Block BROWN_MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_missing_tex_shulker_box");
	public static final Block GREEN_MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_missing_tex_shulker_box");
	public static final Block RED_MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.RED), "red_missing_tex_shulker_box");
	public static final Block BLACK_MISSING_TEX_SHULKER_BOX = registerInventoryShulker(new MissingTexBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_missing_tex_shulker_box");

	/*
	 * ========================
	 *   Cursed Shulker Boxes
	 *       Slab Edition
	 * ========================
	 */

	public static final Block SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, null), "slab_shulker_box");
	public static final Block WHITE_SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, DyeColor.WHITE), "white_slab_shulker_box");
	public static final Block ORANGE_SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, DyeColor.ORANGE), "orange_slab_shulker_box");
	public static final Block MAGENTA_SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_slab_shulker_box");
	public static final Block LIGHT_BLUE_SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_slab_shulker_box");
	public static final Block YELLOW_SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, DyeColor.YELLOW), "yellow_slab_shulker_box");
	public static final Block LIME_SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, DyeColor.LIME), "lime_slab_shulker_box");
	public static final Block PINK_SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, DyeColor.PINK), "pink_slab_shulker_box");
	public static final Block GRAY_SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, DyeColor.GRAY), "gray_slab_shulker_box");
	public static final Block LIGHT_GRAY_SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_slab_shulker_box");
	public static final Block CYAN_SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, DyeColor.CYAN), "cyan_slab_shulker_box");
	public static final Block PURPLE_SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, DyeColor.PURPLE), "purple_slab_shulker_box");
	public static final Block BLUE_SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, DyeColor.BLUE), "blue_slab_shulker_box");
	public static final Block BROWN_SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, DyeColor.BROWN), "brown_slab_shulker_box");
	public static final Block GREEN_SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, DyeColor.GREEN), "green_slab_shulker_box");
	public static final Block RED_SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, DyeColor.RED), "red_slab_shulker_box");
	public static final Block BLACK_SLAB_SHULKER_BOX = registerInventoryShulker(new ColoredSlabShulkerBoxBlock(SHULKER_SLAB_BOX_SETTINGS, DyeColor.BLACK), "black_slab_shulker_box");

	/*
	 * ========================
	 *   Cursed Shulker Boxes
	 *       Stair Edition
	 * ========================
	 */

	public static final Block STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, null), "stair_shulker_box");
	public static final Block WHITE_STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.WHITE), "white_stair_shulker_box");
	public static final Block ORANGE_STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.ORANGE), "orange_stair_shulker_box");
	public static final Block MAGENTA_STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.MAGENTA), "magenta_stair_shulker_box");
	public static final Block LIGHT_BLUE_STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_BLUE), "light_blue_stair_shulker_box");
	public static final Block YELLOW_STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.YELLOW), "yellow_stair_shulker_box");
	public static final Block LIME_STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIME), "lime_stair_shulker_box");
	public static final Block PINK_STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PINK), "pink_stair_shulker_box");
	public static final Block GRAY_STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GRAY), "gray_stair_shulker_box");
	public static final Block LIGHT_GRAY_STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.LIGHT_GRAY), "light_gray_stair_shulker_box");
	public static final Block CYAN_STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.CYAN), "cyan_stair_shulker_box");
	public static final Block PURPLE_STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.PURPLE), "purple_stair_shulker_box");
	public static final Block BLUE_STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLUE), "blue_stair_shulker_box");
	public static final Block BROWN_STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BROWN), "brown_stair_shulker_box");
	public static final Block GREEN_STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.GREEN), "green_stair_shulker_box");
	public static final Block RED_STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.RED), "red_stair_shulker_box");
	public static final Block BLACK_STAIR_SHULKER_BOX = registerInventoryShulker(new StairShulkerBoxBlock(SHULKER_BOX_SETTINGS, DyeColor.BLACK), "black_stair_shulker_box");

	public static final Block ENDER_SLAB_BOX = register(new EnderSlabBlock(ENDER_SLAB_SETTINGS), "ender_slab");

	/*
	 * ========================
	 *  Shulker Injector blocks
	 * ========================
	 */
	// For the future
	// public static final Block SHULKER_INJECTOR = registerShulker(new ShulkerInjectorBlock(FabricBlockSettings.of(Material.METAL).build()), "shulker_injector", ShulkerItemGroups.MATERIAL_SETTINGS);

	private ShulkerBlocks() {
		throw new AssertionError("You should not be instantiating this");
	}

	static void init() {
	}

	public static void iterateColors(Block block, BiConsumer<ItemConvertible, @Nullable DyeColor> consumer) {
		if (block instanceof BasicShulkerBlock) {
			for (DyeColor color : DyeColor.values()) {
				consumer.accept(((BasicShulkerBlock) block).getItem(color), color);
			}

			consumer.accept(((BasicShulkerBlock) block).getItem(null), null);
		}
	}

	public static Block registerInvulInventoryShulker(AbstractShulkerBoxBlock block, String path) {
		return registerInvulInventoryShulker(block, path, ShulkerItemGroups.INVUL_UNSTACKABLE_CURSED_ITEM_SETTINGS);
	}

	public static Block registerInvulInventoryShulker(AbstractShulkerBoxBlock block, String path, Item.Settings settings) {
		Block b = Registry.register(Registry.BLOCK, BulkyShulkies.id(path), block);
		Registry.register(Registry.ITEM, BulkyShulkies.id(path), new InventoryShulkerBlockItem(block, settings));
		return b;
	}

	public static Block registerInventoryShulker(AbstractShulkerBoxBlock block, String path) {
		return registerInventoryShulker(block, path, ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS);
	}

	public static Block registerInventoryShulker(AbstractShulkerBoxBlock block, String path, Item.Settings settings) {
		Block b = Registry.register(Registry.BLOCK, BulkyShulkies.id(path), block);
		Registry.register(Registry.ITEM, BulkyShulkies.id(path), new InventoryShulkerBlockItem(block, settings));
		return b;
	}

	private static Block register(Block block, String path) {
		return register(block, path, ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS);
	}

	public static Block register(Block block, String path, Item.Settings settings) {
		Block b = Registry.register(Registry.BLOCK, BulkyShulkies.id(path), block);
		Registry.register(Registry.ITEM, BulkyShulkies.id(path), new BlockItem(block, settings));
		return b;
	}
}
