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
import me.i509.fabric.cursedshulkerboxes.block.cursed.slab.CursedSlabShulkerBoxBE;
import me.i509.fabric.cursedshulkerboxes.block.material.copper.CopperShulkerBoxBlockEntity;
import me.i509.fabric.cursedshulkerboxes.block.material.copper.ExampleTallBE;
import me.i509.fabric.cursedshulkerboxes.block.material.iron.IronShulkerBoxBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class ShulkerBlockEntities {

    public static final BlockEntityType<CopperShulkerBoxBlockEntity> COPPER_SHULKER_BOX = register("copper_shulker_box",
            CopperShulkerBoxBlockEntity::new,
            ShulkerBlocks.COPPER_SHULKER_BOX, // Uncolored
            ShulkerBlocks.WHITE_COPPER_SHULKER_BOX,
            ShulkerBlocks.ORANGE_COPPER_SHULKER_BOX,
            ShulkerBlocks.MAGENTA_COPPER_SHULKER_BOX,
            ShulkerBlocks.LIGHT_BLUE_COPPER_SHULKER_BOX,
            ShulkerBlocks.YELLOW_COPPER_SHULKER_BOX,
            ShulkerBlocks.LIME_COPPER_SHULKER_BOX,
            ShulkerBlocks.PINK_COPPER_SHULKER_BOX,
            ShulkerBlocks.GRAY_COPPER_SHULKER_BOX,
            ShulkerBlocks.LIGHT_GRAY_COPPER_SHULKER_BOX,
            ShulkerBlocks.CYAN_COPPER_SHULKER_BOX,
            ShulkerBlocks.PURPLE_COPPER_SHULKER_BOX,
            ShulkerBlocks.BLUE_COPPER_SHULKER_BOX,
            ShulkerBlocks.BROWN_COPPER_SHULKER_BOX,
            ShulkerBlocks.GREEN_COPPER_SHULKER_BOX,
            ShulkerBlocks.RED_COPPER_SHULKER_BOX,
            ShulkerBlocks.BLACK_COPPER_SHULKER_BOX
    );

    public static final BlockEntityType<IronShulkerBoxBlockEntity> IRON_SHULKER_BOX = register("iron_shulker_box",
            IronShulkerBoxBlockEntity::new,
            ShulkerBlocks.IRON_SHULKER_BOX, // Uncolored
            ShulkerBlocks.WHITE_IRON_SHULKER_BOX,
            ShulkerBlocks.ORANGE_IRON_SHULKER_BOX,
            ShulkerBlocks.MAGENTA_IRON_SHULKER_BOX,
            ShulkerBlocks.LIGHT_BLUE_IRON_SHULKER_BOX,
            ShulkerBlocks.YELLOW_IRON_SHULKER_BOX,
            ShulkerBlocks.LIME_IRON_SHULKER_BOX,
            ShulkerBlocks.PINK_IRON_SHULKER_BOX,
            ShulkerBlocks.GRAY_IRON_SHULKER_BOX,
            ShulkerBlocks.LIGHT_GRAY_IRON_SHULKER_BOX,
            ShulkerBlocks.CYAN_IRON_SHULKER_BOX,
            ShulkerBlocks.PURPLE_IRON_SHULKER_BOX,
            ShulkerBlocks.BLUE_IRON_SHULKER_BOX,
            ShulkerBlocks.BROWN_IRON_SHULKER_BOX,
            ShulkerBlocks.GREEN_IRON_SHULKER_BOX,
            ShulkerBlocks.RED_IRON_SHULKER_BOX,
            ShulkerBlocks.BLACK_IRON_SHULKER_BOX
            );

    public static final BlockEntityType<ExampleTallBE> EXAMPLE_TALL = register("example_tall_shulker_box",
            ExampleTallBE::new,
            ShulkerBlocks.TEST_TALL
    );

    public static final BlockEntityType<CursedSlabShulkerBoxBE> SLAB_SHULKER_BOX = register("slab_shulker_box",
            CursedSlabShulkerBoxBE::new,
            ShulkerBlocks.SLAB_SHULKER_BOX // TODO Add all the colors
    );

    private ShulkerBlockEntities(){}
    public static void init(){}

    private static <B extends BlockEntity> BlockEntityType<B> register(String name, Supplier<B> supplier, Block... supportedBlocks) {
        return Registry.register(Registry.BLOCK_ENTITY, CursedShulkerBoxMod.id(name), BlockEntityType.Builder.create(supplier, supportedBlocks).build(null)); // TODO Replace null when DataFixers are done.
    }
}
