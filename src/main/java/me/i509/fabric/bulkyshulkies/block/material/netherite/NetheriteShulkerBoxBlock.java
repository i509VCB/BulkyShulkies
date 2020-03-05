package me.i509.fabric.bulkyshulkies.block.material.netherite;

import me.i509.fabric.bulkyshulkies.api.block.Facing1x1ShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.block.ShulkerBoxConstants;
import me.i509.fabric.bulkyshulkies.container.ContainerKeys;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlocks;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class NetheriteShulkerBoxBlock extends Facing1x1ShulkerBoxBlock {
	public NetheriteShulkerBoxBlock(Settings settings, @Nullable DyeColor color) {
		super(settings, ShulkerBoxConstants.NETHERITE_SLOT_COUNT, color);
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
			return new ItemStack(ShulkerBlocks.NETHERITE_SHULKER_BOX);
		}

		switch (color) {
		case WHITE:
			return new ItemStack(ShulkerBlocks.WHITE_NETHERITE_SHULKER_BOX);
		case ORANGE:
			return new ItemStack(ShulkerBlocks.ORANGE_NETHERITE_SHULKER_BOX);
		case MAGENTA:
			return new ItemStack(ShulkerBlocks.MAGENTA_NETHERITE_SHULKER_BOX);
		case LIGHT_BLUE:
			return new ItemStack(ShulkerBlocks.LIGHT_BLUE_NETHERITE_SHULKER_BOX);
		case YELLOW:
			return new ItemStack(ShulkerBlocks.YELLOW_NETHERITE_SHULKER_BOX);
		case LIME:
			return new ItemStack(ShulkerBlocks.LIME_NETHERITE_SHULKER_BOX);
		case PINK:
			return new ItemStack(ShulkerBlocks.PINK_NETHERITE_SHULKER_BOX);
		case GRAY:
			return new ItemStack(ShulkerBlocks.GRAY_NETHERITE_SHULKER_BOX);
		case LIGHT_GRAY:
			return new ItemStack(ShulkerBlocks.LIGHT_GRAY_NETHERITE_SHULKER_BOX);
		case CYAN:
			return new ItemStack(ShulkerBlocks.CYAN_NETHERITE_SHULKER_BOX);
		case PURPLE:
		default:
			return new ItemStack(ShulkerBlocks.PURPLE_NETHERITE_SHULKER_BOX);
		case BLUE:
			return new ItemStack(ShulkerBlocks.BLUE_NETHERITE_SHULKER_BOX);
		case BROWN:
			return new ItemStack(ShulkerBlocks.BROWN_NETHERITE_SHULKER_BOX);
		case GREEN:
			return new ItemStack(ShulkerBlocks.GREEN_NETHERITE_SHULKER_BOX);
		case RED:
			return new ItemStack(ShulkerBlocks.RED_NETHERITE_SHULKER_BOX);
		case BLACK:
			return new ItemStack(ShulkerBlocks.BLACK_NETHERITE_SHULKER_BOX);
		}
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new NetheriteShulkerBoxBE(this.color);
	}
}
