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

package me.i509.fabric.cursedshulkerboxes.registry;

import me.i509.fabric.cursedshulkerboxes.CursedShulkerBoxMod;
import me.i509.fabric.cursedshulkerboxes.block.material.copper.CopperShulkerBoxBlock;
import me.i509.fabric.cursedshulkerboxes.registry.ShulkerItemGroups;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;

public class ShulkerBlocks {
    public static final Item.Settings CURSED_ITEM_SETTINGS = new Item.Settings().maxCount(1).group(ShulkerItemGroups.CURSED_GROUP);
    public static final Item.Settings MATERIAL_ITEM_SETTINGS = new Item.Settings().maxCount(1).group(ShulkerItemGroups.MATERIAL_GROUP);

    public static final Block COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(FabricBlockSettings.of(Material.SHULKER_BOX).build(), null), "copper_shulker_box");
    public static final Block BLACK_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(FabricBlockSettings.of(Material.SHULKER_BOX).build(), DyeColor.BLACK), "black_copper_shulker_box");
    public static final Block BLUE_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(FabricBlockSettings.of(Material.SHULKER_BOX).build(), DyeColor.BLUE), "blue_copper_shulker_box");
    public static final Block BROWN_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(FabricBlockSettings.of(Material.SHULKER_BOX).build(), DyeColor.BROWN), "brown_copper_shulker_box");
    public static final Block RED_COPPER_SHULKER_BOX = register(new CopperShulkerBoxBlock(FabricBlockSettings.of(Material.SHULKER_BOX).build(), DyeColor.RED), "red_copper_shulker_box");


    private ShulkerBlocks(){}
    public static void init(){}

    public static Block register(Block block, String path) {
        return register(block, path, CURSED_ITEM_SETTINGS);
    }

    public static Block register(Block block, String path, Item.Settings settings) {
        Block b = Registry.register(Registry.BLOCK, CursedShulkerBoxMod.id(path), block);
        Registry.register(Registry.ITEM, CursedShulkerBoxMod.id(path), new BlockItem(block, settings));
        return b;
    }
}
