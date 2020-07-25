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

package me.i509.fabric.bulkyshulkies.api.block.colored;

import java.util.Iterator;
import java.util.List;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.stat.Stats;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.util.NbtType;

import me.i509.fabric.bulkyshulkies.api.block.AbstractShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.api.block.base.InventoryShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.api.block.entity.inventory.AbstractInventoryShulkerBoxBlockEntity;
import me.i509.fabric.bulkyshulkies.extension.ShulkerHooks;

public abstract class AbstractColoredInventoryShulkerBoxBlock extends AbstractShulkerBoxBlock implements ColoredShulkerBoxBlock, InventoryShulkerBoxBlock {
	protected static final Identifier CONTENTS = new Identifier("contents");

	protected final DyeColor color;
	protected final int slots;

	protected AbstractColoredInventoryShulkerBoxBlock(Settings settings, int slots, @Nullable DyeColor color) {
		super(settings);
		this.slots = slots;
		this.color = color;
	}

	@Nullable
	@Override
	public DyeColor getColor() {
		return this.color;
	}

	@Override
	public void onPlaced(World world, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
		if (itemStack.hasCustomName()) {
			BlockEntity blockEntity = world.getBlockEntity(blockPos);

			if (blockEntity instanceof AbstractInventoryShulkerBoxBlockEntity) {
				((AbstractInventoryShulkerBoxBlockEntity) blockEntity).setCustomName(itemStack.getName());
			}
		}
	}

	@Override
	public int getSlots() {
		return this.slots;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
		ItemStack pickStack = super.getPickStack(world, pos, state);
		AbstractInventoryShulkerBoxBlockEntity blockEntity = (AbstractInventoryShulkerBoxBlockEntity) world.getBlockEntity(pos);
		CompoundTag serializedInventory = blockEntity.inventoryToTag(new CompoundTag());

		if (!serializedInventory.isEmpty()) {
			pickStack.putSubTag("BlockEntityTag", serializedInventory);
		}

		return pickStack;
	}

	@Override
	public List<ItemStack> getDroppedStacks(BlockState state, net.minecraft.loot.context.LootContext.Builder builder) {
		BlockEntity blockEntity = builder.getNullable(LootContextParameters.BLOCK_ENTITY);

		if (blockEntity instanceof AbstractInventoryShulkerBoxBlockEntity) {
			AbstractInventoryShulkerBoxBlockEntity abstractShulkerBoxBlockEntity = (AbstractInventoryShulkerBoxBlockEntity) blockEntity;
			builder = builder.putDrop(CONTENTS, (lootContext, consumer) -> {
				for (int inventoryPos = 0; inventoryPos < abstractShulkerBoxBlockEntity.size(); ++inventoryPos) {
					consumer.accept(abstractShulkerBoxBlockEntity.getStack(inventoryPos));
				}
			});
		}

		return super.getDroppedStacks(state, builder);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockHitResult hitResult) {
		if (world.isClient) {
			return ActionResult.SUCCESS;
		} else if (playerEntity.isSpectator()) {
			return ActionResult.SUCCESS;
		} else {
			BlockEntity blockEntity = world.getBlockEntity(pos);

			if (blockEntity instanceof AbstractInventoryShulkerBoxBlockEntity) {
				Direction facing = Direction.UP;
				AbstractInventoryShulkerBoxBlockEntity cursedBlockEntity = (AbstractInventoryShulkerBoxBlockEntity) blockEntity;
				boolean shouldOpen;

				if (cursedBlockEntity.getAnimationStage() == ShulkerBoxBlockEntity.AnimationStage.CLOSED) {
					Box openBox = getLidCollisionBox(facing);
					shouldOpen = world.doesNotCollide(openBox.offset(pos.offset(facing)));
				} else {
					shouldOpen = true;
				}

				if (shouldOpen) {
					// TODO: Abstract this logic for opening screens
					if (cursedBlockEntity.checkUnlocked(playerEntity)) {
						cursedBlockEntity.checkLootInteraction(playerEntity);
						playerEntity.openHandledScreen(cursedBlockEntity);
						playerEntity.incrementStat(Stats.OPEN_SHULKER_BOX);
						// Replicate vanilla
						PiglinBrain.onGuardedBlockBroken(playerEntity, true);
					}
				}

				return ActionResult.SUCCESS;
			} else {
				return ActionResult.PASS;
			}
		}
	}

	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity playerEntity) {
		BlockEntity blockEntity = world.getBlockEntity(pos);

		if (blockEntity instanceof AbstractInventoryShulkerBoxBlockEntity) {
			AbstractInventoryShulkerBoxBlockEntity abstractShulkerBoxBlockEntity = (AbstractInventoryShulkerBoxBlockEntity) blockEntity;

			if (!world.isClient && playerEntity.isCreative() && !abstractShulkerBoxBlockEntity.isEmpty()) {
				ItemStack stack = getItemStack(this.getColor());
				CompoundTag serializedInventory = abstractShulkerBoxBlockEntity.inventoryToTag(new CompoundTag());

				if (!serializedInventory.isEmpty()) {
					stack.putSubTag("BlockEntityTag", serializedInventory);
				}

				if (abstractShulkerBoxBlockEntity.hasCustomName()) {
					stack.setCustomName(abstractShulkerBoxBlockEntity.getCustomName());
				}

				ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
				itemEntity.setToDefaultPickupDelay();
				world.spawnEntity(itemEntity);
			} else {
				abstractShulkerBoxBlockEntity.checkLootInteraction(playerEntity);
			}
		}

		super.onBreak(world, pos, state, playerEntity);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack itemStack, @Nullable BlockView world, List<Text> list, TooltipContext context) {
		super.appendTooltip(itemStack, world, list, context);

		if (ShulkerHooks.shulkerTooltipsArePresent()) {
			return; // Shulker Tooltips will handle the Tooltip itself if installed.
		}

		CompoundTag blockEntityTag = itemStack.getSubTag("BlockEntityTag");

		if (blockEntityTag != null) {
			if (blockEntityTag.contains("LootTable", NbtType.STRING)) {
				list.add(new LiteralText("???????"));
			}

			if (blockEntityTag.contains("Items", NbtType.LIST)) {
				DefaultedList<ItemStack> itemStacks = DefaultedList.ofSize(this.slots, ItemStack.EMPTY);
				Inventories.fromTag(blockEntityTag, itemStacks);
				int currentPosition = 0;
				int filledSlots = 0;
				Iterator<ItemStack> itemIterator = itemStacks.iterator();

				while (itemIterator.hasNext()) {
					ItemStack stack = itemIterator.next();

					if (!stack.isEmpty()) {
						++filledSlots;

						if (currentPosition <= 4) {
							++currentPosition;
							MutableText text = stack.getName().copy();
							text.append(" x").append(String.valueOf(stack.getCount()));
							list.add(text);
						}
					}
				}

				if (filledSlots - currentPosition > 0) {
					list.add((new TranslatableText("container.shulkerBox.more", filledSlots - currentPosition)).formatted(Formatting.ITALIC));
				}
			}
		}
	}
}
