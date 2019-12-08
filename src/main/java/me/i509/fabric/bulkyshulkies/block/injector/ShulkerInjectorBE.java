package me.i509.fabric.bulkyshulkies.block.injector;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Tickable;

public class ShulkerInjectorBE extends BlockEntity implements Tickable {
	public ShulkerInjectorBE(BlockEntityType<?> type) {
		super(type);
	}

	@Override
	public void tick() {
		// TODO
	}
}
