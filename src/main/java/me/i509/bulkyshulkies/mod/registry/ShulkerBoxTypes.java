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

package me.i509.bulkyshulkies.mod.registry;

import me.i509.bulkyshulkies.api.ShulkerBoxTicking;
import me.i509.bulkyshulkies.api.ShulkerBoxType;
import me.i509.bulkyshulkies.api.component.MagnetismCooldownComponent;
import me.i509.bulkyshulkies.api.component.ShulkerBoxColorComponent;
import me.i509.bulkyshulkies.mod.BulkyShulkiesImpl;
import me.i509.bulkyshulkies.mod.Uninstantiable;

import net.minecraft.block.FacingBlock;

public final class ShulkerBoxTypes {
	public static final ShulkerBoxType COPPER = ShulkerBoxType.builder()
			.id(BulkyShulkiesImpl.id("copper_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlockSettings.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType IRON = ShulkerBoxType.builder()
			.id(BulkyShulkiesImpl.id("iron_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlockSettings.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType SILVER = ShulkerBoxType.builder()
			.id(BulkyShulkiesImpl.id("silver_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlockSettings.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType GOLD = ShulkerBoxType.builder()
			.id(BulkyShulkiesImpl.id("gold_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlockSettings.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType DIAMOND = ShulkerBoxType.builder()
			.id(BulkyShulkiesImpl.id("diamond_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlockSettings.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType CLEAR = ShulkerBoxType.builder()
			.id(BulkyShulkiesImpl.id("clear_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlockSettings.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType OBSIDIAN = ShulkerBoxType.builder()
			.id(BulkyShulkiesImpl.id("obsidian_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlockSettings.EXPLOSION_PROOF_SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType PLATINUM = ShulkerBoxType.builder()
			.id(BulkyShulkiesImpl.id("platinum_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToBlockEntity(ShulkerComponents.MAGNETISM_COOLDOWN, ignored -> new MagnetismCooldownComponent())
			.blockSettings(ShulkerBlockSettings.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType NETHERITE = ShulkerBoxType.builder()
			.id(BulkyShulkiesImpl.id("netherite_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlockSettings.EXPLOSION_PROOF_SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.INVUL_UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::platinumServerTick)
			.buildAndRegister();

	public static final ShulkerBoxType SLAB = ShulkerBoxType.builder()
			.id(BulkyShulkiesImpl.id("slab_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlockSettings.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType STAIR = ShulkerBoxType.builder()
			.id(BulkyShulkiesImpl.id("stair_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlockSettings.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType ENDER_SLAB = ShulkerBoxType.builder()
			.id(BulkyShulkiesImpl.id("ender_slab"))
			.directionProperty(FacingBlock.FACING)
			.blockSettings(ShulkerBlockSettings.EXPLOSION_PROOF_SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS) // TODO: Stackable
			.clientTicker(ShulkerBoxTicking::enderSlabTick)
			.serverTicker(ShulkerBoxTicking::enderSlabTick)
			.allowInsertionIntoShulkerBox()
			.buildAndRegister();

	static void init() {
	}

	private ShulkerBoxTypes() {
		Uninstantiable.whyDoIHearBossMusic(ShulkerBoxTypes.class);
	}
}
