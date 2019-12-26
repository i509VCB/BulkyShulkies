package me.i509.fabric.bulkyshulkies.mixin.client;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.bulkyshulkies.api.block.base.AbstractShulkerBoxBlock;
import me.i509.fabric.bulkyshulkies.client.BulkyShulkiesClientMod;

@Environment(EnvType.CLIENT)
@Mixin(BuiltinModelItemRenderer.class)
public class BuiltinModelItemRendererMixin {
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/item/BlockItem;getBlock()Lnet/minecraft/block/Block;", shift = At.Shift.AFTER), method = "render")
	private void bulkyshulkies_onRender(ItemStack stack, MatrixStack matrix, VertexConsumerProvider vertexConsumerProvider, int light, int overlay, CallbackInfo ci) {
		Block block = ((BlockItem) stack.getItem()).getBlock();

		if (block instanceof AbstractShulkerBoxBlock) {
			@Nullable DyeColor color = ((AbstractShulkerBoxBlock) block).getColor();

			BlockEntity blockEntity = BulkyShulkiesClientMod.getRenderBlockEntity((AbstractShulkerBoxBlock) block, color);
			BlockEntityRenderDispatcher.INSTANCE.renderEntity(blockEntity, matrix, vertexConsumerProvider, light, overlay);
		}
	}
}
