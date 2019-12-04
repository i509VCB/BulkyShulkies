/*
 * MIT License
 *
 * Copyright (c) 2019 i509VCB
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.i509.fabric.bulkyshulkies.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;

import me.i509.fabric.bulkyshulkies.api.item.HelmetTrackedDataStage;
import me.i509.fabric.bulkyshulkies.api.item.ShulkerHelmetStage;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements ShulkerHelmetStage {
	private static final TrackedData<ShulkerBoxBlockEntity.AnimationStage> SHULKER_HELMET_STAGE = DataTracker.registerData(LivingEntity.class, HelmetTrackedDataStage.INSTANCE);
	private static final TrackedData<Float> SHULKER_HELMET_ANIMATION_PROGRESS = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.FLOAT);

	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(at = @At("TAIL"), method = "initDataTracker")
	private void onInitDataTrackers(CallbackInfo ci) { // For some reason dataTracker field and method are inaccessible in LivingEntity and any subclass of entity, so I have to do some casting hacks.
		getDataTracker().startTracking(SHULKER_HELMET_STAGE, ShulkerBoxBlockEntity.AnimationStage.CLOSED);
	}

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;tickActiveItemStack()V"), method = "tick()V")
	public void onTick(CallbackInfo ci) {
		// TODO Animation logic here
	}

	@Override
	public void setStage(ShulkerBoxBlockEntity.AnimationStage stage) {
		getDataTracker().set(SHULKER_HELMET_STAGE, stage);
	}

	@Override
	public ShulkerBoxBlockEntity.AnimationStage getStage() {
		return getDataTracker().get(SHULKER_HELMET_STAGE);
	}

	@Override
	public float getAnimationProgress() {
		return getDataTracker().get(SHULKER_HELMET_ANIMATION_PROGRESS);
	}
}
