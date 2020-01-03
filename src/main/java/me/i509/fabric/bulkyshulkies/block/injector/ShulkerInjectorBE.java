/*
 * MIT License
 *
 * Copyright (c) 2019-2020 i509VCB
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.i509.fabric.bulkyshulkies.block.injector;

import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Direction;

// import me.i509.fabric.bulkyshulkies.registry.ShulkerBlockEntities;

public class ShulkerInjectorBE extends LockableContainerBlockEntity implements SidedInventory, Tickable {
	protected DefaultedList<ItemStack> inventory;

	public ShulkerInjectorBE() {
		super(null); // TODO
		//super(ShulkerBlockEntities.SHULKER_INJECTOR);
		this.inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
	}

	@Override
	public void tick() {
		// TODO
	}

	@Override
	protected Text getContainerName() {
		return new TranslatableText("container.shulkerInjector");
	}

	@Override
	protected Container createContainer(int i, PlayerInventory playerInventory) {
		return null; // We don't use MC's create container.
	}

	@Override
	public int[] getInvAvailableSlots(Direction side) {
		return new int[0];
	}

	@Override
	public boolean canInsertInvStack(int slot, ItemStack stack, Direction dir) {
		return false;
	}

	@Override
	public boolean canExtractInvStack(int slot, ItemStack stack, Direction dir) {
		return false;
	}

	@Override
	public int getInvSize() {
		return 0;
	}

	@Override
	public boolean isInvEmpty() {
		return false;
	}

	@Override
	public ItemStack getInvStack(int slot) {
		return null;
	}

	@Override
	public ItemStack takeInvStack(int slot, int amount) {
		return null;
	}

	@Override
	public ItemStack removeInvStack(int slot) {
		return null;
	}

	@Override
	public void setInvStack(int slot, ItemStack stack) {
		// TODO:
	}

	@Override
	public boolean canPlayerUseInv(PlayerEntity player) {
		return false;
	}

	@Override
	public void clear() {
		// TODO:
	}
}
