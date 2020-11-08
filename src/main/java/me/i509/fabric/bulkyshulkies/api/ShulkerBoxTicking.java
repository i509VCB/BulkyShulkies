package me.i509.fabric.bulkyshulkies.api;

import java.util.List;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.api.block.entity.ShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.registry.ShulkerComponents;

import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public final class ShulkerBoxTicking {
	public static <B extends BlockEntity & ShulkerBoxBlockEntity> void simpleTick(World world, BlockPos pos, BlockState state, B blockEntity) {
	}

	public static <B extends BlockEntity & ShulkerBoxBlockEntity> void platinumServerTick(World world, BlockPos pos, BlockState state, B blockEntity) {
		simpleTick(world, pos, state, blockEntity);

		if (BulkyShulkies.getInstance().getConfig().shouldPlatinumUseMagnetism()) {
			if (!world.isClient()) {
				ShulkerComponents.MAGNETISM_COOLDOWN.maybeGet(blockEntity).ifPresent(component -> {
					if (component.getCooldownValue() >= BulkyShulkies.getInstance().getConfig().getMagnetismTickDelay()) {
						component.setCooldownValue(0);

						final int range = BulkyShulkies.getInstance().getConfig().getPlatinumMagnetMaxRange();
						final Direction facing = world.getBlockState(pos).get(FacingBlock.FACING);
						final Box box = new Box(0, 0, 0, range, range, range)
								.offset(pos)
								.offset(Magnetism.getDirectionalBoxOffset(facing));

						final List<ItemEntity> entities = world.getEntitiesByType(EntityType.ITEM, box, EntityPredicates.VALID_ENTITY);

						Magnetism.BEFORE_MAGNETISM_COLLECTION.invoker().beforeMagnetismCollection(entities, world, pos, blockEntity);

						// Attempt to insert the items into the box
						final Inventory inventory = HopperBlockEntity.getInventoryAt(world, pos);

						for (ItemEntity entity : entities) {
							final ItemStack stack = entity.getStack();

							if (inventory instanceof SidedInventory) {
								if (!((SidedInventory) inventory).canInsert(0, stack, Direction.UP)) { // We do this to make sure people don't try to do recursive shulker boxes.
									return;
								}
							}

							final ItemStack result = add(stack, inventory);
							entity.setStack(result);
						}
					}
				});
			}
		}
	}

	public static ItemStack add(ItemStack itemStack, Inventory inventory) {
		ItemStack copied = itemStack.copy();
		addToExistingSlot(copied, inventory);

		if (copied.isEmpty()) {
			return ItemStack.EMPTY;
		} else {
			addToNewSlot(copied, inventory);
			return copied.isEmpty() ? ItemStack.EMPTY : copied;
		}
	}

	private static void addToNewSlot(ItemStack stack, Inventory inventory) {
		for (int slot = 0; slot < inventory.size(); ++slot) {
			ItemStack itemStack = inventory.getStack(slot);

			if (itemStack.isEmpty()) {
				inventory.setStack(slot, stack.copy());
				stack.setCount(0);
				return;
			}
		}
	}

	private static void addToExistingSlot(ItemStack stack, Inventory inventory) {
		for (int slot = 0; slot < inventory.size(); ++slot) {
			ItemStack itemStack = inventory.getStack(slot);

			if (ItemStack.areItemsEqualIgnoreDamage(itemStack, stack)) {
				transfer(stack, itemStack, inventory);

				if (stack.isEmpty()) {
					return;
				}
			}
		}
	}

	private static void transfer(ItemStack source, ItemStack target, Inventory inventory) {
		int maximumStackSize = Math.min(inventory.getMaxCountPerStack(), target.getMaxCount());
		int remaining = Math.min(source.getCount(), maximumStackSize - target.getCount());

		if (remaining > 0) {
			target.increment(remaining);
			source.decrement(remaining);
			inventory.markDirty();
		}
	}

	public static <B extends BlockEntity & ShulkerBoxBlockEntity> void enderSlabTick(World world, BlockPos pos, BlockState state, B blockEntity) {
	}

	private ShulkerBoxTicking() {
	}
}
