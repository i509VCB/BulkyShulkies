package me.i509.fabric.bulkyshulkies.block.entity;

import me.i509.fabric.bulkyshulkies.api.ShulkerBoxType;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class SimpleShulkerBoxBlockEntity extends AbstractShulkerBoxBlockEntity {
	public SimpleShulkerBoxBlockEntity(ShulkerBoxType shulkerBoxType, BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
		super(shulkerBoxType, blockEntityType, pos, state);
	}
}
