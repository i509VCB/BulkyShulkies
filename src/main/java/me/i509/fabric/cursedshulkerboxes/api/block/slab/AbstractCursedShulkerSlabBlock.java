package me.i509.fabric.cursedshulkerboxes.api.block.slab;

import me.i509.fabric.cursedshulkerboxes.api.block.AbstractCursedShulkerBoxBlock;
import me.i509.fabric.cursedshulkerboxes.util.DefaultReturnHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.DyeColor;
import net.minecraft.util.SystemUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public abstract class AbstractCursedShulkerSlabBlock extends AbstractCursedShulkerBoxBlock {
    protected static final VoxelShape BOTTOM_SHAPE = /*Block.createCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D); //*/Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    protected static final VoxelShape TOP_SHAPE = Block.createCuboidShape(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D);
    protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D);

    private static final Map<Direction, VoxelShape> SHAPES = SystemUtil.consume(new DefaultReturnHashMap<>(BOTTOM_SHAPE), map -> {
        map.put(Direction.DOWN, TOP_SHAPE);
        map.put(Direction.UP, BOTTOM_SHAPE);
        map.put(Direction.WEST, WEST_SHAPE);
        map.put(Direction.EAST, EAST_SHAPE);
        map.put(Direction.NORTH, NORTH_SHAPE);
        map.put(Direction.SOUTH, SOUTH_SHAPE);
    });

    protected AbstractCursedShulkerSlabBlock(Settings settings, int slotCount, @Nullable DyeColor color) {
        super(settings, slotCount, color);
    }

    public static VoxelShape getShape(Direction facing) {
        switch (facing) {
            case DOWN:
                return AbstractCursedShulkerSlabBlock.TOP_SHAPE;
            case SOUTH:
                return AbstractCursedShulkerSlabBlock.SOUTH_SHAPE;
            case WEST:
                return AbstractCursedShulkerSlabBlock.WEST_SHAPE;
            case EAST:
                return AbstractCursedShulkerSlabBlock.EAST_SHAPE;
            case NORTH:
                return AbstractCursedShulkerSlabBlock.NORTH_SHAPE;
            case UP:
            default:
                return AbstractCursedShulkerSlabBlock.BOTTOM_SHAPE;
        }
    }


    @Override
    public VoxelShape getCollisionShape(BlockState blockState, BlockView blockView, BlockPos blockPos, EntityContext entityContext) {
        return getOutlineShape(blockState, blockView, blockPos, entityContext);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, EntityContext entityContext) {
        BlockEntity blockEntity = blockView.getBlockEntity(blockPos);
        return blockEntity instanceof AbstractCursedShulkerSlabBlockEntity ?
                VoxelShapes.cuboid(((AbstractCursedShulkerSlabBlockEntity) blockEntity).getBoundingBox(blockState))
                : AbstractCursedShulkerSlabBlock.getShape(blockState.get(AbstractCursedShulkerSlabBlock.FACING));
    }

    @Override
    public Box getOpenBox(Direction facing) {
        return getShape(facing).getBoundingBox().stretch(0.25F * facing.getOffsetX(), 0.25F * facing.getOffsetY(), 0.25F * facing.getOffsetZ()).shrink(facing.getOffsetX(), facing.getOffsetY(), facing.getOffsetZ());
    }
}
