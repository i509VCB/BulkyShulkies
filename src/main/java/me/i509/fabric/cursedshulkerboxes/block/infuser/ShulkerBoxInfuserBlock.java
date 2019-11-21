package me.i509.fabric.cursedshulkerboxes.block.infuser;

import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.AbstractFurnaceContainer;
import net.minecraft.world.BlockView;

import javax.annotation.Nullable;

public class ShulkerBoxInfuserBlock extends BlockWithEntity {
    protected ShulkerBoxInfuserBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return null;
    }
}
