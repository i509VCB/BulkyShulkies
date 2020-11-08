package me.i509.fabric.bulkyshulkies;

import me.i509.fabric.bulkyshulkies.api.ShulkerBoxBehavior;
import me.i509.fabric.bulkyshulkies.api.block.entity.ShulkerBoxBlockEntity;

import net.minecraft.block.BlockState;
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

public final class DefaultShulkerBoxBehavior implements ShulkerBoxBehavior {
	@Override
	public VoxelShape getShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return null;
	}

	@Override
	public Box getLidCollisionBox(Direction facing) {
		return null;
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockHitResult hitResult) {
		return null;
	}

	@Override
	public void updateAnimation(World world, ShulkerBoxBlockEntity blockEntity, DirectionProperty property) {

	}

	@Override
	public void pushEntities(World world, ShulkerBoxBlockEntity blockEntity, DirectionProperty property) {

	}
}
