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

package me.i509.fabric.bulkyshulkies.api.block.base;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.container.Container;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.api.block.FacingShulkerBoxBlock;

public abstract class AbstractShulkerBoxBE extends LootableContainerBlockEntity implements SidedInventory, Tickable, BasicShulkerBlockEntity {
	protected final int[] AVAILABLE_SLOTS;
	protected DefaultedList<ItemStack> inventory;
	private int viewerCount;
	protected AnimationStatus animationStage;
	protected float animationProgress;
	protected float prevAnimationProgress;
	private DyeColor cachedColor;
	private boolean cachedColorUpdateNeeded;

	protected AbstractShulkerBoxBE(BlockEntityType<?> blockEntityType, int maxAvailableSlot, @Nullable DyeColor color) {
		super(blockEntityType);
		checkArgument(maxAvailableSlot > 0, "Maximum available slots cannot be less than 1");
		this.AVAILABLE_SLOTS = IntStream.range(0, maxAvailableSlot).toArray();
		this.animationStage = AnimationStatus.CLOSED;
		this.cachedColor = color;
	}

	@Override
	public abstract VoxelShape getBoundingBox(BlockState blockState);

	@Override
	public abstract Box getLidCollisionBox(BlockState blockState);

	@Override
	public void tick() {
		this.updateAnimation();

		if (this.animationStage == AnimationStatus.OPENING || this.animationStage == AnimationStatus.CLOSING) {
			this.pushEntities();
		}
	}

	protected void updateAnimation() {
		this.prevAnimationProgress = this.animationProgress;
		switch (this.animationStage) {
		case CLOSED:
			this.animationProgress = 0.0F;
			break;
		case OPENING:
			this.animationProgress += 0.1F;

			if (this.animationProgress >= 1.0F) {
				this.pushEntities();
				this.animationStage = AnimationStatus.OPENED;
				this.animationProgress = 1.0F;
				this.updateNeighborStates();
			}

			break;
		case CLOSING:
			this.animationProgress -= 0.1F;

			if (this.animationProgress <= 0.0F) {
				this.animationStage = AnimationStatus.CLOSED;
				this.animationProgress = 0.0F;
				this.updateNeighborStates();
			}

			break;
		case OPENED:
			this.animationProgress = 1.0F;
		}
	}

	@Override
	public AnimationStatus getAnimationStage() {
		return this.animationStage;
	}

	protected void pushEntities() {
		BlockState blockState = this.world.getBlockState(this.getPos());

		if (blockState.getBlock() instanceof BasicShulkerBlock) {
			Direction direction = blockState.get(FacingShulkerBoxBlock.FACING);
			Box box = this.getLidCollisionBox(blockState).offset(this.pos);
			List<Entity> list = this.world.getEntities(null, box);

			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); ++i) {
					Entity entity = list.get(i);

					if (entity.getPistonBehavior() != PistonBehavior.IGNORE) {
						double d = 0.0D;
						double e = 0.0D;
						double f = 0.0D;
						Box box2 = entity.getBoundingBox();
						switch (direction.getAxis()) {
						case X:
							if (direction.getDirection() == Direction.AxisDirection.POSITIVE) {
								d = box.x2 - box2.x1;
							} else {
								d = box2.x2 - box.x1;
							}

							d += 0.01D;
							break;
						case Y:
							if (direction.getDirection() == Direction.AxisDirection.POSITIVE) {
								e = box.y2 - box2.y1;
							} else {
								e = box2.y2 - box.y1;
							}

							e += 0.01D;
							break;
						case Z:
							if (direction.getDirection() == Direction.AxisDirection.POSITIVE) {
								f = box.z2 - box2.z1;
							} else {
								f = box2.z2 - box.z1;
							}

							f += 0.01D;
						}

						entity.move(MovementType.SHULKER_BOX, new Vec3d(d * (double) direction.getOffsetX(), e * (double) direction.getOffsetY(), f * (double) direction.getOffsetZ()));
					}
				}
			}
		}
	}

	@Override
	public int getInvSize() {
		return this.inventory.size();
	}

	@Override
	public boolean onBlockAction(int value, int interactorCount) {
		if (value == 1) {
			this.viewerCount = interactorCount;

			if (interactorCount == 0) {
				this.animationStage = AnimationStatus.CLOSING;
				this.updateNeighborStates();
			}

			if (interactorCount == 1) {
				this.animationStage = AnimationStatus.OPENING;
				this.updateNeighborStates();
			}

			return true;
		} else {
			return super.onBlockAction(value, interactorCount);
		}
	}

	protected void updateNeighborStates() {
		this.getCachedState().updateNeighborStates(this.getWorld(), this.getPos(), 3);
	}

	@Override
	public void onInvOpen(PlayerEntity playerEntity) {
		if (!playerEntity.isSpectator()) {
			if (this.viewerCount < 0) {
				this.viewerCount = 0;
			}

			++this.viewerCount;
			this.world.addBlockAction(this.pos, this.getCachedState().getBlock(), 1, this.viewerCount);

			if (this.viewerCount == 1) {
				this.world.playSound(null, this.pos, SoundEvents.BLOCK_SHULKER_BOX_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
			}
		}
	}

	@Override
	public void onInvClose(PlayerEntity playerEntity) {
		if (!playerEntity.isSpectator()) {
			--this.viewerCount;
			this.world.addBlockAction(this.pos, this.getCachedState().getBlock(), 1, this.viewerCount);

			if (this.viewerCount <= 0) {
				this.world.playSound(null, this.pos, SoundEvents.BLOCK_SHULKER_BOX_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
			}
		}
	}

	@Override
	public void fromTag(CompoundTag input) {
		super.fromTag(input);
		this.deserializeInventory(input);
	}

	@Override
	public CompoundTag toTag(CompoundTag output) {
		super.toTag(output);
		return this.serializeInventory(output);
	}

	public void deserializeInventory(CompoundTag tag) {
		this.inventory = DefaultedList.ofSize(this.getInvSize(), ItemStack.EMPTY);

		if (!this.deserializeLootTable(tag) && tag.contains("Items", 9)) {
			Inventories.fromTag(tag, this.inventory);
		}
	}

	public CompoundTag serializeInventory(CompoundTag output) {
		if (!this.serializeLootTable(output)) {
			Inventories.toTag(output, this.inventory, false);
		}

		return output;
	}

	@Override
	protected DefaultedList<ItemStack> getInvStackList() {
		return this.inventory;
	}

	@Override
	protected void setInvStackList(DefaultedList<ItemStack> itemStackDefaultedList) {
		this.inventory = itemStackDefaultedList;
	}

	@Override
	public boolean isInvEmpty() {
		Iterator<ItemStack> stackIterator = this.inventory.iterator();

		ItemStack currentStack;

		do {
			if (!stackIterator.hasNext()) {
				return true;
			}

			currentStack = stackIterator.next();
		} while (currentStack.isEmpty());
		return false;
	}

	@Override
	public int[] getInvAvailableSlots(Direction direction) {
		return AVAILABLE_SLOTS;
	}

	@Override
	public boolean canExtractInvStack(int inventorySlot, ItemStack stack, Direction direction) {
		return true;
	}

	@Override
	public boolean canInsertInvStack(int inventorySlot, ItemStack stack, @Nullable Direction direction) {
		return BulkyShulkies.getInstance().canInsertItem(stack);
	}

	@Override
	public float getAnimationProgress(float currentProgress) {
		return MathHelper.lerp(currentProgress, this.prevAnimationProgress, this.animationProgress);
	}

	@Override
	public int getProgress() {
		return (int) (this.animationProgress * 10);
	}

	@Environment(EnvType.CLIENT)
	public DyeColor getColor() {
		if (this.cachedColorUpdateNeeded) {
			this.cachedColor = AbstractShulkerBoxBlock.getColor(this.getCachedState().getBlock());
			this.cachedColorUpdateNeeded = false;
		}

		return this.cachedColor;
	}

	@Override
	protected Container createContainer(int syncId, PlayerInventory playerInventory) {
		return null; // Our implementation does not require this method since the PropertyRetriever and Fabric-API handle the containers.
	}

	public static class DirectionalShapeContainer {
		private static final Direction[] HORIZONTAL = new Direction[] {
			Direction.NORTH,
			Direction.SOUTH,
			Direction.EAST,
			Direction.WEST
		};

		private static final Direction[] VERTICAL = new Direction[] {
			Direction.UP,
			Direction.DOWN
		};

		private final ForwardingMap<Direction, VoxelShape> shapes;

		public DirectionalShapeContainer(ImmutableMap<Direction, VoxelShape> built) {
			this.shapes = new ForwardingMap<Direction, VoxelShape>() {
				@Override
				protected Map<Direction, VoxelShape> delegate() {
					return built;
				}

				@Override
				public VoxelShape get(Object direction) {
					VoxelShape v = super.get(direction);

					if (v == null) {
						return VoxelShapes.fullCube(); // Fallback
					}

					return v;
				}
			};
		}

		public static DirectionalShapeContainer createAll(double animation, BiFunction<Double, Direction, VoxelShape> shapeFunction) {
			ImmutableMap.Builder<Direction, VoxelShape> builder = ImmutableMap.builder();

			for (Direction value : Direction.values()) {
				builder.put(value, shapeFunction.apply(animation, value));
			}

			return new DirectionalShapeContainer(builder.build());
		}

		public static DirectionalShapeContainer createHorizontal(double animation, BiFunction<Double, Direction, VoxelShape> shapeFunction) {
			ImmutableMap.Builder<Direction, VoxelShape> builder = ImmutableMap.builder();

			for (Direction value : DirectionalShapeContainer.HORIZONTAL) {
				builder.put(value, shapeFunction.apply(animation, value));
			}

			return new DirectionalShapeContainer(builder.build());
		}

		public VoxelShape get(Direction direction) {
			return this.shapes.get(direction);
		}
	}
}
