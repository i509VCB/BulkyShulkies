/*
 * MIT License
 *
 * Copyright (c) 2019, 2020 i509VCB
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

package me.i509.bulkyshulkies.mod.mixin.core.entity;

import me.i509.bulkyshulkies.api.item.ShulkerHelmetItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

import me.i509.bulkyshulkies.api.item.HelmetTrackedDataStage;
import me.i509.bulkyshulkies.api.item.ShulkerHelmetStage;

@Mixin(LivingEntity.class)
abstract class LivingEntityMixin extends Entity implements ShulkerHelmetStage {
	// TODO Make these components
	private static final TrackedData<ShulkerBoxBlockEntity.AnimationStage> SHULKER_HELMET_STAGE = DataTracker.registerData(LivingEntity.class, HelmetTrackedDataStage.INSTANCE);
	private static final TrackedData<Float> SHULKER_HELMET_ANIMATION_PROGRESS = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.FLOAT);
	private static final TrackedData<Float> PREVIOUS_HELMET_ANIMATION_PROGRESS = DataTracker.registerData(LivingEntity.class, TrackedDataHandlerRegistry.FLOAT);

	@Shadow public abstract Iterable<ItemStack> getArmorItems();

	private LivingEntityMixin() {
		super(null, null);
	}

	// TODO: Convert to ModifyArg
	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/DamageUtil;getDamageLeft(FFF)F"), method = "applyArmorToDamage")
	public float onApplyArmorToDamage(float damageTaken, float armorPoints, float v) {
		float realArmorPoints = armorPoints; // This is the original armor points.

		for (ItemStack stack : this.getArmorItems()) {
			if (stack.getItem() instanceof ShulkerHelmetItem) {
				if (stack.getMaxDamage() - 1 <= stack.getDamage()) {
					realArmorPoints -= ((ArmorItem) stack.getItem()).getProtection(); // Here we remove the armor points logically supplied by the server.
				}
			}
		}

		return DamageUtil.getDamageLeft(damageTaken, realArmorPoints, v);
	}

	@Inject(at = @At("TAIL"), method = "initDataTracker()V")
	private void bulkyshulkies_initHelmetDataTrackers(CallbackInfo ci) {
		this.getDataTracker().startTracking(SHULKER_HELMET_STAGE, ShulkerBoxBlockEntity.AnimationStage.CLOSED);
		this.getDataTracker().startTracking(SHULKER_HELMET_ANIMATION_PROGRESS, 0.0F);
		this.getDataTracker().startTracking(PREVIOUS_HELMET_ANIMATION_PROGRESS, 0.0F);
	}

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;tickActiveItemStack()V"), method = "tick()V")
	private void bulkyshulkies$tickShulkerHelmet(CallbackInfo ci) {
		if (this.bulkyshulkies$getStage() != ShulkerBoxBlockEntity.AnimationStage.CLOSED || this.bulkyshulkies$getStage() != ShulkerBoxBlockEntity.AnimationStage.OPENED) {
			this.bulkyshulkies$updateAnimation();
		}
	}

	protected void bulkyshulkies$updateAnimation() {
		float animationProgress = this.bulkyshulkies$getAnimationProgress();
		this.bulkyshulkies$setPreviousAnimationProgress(this.bulkyshulkies$getAnimationProgress());

		switch (this.bulkyshulkies$getStage()) {
		case CLOSED:
			this.bulkyshulkies$setAnimationProgress(0.0F);
			break;
		case OPENING:
			animationProgress += 0.1F;

			if (animationProgress >= 1.0F) {
				this.bulkyshulkies$setStage(ShulkerBoxBlockEntity.AnimationStage.OPENED);
				animationProgress = 1.0F;
			}

			break;
		case CLOSING:
			animationProgress -= 0.1F;

			if (animationProgress <= 0.0F) {
				this.bulkyshulkies$setStage(ShulkerBoxBlockEntity.AnimationStage.CLOSED);
				animationProgress = 0.0F;
			}

			break;
		case OPENED:
			animationProgress = 1.0F;
		}

		this.bulkyshulkies$setAnimationProgress(animationProgress);
	}

	@Override
	public void bulkyshulkies$setStage(ShulkerBoxBlockEntity.AnimationStage stage) {
		this.getDataTracker().set(SHULKER_HELMET_STAGE, stage);
	}

	@Override
	public ShulkerBoxBlockEntity.AnimationStage bulkyshulkies$getStage() {
		return this.getDataTracker().get(SHULKER_HELMET_STAGE);
	}

	@Override
	public float bulkyshulkies$getAnimationProgress() {
		return this.getDataTracker().get(SHULKER_HELMET_ANIMATION_PROGRESS);
	}

	public float bulkyshulkies$getPreviousAnimationProgress() {
		return this.getDataTracker().get(PREVIOUS_HELMET_ANIMATION_PROGRESS);
	}

	public void bulkyshulkies$setAnimationProgress(float animationProgress) {
		this.getDataTracker().set(SHULKER_HELMET_ANIMATION_PROGRESS, animationProgress);
	}

	public void bulkyshulkies$setPreviousAnimationProgress(float animationProgress) {
		this.getDataTracker().set(PREVIOUS_HELMET_ANIMATION_PROGRESS, animationProgress);
	}
}
