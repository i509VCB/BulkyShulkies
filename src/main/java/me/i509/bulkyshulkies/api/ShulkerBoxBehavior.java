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

package me.i509.bulkyshulkies.api;

import me.i509.bulkyshulkies.api.block.entity.ShulkerBoxBlockEntity;
import me.i509.bulkyshulkies.mod.DefaultShulkerBoxBehavior;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

/**
 * Defines how a shulker box behaves.
 *
 * <p>A shulker box's behavior defines what occurs when shulker box block is used, what the collision shape is, pushing
 * entities and updating the animation.
 *
 * @see ShulkerBoxBehavior#getDefault()
 */
public interface ShulkerBoxBehavior {
	/**
	 * Gets the default shulker box behavior.
	 *
	 * <p>This shulker box behavior is similar to how the {@link Blocks#SHULKER_BOX vanilla shulker boxes behave}.
	 *
	 * @return the default shulker box behavior.
	 */
	static ShulkerBoxBehavior getDefault() {
		return DefaultShulkerBoxBehavior.INSTANCE;
	}

	VoxelShape getShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context);

	Box getLidCollisionBox(Direction facing);

	ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity user, Hand hand, BlockHitResult hit);

	void updateAnimation(World world, ShulkerBoxBlockEntity blockEntity, DirectionProperty property);

	void pushEntities(World world, ShulkerBoxBlockEntity blockEntity, DirectionProperty property);
}
