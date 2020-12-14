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

package me.i509.bulkyshulkies.mod.integration.rei;

import java.util.ArrayList;
import java.util.List;

import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.RecipeHelper;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import me.shedaniel.rei.plugin.DefaultPlugin;
import me.shedaniel.rei.plugin.information.DefaultInformationDisplay;

import net.minecraft.item.Item;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.bulkyshulkies.api.registry.ShulkerBoxTypes;
import me.i509.bulkyshulkies.mod.BulkyShulkiesImpl;

@Environment(EnvType.CLIENT)
public final class ReiIntegration implements REIPluginV0 {
	private static List<EntryStack> ENTRY_LIST;

	@Override
	public Identifier getPluginIdentifier() {
		return BulkyShulkiesImpl.id("integration");
	}

	@Override
	public void registerRecipeDisplays(RecipeHelper recipeHelper) {
		DefaultInformationDisplay display = DefaultInformationDisplay.createFromEntries(createAllEntries(), new TranslatableText("bulkyshulkies.rei.info")).line(new TranslatableText("bulkyshulkies.rei.info.nbt"));
		DefaultPlugin.registerInfoDisplay(display);
	}

	private static List<EntryStack> createAllEntries() {
		if (ENTRY_LIST == null) {
			ENTRY_LIST = new ArrayList<>();

			Tag<Item> itemTags = ItemTags.getTagGroup().getTagOrEmpty(BulkyShulkiesImpl.id("slab_shulker_boxes"));
			itemTags.values().forEach(item -> ENTRY_LIST.add(EntryStack.create(item)));

			ENTRY_LIST.add(EntryStack.create(ShulkerBoxTypes.ENDER_SLAB.createItemStack()));
		}

		return ENTRY_LIST;
	}
}
