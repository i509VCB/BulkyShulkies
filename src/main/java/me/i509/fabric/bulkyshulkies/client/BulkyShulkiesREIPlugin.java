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

package me.i509.fabric.bulkyshulkies.client;

import java.util.ArrayList;
import java.util.List;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.BaseBoundsHandler;
import me.shedaniel.rei.api.DisplayHelper;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.RecipeHelper;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import me.shedaniel.rei.plugin.DefaultPlugin;
import me.shedaniel.rei.plugin.information.DefaultInformationDisplay;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.client.screen.ScrollableScreen;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlocks;

@Environment(EnvType.CLIENT)
public class BulkyShulkiesREIPlugin implements REIPluginV0 {
	private static List<EntryStack> ENTRY_LIST;

	@Override
	public Identifier getPluginIdentifier() {
		return BulkyShulkies.id("reiplugin");
	}

	@Override
	public void registerBounds(DisplayHelper displayHelper) {
		BaseBoundsHandler boundsHandler = BaseBoundsHandler.getInstance();
		boundsHandler.registerExclusionZones(ScrollableScreen.class, this::createScrollableExclusionZones);
	}

	private List<Rectangle> createScrollableExclusionZones() {
		ScrollableScreen screen = (ScrollableScreen) MinecraftClient.getInstance().currentScreen;
		ArrayList<Rectangle> rectangles = new ArrayList<>(1);

		if (screen.hasScrollbar()) {
			rectangles.add(new Rectangle(screen.getLeft() + 172, screen.getTop(), 22, 132));
		}

		return rectangles;
	}

	@Override
	public void registerRecipeDisplays(RecipeHelper recipeHelper) {
		DefaultInformationDisplay display = DefaultInformationDisplay.createFromEntries(createAllEntries(), new TranslatableText("bulkyshulkies.rei.info")).line(new TranslatableText("bulkyshulkies.rei.info.nbt"));
		DefaultPlugin.registerInfoDisplay(display);
	}

	private static List<EntryStack> createAllEntries() {
		if (ENTRY_LIST == null) {
			ENTRY_LIST = new ArrayList<>();
			Tag<Item> itemTags = ItemTags.getContainer().get(BulkyShulkies.id("slab_shulker_boxes"));

			if (itemTags != null) {
				itemTags.values().forEach(item -> ENTRY_LIST.add(EntryStack.create(item)));
			} else {
				BulkyShulkies.getInstance().getLogger().error("Could not find 'slab_shulker_boxes' tag to generate REI info for.");
			}

			ENTRY_LIST.add(EntryStack.create(ShulkerBlocks.ENDER_SLAB_BOX));
		}

		return ENTRY_LIST;
	}
}
