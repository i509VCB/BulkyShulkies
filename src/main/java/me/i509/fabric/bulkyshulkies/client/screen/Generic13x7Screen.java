package me.i509.fabric.bulkyshulkies.client.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.AbstractContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

import me.i509.fabric.bulkyshulkies.container.GenericContainer13x7;

public class Generic13x7Screen extends AbstractContainerScreen<GenericContainer13x7> {
	public Generic13x7Screen(GenericContainer13x7 container, PlayerInventory playerInventory, Text name) {
		super(container, playerInventory, name);
	}

	@Override
	protected void drawBackground(float delta, int mouseX, int mouseY) {
		// TODO
	}

	public static AbstractContainerScreen<GenericContainer13x7> createScreen(GenericContainer13x7 container) {
		return new Generic13x7Screen(container, MinecraftClient.getInstance().player.inventory, container.getDisplayName());
	}
}
