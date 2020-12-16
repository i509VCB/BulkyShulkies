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

import java.util.function.BiConsumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.DyeColor;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;

import me.i509.bulkyshulkies.api.block.old.base.OldShulkerBox;
import me.i509.bulkyshulkies.mod.Uninstantiable;

public final class ShulkerBlockSettings {
	private static final AbstractBlock.ContextPredicate FALSE_CTX = (state, world, pos) -> false;
	// Dynamic bounds are VERY IMPORTANT. Otherwise your box will make entities suffocate since the game's cached block is exactly 1 full cube, so collisions act odd for this reason without the dynamic bounds.
	public static final AbstractBlock.Settings SHULKER_BOX_SETTINGS = FabricBlockSettings.of(Material.SHULKER_BOX).breakByTool(FabricToolTags.PICKAXES).hardness(2.0F).dynamicBounds().nonOpaque();
	public static final AbstractBlock.Settings SHULKER_SLAB_BOX_SETTINGS = FabricBlockSettings.of(Material.SHULKER_BOX).breakByTool(FabricToolTags.PICKAXES).hardness(2.0F).dynamicBounds().nonOpaque().suffocates(ShulkerBlockSettings.FALSE_CTX);
	public static final AbstractBlock.Settings EXPLOSION_PROOF_SHULKER_BOX_SETTINGS = FabricBlockSettings.of(Material.SHULKER_BOX).breakByTool(FabricToolTags.PICKAXES).hardness(2.0F).resistance(1200.0F).dynamicBounds().nonOpaque();
	public static final AbstractBlock.Settings ENDER_SLAB_SETTINGS = FabricBlockSettings.of(Material.SHULKER_BOX).breakByTool(FabricToolTags.PICKAXES).hardness(2.0F).resistance(1200.0F).dynamicBounds().nonOpaque().lightLevel(7).suffocates(ShulkerBlockSettings.FALSE_CTX);

	/*
	 * ========================
	 *  Shulker Injector blocks
	 * ========================
	 */
	// For the future
	// public static final Block SHULKER_INJECTOR = registerShulker(new ShulkerInjectorBlock(FabricBlockSettings.of(Material.METAL).build()), "shulker_injector", ShulkerItemGroups.MATERIAL_SETTINGS);

	private ShulkerBlockSettings() {
		Uninstantiable.whyDoIHearBossMusic(ShulkerBlockSettings.class);
	}

	static void init() {
	}

	public static void iterateColors(Block block, BiConsumer<ItemConvertible, @Nullable DyeColor> consumer) {
		if (block instanceof OldShulkerBox) {
			for (DyeColor color : DyeColor.values()) {
				consumer.accept(((OldShulkerBox) block).getItem(color), color);
			}

			consumer.accept(((OldShulkerBox) block).getItem(null), null);
		}
	}

	/*public static Block registerInvulInventoryShulker(AbstractShulkerBoxBlock block, String path) {
		return registerInvulInventoryShulker(block, path, ShulkerItemGroups.INVUL_UNSTACKABLE_CURSED_ITEM_SETTINGS);
	}

	public static Block registerInvulInventoryShulker(AbstractShulkerBoxBlock block, String path, Item.Settings settings) {
		Block b = Registry.register(Registry.BLOCK, BulkyShulkies.id(path), block);
		Registry.register(Registry.ITEM, BulkyShulkies.id(path), new ShulkerBlockItem(block, settings));
		return b;
	}

	public static Block registerInventoryShulker(AbstractShulkerBoxBlock block, String path) {
		return registerInventoryShulker(block, path, ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS);
	}

	public static Block registerInventoryShulker(AbstractShulkerBoxBlock block, String path, Item.Settings settings) {
		Block b = Registry.register(Registry.BLOCK, BulkyShulkies.id(path), block);
		Registry.register(Registry.ITEM, BulkyShulkies.id(path), new ShulkerBlockItem(block, settings));
		return b;
	}

	private static Block register(Block block, String path) {
		return register(block, path, ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS);
	}

	public static Block register(Block block, String path, Item.Settings settings) {
		Block b = Registry.register(Registry.BLOCK, BulkyShulkies.id(path), block);
		Registry.register(Registry.ITEM, BulkyShulkies.id(path), new BlockItem(block, settings));
		return b;
	}*/
}
