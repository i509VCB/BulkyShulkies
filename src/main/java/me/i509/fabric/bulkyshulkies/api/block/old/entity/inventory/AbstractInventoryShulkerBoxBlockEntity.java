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

package me.i509.fabric.bulkyshulkies.api.block.old.entity.inventory;

import java.util.stream.IntStream;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ContainerLock;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import net.fabricmc.fabric.api.util.NbtType;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.api.inventory.ForwardingSidedInventory;

public abstract class AbstractInventoryShulkerBoxBlockEntity extends AbstractShulkerBoxBlockEntity implements
				LockableAndLootableBlockEntityWithInventory,
				ForwardingSidedInventory,
				NamedScreenHandlerFactory {
	private final ShulkerInventory inventory;
	private final int[] availibleSlots;
	private ContainerLock lock = ContainerLock.EMPTY;
	@Nullable private Identifier lootTableId;
	private long lootTableSeed = 0L;
	private Text customName;

	protected AbstractInventoryShulkerBoxBlockEntity(
			BlockEntityType<? extends AbstractInventoryShulkerBoxBlockEntity> blockEntityType,
			DirectionProperty directionProperty,
			int slots) {
		super(blockEntityType, directionProperty);
		this.availibleSlots = IntStream.range(0, slots).toArray();
		this.inventory = new ShulkerInventory(slots);
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) {
		if (this.customName != null) {
			tag.putString("CustomName", Text.Serializer.toJson(this.customName));
		}

		return this.lootTableOrInventoryToTag(super.toTag(tag));
	}

	@Override
	public void fromTag(CompoundTag tag) {
		super.fromTag(tag);
		this.lootTableOrInventoryFromTag(tag);

		if (tag.contains("CustomName", NbtType.STRING)) {
			this.customName = Text.Serializer.fromJson(tag.getString("CustomName"));
		}
	}

	// LockableBlockEntity

	@Override
	public ContainerLock getLock() {
		return this.lock;
	}

	@Override
	public void setLock(ContainerLock lock) {
		this.lock = lock;
	}

	// LootableBlockEntity

	@Override
	public Identifier getLootTableId() {
		return this.lootTableId;
	}

	@Override
	public long getLootTableSeed() {
		return this.lootTableSeed;
	}

	@Override
	public void setLootTable(Identifier id, long seed) {
		this.lootTableId = id;
		this.lootTableSeed = seed;
	}

	@Override
	public void checkLootInteraction(@Nullable PlayerEntity playerEntity) {
		if (this.lootTableId != null && this.world.getServer() != null) {
			LootTable lootTable = this.world.getServer().getLootManager().getTable(this.lootTableId);

			if (playerEntity instanceof ServerPlayerEntity) {
				Criteria.PLAYER_GENERATES_CONTAINER_LOOT.test((ServerPlayerEntity) playerEntity, this.lootTableId);
			}

			this.lootTableId = null;
			LootContext.Builder builder = new LootContext.Builder((ServerWorld) this.world)
					.parameter(LootContextParameters.ORIGIN, Vec3d.ofCenter(this.pos))
					.random(this.lootTableSeed);
			if (playerEntity != null) {
				builder.luck(playerEntity.getLuck())
					.parameter(LootContextParameters.THIS_ENTITY, playerEntity);
			}

			lootTable.supplyInventory(this, builder.build(LootContextTypes.CHEST));
		}
	}

	// Nameable

	@Override
	public Text getName() {
		return this.customName != null ? this.customName : this.getDefaultName();
	}

	@Override
	public Text getDisplayName() {
		return this.getName();
	}

	@Override
	public Text getCustomName() {
		return this.customName;
	}

	public void setCustomName(Text customName) {
		this.customName = customName;
	}

	@Nullable
	public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
		return this.checkUnlocked(player) ? this.createScreenHandler(syncId, playerInventory) : null;
	}

	protected abstract ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory);

	protected abstract Text getDefaultName();

	// Inventory

	@Override
	public SidedInventory getInventory() {
		return this.inventory;
	}

	protected class ShulkerInventory extends SimpleInventory implements SidedInventory {
		protected ShulkerInventory(int slots) {
			super(slots);
		}

		@Override
		public boolean canInsert(ItemStack stack) {
			return BulkyShulkies.getInstance().canInsertItem(stack);
		}

		@Override
		public void markDirty() {
			super.markDirty();
			AbstractInventoryShulkerBoxBlockEntity.this.markDirty();
		}

		@Override
		public int[] getAvailableSlots(Direction side) {
			return AbstractInventoryShulkerBoxBlockEntity.this.availibleSlots;
		}

		@Override
		public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
			return BulkyShulkies.getInstance().canInsertItem(stack);
		}

		@Override
		public boolean canExtract(int slot, ItemStack stack, Direction dir) {
			return true;
		}
	}
}
