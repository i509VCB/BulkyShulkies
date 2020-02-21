/*
*
* MIT No Attribution
*
* Copyright 2020 NinjaPhenix
*
* Permission is hereby granted, free of charge, to any person obtaining a copy of this
* software and associated documentation files (the "Software"), to deal in the Software
* without restriction, including without limitation the rights to use, copy, modify,
* merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
* permit persons to whom the Software is furnished to do so.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
* INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
* PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
* HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
* OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
* SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package me.i509.fabric.bulkyshulkies.container;

import java.util.Arrays;

import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.api.SlotDuck;
import me.i509.fabric.bulkyshulkies.api.SlotFactory;

/**
 * Credit: NinjaPhoenix.
 */
public class ScrollableContainer extends Container {
	private final Text containerName;
	private final Inventory inventory;
	private final int rows;
	private final int realRows;

	@Environment(EnvType.CLIENT)
	private String searchTerm = "";
	@Environment(EnvType.CLIENT)
	private Integer[] unsortedToSortedSlotMap;

	public ScrollableContainer(int syncId, SlotFactory slotFactory, PlayerInventory playerInventory, Inventory inventory, Text containerName) {
		super(null, syncId);
		this.inventory = inventory;
		this.containerName = containerName;
		realRows = inventory.getInvSize() / 9;
		rows = realRows > 6 ? 6 : realRows;

		// todo eval if fabric loader removes this statement on server side
		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			unsortedToSortedSlotMap = new Integer[realRows * 9];
		}

		int int_3 = (rows - 4) * 18;
		inventory.onInvOpen(playerInventory.player);

		for (int y = 0; y < realRows; ++y) {
			int yPos = -2000;

			if (y < rows) {
				yPos = 18 + y * 18;
			}

			for (int x = 0; x < 9; ++x) {
				int slot = x + 9 * y;

				if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
					unsortedToSortedSlotMap[slot] = slot;
				}

				addSlot(slotFactory.create(inventory, slot, 8 + x * 18, yPos));
			}
		}

		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				addSlot(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 103 + y * 18 + int_3));
			}
		}

		for (int x = 0; x < 9; ++x) {
			addSlot(new Slot(playerInventory, x, 8 + x * 18, 161 + int_3));
		}
	}

	public Inventory getInventory() {
		return inventory;
	}

	@Environment(EnvType.CLIENT)
	public int getRows() {
		return realRows;
	}

	@Environment(EnvType.CLIENT)
	public Text getDisplayName() {
		return containerName;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return inventory.canPlayerUseInv(player);
	}

	@Override
	public void close(PlayerEntity player) {
		super.close(player);
		inventory.onInvClose(player);
	}

	@Environment(EnvType.CLIENT)
	public void setSearchTerm(String term) {
		searchTerm = term.toLowerCase();
		updateSlotPositions(0, true);
	}

	@Environment(EnvType.CLIENT)
	public void updateSlotPositions(int offset, boolean termChanged) {
		int index = 0;

		if (termChanged && !searchTerm.equals("")) {
			Arrays.sort(unsortedToSortedSlotMap, this::compare);
		} else if (termChanged) {
			Arrays.sort(unsortedToSortedSlotMap);
		}

		for (Integer slotID : unsortedToSortedSlotMap) {
			Slot slot = slots.get(slotID);
			int y = (index / 9) - offset;
			//slot.xPosition = 8 + 18 * (index % 9);
			//slot.yPosition = (y >= rows || y < 0) ? -2000 : 18 + 18 * y;
			SlotDuck duck = (SlotDuck) slot; // TODO: In the future replace this with something nicer.
			duck.setXPosition(8 + 18 * (index % 9));
			duck.setYPosition((y >= rows || y < 0) ? -2000 : 18 + 18 * y);
			index++;
		}
	}

	private int compare(Integer a, Integer b) {
		if (a == null || b == null) {
			return 0;
		}

		ItemStack stack_a = slots.get(a).getStack();
		ItemStack stack_b = slots.get(b).getStack();

		if (stack_a.isEmpty() && !stack_b.isEmpty()) {
			return 1;
		}

		if (!stack_a.isEmpty() && stack_b.isEmpty()) {
			return -1;
		}

		if (stack_a.isEmpty() && stack_b.isEmpty()) {
			return 0;
		}

		return stack_a.getName().getString().toLowerCase().contains(searchTerm) ? -1 : stack_b.getName().getString().toLowerCase().contains(searchTerm) ? 1 : 0;
	}

	@Override
	public ItemStack transferSlot(PlayerEntity player, int slotIndex) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = slots.get(slotIndex);

		if (slot != null && slot.hasStack()) {
			ItemStack slotStack = slot.getStack();
			stack = slotStack.copy();

			if (slotIndex < inventory.getInvSize()) {
				if (!insertItem(slotStack, inventory.getInvSize(), slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!insertItem(slotStack, 0, inventory.getInvSize(), false)) {
				return ItemStack.EMPTY;
			}

			if (slotStack.isEmpty()) {
				slot.setStack(ItemStack.EMPTY);
			} else {
				slot.markDirty();
			}
		}

		return stack;
	}

	public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
		return BulkyShulkies.getInstance().canInsertItem(stack);
	}
}
