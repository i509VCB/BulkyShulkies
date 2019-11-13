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

package me.i509.fabric.cursedshulkerboxes.api.block.material;

import me.i509.fabric.cursedshulkerboxes.api.block.AbstractCursedShulkerBoxBlock;
import me.i509.fabric.cursedshulkerboxes.api.block.AbstractCursedShulkerBoxBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShapes;
import org.jetbrains.annotations.Nullable;

public class AbstractMaterialBasedShulkerBoxBlockEntity extends AbstractCursedShulkerBoxBlockEntity {
    protected AbstractMaterialBasedShulkerBoxBlockEntity(BlockEntityType<?> blockEntityType, int maxAvailableSlot, @Nullable DyeColor color) {
        super(blockEntityType, maxAvailableSlot, color);
        this.inventory = DefaultedList.ofSize(this.AVAILABLE_SLOTS.length, ItemStack.EMPTY);
    }

    protected AbstractMaterialBasedShulkerBoxBlockEntity(BlockEntityType<?> blockEntityType, int maxAvailableSlot) {
        this(blockEntityType, maxAvailableSlot, null);
    }

    @Override
    public Box getBoundingBox(BlockState blockState) {
        return this.getBoundingBox(blockState.get(AbstractCursedShulkerBoxBlock.FACING));
    }

    @Override
    public Box getBoundingBox(Direction direction) {
        float lerpedProgress = this.getAnimationProgress(1.0F);
        return VoxelShapes.fullCube().getBoundingBox().stretch(0.5F * lerpedProgress * direction.getOffsetX(), 0.5F * lerpedProgress * direction.getOffsetY(), 0.5F * lerpedProgress * direction.getOffsetZ());
    }

    @Override
    public Box getCollisionBox(Direction direction) {
        Direction opposite = direction.getOpposite();
        return this.getBoundingBox(direction).shrink(opposite.getOffsetX(), opposite.getOffsetY(), opposite.getOffsetZ());
    }
}
