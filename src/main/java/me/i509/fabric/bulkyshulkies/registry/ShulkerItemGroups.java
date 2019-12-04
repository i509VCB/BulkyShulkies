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

package me.i509.fabric.bulkyshulkies.registry;

import me.i509.fabric.bulkyshulkies.BulkyShulkiesMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;

public class ShulkerItemGroups {
	public static final ItemGroup CURSED_GROUP = FabricItemGroupBuilder.build(BulkyShulkiesMod.id("cursedgroup"), () -> new ItemStack(ShulkerBlocks.COPPER_SHULKER_BOX));
	public static final ItemGroup MATERIAL_GROUP = FabricItemGroupBuilder.build(BulkyShulkiesMod.id("materialgroup"), () -> new ItemStack(Items.SHULKER_SHELL));

	public static final Item.Settings UNSTACKABLE_CURSED_ITEM_SETTINGS = new Item.Settings().maxCount(1).group(CURSED_GROUP);
	public static final Item.Settings UNSTACKABLE_MATERIAL_ITEM_SETTINGS = new Item.Settings().maxCount(1).group(ShulkerItemGroups.MATERIAL_GROUP);

	private ShulkerItemGroups() {
		// NO-OP
	}

	public static void init() {
		// NO-OP
	}
}
