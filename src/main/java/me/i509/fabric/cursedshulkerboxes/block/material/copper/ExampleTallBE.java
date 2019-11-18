package me.i509.fabric.cursedshulkerboxes.block.material.copper;

import me.i509.fabric.cursedshulkerboxes.api.block.multi.AbstractCursedShulkerBoxMultiBlockEntity;
import me.i509.fabric.cursedshulkerboxes.registry.ShulkerBlockEntities;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

public class ExampleTallBE extends AbstractCursedShulkerBoxMultiBlockEntity {
    public ExampleTallBE(@Nullable DyeColor color) {
        super(ShulkerBlockEntities.EXAMPLE_TALL, 54, color);
    }

    public ExampleTallBE() {
        this(null);
    }
}
