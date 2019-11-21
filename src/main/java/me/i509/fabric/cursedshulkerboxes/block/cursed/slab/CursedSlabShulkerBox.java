package me.i509.fabric.cursedshulkerboxes.block.cursed.slab;

import me.i509.fabric.cursedshulkerboxes.api.block.slab.AbstractCursedShulkerSlabBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class CursedSlabShulkerBox extends AbstractCursedShulkerSlabBlock {
    public CursedSlabShulkerBox(Settings settings, @Nullable DyeColor color) {
        super(settings, 18, color);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new CursedSlabShulkerBoxBE(this.getColor());
    }

    @Override
    public ItemStack getItemStack(@Nullable DyeColor color) {
        return null;
    }
}
