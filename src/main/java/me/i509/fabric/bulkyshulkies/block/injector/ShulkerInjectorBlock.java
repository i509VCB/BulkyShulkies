package me.i509.fabric.bulkyshulkies.block.injector;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.EntityContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class ShulkerInjectorBlock extends BlockWithEntity {
	public static final VoxelShape BOTTOM = VoxelShapes.fullCube();
	public static final VoxelShape TOP = VoxelShapes.cuboid(VoxelShapes.fullCube().getBoundingBox().shrink(0, 0.25, 0));

	public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

	public ShulkerInjectorBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getStateManager().getDefaultState().with(HALF, DoubleBlockHalf.LOWER));
	}

	@Override
	public BlockEntity createBlockEntity(BlockView view) {
		return null;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos) {
		if (state.getBlock() instanceof ShulkerInjectorBlock) {
			switch (state.get(HALF)) {
			case UPPER:
				return TOP;
			case LOWER:
				return BOTTOM;
			}
		}

		return VoxelShapes.fullCube();
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(HALF);
	}
}
