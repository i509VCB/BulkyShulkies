package me.i509.fabric.cursedshulkerboxes.api.block.base;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a shulker box block entity and exposes some information about the blockentity's state.
 * <p>It is recommended to use {@link AbstractCursedShulkerBoxBlockEntity} instead, which will handle most of the boilerplate from container based storage blocks.
 */
public interface BaseShulkerBlockEntity {
    /**
     * Gets the bounding box of the BlockEntity.
     * @param blockState The blockState of the BlockEntity.
     * @return The bounding box of the block entity.
     */
    Box getBoundingBox(BlockState blockState);

    /**
     * Gets the current animation stage of the shulker box.
     * @return The current {@link net.minecraft.block.entity.ShulkerBoxBlockEntity.AnimationStage}
     */
    ShulkerBoxBlockEntity.AnimationStage getAnimationStage();

    /**
     * Gets the color of the shulker box.
     * @return The color of the box, or null if the box is not colored.
     */
    @Nullable
    DyeColor getColor();

    /**
     * Gets the current lerped progress of the animation.
     * @param currentProgress The raw currentProgress number.
     * @return The current lerped progress.
     */
    float getAnimationProgress(float currentProgress);

    /**
     * Checks if an item can be inserted into an item slot.
     * @param inventorySlot The inventory slot the item is being placed at.
     * @param stack The itemstack that may be added to inventory.
     * @param direction The facing direction of the BlockEntity.
     * @return If true, the item will enter the inventory, otherwise the item will not enter the inventory.
     */
    boolean canInsertInvStack(int inventorySlot, ItemStack stack, @Nullable Direction direction);
}
