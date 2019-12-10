package me.i509.fabric.bulkyshulkies.block.material.platinum;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;

import me.i509.fabric.bulkyshulkies.api.block.Abstract1x1ShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.ShulkerBoxConstants;
import me.i509.fabric.bulkyshulkies.container.ContainerKeys;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlocks;

public class PlatinumShulkerBoxBlock extends Abstract1x1ShulkerBoxBlock {
	public PlatinumShulkerBoxBlock(Settings settings, @Nullable DyeColor color) {
		super(settings, ShulkerBoxConstants.PLATINUM_SLOT_COUNT, color);
	}

	@Override
	protected void openContainer(BlockPos pos, PlayerEntity playerEntity, Text displayName) {
		ContainerProviderRegistry.INSTANCE.openContainer(ContainerKeys.SHULKER_13x7_CONTAINER, playerEntity, (packetByteBuf -> {
			packetByteBuf.writeBlockPos(pos);
			packetByteBuf.writeText(displayName);
		}));
	}

	@Override
	public ItemStack getItemStack(@Nullable DyeColor color) {
		if (color == null) {
			return new ItemStack(ShulkerBlocks.PLATINUM_SHULKER_BOX);
		}

		switch (color) {
		case WHITE:
			return new ItemStack(ShulkerBlocks.WHITE_PLATINUM_SHULKER_BOX);
		case ORANGE:
			return new ItemStack(ShulkerBlocks.ORANGE_PLATINUM_SHULKER_BOX);
		case MAGENTA:
			return new ItemStack(ShulkerBlocks.MAGENTA_PLATINUM_SHULKER_BOX);
		case LIGHT_BLUE:
			return new ItemStack(ShulkerBlocks.LIGHT_BLUE_PLATINUM_SHULKER_BOX);
		case YELLOW:
			return new ItemStack(ShulkerBlocks.YELLOW_PLATINUM_SHULKER_BOX);
		case LIME:
			return new ItemStack(ShulkerBlocks.LIME_PLATINUM_SHULKER_BOX);
		case PINK:
			return new ItemStack(ShulkerBlocks.PINK_PLATINUM_SHULKER_BOX);
		case GRAY:
			return new ItemStack(ShulkerBlocks.GRAY_PLATINUM_SHULKER_BOX);
		case LIGHT_GRAY:
			return new ItemStack(ShulkerBlocks.LIGHT_GRAY_PLATINUM_SHULKER_BOX);
		case CYAN:
			return new ItemStack(ShulkerBlocks.CYAN_PLATINUM_SHULKER_BOX);
		case PURPLE:
		default:
			return new ItemStack(ShulkerBlocks.PURPLE_PLATINUM_SHULKER_BOX);
		case BLUE:
			return new ItemStack(ShulkerBlocks.BLUE_PLATINUM_SHULKER_BOX);
		case BROWN:
			return new ItemStack(ShulkerBlocks.BROWN_PLATINUM_SHULKER_BOX);
		case GREEN:
			return new ItemStack(ShulkerBlocks.GREEN_PLATINUM_SHULKER_BOX);
		case RED:
			return new ItemStack(ShulkerBlocks.RED_PLATINUM_SHULKER_BOX);
		case BLACK:
			return new ItemStack(ShulkerBlocks.BLACK_PLATINUM_SHULKER_BOX);
		}
	}

	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return new PlatinumShulkerBoxBE(this.getColor());
	}
}
