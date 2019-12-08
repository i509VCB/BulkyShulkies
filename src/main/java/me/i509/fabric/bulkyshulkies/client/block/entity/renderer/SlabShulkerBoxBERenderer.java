package me.i509.fabric.bulkyshulkies.client.block.entity.renderer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.model.ShulkerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.ShulkerEntity;

import me.i509.fabric.bulkyshulkies.api.block.slab.AbstractCursedShulkerSlabBE;

public class SlabShulkerBoxBERenderer extends BlockEntityRenderer<AbstractCursedShulkerSlabBE> {
	private static final ShulkerEntityModel<ShulkerEntity> SHULKER_ENTITY_MODEL = new ShulkerEntityModel<>();

	public SlabShulkerBoxBERenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher) {
		super(blockEntityRenderDispatcher);
	}

	@Override
	public void render(AbstractCursedShulkerSlabBE blockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
		// TODO Renderer magic
	}
}
