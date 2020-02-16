package me.i509.fabric.bulkyshulkies.client.feature;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.client.model.AbstractShulkerLidHelmetModel;
import me.i509.fabric.bulkyshulkies.client.model.HumanoidShulkerLidHelmetModel;
import me.i509.fabric.bulkyshulkies.registry.ShulkerItems;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class HelmetLidFeatureRenderer extends FeatureRenderer<LivingEntity, BipedEntityModel<LivingEntity>> {
	public HelmetLidFeatureRenderer(FeatureRendererContext<LivingEntity, BipedEntityModel<LivingEntity>> context) {
		super(context);
	}

	private static final AbstractShulkerLidHelmetModel MODEL = new HumanoidShulkerLidHelmetModel();

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float customAngle, float headYaw, float headPitch) {
		if (entity.getEquippedStack(EquipmentSlot.HEAD).getItem() == ShulkerItems.SHULKER_HELMET) {
			VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getOutline(BulkyShulkies.id("yeet")));
			matrices.translate(0, 2, 0);
			MODEL.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}
