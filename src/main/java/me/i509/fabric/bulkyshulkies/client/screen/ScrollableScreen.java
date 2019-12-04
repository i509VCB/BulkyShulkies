/*
 * MIT License
 *
 * Copyright (c) 2019 NinjaPhenix
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

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.AbstractContainerScreen;
import net.minecraft.client.gui.screen.ingame.ContainerProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

import me.i509.fabric.bulkyshulkies.BulkyShulkiesMod;
import me.i509.fabric.bulkyshulkies.client.screen.widget.SearchTextFieldWidget;
import me.i509.fabric.bulkyshulkies.container.ShulkerBoxScrollableContainer;

/**
 * Credit: NinjaPhenix, Code is MIT like rest of project.
 */
@Environment(EnvType.CLIENT)
public class ScrollableScreen extends AbstractContainerScreen<ShulkerBoxScrollableContainer> implements ContainerProvider<ShulkerBoxScrollableContainer> {
	private static final Identifier BASE_TEXTURE = new Identifier("textures/gui/container/generic_54.png");
	private static final Identifier WIDGETS_TEXTURE = BulkyShulkiesMod.id("textures/gui/container/widgets.png");
	private final int displayedRows;
	private final int totalRows;
	private int topRow;
	private double progress;
	private boolean dragging;
	private SearchTextFieldWidget searchBox;
	private String searchBoxOldText;

	public ScrollableScreen(ShulkerBoxScrollableContainer container, PlayerInventory playerInventory, Text containerTitle) {
		super(container, playerInventory, containerTitle);
		totalRows = container.getRows();
		topRow = 0;
		displayedRows = hasScrollbar() ? 6 : totalRows;
		if (hasScrollbar() && !FabricLoader.getInstance().isModLoaded("roughlyenoughitems")) containerWidth += 22;
		containerHeight = 114 + displayedRows * 18;
		progress = 0;
		container.setSearchTerm("");
		searchBoxOldText = "";
	}

	public static ScrollableScreen createScreen(ShulkerBoxScrollableContainer container) {
		return new ScrollableScreen(container, MinecraftClient.getInstance().player.inventory, container.getDisplayName());
	}

	@Override
	public void init() {
		super.init();
		searchBox = addButton(new SearchTextFieldWidget(font, this.x + 82, this.y + 127, 80, 8, ""));
		searchBox.setMaxLength(50);
		searchBox.setHasBorder(false);
		searchBox.setVisible(hasScrollbar());
		searchBox.setEditableColor(16777215);
		searchBox.setChangedListener(str -> {
			if (str.equals(searchBoxOldText)) return;
			container.setSearchTerm(str);
			progress = 0;
			topRow = 0;
			searchBoxOldText = str;
		});
	}

	@Override
	public void tick() {
		searchBox.tick();
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
		font.draw(title.asFormattedString(), 8, 6, 4210752);
		font.draw(playerInventory.getDisplayName().asFormattedString(), 8, containerHeight - 94, 4210752);
	}

	@Override
	protected void drawBackground(float lastFrameDuration, int mouseX, int mouseY) {
		RenderSystem.color4f(1, 1, 1, 1);
		minecraft.getTextureManager().bindTexture(BASE_TEXTURE);
		int x = (width - containerWidth) / 2;
		int y = (height - containerHeight) / 2;
		blit(x, y, 0, 0, containerWidth, displayedRows * 18 + 17);
		blit(x, y + displayedRows * 18 + 17, 0, 126, containerWidth, 96);

		if (hasScrollbar()) {
			minecraft.getTextureManager().bindTexture(WIDGETS_TEXTURE);
			blit(x + 172, y, 0, 0, 22, 132);
			blit(x + 174, (int) (y + 18 + 91 * progress), 22, 0, 12, 15);
			blit(x + 79, y + 126, 34, 0, 90, 11);
			searchBox.render(mouseX, mouseY, lastFrameDuration);
		}
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double scrollDelta) {
		if (hasScrollbar()) {
			setTopRow(topRow - (int) scrollDelta);
			progress = ((double) topRow) / ((double) (totalRows - 6));
			return true;
		}

		return false;
	}

	@Override
	protected boolean isClickOutsideBounds(double mouseX, double mouseY, int left, int top, int mouseButton) {
		boolean left_up_down = mouseX < left || mouseY < top || mouseY > top + height;
		boolean right = mouseX > left + width;
		if (hasScrollbar()) right = (right && mouseY > top + 132) || mouseX > left + width + 18;
		return left_up_down || right;
	}

	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
		if (!dragging) return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
		progress = MathHelper.clamp((mouseY - this.y - 25.5) / 90, 0, 1);
		setTopRow((int) (progress * (totalRows - 6)));
		return true;
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (searchBox.isFocused() && !searchBox.isMouseInBounds(mouseX, mouseY) && button == 0) {
			searchBox.changeFocus(true);
			this.setFocused(null);
		}

		if (button == 0 && this.x + 172 < mouseX && mouseX < this.x + 184 && this.y + 18 < mouseY && mouseY < this.y + 123) {
			dragging = true;
			return true;
		}

		return super.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		if (dragging && button == 0) dragging = false;
		return super.mouseReleased(mouseX, mouseY, button);
	}

	private void setTopRow(int row) {
		topRow = MathHelper.clamp(row, 0, totalRows - 6);
		container.updateSlotPositions(topRow, false);
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (keyCode == 256) {
			minecraft.player.closeContainer();
			return true;
		}

		if (!searchBox.isFocused()) {
			if (minecraft.options.keyChat.matchesKey(keyCode, scanCode)) {
				searchBox.changeFocus(true);
				this.setFocused(searchBox);
				searchBox.ignoreNextChar();
				return true;
			}

			return super.keyPressed(keyCode, scanCode, modifiers);
		}

		return searchBox.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	public boolean charTyped(char character, int int_1) {
		if (searchBox.isFocused()) return searchBox.charTyped(character, int_1);
		return super.charTyped(character, int_1);
	}

	@Override
	public void resize(MinecraftClient client, int width, int height) {
		String text = searchBox.getText();
		boolean focused = searchBox.isFocused();
		super.resize(client, width, height);
		searchBox.setText(text);

		if (focused) {
			searchBox.changeFocus(true);
			setFocused(searchBox);
		}
	}

	public int getTop() {
		return this.y;
	}

	public int getLeft() {
		return this.x;
	}

	public boolean hasScrollbar() {
		return totalRows > 6;
	}
}

