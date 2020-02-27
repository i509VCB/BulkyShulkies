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

package me.i509.fabric.bulkyshulkies.registry;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.block.cursed.slab.CursedSlabShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.block.cursed.stair.StairShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.block.material.copper.CopperShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.block.material.diamond.DiamondShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.block.ender.EnderSlabBoxBE;
import me.i509.fabric.bulkyshulkies.block.material.iron.IronShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.block.material.gold.GoldShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.block.missing.MissingTexBoxBE;
import me.i509.fabric.bulkyshulkies.block.material.obsidian.ObsidianShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.block.material.platinum.PlatinumShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.block.material.silver.SilverShulkerBoxBE;
// import me.i509.fabric.bulkyshulkies.block.injector.ShulkerInjectorBE;

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

	public static final BlockEntityType<ObsidianShulkerBoxBE> OBSIDIAN_SHULKER_BOX = register("obsidian_shulker_box",
			ObsidianShulkerBoxBE::new,
			ShulkerBlocks.OBSIDIAN_SHULKER_BOX,
			ShulkerBlocks.WHITE_OBSIDIAN_SHULKER_BOX,
			ShulkerBlocks.ORANGE_OBSIDIAN_SHULKER_BOX,
			ShulkerBlocks.MAGENTA_OBSIDIAN_SHULKER_BOX,
			ShulkerBlocks.LIGHT_BLUE_OBSIDIAN_SHULKER_BOX,
			ShulkerBlocks.YELLOW_OBSIDIAN_SHULKER_BOX,
			ShulkerBlocks.LIME_OBSIDIAN_SHULKER_BOX,
			ShulkerBlocks.PINK_OBSIDIAN_SHULKER_BOX,
			ShulkerBlocks.GRAY_OBSIDIAN_SHULKER_BOX,
			ShulkerBlocks.LIGHT_GRAY_OBSIDIAN_SHULKER_BOX,
			ShulkerBlocks.CYAN_OBSIDIAN_SHULKER_BOX,
			ShulkerBlocks.PURPLE_OBSIDIAN_SHULKER_BOX,
			ShulkerBlocks.BLUE_OBSIDIAN_SHULKER_BOX,
			ShulkerBlocks.BROWN_OBSIDIAN_SHULKER_BOX,
			ShulkerBlocks.GREEN_OBSIDIAN_SHULKER_BOX,
			ShulkerBlocks.RED_OBSIDIAN_SHULKER_BOX,
			ShulkerBlocks.BLACK_OBSIDIAN_SHULKER_BOX
	);

	public static final BlockEntityType<PlatinumShulkerBoxBE> PLATINUM_SHULKER_BOX = register("platinum_shulker_box",
			PlatinumShulkerBoxBE::new,
			ShulkerBlocks.PLATINUM_SHULKER_BOX,
			ShulkerBlocks.WHITE_PLATINUM_SHULKER_BOX,
			ShulkerBlocks.ORANGE_PLATINUM_SHULKER_BOX,
			ShulkerBlocks.MAGENTA_PLATINUM_SHULKER_BOX,
			ShulkerBlocks.LIGHT_BLUE_PLATINUM_SHULKER_BOX,
			ShulkerBlocks.YELLOW_PLATINUM_SHULKER_BOX,
			ShulkerBlocks.LIME_PLATINUM_SHULKER_BOX,
			ShulkerBlocks.PINK_PLATINUM_SHULKER_BOX,
			ShulkerBlocks.GRAY_PLATINUM_SHULKER_BOX,
			ShulkerBlocks.LIGHT_GRAY_PLATINUM_SHULKER_BOX,
			ShulkerBlocks.CYAN_PLATINUM_SHULKER_BOX,
			ShulkerBlocks.PURPLE_PLATINUM_SHULKER_BOX,
			ShulkerBlocks.BLUE_PLATINUM_SHULKER_BOX,
			ShulkerBlocks.BROWN_PLATINUM_SHULKER_BOX,
			ShulkerBlocks.GREEN_PLATINUM_SHULKER_BOX,
			ShulkerBlocks.RED_PLATINUM_SHULKER_BOX,
			ShulkerBlocks.BLACK_PLATINUM_SHULKER_BOX
	);

	public static final BlockEntityType<MissingTexBoxBE> MISSING_TEX = register("missing_tex_box",
			MissingTexBoxBE::new,
			ShulkerBlocks.MISSING_TEX_SHULKER_BOX,
			ShulkerBlocks.WHITE_MISSING_TEX_SHULKER_BOX,
			ShulkerBlocks.ORANGE_MISSING_TEX_SHULKER_BOX,
			ShulkerBlocks.MAGENTA_MISSING_TEX_SHULKER_BOX,
			ShulkerBlocks.LIGHT_BLUE_MISSING_TEX_SHULKER_BOX,
			ShulkerBlocks.YELLOW_MISSING_TEX_SHULKER_BOX,
			ShulkerBlocks.LIME_MISSING_TEX_SHULKER_BOX,
			ShulkerBlocks.PINK_MISSING_TEX_SHULKER_BOX,
			ShulkerBlocks.GRAY_MISSING_TEX_SHULKER_BOX,
			ShulkerBlocks.LIGHT_GRAY_MISSING_TEX_SHULKER_BOX,
			ShulkerBlocks.CYAN_MISSING_TEX_SHULKER_BOX,
			ShulkerBlocks.PURPLE_MISSING_TEX_SHULKER_BOX,
			ShulkerBlocks.BLUE_MISSING_TEX_SHULKER_BOX,
			ShulkerBlocks.BROWN_MISSING_TEX_SHULKER_BOX,
			ShulkerBlocks.GREEN_MISSING_TEX_SHULKER_BOX,
			ShulkerBlocks.RED_MISSING_TEX_SHULKER_BOX,
			ShulkerBlocks.BLACK_MISSING_TEX_SHULKER_BOX
	);

	public static final BlockEntityType<?> STAIR = register("stair_shulker_box",
			StairShulkerBoxBE::new,
			ShulkerBlocks.STAIR_SHULKER_BOX,
			ShulkerBlocks.WHITE_STAIR_SHULKER_BOX,
			ShulkerBlocks.ORANGE_STAIR_SHULKER_BOX,
			ShulkerBlocks.MAGENTA_STAIR_SHULKER_BOX,
			ShulkerBlocks.LIGHT_BLUE_STAIR_SHULKER_BOX,
			ShulkerBlocks.YELLOW_STAIR_SHULKER_BOX,
			ShulkerBlocks.LIME_STAIR_SHULKER_BOX,
			ShulkerBlocks.PINK_STAIR_SHULKER_BOX,
			ShulkerBlocks.GRAY_STAIR_SHULKER_BOX,
			ShulkerBlocks.LIGHT_GRAY_STAIR_SHULKER_BOX,
			ShulkerBlocks.CYAN_STAIR_SHULKER_BOX,
			ShulkerBlocks.PURPLE_STAIR_SHULKER_BOX,
			ShulkerBlocks.BLUE_STAIR_SHULKER_BOX,
			ShulkerBlocks.BROWN_STAIR_SHULKER_BOX,
			ShulkerBlocks.GREEN_STAIR_SHULKER_BOX,
			ShulkerBlocks.RED_STAIR_SHULKER_BOX,
			ShulkerBlocks.BLACK_STAIR_SHULKER_BOX
	);

	public static final BlockEntityType<EnderSlabBoxBE> ENDER_SLAB = register("ender_slab",
			EnderSlabBoxBE::new,
			ShulkerBlocks.ENDER_SLAB_BOX
	);

	/*public static final BlockEntityType<ShulkerInjectorBE> SHULKER_INJECTOR = register("shulker_injector",
			ShulkerInjectorBE::new,
			ShulkerBlocks.SHULKER_INJECTOR
	);*/

	private ShulkerBlockEntities() {
		// NO-OP
	}

	public static void init() {
		// NO-OP
	}

	private static <B extends BlockEntity> BlockEntityType<B> register(String name, Supplier<B> supplier, Block... supportedBlocks) {
		return Registry.register(Registry.BLOCK_ENTITY_TYPE, BulkyShulkies.id(name), BlockEntityType.Builder.create(supplier, supportedBlocks).build(null)); // TODO Replace null when DataFixers are done.
	}
}
