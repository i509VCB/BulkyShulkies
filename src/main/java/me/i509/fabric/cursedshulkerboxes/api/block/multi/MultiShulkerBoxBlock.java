package me.i509.fabric.cursedshulkerboxes.api.block.multi;

import me.i509.fabric.cursedshulkerboxes.api.block.base.BaseShulkerBlock;
import net.minecraft.block.BlockState;

public interface MultiShulkerBoxBlock extends BaseShulkerBlock {
    boolean isBottom(BlockState state);
}
