package me.i509.fabric.bulkyshulkies.block.material.diamond;

import me.i509.fabric.bulkyshulkies.api.block.material.AbstractMaterialBasedShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class DiamondShulkerBoxBlock extends AbstractMaterialBasedShulkerBoxBlock {
	public DiamondShulkerBoxBlock(Settings settings, @Nullable DyeColor color) {
		super(settings, 72, color);
	}

	@Override
	public BlockEntity createBlockEntity(BlockView blockView) {
		return new DiamondShulkerBoxBE(this.getColor());
	}

	@Override
	public ItemStack getItemStack(@Nullable DyeColor color) {
		if (color == null) {
			return new ItemStack(ShulkerBlocks.DIAMOND_SHULKER_BOX);
		}

		switch (color) {
		case WHITE:
			return new ItemStack(ShulkerBlocks.WHITE_DIAMOND_SHULKER_BOX);
		case ORANGE:
			return new ItemStack(ShulkerBlocks.ORANGE_DIAMOND_SHULKER_BOX);
		case MAGENTA:
			return new ItemStack(ShulkerBlocks.MAGENTA_DIAMOND_SHULKER_BOX);
		case LIGHT_BLUE:
			return new ItemStack(ShulkerBlocks.LIGHT_BLUE_DIAMOND_SHULKER_BOX);
		case YELLOW:
			return new ItemStack(ShulkerBlocks.YELLOW_DIAMOND_SHULKER_BOX);
		case LIME:
			return new ItemStack(ShulkerBlocks.LIME_DIAMOND_SHULKER_BOX);
		case PINK:
			return new ItemStack(ShulkerBlocks.PINK_DIAMOND_SHULKER_BOX);
		case GRAY:
			return new ItemStack(ShulkerBlocks.GRAY_DIAMOND_SHULKER_BOX);
		case LIGHT_GRAY:
			return new ItemStack(ShulkerBlocks.LIGHT_GRAY_DIAMOND_SHULKER_BOX);
		case CYAN:
			return new ItemStack(ShulkerBlocks.CYAN_DIAMOND_SHULKER_BOX);
		case PURPLE:
		default:
			return new ItemStack(ShulkerBlocks.PURPLE_DIAMOND_SHULKER_BOX);
		case BLUE:
			return new ItemStack(ShulkerBlocks.BLUE_DIAMOND_SHULKER_BOX);
		case BROWN:
			return new ItemStack(ShulkerBlocks.BROWN_DIAMOND_SHULKER_BOX);
		case GREEN:
			return new ItemStack(ShulkerBlocks.GREEN_DIAMOND_SHULKER_BOX);
		case RED:
			return new ItemStack(ShulkerBlocks.RED_DIAMOND_SHULKER_BOX);
		case BLACK:
			return new ItemStack(ShulkerBlocks.BLACK_DIAMOND_SHULKER_BOX);
		}
	}
}
