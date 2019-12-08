package me.i509.fabric.bulkyshulkies.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.AbstractContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.container.GenericContainer11x7;

public class Generic11x7Screen extends AbstractContainerScreen<GenericContainer11x7> {
	private static final Identifier BASE_TEXTURE = BulkyShulkies.id("textures/gui/container/generic_7x11.png");

	public Generic11x7Screen(GenericContainer11x7 container, PlayerInventory playerInventory, Text name) {
		super(container, playerInventory, name);
		containerHeight = 90 + (7 * 18); // 96
		containerWidth += 28;
	}

	public static AbstractContainerScreen<GenericContainer11x7> createScreen(GenericContainer11x7 container) {
		return new Generic11x7Screen(container, MinecraftClient.getInstance().player.inventory, container.getDisplayName());
	}

	@Override
	public void render(int mouseX, int mouseY, float lastFrameDuration) {
		renderBackground();
		drawBackground(lastFrameDuration, mouseX, mouseY);
		super.render(mouseX, mouseY, lastFrameDuration);
		drawMouseoverTooltip(mouseX, mouseY);
	}

	@Override
	protected void drawBackground(float delta, int mouseX, int mouseY) {
		RenderSystem.color4f(1, 1, 1, 1);
		minecraft.getTextureManager().bindTexture(BASE_TEXTURE);
		int x = (width - containerWidth) / 2;
		int y = (height - containerHeight) / 2 - 18;
		blit(x, y, 0, 0, containerWidth, 7 * 18 + 17);
		blit(x, y + 7 * 18 + 17, 0, 126, containerWidth, 78); // 96
	}
}
