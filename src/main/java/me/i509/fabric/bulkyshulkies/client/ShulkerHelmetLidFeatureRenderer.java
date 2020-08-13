package me.i509.fabric.bulkyshulkies.client;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PiglinEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.Identifier;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.client.model.AbstractShulkerLidHelmetModel;
import me.i509.fabric.bulkyshulkies.client.model.HumanoidShulkerLidHelmetModel;
import me.i509.fabric.bulkyshulkies.client.model.PiglinShulkerLidHelmetModel;
import me.i509.fabric.bulkyshulkies.registry.ShulkerItems;

public class ShulkerHelmetLidFeatureRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
	private static final Identifier TEXTURE = BulkyShulkies.id("textures/armor/shulker_helmet_lid.png");
	private final AbstractShulkerLidHelmetModel<T> helmetLidModel;

	public ShulkerHelmetLidFeatureRenderer(FeatureRendererContext<T, M> context) {
		super(context);

		if (context instanceof PiglinEntityRenderer) {
			this.helmetLidModel = new PiglinShulkerLidHelmetModel<>(context);
		} else {
			this.helmetLidModel = new HumanoidShulkerLidHelmetModel<>(context);
		}
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		if (entity.getEquippedStack(EquipmentSlot.HEAD).getItem() == ShulkerItems.SHULKER_HELMET) {
			matrices.push();

			if (entity instanceof ArmorStandEntity) {
				matrices.translate(0.0D, 0.065D, 0.0D);
			}

			this.getContextModel().copyStateTo(this.helmetLidModel);
			this.helmetLidModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
			// TODO: Use Armor vertex consumer
			this.helmetLidModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(ShulkerHelmetLidFeatureRenderer.TEXTURE)), light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
			matrices.pop();
		}
	}
}
