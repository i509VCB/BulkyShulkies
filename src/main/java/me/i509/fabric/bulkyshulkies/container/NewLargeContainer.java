package me.i509.fabric.bulkyshulkies.container;

import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.text.Text;

public class NewLargeContainer extends Container {
	private final PlayerInventory playerInventory;
	private final SidedInventory inventory;
	private final Text containerName;

	public NewLargeContainer(int syncId, PlayerInventory playerInventory, SidedInventory inventory, Text containerName) {
		super(null, syncId);
		this.playerInventory = playerInventory;
		this.inventory = inventory;
		this.containerName = containerName;
	}

	private void addPlayerSlots() {
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return true;
	}
}
