package me.i509.fabric.bulkyshulkies.client.feature;

import me.i509.fabric.bulkyshulkies.registry.ShulkerItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class PiglinHelmetFeatureRenderer extends FeatureRenderer<LivingEntity, BipedEntityModel<LivingEntity>> {
	public PiglinHelmetFeatureRenderer(FeatureRendererContext<LivingEntity, BipedEntityModel<LivingEntity>> context) {
		super(context);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float customAngle, float headYaw, float headPitch) {
		if (entity.getEquippedStack(EquipmentSlot.HEAD).getItem() == ShulkerItems.SHULKER_HELMET) {

		}
	}
}
