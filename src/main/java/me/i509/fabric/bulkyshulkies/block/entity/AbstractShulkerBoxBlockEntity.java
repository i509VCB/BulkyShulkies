package me.i509.fabric.bulkyshulkies.block.entity;

import me.i509.fabric.bulkyshulkies.api.ShulkerBoxType;
import me.i509.fabric.bulkyshulkies.api.block.entity.ShulkerBoxBlockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public abstract class AbstractShulkerBoxBlockEntity extends BlockEntity implements ShulkerBoxBlockEntity {
	private final ShulkerBoxType shulkerBoxType;

	protected AbstractShulkerBoxBlockEntity(ShulkerBoxType shulkerBoxType, BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
		super(blockEntityType, pos, state);
		this.shulkerBoxType = shulkerBoxType;
	}

	@Override
	public final ShulkerBoxType getShulkerBoxType() {
		return this.shulkerBoxType;
	}

	@Override
	public BlockEntity toBlockEntity() {
		return this;
	}
}
