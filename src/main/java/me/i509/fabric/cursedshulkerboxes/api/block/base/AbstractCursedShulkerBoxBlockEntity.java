/*
 * MIT License
 *
 * Copyright (c) 2019 i509VCB
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

package me.i509.fabric.cursedshulkerboxes.api.block.base;

import me.i509.fabric.cursedshulkerboxes.CursedShulkerBox;
import me.i509.fabric.cursedshulkerboxes.container.ShulkerBoxScrollableContainer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.container.Container;
import net.minecraft.container.ShulkerBoxContainer;
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
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class AbstractCursedShulkerBoxBlockEntity extends LootableContainerBlockEntity implements Tickable, BaseShulkerBlockEntity {
    protected final int[] AVAILABLE_SLOTS;
    protected DefaultedList<ItemStack> inventory;
    private int viewerCount;
    protected ShulkerBoxBlockEntity.AnimationStage animationStage;
    protected float animationProgress;
    protected float prevAnimationProgress;
    private DyeColor cachedColor;
    private boolean cachedColorUpdateNeeded;

    protected AbstractCursedShulkerBoxBlockEntity(BlockEntityType<?> blockEntityType, int maxAvailableSlot, @Nullable DyeColor color) {
        super(blockEntityType);
        checkArgument(maxAvailableSlot > 0, "Maximum available slot cannot be less than 1");
        this.AVAILABLE_SLOTS = IntStream.range(0, maxAvailableSlot).toArray();
        this.animationStage = ShulkerBoxBlockEntity.AnimationStage.CLOSED;
        this.cachedColor = color;
    }

    public abstract Box getBoundingBox(BlockState blockState);

    public void tick() {
        this.updateAnimation();
        if (this.animationStage == ShulkerBoxBlockEntity.AnimationStage.OPENING || this.animationStage == ShulkerBoxBlockEntity.AnimationStage.CLOSING) {
            this.pushEntities();
        }

    }

    protected void updateAnimation() {
        this.prevAnimationProgress = this.animationProgress;
        switch(this.animationStage) {
            case CLOSED:
                this.animationProgress = 0.0F;
                break;
            case OPENING:
                this.animationProgress += 0.1F;
                if (this.animationProgress >= 1.0F) {
                    this.pushEntities();
                    this.animationStage = ShulkerBoxBlockEntity.AnimationStage.OPENED;
                    this.animationProgress = 1.0F;
                    this.updateNeighborStates();
                }
                break;
            case CLOSING:
                this.animationProgress -= 0.1F;
                if (this.animationProgress <= 0.0F) {
                    this.animationStage = ShulkerBoxBlockEntity.AnimationStage.CLOSED;
                    this.animationProgress = 0.0F;
                    this.updateNeighborStates();
                }
                break;
            case OPENED:
                this.animationProgress = 1.0F;
        }

    }

    public ShulkerBoxBlockEntity.AnimationStage getAnimationStage() {
        return this.animationStage;
    }

    private void pushEntities() {
        BlockState blockState = this.world.getBlockState(this.getPos());

        if (blockState.getBlock() instanceof AbstractCursedShulkerBoxBlock) {
            Direction facing = blockState.get(AbstractCursedShulkerBoxBlock.FACING);
            Box adjacentToLid = this.getBoundingBox(blockState).offset(this.pos);
            List<Entity> entities = this.world.getEntities((Entity)null, adjacentToLid);

            if (!entities.isEmpty()) {
                for(int entityListPos = 0; entityListPos < entities.size(); ++entityListPos) {
                    Entity entity = entities.get(entityListPos);
                    if (entity.getPistonBehavior() != PistonBehavior.IGNORE) {
                        double xOffset = 0.0D;
                        double yOffset = 0.0D;
                        double zOffset = 0.0D;
                        Box entityBoundingBox = entity.getBoundingBox();
                        switch(facing.getAxis()) {
                            case X:
                                if (facing.getDirection() == Direction.AxisDirection.POSITIVE) {
                                    xOffset = adjacentToLid.maxX - entityBoundingBox.minX;
                                } else {
                                    xOffset = entityBoundingBox.maxX - adjacentToLid.minX;
                                }

                                xOffset += 0.01D;
                                break;
                            case Y:
                                if (facing.getDirection() == Direction.AxisDirection.POSITIVE) {
                                    yOffset = adjacentToLid.maxY - entityBoundingBox.minY;
                                } else {
                                    yOffset = entityBoundingBox.maxY - adjacentToLid.minY;
                                }

                                yOffset += 0.01D;
                                break;
                            case Z:
                                if (facing.getDirection() == Direction.AxisDirection.POSITIVE) {
                                    zOffset = adjacentToLid.maxZ - entityBoundingBox.minZ;
                                } else {
                                    zOffset = entityBoundingBox.maxZ - adjacentToLid.minZ;
                                }

                                zOffset += 0.01D;
                        }

                        entity.move(MovementType.SHULKER_BOX, new Vec3d(xOffset * facing.getOffsetX(), yOffset * facing.getOffsetY(), zOffset * facing.getOffsetZ()));
                    }
                }

            }
        }
    }

    public int getInvSize() {
        return this.inventory.size();
    }

    public boolean onBlockAction(int value, int interactorCount) {
        if (value == 1) {
            this.viewerCount = interactorCount;
            if (interactorCount == 0) {
                this.animationStage = ShulkerBoxBlockEntity.AnimationStage.CLOSING;
                this.updateNeighborStates();
            }

            if (interactorCount == 1) {
                this.animationStage = ShulkerBoxBlockEntity.AnimationStage.OPENING;
                this.updateNeighborStates();
            }

            return true;
        } else {
            return super.onBlockAction(value, interactorCount);
        }
    }

    private void updateNeighborStates() {
        this.getCachedState().updateNeighborStates(this.getWorld(), this.getPos(), 3);
    }

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

    public void onInvClose(PlayerEntity playerEntity) {
        if (!playerEntity.isSpectator()) {
            --this.viewerCount;
            this.world.addBlockAction(this.pos, this.getCachedState().getBlock(), 1, this.viewerCount);
            if (this.viewerCount <= 0) {
                this.world.playSound(null, this.pos, SoundEvents.BLOCK_SHULKER_BOX_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
            }
        }

    }

    protected Text getContainerName() {
        return new TranslatableText("container.shulkerBox", new Object[0]);
    }

    public void fromTag(CompoundTag input) {
        super.fromTag(input);
        this.deserializeInventory(input);
    }

    public CompoundTag toTag(CompoundTag output) {
        super.toTag(output);
        return this.serializeInventory(output);
    }

    public void deserializeInventory(CompoundTag tag) {
        this.inventory = DefaultedList.ofSize(this.getInvSize(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(tag) && tag.containsKey("Items", 9)) {
            Inventories.fromTag(tag, this.inventory);
        }

    }

    public CompoundTag serializeInventory(CompoundTag output) {
        if (!this.serializeLootTable(output)) {
            Inventories.toTag(output, this.inventory, false);
        }

        return output;
    }

    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inventory;
    }

    protected void setInvStackList(DefaultedList<ItemStack> itemStackDefaultedList) {
        this.inventory = itemStackDefaultedList;
    }

    public boolean isInvEmpty() {
        Iterator<ItemStack> stackIterator = this.inventory.iterator(); // TODO This could be cleaner.

        ItemStack currentStack;
        do {
            if (!stackIterator.hasNext()) {
                return true;
            }

            currentStack = stackIterator.next();
        } while(currentStack.isEmpty());
        return false;
    }

    public int[] getInvAvailableSlots(Direction direction) {
        return AVAILABLE_SLOTS;
    }

    public boolean canInsertInvStack(int inventorySlot, ItemStack stack, @Nullable Direction direction) {
        return CursedShulkerBox.getInstance().canInsertItem(stack);
    }

    public float getAnimationProgress(float currentProgress) {
        return MathHelper.lerp(currentProgress, this.prevAnimationProgress, this.animationProgress);
    }

    @Environment(EnvType.CLIENT)
    public DyeColor getColor() {
        if (this.cachedColorUpdateNeeded) {
            this.cachedColor = ShulkerBoxBlock.getColor(this.getCachedState().getBlock());
            this.cachedColorUpdateNeeded = false;
        }

        return this.cachedColor;
    }
}
