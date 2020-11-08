package me.i509.fabric.bulkyshulkies.api.component;

import java.util.Objects;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import me.i509.fabric.bulkyshulkies.api.block.ShulkerBoxColor;

import net.minecraft.nbt.CompoundTag;

import net.fabricmc.fabric.api.util.NbtType;

public final class ShulkerBoxColorComponent implements ComponentV3, AutoSyncedComponent {
	private ShulkerBoxColor color = ShulkerBoxColor.NONE;

	public ShulkerBoxColor getColor() {
		return this.color;
	}

	public void setColor(ShulkerBoxColor color) {
		this.color = Objects.requireNonNull(color);
	}

	@Override
	public void readFromNbt(CompoundTag tag) {
		if (tag.contains("ShulkerBoxColor", NbtType.STRING)) {
			this.color = ShulkerBoxColor.valueOf(tag.getString("ShulkerBoxColor"));
		}
	}

	@Override
	public void writeToNbt(CompoundTag tag) {
		tag.putString("ShulkerBoxColor", this.color.name());
	}
}
