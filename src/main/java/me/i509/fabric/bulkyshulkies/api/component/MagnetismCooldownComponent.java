package me.i509.fabric.bulkyshulkies.api.component;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;

import net.minecraft.nbt.CompoundTag;

public final class MagnetismCooldownComponent implements ComponentV3, ServerTickingComponent {
	private int cooldownValue;

	public int getCooldownValue() {
		return this.cooldownValue;
	}

	public void setCooldownValue(int cooldownValue) {
		this.cooldownValue = cooldownValue;
	}

	@Override
	public void readFromNbt(CompoundTag tag) {
	}

	@Override
	public void writeToNbt(CompoundTag tag) {
	}

	@Override
	public void serverTick() {
		this.cooldownValue++;
	}
}
