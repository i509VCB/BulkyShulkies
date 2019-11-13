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

package me.i509.fabric.cursedshulkerboxes.client.screen.widget;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;

/**
 * Credit: NinjaPhoenix, Code is MIT like rest of project.
 */
@Environment(EnvType.CLIENT)
public class SearchTextFieldWidget extends TextFieldWidget {
    private boolean ignoreNextChar;

    public SearchTextFieldWidget(TextRenderer textRenderer, int x, int y, int width, int height, String message) {
        super(textRenderer, x, y, width, height, message);
        ignoreNextChar = false;
    }

    @Override
    public boolean mouseClicked(double x, double y, int button) {
        if (isVisible() && button == 1 && clicked(x, y)) {
            setText("");
            return true;
        }
        return super.mouseClicked(x, y, button);
    }

    @Override
    public boolean charTyped(char character, int int_1) {
        if (ignoreNextChar) {
            ignoreNextChar = false;
            return false;
        }
        return super.charTyped(character, int_1);
    }

    public boolean isMouseInBounds(double x, double y) {
        return clicked(x, y);
    }

    public void ignoreNextChar() {
        ignoreNextChar = true;
    }
}
