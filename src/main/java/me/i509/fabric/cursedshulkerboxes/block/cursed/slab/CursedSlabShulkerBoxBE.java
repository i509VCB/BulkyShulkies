package me.i509.fabric.cursedshulkerboxes.block.cursed.slab;

import me.i509.fabric.cursedshulkerboxes.api.block.slab.AbstractCursedShulkerSlabBlockEntity;
import me.i509.fabric.cursedshulkerboxes.registry.ShulkerBlockEntities;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

public class CursedSlabShulkerBoxBE extends AbstractCursedShulkerSlabBlockEntity {
    public CursedSlabShulkerBoxBE(@Nullable DyeColor color) {
        super(ShulkerBlockEntities.SLAB_SHULKER_BOX, 18, color);
    }

    public CursedSlabShulkerBoxBE() {
        this(null);
    }
}
