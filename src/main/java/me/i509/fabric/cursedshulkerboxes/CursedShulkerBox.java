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

package me.i509.fabric.cursedshulkerboxes;

import me.i509.fabric.cursedshulkerboxes.api.block.AbstractCursedShulkerBoxBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CursedShulkerBox {
    private CursedShulkerBox(){
        disallowedItems.add((stack) -> Block.getBlockFromItem(stack.getItem()) instanceof ShulkerBoxBlock);
        disallowedItems.add((stack) -> Block.getBlockFromItem(stack.getItem()) instanceof AbstractCursedShulkerBoxBlock);
    }

    private static final CursedShulkerBox instance = new CursedShulkerBox();

    private List<Predicate<ItemStack>> disallowedItems = new ArrayList<>();

    public static CursedShulkerBox getInstance() {
        return instance;
    }

    public void addDisallowedShulkerItem(Predicate<ItemStack> predicate) {
        this.disallowedItems.add(predicate);
    }

    public boolean canInsertItem(ItemStack stack) {
        for (Predicate<ItemStack> disallowedItems : disallowedItems) {
            if(disallowedItems.test(stack)) {
                return false;
            }
        }

        return true;
    }
}
