package me.i509.fabric.bulkyshulkies.api.block.multi;

import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;

import me.i509.fabric.bulkyshulkies.api.block.base.BaseBlockEntity;

public interface MultiblockDependant extends BaseBlockEntity, Tickable {
	BlockPos getParentPos();

	boolean hasParent();

	@Override
	default void tick() {
		if (this.hasWorld()) {
			// TODO
		}
	}
}
