package me.i509.fabric.bulkyshulkies.inventory;

import me.i509.fabric.bulkyshulkies.block.ender.EnderSlabBoxBE;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;

import javax.annotation.Nullable;

public class EnderSlabInventory extends BasicInventory {
	@Nullable private EnderSlabBoxBE currentBlockEntity;

	public EnderSlabInventory() {
		super(18);
	}

	public void setCurrentBlockEntity(EnderSlabBoxBE enderSlabBoxBE) {
		this.currentBlockEntity = enderSlabBoxBE;
	}

	public void readTags(ListTag listTag) {
		int j;
		for(j = 0; j < this.getInvSize(); ++j) {
			this.setInvStack(j, ItemStack.EMPTY);
		}

		for(j = 0; j < listTag.size(); ++j) {
			CompoundTag compoundTag = listTag.getCompound(j);
			int k = compoundTag.getByte("Slot") & 255;
			if (k >= 0 && k < this.getInvSize()) {
				this.setInvStack(k, ItemStack.fromTag(compoundTag));
			}
		}

	}

	public ListTag getTags() {
		ListTag listTag = new ListTag();

		for(int i = 0; i < this.getInvSize(); ++i) {
			ItemStack itemStack = this.getInvStack(i);
			if (!itemStack.isEmpty()) {
				CompoundTag compoundTag = new CompoundTag();
				compoundTag.putByte("Slot", (byte)i);
				itemStack.toTag(compoundTag);
				listTag.add(compoundTag);
			}
		}

		return listTag;
	}

	public boolean canPlayerUseInv(PlayerEntity player) {
		return this.currentBlockEntity != null && !this.currentBlockEntity.canPlayerUseInv(player) ? false : super.canPlayerUseInv(player);
	}

	public void onInvOpen(PlayerEntity player) {
		if (this.currentBlockEntity != null) {
			this.currentBlockEntity.onInvOpen(player);
		}

		super.onInvOpen(player);
	}

	public void onInvClose(PlayerEntity player) {
		if (this.currentBlockEntity != null) {
			this.currentBlockEntity.onInvClose(player);
		}

		super.onInvClose(player);
		this.currentBlockEntity = null;
	}
}
