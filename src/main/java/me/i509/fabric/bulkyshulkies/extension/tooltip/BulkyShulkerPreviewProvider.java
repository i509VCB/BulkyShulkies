package me.i509.fabric.bulkyshulkies.extension.tooltip;

import com.misterpemodder.shulkerboxtooltip.api.provider.BlockEntityPreviewProvider;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.bulkyshulkies.api.block.base.AbstractShulkerBoxBlock;

@Environment(EnvType.CLIENT)
public class BulkyShulkerPreviewProvider extends BlockEntityPreviewProvider {
	private static float[] SHULKER_BOX_COLOR = new float[] {0.592f, 0.403f, 0.592f};

	public BulkyShulkerPreviewProvider(int maxInvSize) {
		super(maxInvSize, true);
	}

	@Override
	public float[] getWindowColor(ItemStack stack) {
		DyeColor dye = ((AbstractShulkerBoxBlock) Block.getBlockFromItem(stack.getItem())).getColor();

		if (dye != null) {
			float[] components = dye.getColorComponents();
			return new float[] {Math.max(0.15f, components[0]), Math.max(0.15f, components[1]),
				Math.max(0.15f, components[2])};
		} else {
			return SHULKER_BOX_COLOR;
		}
	}

	@Override
	public boolean showTooltipHints(ItemStack stack) {
		return true;
	}
}
