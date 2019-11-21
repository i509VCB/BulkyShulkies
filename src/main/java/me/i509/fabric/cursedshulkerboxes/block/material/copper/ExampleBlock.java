package me.i509.fabric.cursedshulkerboxes.block.material.copper;

import me.i509.fabric.cursedshulkerboxes.api.block.base.BaseShulkerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

import static me.i509.fabric.cursedshulkerboxes.api.block.base.BaseShulkerBlock.FACING;

public class ExampleBlock extends Block {
    public ExampleBlock(Settings settings) {
        super(settings);
        this.setDefaultState(getStateFactory().getDefaultState().with(FACING, Direction.UP));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext ePos) {
        Direction direction = state.get(FACING);
        return VoxelShapes.cuboid(VoxelShapes.fullCube()
                .getBoundingBox()
                .stretch(0.5F * direction.getOffsetX(), 0.5F * direction.getOffsetY(), 0.5F * direction.getOffsetZ()));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext placementContext) {
        return this.getDefaultState().with(FACING, placementContext.getSide());
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }
}
