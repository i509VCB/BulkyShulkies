package me.i509.fabric.cursedshulkerboxes.block.material.copper;

import me.i509.fabric.cursedshulkerboxes.api.block.multi.AbstractCursedShulkerBoxMultiBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ExampleTallBox extends AbstractCursedShulkerBoxMultiBlock {
    public ExampleTallBox(Settings settings, @Nullable DyeColor color) {
        super(settings, 54, color);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new ExampleTallBE(getColor());
    }

    @Override
    public ItemStack getItemStack(@Nullable DyeColor color) {
        return null;
    }

    @Override
    protected Box getOpenBox(Direction facing, World world, BlockPos blockPos) {
        if(world.getBlockState(blockPos).get(HALF) == DoubleBlockHalf.LOWER) {
            return VoxelShapes.fullCube().getBoundingBox();
        }

        return VoxelShapes.fullCube().getBoundingBox().stretch(0.75F * facing.getOffsetX(), 0.75F * facing.getOffsetY(), 0.75F * facing.getOffsetZ()).shrink(facing.getOffsetX(), facing.getOffsetY(), facing.getOffsetZ());
    }
}
