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
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.screen.GenericScreenHandler11x7;

@Environment(EnvType.CLIENT)
public class Generic11x7Screen extends HandledScreen<GenericScreenHandler11x7> {
	private static final Identifier TEXTURE = BulkyShulkies.id("textures/gui/container/generic_11x7.png");

	public Generic11x7Screen(GenericScreenHandler11x7 handler, PlayerInventory playerInventory, Text name) {
		super(handler, playerInventory, name);
		this.backgroundHeight = 132 + 7 * 18;
		this.backgroundWidth += 36;
	}

	public static HandledScreen<GenericScreenHandler11x7> createScreen(GenericScreenHandler11x7 handler) {
		return new Generic11x7Screen(handler, MinecraftClient.getInstance().player.inventory, handler.getDisplayName());
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float tickDelta) {
		this.renderBackground(matrices);
		this.drawBackground(matrices, tickDelta, mouseX, mouseY);
		super.render(matrices, mouseX, mouseY, tickDelta);
		this.drawMouseoverTooltip(matrices, mouseX, mouseY);
	}

	@Override
	protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
		this.textRenderer.draw(matrices, this.title.asString(), 8.0F, 7.0F, 4210752);
		this.textRenderer.draw(matrices, this.playerInventory.getDisplayName().asString(), 8.0F, (float) (this.backgroundHeight - 114 + 2), 4210752);
	}

	@Override
	protected void drawBackground(MatrixStack matrices, float tickDelta, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.client.getTextureManager().bindTexture(TEXTURE);
		int i = (this.width - this.backgroundWidth) / 2;
		int j = (this.height - this.backgroundHeight) / 2;
		this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, 7 * 18 + 17);
		this.drawTexture(matrices, i, j + 7 * 18, 0, 126, this.backgroundWidth, 114);
	}
}
