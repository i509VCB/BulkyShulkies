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
