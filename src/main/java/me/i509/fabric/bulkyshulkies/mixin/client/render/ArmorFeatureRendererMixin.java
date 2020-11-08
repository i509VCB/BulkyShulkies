/*
 * MIT License
 *
 * Copyright (c) 2019-2020 i509VCB
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

package me.i509.fabric.bulkyshulkies.mixin.client.render;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.client.model.AbstractShulkerLidHelmetModel;

@Environment(EnvType.CLIENT)
@Mixin(ArmorFeatureRenderer.class)
abstract class ArmorFeatureRendererMixin<T extends LivingEntity, A extends BipedEntityModel<T>, M extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {
	@Shadow
	@Final
	protected A bodyModel;

	private AbstractShulkerLidHelmetModel bulkyshulkies$shulkerLidModel;

	public ArmorFeatureRendererMixin() {
		super(null);
	}

	/*
	@Inject(at = @At("TAIL"), method = "<init>")
	private void bulkyshulkies_initHelmetModel(CallbackInfo ci) {
		//noinspection ConstantConditions -- Mixin
		if ((Object) this instanceof PiglinBipedArmorFeatureRenderer) {
			this.bulkyshulkies$shulkerLidModel = new PiglinShulkerLidHelmetModel();
		} else {
			this.bulkyshulkies$shulkerLidModel = new HumanoidShulkerLidHelmetModel();
		}
	}

	@Inject(
					at = @At(
									value = "INVOKE",
									target = "Lnet/minecraft/client/render/entity/feature/ArmorFeatureRenderer;renderArmorParts(Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/item/ArmorItem;ZLnet/minecraft/client/render/entity/model/BipedEntityModel;ZFFFLjava/lang/String;)V",
									ordinal = 2
					),
					method = "renderArmor(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/entity/LivingEntity;FFFFFFLnet/minecraft/entity/EquipmentSlot;ILnet/minecraft/client/render/entity/model/BipedEntityModel;)V"
	)
	private void bulkyshulkies_renderShulkerLid(MatrixStack matrices, VertexConsumerProvider vertexConsumers, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float customAngle, float headYaw, float headPitch, EquipmentSlot slot, int light, A armorModel, CallbackInfo ci) {
		if (slot == EquipmentSlot.HEAD) {
			ItemStack itemStack = entity.getEquippedStack(slot);

			if (itemStack.getItem() instanceof ShulkerHelmetItem) {
				ShulkerHelmetStage stage = ShulkerHelmetStage.bulkyshulkies$getStageFromEntity(entity);
				this.bulkyshulkies$shulkerLidModel.lid.copyPositionAndRotation(this.getContextModel().head);
				VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(this.bulkyshulkies_getLidTexture()));
				this.bulkyshulkies$shulkerLidModel.setAngles(entity, limbAngle, limbDistance, customAngle, headYaw, headPitch);

				if (entity.isBaby()) { // Children need the little lids
					matrices.push();

					float scaleFactor;
					AnimalModelAccessor accessor = (AnimalModelAccessor) this.getContextModel();

					if (accessor.accessor$headScaled()) {
						scaleFactor = 1.5F / accessor.accessor$invertedChildHeadScale();
						matrices.scale(scaleFactor, scaleFactor, scaleFactor);
					}

					matrices.translate(0.0D, accessor.accessor$childHeadYOffset() / 16.0F, accessor.accessor$childHeadZOffset() / 16.0F);
					// TODO LINE
					//matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(270.0F * ArmorFeatureRendererMixin.getAnimationProgress(tickDelta, stage.bulkyshulkies$getPreviousAnimationProgress(), stage.bulkyshulkies$getAnimationProgress())));
					// TODO LINE
					this.bulkyshulkies$shulkerLidModel.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

					matrices.pop();
				} else {
					// ------
					matrices.push();

					// TODO LINE
					//matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(270.0F * ArmorFeatureRendererMixin.getAnimationProgress(tickDelta, stage.bulkyshulkies$getPreviousAnimationProgress(), stage.bulkyshulkies$getAnimationProgress())));
					// TODO LINE
					this.bulkyshulkies$shulkerLidModel.render(matrices, consumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);

					matrices.pop();
				}
			}
		}
	}

	@Inject(
					at = @At(
									value = "INVOKE",
									target = "Lnet/minecraft/client/render/entity/feature/ArmorFeatureRenderer;setVisible(Lnet/minecraft/client/render/entity/model/BipedEntityModel;Lnet/minecraft/entity/EquipmentSlot;)V",
									shift = At.Shift.AFTER
					),
					method = "renderArmor(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/entity/LivingEntity;FFFFFFLnet/minecraft/entity/EquipmentSlot;ILnet/minecraft/client/render/entity/model/BipedEntityModel;)V"
	)
	private void bulkyshulkies_animateShulkerLidRotation(MatrixStack matrices, VertexConsumerProvider vertexConsumers, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float customAngle, float headYaw, float headPitch, EquipmentSlot slot, int light, A armorModel, CallbackInfo ci) {
		this.bulkyshulkies$shulkerLidModel.animateModel(entity, limbAngle, limbDistance, tickDelta);
	}

	private static float getAnimationProgress(float tickDelta, float prevAnimationProgress, float animationProgress) {
		return MathHelper.lerp(tickDelta, prevAnimationProgress, animationProgress);
	}*/

	protected Identifier bulkyshulkies_getLidTexture() {
		return BulkyShulkies.id("shulker_helmet_lid");
	}
}
