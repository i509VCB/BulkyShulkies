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

import java.util.function.Supplier;

import me.i509.fabric.bulkyshulkies.block.material.diamond.DiamondShulkerBoxBE;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import me.i509.fabric.bulkyshulkies.BulkyShulkiesMod;
import me.i509.fabric.bulkyshulkies.block.cursed.slab.CursedSlabShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.block.material.copper.CopperShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.block.material.iron.IronShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.block.material.gold.GoldShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.block.material.silver.SilverShulkerBoxBE;

public class ShulkerBlockEntities {
	public static final BlockEntityType<CopperShulkerBoxBE> COPPER_SHULKER_BOX = register("copper_shulker_box",
			CopperShulkerBoxBE::new,
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

	public static final BlockEntityType<IronShulkerBoxBE> IRON_SHULKER_BOX = register("iron_shulker_box",
			IronShulkerBoxBE::new,
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

	public static final BlockEntityType<SilverShulkerBoxBE> SILVER_SHULKER_BOX = register("silver_shulker_box",
			SilverShulkerBoxBE::new,
			ShulkerBlocks.SILVER_SHULKER_BOX, // Uncolored
			ShulkerBlocks.WHITE_SILVER_SHULKER_BOX,
			ShulkerBlocks.ORANGE_SILVER_SHULKER_BOX,
			ShulkerBlocks.MAGENTA_SILVER_SHULKER_BOX,
			ShulkerBlocks.LIGHT_BLUE_SILVER_SHULKER_BOX,
			ShulkerBlocks.YELLOW_SILVER_SHULKER_BOX,
			ShulkerBlocks.LIME_SILVER_SHULKER_BOX,
			ShulkerBlocks.PINK_SILVER_SHULKER_BOX,
			ShulkerBlocks.GRAY_SILVER_SHULKER_BOX,
			ShulkerBlocks.LIGHT_GRAY_SILVER_SHULKER_BOX,
			ShulkerBlocks.CYAN_SILVER_SHULKER_BOX,
			ShulkerBlocks.PURPLE_SILVER_SHULKER_BOX,
			ShulkerBlocks.BLUE_SILVER_SHULKER_BOX,
			ShulkerBlocks.BROWN_SILVER_SHULKER_BOX,
			ShulkerBlocks.GREEN_SILVER_SHULKER_BOX,
			ShulkerBlocks.RED_SILVER_SHULKER_BOX,
			ShulkerBlocks.BLACK_SILVER_SHULKER_BOX
	);

	public static final BlockEntityType<GoldShulkerBoxBE> GOLD_SHULKER_BOX = register("gold_shulker_box",
			GoldShulkerBoxBE::new,
			ShulkerBlocks.GOLD_SHULKER_BOX, // Uncolored
			ShulkerBlocks.WHITE_GOLD_SHULKER_BOX,
			ShulkerBlocks.ORANGE_GOLD_SHULKER_BOX,
			ShulkerBlocks.MAGENTA_GOLD_SHULKER_BOX,
			ShulkerBlocks.LIGHT_BLUE_GOLD_SHULKER_BOX,
			ShulkerBlocks.YELLOW_GOLD_SHULKER_BOX,
			ShulkerBlocks.LIME_GOLD_SHULKER_BOX,
			ShulkerBlocks.PINK_GOLD_SHULKER_BOX,
			ShulkerBlocks.GRAY_GOLD_SHULKER_BOX,
			ShulkerBlocks.LIGHT_GRAY_GOLD_SHULKER_BOX,
			ShulkerBlocks.CYAN_GOLD_SHULKER_BOX,
			ShulkerBlocks.PURPLE_GOLD_SHULKER_BOX,
			ShulkerBlocks.BLUE_GOLD_SHULKER_BOX,
			ShulkerBlocks.BROWN_GOLD_SHULKER_BOX,
			ShulkerBlocks.GREEN_GOLD_SHULKER_BOX,
			ShulkerBlocks.RED_GOLD_SHULKER_BOX,
			ShulkerBlocks.BLACK_GOLD_SHULKER_BOX
	);

	public static final BlockEntityType<DiamondShulkerBoxBE> DIAMOND_SHULKER_BOX = register("diamond_shulker_box",
		DiamondShulkerBoxBE::new,
		ShulkerBlocks.DIAMOND_SHULKER_BOX, // Uncolored
		ShulkerBlocks.WHITE_DIAMOND_SHULKER_BOX,
		ShulkerBlocks.ORANGE_DIAMOND_SHULKER_BOX,
		ShulkerBlocks.MAGENTA_DIAMOND_SHULKER_BOX,
		ShulkerBlocks.LIGHT_BLUE_DIAMOND_SHULKER_BOX,
		ShulkerBlocks.YELLOW_DIAMOND_SHULKER_BOX,
		ShulkerBlocks.LIME_DIAMOND_SHULKER_BOX,
		ShulkerBlocks.PINK_DIAMOND_SHULKER_BOX,
		ShulkerBlocks.GRAY_DIAMOND_SHULKER_BOX,
		ShulkerBlocks.LIGHT_GRAY_DIAMOND_SHULKER_BOX,
		ShulkerBlocks.CYAN_DIAMOND_SHULKER_BOX,
		ShulkerBlocks.PURPLE_DIAMOND_SHULKER_BOX,
		ShulkerBlocks.BLUE_DIAMOND_SHULKER_BOX,
		ShulkerBlocks.BROWN_DIAMOND_SHULKER_BOX,
		ShulkerBlocks.GREEN_DIAMOND_SHULKER_BOX,
		ShulkerBlocks.RED_DIAMOND_SHULKER_BOX,
		ShulkerBlocks.BLACK_DIAMOND_SHULKER_BOX
	);

	public static final BlockEntityType<CursedSlabShulkerBoxBE> SLAB_SHULKER_BOX = register("slab_shulker_box",
			CursedSlabShulkerBoxBE::new,
			ShulkerBlocks.SLAB_SHULKER_BOX,
			ShulkerBlocks.WHITE_SLAB_SHULKER_BOX,
			ShulkerBlocks.ORANGE_SLAB_SHULKER_BOX,
			ShulkerBlocks.MAGENTA_SLAB_SHULKER_BOX,
			ShulkerBlocks.LIGHT_BLUE_SLAB_SHULKER_BOX,
			ShulkerBlocks.YELLOW_SLAB_SHULKER_BOX,
			ShulkerBlocks.LIME_SLAB_SHULKER_BOX,
			ShulkerBlocks.PINK_SLAB_SHULKER_BOX,
			ShulkerBlocks.GRAY_SLAB_SHULKER_BOX,
			ShulkerBlocks.LIGHT_GRAY_SLAB_SHULKER_BOX,
			ShulkerBlocks.CYAN_SLAB_SHULKER_BOX,
			ShulkerBlocks.PURPLE_SLAB_SHULKER_BOX,
			ShulkerBlocks.BLUE_SLAB_SHULKER_BOX,
			ShulkerBlocks.BROWN_SLAB_SHULKER_BOX,
			ShulkerBlocks.GREEN_SLAB_SHULKER_BOX,
			ShulkerBlocks.RED_SLAB_SHULKER_BOX,
			ShulkerBlocks.BLACK_SLAB_SHULKER_BOX
	);

	private ShulkerBlockEntities() {
		// NO-OP
	}

	public static void init() {
		// NO-OP
	}

	private static <B extends BlockEntity> BlockEntityType<B> register(String name, Supplier<B> supplier, Block... supportedBlocks) {
		return Registry.register(Registry.BLOCK_ENTITY, BulkyShulkiesMod.id(name), BlockEntityType.Builder.create(supplier, supportedBlocks).build(null)); // TODO Replace null when DataFixers are done.
	}
}
