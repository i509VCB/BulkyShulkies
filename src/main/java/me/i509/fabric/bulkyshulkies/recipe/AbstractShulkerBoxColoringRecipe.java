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
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.bulkyshulkies.api.block.old.base.OldShulkerBox;
import me.i509.fabric.bulkyshulkies.api.block.old.colored.ColoredShulkerBoxBlock;

public class AbstractShulkerBoxColoringRecipe extends SpecialCraftingRecipe {
	public AbstractShulkerBoxColoringRecipe(Identifier identifier) {
		super(identifier);
	}

	public boolean matches(CraftingInventory craftingInventory, World world) {
		int i = 0;
		int j = 0;

		for (int k = 0; k < craftingInventory.size(); ++k) {
			ItemStack itemStack = craftingInventory.getStack(k);

			if (!itemStack.isEmpty()) {
				if (Block.getBlockFromItem(itemStack.getItem()) instanceof ColoredShulkerBoxBlock) {
					++i;
				} else {
					if (!(itemStack.getItem() instanceof DyeItem)) {
						return false;
					}

					++j;
				}

				if (j > 1 || i > 1) {
					return false;
				}
			}
		}

		return i == 1 && j == 1;
	}

	public ItemStack craft(CraftingInventory craftingInventory) {
		ItemStack itemStack = ItemStack.EMPTY;
		DyeItem dyeItem = (DyeItem) Items.WHITE_DYE;

		for (int i = 0; i < craftingInventory.size(); ++i) {
			ItemStack selectedStack = craftingInventory.getStack(i);

			if (!selectedStack.isEmpty()) {
				Item item = selectedStack.getItem();

				if (Block.getBlockFromItem(item) instanceof ColoredShulkerBoxBlock) {
					itemStack = selectedStack;
				} else if (item instanceof DyeItem) {
					dyeItem = (DyeItem) item;
				}
			}
		}

		ItemStack newColor = ((OldShulkerBox) Block.getBlockFromItem(itemStack.getItem())).getItemStack(dyeItem.getColor());

		if (itemStack.hasTag()) {
			newColor.setTag(itemStack.getTag().copy()); // Here is where we transfer all the NBT data.
		}

		return newColor;
	}

	@Environment(EnvType.CLIENT)
	public boolean fits(int width, int height) {
		return width * height >= 2;
	}

	public RecipeSerializer getSerializer() {
		return BulkyRecipeSerializers.ABSTRACT_SHULKER_COLORING;
	}
}
