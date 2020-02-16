package me.i509.fabric.bulkyshulkies.client.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public abstract class AbstractShulkerLidHelmetModel extends Model {
	protected final ModelPart lid;

	public AbstractShulkerLidHelmetModel(ModelPart lid) {
		super(RenderLayer::getEntityCutoutNoCull);
		this.lid = lid;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {

	}
}
