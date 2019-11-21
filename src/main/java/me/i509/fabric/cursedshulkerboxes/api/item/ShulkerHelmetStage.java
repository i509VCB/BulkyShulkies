package me.i509.fabric.cursedshulkerboxes.api.item;

import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.LivingEntity;

public interface ShulkerHelmetStage {
    void setStage(ShulkerBoxBlockEntity.AnimationStage stage);

    ShulkerBoxBlockEntity.AnimationStage getStage();

    static ShulkerHelmetStage getStageFromEntity(LivingEntity entity) {
        return (ShulkerHelmetStage) entity;
    }
}
