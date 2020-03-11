/*
 *
 * MIT No Attribution
 *
 * Copyright 2020 NinjaPhenix
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.i509.fabric.bulkyshulkies.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.ContainerScreen;
import net.minecraft.client.gui.screen.ingame.ContainerProvider;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.client.screen.widget.SearchTextFieldWidget;
import me.i509.fabric.bulkyshulkies.container.ScrollableContainer;

/**
 * Credit: NinjaPhenix.
 */
@Environment(EnvType.CLIENT)
public class ScrollableScreen extends ContainerScreen<ScrollableContainer> implements ContainerProvider<ScrollableContainer> {
	private static final Identifier BASE_TEXTURE = new Identifier("textures/gui/container/generic_54.png");
	private static final Identifier WIDGETS_TEXTURE = BulkyShulkies.id("textures/gui/container/widgets.png");
	private final int displayedRows;
	private final int totalRows;
	private int topRow;
	private double progress;
	private boolean dragging;
	private SearchTextFieldWidget searchBox;
	private String searchBoxOldText;

	public ScrollableScreen(ScrollableContainer container, PlayerInventory playerInventory, Text containerTitle) {
		super(container, playerInventory, containerTitle);
		this.totalRows = container.getRows();
		this.topRow = 0;
		this.displayedRows = this.hasScrollbar() ? 6 : this.totalRows;
		if (this.hasScrollbar() && !FabricLoader.getInstance().isModLoaded("roughlyenoughitems")) this.containerWidth += 22;
		this.containerHeight = 114 + this.displayedRows * 18;
		this.progress = 0;
		this.container.setSearchTerm("");
		this.searchBoxOldText = "";
	}

	public static ScrollableScreen createScreen(ScrollableContainer container) {
		return new ScrollableScreen(container, MinecraftClient.getInstance().player.inventory, container.getDisplayName());
	}

	@Override
	public void init() {
		super.init();
		this.searchBox = this.addButton(new SearchTextFieldWidget(this.textRenderer, this.x + 82, this.y + 127, 80, 8, ""));
		this.searchBox.setMaxLength(50);
		this.searchBox.setHasBorder(false);
		this.searchBox.setVisible(this.hasScrollbar());
		this.searchBox.setEditableColor(16777215);
		this.searchBox.setChangedListener(str -> {
			if (str.equals(this.searchBoxOldText)) return;
			this.container.setSearchTerm(str);
			this.progress = 0;
			this.topRow = 0;
			this.searchBoxOldText = str;
		});
	}

	@Override
	public void tick() {
		this.searchBox.tick();
	}

	@Override
	public void render(int mouseX, int mouseY, float lastFrameDuration) {
		this.renderBackground();
		this.drawBackground(lastFrameDuration, mouseX, mouseY);
		super.render(mouseX, mouseY, lastFrameDuration);
		this.drawMouseoverTooltip(mouseX, mouseY);
	}

	@Override
	protected void drawForeground(int mouseX, int mouseY) {
		this.textRenderer.draw(this.title.asFormattedString(), 8, 6, 4210752);
		this.textRenderer.draw(this.playerInventory.getDisplayName().asFormattedString(), 8, containerHeight - 94, 4210752);
	}

	@Override
	protected void drawBackground(float lastFrameDuration, int mouseX, int mouseY) {
		RenderSystem.color4f(1, 1, 1, 1);
		this.client.getTextureManager().bindTexture(BASE_TEXTURE);
		int x = (this.width - this.containerWidth) / 2;
		int y = (this.height - this.containerHeight) / 2;
		blit(x, y, 0, 0, this.containerWidth, this.displayedRows * 18 + 17);
		blit(x, y + this.displayedRows * 18 + 17, 0, 126, this.containerWidth, 96);

		if (hasScrollbar()) {
			this.client.getTextureManager().bindTexture(WIDGETS_TEXTURE);
			blit(x + 172, y, 0, 0, 22, 132);
			blit(x + 174, (int) (y + 18 + 91 * progress), 22, 0, 12, 15);
			blit(x + 79, y + 126, 34, 0, 90, 11);
			this.searchBox.render(mouseX, mouseY, lastFrameDuration);
		}
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double scrollDelta) {
		if (this.hasScrollbar()) {
			this.setTopRow(this.topRow - (int) scrollDelta);
			this.progress = ((double) this.topRow) / ((double) (this.totalRows - 6));
			return true;
		}

		return false;
	}

	@Override
	protected boolean isClickOutsideBounds(double mouseX, double mouseY, int left, int top, int mouseButton) {
		boolean left_up_down = mouseX < left || mouseY < top || mouseY > top + this.height;
		boolean right = mouseX > left + this.width;
		if (hasScrollbar()) right = (right && mouseY > top + 132) || mouseX > left + this.width + 18;
		return left_up_down || right;
	}

	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
		if (!this.dragging) return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
		this.progress = MathHelper.clamp((mouseY - this.y - 25.5) / 90, 0, 1);
		setTopRow((int) (this.progress * (this.totalRows - 6)));
		return true;
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (this.searchBox.isFocused() && !this.searchBox.isMouseInBounds(mouseX, mouseY) && button == 0) {
			this.searchBox.changeFocus(true);
			this.setFocused(null);
		}

		if (button == 0 && this.x + 172 < mouseX && mouseX < this.x + 184 && this.y + 18 < mouseY && mouseY < this.y + 123) {
			this.dragging = true;
			return true;
		}

		return super.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		if (this.dragging && button == 0) this.dragging = false;
		return super.mouseReleased(mouseX, mouseY, button);
	}

	private void setTopRow(int row) {
		this.topRow = MathHelper.clamp(row, 0, this.totalRows - 6);
		this.container.updateSlotPositions(this.topRow, false);
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (keyCode == 256) {
			this.client.player.closeContainer();
			return true;
		}

		if (!this.searchBox.isFocused()) {
			if (this.client.options.keyChat.matchesKey(keyCode, scanCode)) {
				this.searchBox.changeFocus(true);
				this.setFocused(this.searchBox);
				this.searchBox.ignoreNextChar();
				return true;
			}

			return super.keyPressed(keyCode, scanCode, modifiers);
		}

		return this.searchBox.keyPressed(keyCode, scanCode, modifiers);
	}

	@Override
	public boolean charTyped(char character, int int_1) {
		if (this.searchBox.isFocused()) return this.searchBox.charTyped(character, int_1);
		return super.charTyped(character, int_1);
	}

	@Override
	public void resize(MinecraftClient client, int width, int height) {
		String text = this.searchBox.getText();
		boolean focused = this.searchBox.isFocused();
		super.resize(client, width, height);
		this.searchBox.setText(text);

		if (focused) {
			this.searchBox.changeFocus(true);
			this.setFocused(this.searchBox);
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

