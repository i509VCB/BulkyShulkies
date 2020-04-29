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

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import me.i509.fabric.bulkyshulkies.mixin.accessor.ShapedRecipeAccessor;

public class NoNbtRecipe extends ShapedRecipe {
	private final int width;
	private final int height;
	private DefaultedList<Ingredient> inputs;

	public NoNbtRecipe(Identifier id, String group, int width, int height, DefaultedList<Ingredient> ingredients, ItemStack output) {
		super(id, group, width, height, ingredients, output);
		this.width = width;
		this.height = height;
		this.inputs = ingredients;
	}

	public NoNbtRecipe(ShapedRecipe handle) {
		this(handle.getId(), ((ShapedRecipeAccessor) handle).accessor$getGroup(), handle.getWidth(), handle.getHeight(), handle.getPreviewInputs(), handle.getOutput());
	}

	public boolean matches(CraftingInventory craftingInventory, World world) {
		for (int i = 0; i <= craftingInventory.getWidth() - this.width; ++i) {
			for (int j = 0; j <= craftingInventory.getHeight() - this.height; ++j) {
				if (this.matchesSmall(craftingInventory, i, j, true)) {
					return true;
				}

				if (this.matchesSmall(craftingInventory, i, j, false)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean matchesSmall(CraftingInventory inv, int offsetX, int offsetY, boolean bl) {
		for (int i = 0; i < inv.getWidth(); ++i) {
			for (int j = 0; j < inv.getHeight(); ++j) {
				int k = i - offsetX;
				int l = j - offsetY;
				Ingredient ingredient = Ingredient.EMPTY;

				if (k >= 0 && l >= 0 && k < this.width && l < this.height) {
					if (bl) {
						ingredient = this.inputs.get(this.width - k - 1 + l * this.width);
					} else {
						ingredient = this.inputs.get(k + l * this.width);
					}
				}

				if (inv.getStack(i + j * inv.getWidth()).hasTag()) {
					return false;
				}

				if (!ingredient.test(inv.getStack(i + j * inv.getWidth()))) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public ItemStack craft(CraftingInventory inventory) {
		return super.craft(inventory);
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return BulkyRecipeSerializers.NO_NBT;
	}
}
