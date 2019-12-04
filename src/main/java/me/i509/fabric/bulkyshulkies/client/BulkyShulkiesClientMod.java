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

package me.i509.fabric.bulkyshulkies.client;

import java.util.Optional;
import java.util.function.Consumer;

import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;

import me.i509.fabric.bulkyshulkies.BulkyShulkiesMod;
import me.i509.fabric.bulkyshulkies.TextureRegistry;
import me.i509.fabric.bulkyshulkies.client.block.entity.renderer.CopperShulkerBoxBlockEntityRenderer;
import me.i509.fabric.bulkyshulkies.client.block.entity.renderer.IronShulkerBoxBlockEntityRenderer;
import me.i509.fabric.bulkyshulkies.client.screen.ScrollableScreen;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlockEntities;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class BulkyShulkiesClientMod implements ClientModInitializer {
	public static final FabricKeyBinding OPEN_SHULKER_HELMET = FabricKeyBinding.Builder.create(BulkyShulkiesMod.id("open_helmet"), InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, "bulkyshulkies").build();

	@Override
	public void onInitializeClient() {
		KeyBindingRegistry.INSTANCE.register(OPEN_SHULKER_HELMET);
		BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.COPPER_SHULKER_BOX, CopperShulkerBoxBlockEntityRenderer::new);
		//BlockEntityRendererRegistry.INSTANCE.register(ShulkerBlockEntities.IRON_SHULKER_BOX, IronShulkerBoxBlockEntityRenderer::new);

		ScreenProviderRegistry.INSTANCE.registerFactory(BulkyShulkiesMod.id("shulkerscrollcontainer"), ScrollableScreen::createScreen);

		ClientSpriteRegistryCallback.event(BulkyShulkiesMod.id("textures/atlas/shulker_boxes.png")).register((atlas, registry) -> {
			registry.register(ShulkerRenderLayers.Copper.UNCOLORED.getTextureId());
			registry.register(ShulkerRenderLayers.Copper.WHITE.getTextureId());
			registry.register(ShulkerRenderLayers.Copper.ORANGE.getTextureId());
			registry.register(ShulkerRenderLayers.Copper.MAGENTA.getTextureId());
			registry.register(ShulkerRenderLayers.Copper.LIGHT_BLUE.getTextureId());
			registry.register(ShulkerRenderLayers.Copper.YELLOW.getTextureId());
			registry.register(ShulkerRenderLayers.Copper.LIME.getTextureId());
			registry.register(ShulkerRenderLayers.Copper.PINK.getTextureId());
			registry.register(ShulkerRenderLayers.Copper.GRAY.getTextureId());
			registry.register(ShulkerRenderLayers.Copper.LIGHT_GRAY.getTextureId());
			registry.register(ShulkerRenderLayers.Copper.CYAN.getTextureId());
			registry.register(ShulkerRenderLayers.Copper.PURPLE.getTextureId());
			registry.register(ShulkerRenderLayers.Copper.BLUE.getTextureId());
			registry.register(ShulkerRenderLayers.Copper.BROWN.getTextureId());
			registry.register(ShulkerRenderLayers.Copper.GREEN.getTextureId());
			registry.register(ShulkerRenderLayers.Copper.RED.getTextureId());
			registry.register(ShulkerRenderLayers.Copper.BLACK.getTextureId());
		});

		ClientSpriteRegistryCallback.event(new Identifier("textures/atlas/shulker_boxes.png")).register((atlas, registry) -> {
			Optional<TextureRegistry.ShulkerTextureData> data = TextureRegistry.MODELED.getOrEmpty(TextureRegistry.TextureKeys.IRON);
			data.ifPresent((d) -> {
				registry.register(d.getTexture(null));

				for (DyeColor value : DyeColor.values()) {
					registry.register(d.getTexture(value));
				}
			});
		});

		ClientSpriteRegistryCallback.event(BulkyShulkiesMod.id("textures/atlas/silver_shulkerboxes.png")).register((atlas, registry) -> {
			Optional<TextureRegistry.ShulkerTextureData> data = TextureRegistry.MODELED.getOrEmpty(TextureRegistry.TextureKeys.SILVER);

			data.ifPresent((d) -> {
				registry.register(d.getTexture(null));

				for (DyeColor value : DyeColor.values()) {
					registry.register(d.getTexture(value));
				}
			});
		});

		ClientSpriteRegistryCallback.event(BulkyShulkiesMod.id("textures/atlas/gold_shulkerboxes.png")).register((atlas, registry) -> {
			Optional<TextureRegistry.ShulkerTextureData> data = TextureRegistry.MODELED.getOrEmpty(TextureRegistry.TextureKeys.GOLD);

			data.ifPresent((d) -> {
				registry.register(d.getTexture(null));

				for (DyeColor value : DyeColor.values()) {
					registry.register(d.getTexture(value));
				}
			});
		});
	}

	public static void makeAtlases(Consumer<SpriteIdentifier> consumer) {

		ShulkerRenderLayers.Copper.registerTextures(consumer);

		//consumer.accept(CopperShulkerBoxBlockEntityRenderer.UNCOLORED_IDENTIFIER);
		//CopperShulkerBoxBlockEntityRenderer.COLOR_TO_SPRITE_IDENTIFIER.values().forEach(consumer::accept);

		consumer.accept(IronShulkerBoxBlockEntityRenderer.UNCOLORED_IDENTIFIER);
		IronShulkerBoxBlockEntityRenderer.COLOR_TO_SPRITE_IDENTIFIER.values().forEach(consumer::accept);

		/*for (Identifier id : TextureRegistry.MODELED.getIds()) {
			CursedShulkerBox.getInstance().getLogger().info("Registering texture id: " + id.toString());

			if (id.getNamespace().equals(CursedShulkerBoxMod.MODID) && !id.getPath().equals("null")) {
				Optional<TextureRegistry.ShulkerTextureData> data = TextureRegistry.MODELED.getOrEmpty(id);
				data.ifPresent((d) -> {
					System.out.println("==============================================================================================================================================================IsPresent");
					//CursedShulkerBox.getInstance().getLogger().info(d.getTexture(null).toString());
					SpriteIdentifier uncoloredId = new SpriteIdentifier(CursedShulkerBoxMod.id("textures/atlas/" + d.getBaseName() + "_shulkerboxes.png"), d.getTexture(null));
					//CursedShulkerBox.getInstance().getLogger().info(uncoloredId.toString());
					consumer.accept(uncoloredId);

					for (DyeColor color : DyeColor.values()) {
						//CursedShulkerBox.getInstance().getLogger().info(d.getTexture(color).toString());
						SpriteIdentifier coloredId = new SpriteIdentifier(CursedShulkerBoxMod.id("textures/atlas/" + d.getBaseName() + "_shulkerboxes.png"), d.getTexture(color));
						//CursedShulkerBox.getInstance().getLogger().info(coloredId.toString());
						consumer.accept(coloredId);
					}
				});
			}
		}*/
	}
}
