package me.i509.fabric.bulkyshulkies.api.block.multi;

import java.util.List;

import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;

import me.i509.fabric.bulkyshulkies.api.block.base.BaseBlockEntity;

public interface MultiblockController extends BaseBlockEntity, Tickable {
	List<BlockPos> getChildrenPos();

	boolean hasChildren();

	int getRequiredChildren();

	boolean isComplete();

	@Override
	default void tick() {
		if (this.hasWorld()) {
			// TODO
		}
	}
}
