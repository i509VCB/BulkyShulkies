package me.i509.fabric.bulkyshulkies.registry;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.api.ShulkerBoxTicking;
import me.i509.fabric.bulkyshulkies.api.ShulkerBoxType;
import me.i509.fabric.bulkyshulkies.api.component.MagnetismCooldownComponent;
import me.i509.fabric.bulkyshulkies.api.component.ShulkerBoxColorComponent;

import net.minecraft.block.FacingBlock;

public final class ShulkerBoxTypes {
	public static final ShulkerBoxType COPPER = ShulkerBoxType.builder()
			.id(BulkyShulkies.id("copper_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlocks.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType IRON = ShulkerBoxType.builder()
			.id(BulkyShulkies.id("iron_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlocks.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType SILVER = ShulkerBoxType.builder()
			.id(BulkyShulkies.id("silver_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlocks.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType GOLD = ShulkerBoxType.builder()
			.id(BulkyShulkies.id("gold_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlocks.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType DIAMOND = ShulkerBoxType.builder()
			.id(BulkyShulkies.id("diamond_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlocks.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType CLEAR = ShulkerBoxType.builder()
			.id(BulkyShulkies.id("clear_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlocks.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType OBSIDIAN = ShulkerBoxType.builder()
			.id(BulkyShulkies.id("obsidian_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlocks.EXPLOSION_PROOF_SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType PLATINUM = ShulkerBoxType.builder()
			.id(BulkyShulkies.id("platinum_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToBlockEntity(ShulkerComponents.MAGNETISM_COOLDOWN, ignored -> new MagnetismCooldownComponent())
			.blockSettings(ShulkerBlocks.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType NETHERITE = ShulkerBoxType.builder()
			.id(BulkyShulkies.id("netherite_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlocks.EXPLOSION_PROOF_SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.INVUL_UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::platinumServerTick)
			.buildAndRegister();

	public static final ShulkerBoxType SLAB = ShulkerBoxType.builder()
			.id(BulkyShulkies.id("slab_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlocks.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType STAIR = ShulkerBoxType.builder()
			.id(BulkyShulkies.id("stair_shulker_box"))
			.directionProperty(FacingBlock.FACING)
			.attachToBlockEntity(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.attachToItemStack(ShulkerComponents.SHULKER_BOX_COLOR, ignored -> new ShulkerBoxColorComponent())
			.blockSettings(ShulkerBlocks.SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS)
			.inventory()
			.clientTicker(ShulkerBoxTicking::simpleTick)
			.serverTicker(ShulkerBoxTicking::simpleTick)
			.buildAndRegister();

	public static final ShulkerBoxType ENDER_SLAB = ShulkerBoxType.builder()
			.id(BulkyShulkies.id("ender_slab"))
			.directionProperty(FacingBlock.FACING)
			.blockSettings(ShulkerBlocks.EXPLOSION_PROOF_SHULKER_BOX_SETTINGS)
			.itemSettings(ShulkerItemGroups.UNSTACKABLE_CURSED_ITEM_SETTINGS) // TODO: Stackable
			.clientTicker(ShulkerBoxTicking::enderSlabTick)
			.serverTicker(ShulkerBoxTicking::enderSlabTick)
			.buildAndRegister();

	static void init() {
	}

	private ShulkerBoxTypes() {
	}
}
