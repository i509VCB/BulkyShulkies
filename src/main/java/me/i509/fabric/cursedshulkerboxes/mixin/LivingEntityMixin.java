package me.i509.fabric.cursedshulkerboxes.mixin;

import me.i509.fabric.cursedshulkerboxes.api.item.HelmetTrackedDataStage;
import me.i509.fabric.cursedshulkerboxes.api.item.ShulkerHelmetStage;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements ShulkerHelmetStage {
    private static final TrackedData<ShulkerBoxBlockEntity.AnimationStage> SHULKER_HELMET_STAGE = DataTracker.registerData(LivingEntity.class, HelmetTrackedDataStage.INSTANCE);

    @Inject(at = @At("TAIL"), method = "initDataTracker")
    public void onInitDataTrackers(CallbackInfo ci) { // For some reason dataTracker field and method are inaccessible in LivingEntity and any subclass of entity, so I have to do some casting hacks.
        getThis().getDataTracker().startTracking(SHULKER_HELMET_STAGE, ShulkerBoxBlockEntity.AnimationStage.CLOSED);
    }

    private LivingEntity getThis() {
        return (LivingEntity) (Object) this;
    }

    @Override
    public void setStage(ShulkerBoxBlockEntity.AnimationStage stage) {
        getThis().getDataTracker().set(SHULKER_HELMET_STAGE, stage);
    }

    @Override
    public ShulkerBoxBlockEntity.AnimationStage getStage() {
        return getThis().getDataTracker().get(SHULKER_HELMET_STAGE);
    }
}
