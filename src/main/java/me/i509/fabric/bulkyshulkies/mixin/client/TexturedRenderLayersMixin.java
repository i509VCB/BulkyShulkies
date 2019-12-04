package me.i509.fabric.bulkyshulkies.mixin.client;

import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.bulkyshulkies.client.BulkyShulkiesClientMod;

@Environment(EnvType.CLIENT)
@Mixin(TexturedRenderLayers.class)
public class TexturedRenderLayersMixin {
	@Inject(at = @At("HEAD"), method = "addDefaultTextures(Ljava/util/function/Consumer;)V")
	private static void onAddDefaultTextures(Consumer<SpriteIdentifier> consumer, CallbackInfo ci) {
		BulkyShulkiesClientMod.makeAtlases(consumer);
	}
}
