package me.i509.fabric.bulkyshulkies.container;

import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import me.i509.fabric.bulkyshulkies.api.SlotFactory;

public class GenericContainer11x7 extends Container {
	private final Inventory inventory;
	private final Text name;

	public GenericContainer11x7(int syncId, SlotFactory slotFactory, PlayerInventory playerInventory, SidedInventory inventory, Text name) {
		super(null, syncId);

		this.inventory = inventory;
		this.name = name;
		playerInventory.onInvOpen(playerInventory.player);

		int i = 63;

		for (int n = 0; n < 7; ++n) {
			for (int m = 0; m < 11; ++m) {
				//this.addSlot(new ShulkerBoxSlot(inventory, m + n * 11,(m * 18) + 4, (n * 18) - 9));
				this.addSlot(slotFactory.create(inventory, m + n * 11, (m * 18) + 4, (n * 18) - 9));
			}
		}

		for (int n = 0; n < 3; ++n) {
			for (int m = 0; m < 9; ++m) {
				this.addSlot(new Slot(playerInventory, m + n * 9 + 9, 22 + (m * 18), 58 + n * 18 + i));
			}
		}

		for (int n = 0; n < 9; ++n) {
			this.addSlot(new Slot(playerInventory, n, 22 + (n * 18), 116 + i));
		}
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return this.inventory.canPlayerUseInv(player);
	}

	public ItemStack transferSlot(PlayerEntity player, int invSlot) {
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = this.slotList.get(invSlot);

		if (slot != null && slot.hasStack()) {
			ItemStack itemStack2 = slot.getStack();
			itemStack = itemStack2.copy();

			if (invSlot < 77) {
				if (!this.insertItem(itemStack2, 7 * 9, this.slotList.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.insertItem(itemStack2, 0, 77, false)) {
				return ItemStack.EMPTY;
			}

			if (itemStack2.isEmpty()) {
				slot.setStack(ItemStack.EMPTY);
			} else {
				slot.markDirty();
			}
		}

		return itemStack;
	}

	public void close(PlayerEntity player) {
		super.close(player);
		this.inventory.onInvClose(player);
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public Text getDisplayName() {
		return this.name;
	}
}
