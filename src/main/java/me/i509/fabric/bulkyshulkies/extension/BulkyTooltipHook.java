package me.i509.fabric.bulkyshulkies.extension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.misterpemodder.shulkerboxtooltip.api.ShulkerBoxTooltipApi;
import com.misterpemodder.shulkerboxtooltip.api.provider.PreviewProvider;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.bulkyshulkies.BulkyShulkiesMod;
import me.i509.fabric.bulkyshulkies.api.block.base.AbstractShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.ShulkerBoxConstants;
import me.i509.fabric.bulkyshulkies.extension.tooltip.BulkyShulkerPreviewProvider;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlocks;

/**
 * A hook to allow the Bulky Shulker Boxes to support Shulker Box Tooltips.
 */
@Environment(EnvType.CLIENT)
public class BulkyTooltipHook implements ShulkerBoxTooltipApi {
	@Override
	public String getModId() {
		return BulkyShulkiesMod.MODID;
	}

	@Override
	public void registerProviders(Map<PreviewProvider, List<Item>> providers) {
		ShulkerHooks.shulkerTooltipsPresent = true; // If this entrypoint is running, then we must assume ShulkerTooltips is definitely present.
		// Register popup providers for Copper Shulker Boxes.
		List<Item> copper = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.COPPER_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		copper.add(ShulkerBlocks.COPPER_SHULKER_BOX.asItem());
		providers.put(new BulkyShulkerPreviewProvider(ShulkerBoxConstants.COPPER_SLOT_COUNT), copper);
		// Register popup providers for Iron Shulker Boxes.
		List<Item> iron = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.IRON_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		iron.add(ShulkerBlocks.IRON_SHULKER_BOX.asItem());
		providers.put(new BulkyShulkerPreviewProvider(ShulkerBoxConstants.IRON_SLOT_COUNT), iron);
		// Register popup providers for Silver Shulker Boxes.
		List<Item> silver = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.SILVER_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		silver.add(ShulkerBlocks.SILVER_SHULKER_BOX.asItem());
		providers.put(new BulkyShulkerPreviewProvider(ShulkerBoxConstants.IRON_SLOT_COUNT), silver);
		// Register popup providers for Gold Shulker Boxes.
		List<Item> gold = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.GOLD_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		gold.add(ShulkerBlocks.GOLD_SHULKER_BOX.asItem());
		providers.put(new BulkyShulkerPreviewProvider(ShulkerBoxConstants.GOLD_SLOT_COUNT), gold);
		// Register popup providers for Diamond Shulker Boxes.
		List<Item> diamond = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.DIAMOND_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		diamond.add(ShulkerBlocks.DIAMOND_SHULKER_BOX.asItem());
		providers.put(new BulkyShulkerPreviewProvider(ShulkerBoxConstants.DIAMOND_SLOT_COUNT), diamond);
		// Register popup providers for Obsidian Shulker Boxes.
		List<Item> obsidian = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.OBSIDIAN_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		obsidian.add(ShulkerBlocks.OBSIDIAN_SHULKER_BOX.asItem());
		providers.put(new BulkyShulkerPreviewProvider(ShulkerBoxConstants.OBSIDIAN_SLOT_COUNT), obsidian);
		// Register popup providers for Platinum Shulker Boxes.
		List<Item> platinum = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.PLATINUM_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		platinum.add(ShulkerBlocks.PLATINUM_SHULKER_BOX.asItem());
		providers.put(new BulkyShulkerPreviewProvider(ShulkerBoxConstants.PLATINUM_SLOT_COUNT), platinum);
		// Register popup providers for Slab Shulker Boxes.
		List<Item> slab = Arrays.stream(DyeColor.values()).map(((AbstractShulkerBoxBlock) ShulkerBlocks.SLAB_SHULKER_BOX)::getItemStack).map(ItemStack::getItem).collect(Collectors.toList());
		slab.add(ShulkerBlocks.SLAB_SHULKER_BOX.asItem());
		providers.put(new BulkyShulkerPreviewProvider(ShulkerBoxConstants.SLAB_SLOT_COUNT), slab);
	}
}
