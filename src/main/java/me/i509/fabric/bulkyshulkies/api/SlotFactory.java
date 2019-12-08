package me.i509.fabric.bulkyshulkies.api;

import net.minecraft.container.Slot;
import net.minecraft.inventory.Inventory;

/**
 * Represents a Function which creates a slot.
 */
@FunctionalInterface
public interface SlotFactory {
	/**
	 * Creates a Slot.
	 * @param inventory The inventory the slot is attached to.
	 * @param invSlot The slot number of the slot.
	 * @param xPos The x position of the slot in the container.
	 * @param yPos The y position of the slot in the container.
	 * @return A new slot created with above parameters.
	 */
	Slot create(Inventory inventory, int invSlot, int xPos, int yPos);
}
