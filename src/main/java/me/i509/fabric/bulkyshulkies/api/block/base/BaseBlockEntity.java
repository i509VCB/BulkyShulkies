package me.i509.fabric.bulkyshulkies.api.block.base;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface BaseBlockEntity {
	@Nullable
	World getWorld();

	boolean hasWorld();

	BlockPos getPos();

	BlockState getCachedState();
}
