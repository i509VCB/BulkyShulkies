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

package me.i509.bulkyshulkies.api.registry;

import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

import me.i509.bulkyshulkies.api.item.ShulkerHelmetItem;
import me.i509.bulkyshulkies.mod.BulkyShulkiesImpl;
import me.i509.bulkyshulkies.mod.Uninstantiable;
import me.i509.bulkyshulkies.mod.item.ShulkerMagnetWand;
import me.i509.bulkyshulkies.mod.registry.ShulkerItemGroups;

public final class ShulkerItems {
	public static final Item SHULKER_MAGNET_WAND = register(new ShulkerMagnetWand(new Item.Settings().group(ShulkerItemGroups.BULKY_SHULKIES).maxCount(1)), "shulker_magnet_wand");
	public static final Item SHULKER_HELMET = register(new ShulkerHelmetItem(new FabricItemSettings().group(ShulkerItemGroups.BULKY_SHULKIES).maxCount(1)), "shulker_helmet");

	private ShulkerItems() {
		Uninstantiable.whyDoIHearBossMusic(ShulkerItems.class);
	}

	public static void init() {
	}

	public static Item register(Item item, String path) {
		return Registry.register(Registry.ITEM, BulkyShulkiesImpl.id(path), item);
	}
}
