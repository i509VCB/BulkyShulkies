/*
 * MIT License
 *
 * Copyright (c) 2019, 2020 i509VCB
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

package me.i509.bulkyshulkies.mod.block.entity;

import java.util.stream.IntStream;

import me.i509.bulkyshulkies.mod.BulkyShulkiesImpl;
import me.i509.bulkyshulkies.api.ShulkerBoxType;
import me.i509.bulkyshulkies.api.block.old.entity.inventory.LockableAndLootableBlockEntityWithInventory;
import me.i509.bulkyshulkies.api.inventory.ForwardingSidedInventory;
import org.jetbrains.annotations.Nullable;

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
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import net.fabricmc.fabric.api.util.NbtType;

public class InventoryShulkerBoxBlockEntity extends AbstractShulkerBoxBlockEntity implements LockableAndLootableBlockEntityWithInventory, ForwardingSidedInventory, NamedScreenHandlerFactory {
	private final Text defaultName;
	private final int[] availibleSlots;
	private SidedInventory inventory;
	private ContainerLock lock = ContainerLock.EMPTY;
	@Nullable
	private Identifier lootTableId = null;
	private long lootTableSeed = 0;
	@Nullable
	private Text customName = null;

	public InventoryShulkerBoxBlockEntity(ShulkerBoxType shulkerBoxType, Text defaultName, int slots, BlockEntityType<?> blockEntityType, BlockPos pos, BlockState state) {
		super(shulkerBoxType, blockEntityType, pos, state);
		this.defaultName = defaultName;
		this.availibleSlots = IntStream.range(0, this.size()).toArray();
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

	// Inventory stuff

	@Override
	public SidedInventory getInventory() {
		return this.inventory;
	}

	@Override
	public ContainerLock getLock() {
		return this.lock;
	}

	@Override
	public void setLock(ContainerLock lock) {
		this.lock = lock;
	}

	// Loot

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
	public void checkLootInteraction(@Nullable PlayerEntity player) {
		if (this.lootTableId != null && this.getWorld() instanceof ServerWorld) {
			final ServerWorld serverWorld = (ServerWorld) this.getWorld();
			final LootTable lootTable = serverWorld.getServer().getLootManager().getTable(this.lootTableId);

			if (player instanceof ServerPlayerEntity) {
				Criteria.PLAYER_GENERATES_CONTAINER_LOOT.test((ServerPlayerEntity) player, this.lootTableId);
			}

			this.lootTableId = null;
			final LootContext.Builder builder = new LootContext.Builder(serverWorld)
					.parameter(LootContextParameters.ORIGIN, Vec3d.ofCenter(this.pos))
					.random(this.lootTableSeed);

			if (player != null) {
				builder.luck(player.getLuck()).parameter(LootContextParameters.THIS_ENTITY, player);
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

	@Nullable
	@Override
	public Text getCustomName() {
		return this.customName;
	}

	public void setCustomName(@Nullable Text customName) {
		this.customName = customName;
	}

	protected Text getDefaultName() {
		return this.defaultName;
	}

	// NamedScreenHandlerFactory

	@Nullable
	public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
		return this.checkUnlocked(player) ? this.createScreenHandler(syncId, playerInventory) : null;
	}

	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
		return null; // TODO: Implement
	}

	final class ShulkerInventory extends SimpleInventory implements SidedInventory {
		protected ShulkerInventory(int slots) {
			super(slots);
		}

		@Override
		public boolean canInsert(ItemStack stack) {
			return BulkyShulkiesImpl.getInstance().canInsertItem(stack);
		}

		@Override
		public void markDirty() {
			super.markDirty();
			InventoryShulkerBoxBlockEntity.this.markDirty();
		}

		@Override
		public int[] getAvailableSlots(Direction side) {
			return InventoryShulkerBoxBlockEntity.this.availibleSlots;
		}

		@Override
		public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
			return this.canInsert(stack);
		}

		@Override
		public boolean canExtract(int slot, ItemStack stack, Direction dir) {
			return true;
		}
	}
}
