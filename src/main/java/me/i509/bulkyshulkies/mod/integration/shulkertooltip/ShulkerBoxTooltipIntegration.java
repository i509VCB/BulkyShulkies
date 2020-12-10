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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.misterpemodder.shulkerboxtooltip.api.ShulkerBoxTooltipApi;
import com.misterpemodder.shulkerboxtooltip.api.provider.PreviewProvider;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;

import me.i509.bulkyshulkies.api.ShulkerBoxType;
import me.i509.bulkyshulkies.mod.BulkyShulkiesImpl;
import me.i509.bulkyshulkies.mod.BulkyShulkiesMod;
import me.i509.bulkyshulkies.api.block.old.AbstractShulkerBoxBlock;
import me.i509.bulkyshulkies.mod.registry.ShulkerBlocks;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;

/**
 * A hook to allow the Bulky Shulker Boxes to support Shulker Box Tooltips.
 */
public final class ShulkerBoxTooltipIntegration implements ShulkerBoxTooltipApi {
	@Override
	public String getModId() {
		return BulkyShulkiesMod.MODID;
	}

	@Override
	public void registerProviders(Map<PreviewProvider, List<Item>> providers) {
		for (ShulkerBoxType type : BulkyShulkiesImpl.SHULKER_BOX_TYPE) {
			providers.put(new ShulkerBoxPreviewProvider(), Collections.singletonList(type.getItem()));
		}

		RegistryEntryAddedCallback.event(BulkyShulkiesImpl.SHULKER_BOX_TYPE).register((rawId, id, object) -> {
			providers.put(new ShulkerBoxPreviewProvider(), Collections.singletonList(object.getItem()));
		});

		// Register popup providers for Copper Shulker Boxes.
		List<Item> copper = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.COPPER_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		copper.add(ShulkerBlocks.COPPER_SHULKER_BOX.asItem());
		providers.put(new ShulkerBoxPreviewProvider(), copper);

		// Register popup providers for Iron Shulker Boxes.
		List<Item> iron = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.IRON_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		iron.add(ShulkerBlocks.IRON_SHULKER_BOX.asItem());
		providers.put(new ShulkerBoxPreviewProvider(), iron);

		// Register popup providers for Silver Shulker Boxes.
		List<Item> silver = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.SILVER_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		silver.add(ShulkerBlocks.SILVER_SHULKER_BOX.asItem());
		providers.put(new ShulkerBoxPreviewProvider(), silver);

		// Register popup providers for Gold Shulker Boxes.
		List<Item> gold = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.GOLD_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		gold.add(ShulkerBlocks.GOLD_SHULKER_BOX.asItem());
		providers.put(new ShulkerBoxPreviewProvider(), gold);

		// Register popup providers for Diamond Shulker Boxes.
		List<Item> diamond = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.DIAMOND_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		diamond.add(ShulkerBlocks.DIAMOND_SHULKER_BOX.asItem());
		providers.put(new ShulkerBoxPreviewProvider(), diamond);

		// Register popup providers for Obsidian Shulker Boxes.
		List<Item> obsidian = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.OBSIDIAN_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		obsidian.add(ShulkerBlocks.OBSIDIAN_SHULKER_BOX.asItem());
		providers.put(new ShulkerBoxPreviewProvider(), obsidian);

		// Register popup providers for Platinum Shulker Boxes.
		List<Item> platinum = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.PLATINUM_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		platinum.add(ShulkerBlocks.PLATINUM_SHULKER_BOX.asItem());
		providers.put(new ShulkerBoxPreviewProvider(), platinum);

		// Register popup providers for Slab Shulker Boxes.
		List<Item> slab = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.SLAB_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		slab.add(ShulkerBlocks.SLAB_SHULKER_BOX.asItem());
		providers.put(new ShulkerBoxPreviewProvider(), slab);

		// Register popup providers for Missing Tex Shulker Boxes.
		List<Item> missingTex = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.MISSING_TEX_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		missingTex.add(ShulkerBlocks.MISSING_TEX_SHULKER_BOX.asItem());
		providers.put(new ShulkerBoxPreviewProvider(), missingTex);

		// Register popup providers for Stair Shulker Boxes.
		List<Item> stairs = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.STAIR_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		stairs.add(ShulkerBlocks.STAIR_SHULKER_BOX.asItem());
		providers.put(new ShulkerBoxPreviewProvider(), stairs);
	}
}
