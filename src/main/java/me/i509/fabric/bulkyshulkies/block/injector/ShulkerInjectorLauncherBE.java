package me.i509.fabric.bulkyshulkies.block.injector;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Tickable;

public class ShulkerInjectorLauncherBE extends BlockEntity implements Tickable {
	public ShulkerInjectorLauncherBE(BlockEntityType<?> type) {
		super(type);
	}

	@Override
	public void tick() {
		// TODO
	}
}
