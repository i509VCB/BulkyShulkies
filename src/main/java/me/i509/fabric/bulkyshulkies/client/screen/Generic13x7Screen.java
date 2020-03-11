/*
 * MIT License
 *
 * Copyright (c) 2019-2020 i509VCB
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

package me.i509.fabric.bulkyshulkies.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.ScreenWithHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.Identifier;
import net.minecraft.text.Text;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.screen.GenericScreenHandler13x7;

@Environment(EnvType.CLIENT)
public class Generic13x7Screen extends ScreenWithHandler<GenericScreenHandler13x7> {
	private static final Identifier TEXTURE = BulkyShulkies.id("textures/gui/container/generic_13x7.png");

	public Generic13x7Screen(GenericScreenHandler13x7 handler, PlayerInventory playerInventory, Text name) {
		super(handler, playerInventory, name);
		// containerHeight = 90 + (7 * 18); // 96
		this.backgroundHeight = 132 + 7 * 18;
		backgroundWidth += 36 + 36;
	}

	public static ScreenWithHandler<GenericScreenHandler13x7> createScreen(GenericScreenHandler13x7 handler) {
		return new Generic13x7Screen(handler, MinecraftClient.getInstance().player.inventory, handler.getDisplayName());
	}

	@Override
	public void render(int mouseX, int mouseY, float lastFrameDuration) {
		renderBackground();
		drawBackground(lastFrameDuration, mouseX, mouseY);
		super.render(mouseX, mouseY, lastFrameDuration);
		drawMouseoverTooltip(mouseX, mouseY);
	}

	@Override
	protected void drawForeground(int mouseX, int mouseY) {
		this.textRenderer.draw(this.title.asFormattedString(), 8.0F, 7.0F, 4210752);
		this.textRenderer.draw(this.playerInventory.getDisplayName().asFormattedString(), 8.0F, (float) (this.backgroundHeight - 114 + 2), 4210752);
	}

	@Override
	protected void drawBackground(float delta, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.client.getTextureManager().bindTexture(TEXTURE);
		int i = (this.width - this.backgroundWidth) / 2;
		int j = (this.height - this.backgroundHeight) / 2;
		this.blit(i, j, 0, 0, this.backgroundWidth, 7 * 18 + 17);
		this.blit(i, j + 7 * 18, 0, 126, this.backgroundWidth, 114);
	}
}
