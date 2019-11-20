package me.i509.fabric.cursedshulkerboxes.api.item;

import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.util.PacketByteBuf;

public class HelmetTrackedDataStage implements TrackedDataHandler<ShulkerBoxBlockEntity.AnimationStage> {
    private HelmetTrackedDataStage() {}

    public static final TrackedDataHandler<ShulkerBoxBlockEntity.AnimationStage> INSTANCE = new HelmetTrackedDataStage();

    @Override
    public void write(PacketByteBuf packetByteBuf, ShulkerBoxBlockEntity.AnimationStage animationStage) {
        packetByteBuf.writeInt(animationStage.ordinal());
    }

    @Override
    public ShulkerBoxBlockEntity.AnimationStage read(PacketByteBuf packetByteBuf) {
        return ShulkerBoxBlockEntity.AnimationStage.values()[packetByteBuf.readInt()];
    }

    @Override
    public ShulkerBoxBlockEntity.AnimationStage copy(ShulkerBoxBlockEntity.AnimationStage animationStage) {
        switch (animationStage) {
            case CLOSED:
                return ShulkerBoxBlockEntity.AnimationStage.CLOSED;
            case OPENING:
                return ShulkerBoxBlockEntity.AnimationStage.OPENING;
            case OPENED:
                return ShulkerBoxBlockEntity.AnimationStage.OPENED;
            case CLOSING:
                return ShulkerBoxBlockEntity.AnimationStage.CLOSING;
            default:
                throw new IllegalStateException("Tried to copy invalid Shulker AnimationStage.");
        }
    }
}
