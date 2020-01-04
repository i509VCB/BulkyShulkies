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

package me.i509.fabric.bulkyshulkies.recipe;

import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;

import me.i509.fabric.bulkyshulkies.api.block.base.BaseShulkerBlock;
import me.i509.fabric.bulkyshulkies.mixin.ShapedRecipeAccessor;

public class AbstractShulkerBoxUpgradeRecipe extends ShapedRecipe {
	public AbstractShulkerBoxUpgradeRecipe(Identifier id, String group, int width, int height, DefaultedList<Ingredient> ingredients, ItemStack output) {
		super(id, group, width, height, ingredients, output);
	}

	public AbstractShulkerBoxUpgradeRecipe(ShapedRecipe handle) {
		this(handle.getId(), ((ShapedRecipeAccessor) handle).accessor$getGroup(), handle.getWidth(), handle.getHeight(), handle.getPreviewInputs(), handle.getOutput());
	}

	@Override
	public ItemStack craft(CraftingInventory inventory) {
		ItemStack reference = null;

		for (int i = 0; i < inventory.getInvSize(); ++i) {
			ItemStack selectedStack = inventory.getInvStack(i);

			if (!selectedStack.isEmpty()) {
				Item item = selectedStack.getItem();

				if (Block.getBlockFromItem(item) instanceof BaseShulkerBlock || Block.getBlockFromItem(item) instanceof ShulkerBoxBlock) {
					reference = selectedStack;
					break;
				}
			}
		}

		if (reference == null) {
			throw new IllegalArgumentException("You need a shulker box somewhere in your recipe for this");
		}

		ItemStack result = getOutput().copy();

		if (reference.hasTag()) {
			result.setTag(reference.getTag().copy());
		}

		return result;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return BulkyRecipeSerializers.UPGRADE;
	}
}
