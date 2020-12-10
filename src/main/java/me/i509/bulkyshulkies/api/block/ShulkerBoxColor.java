/*
 * MIT License
 *
 * Copyright (c) 2019, 2020 i509VCB
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

package me.i509.bulkyshulkies.api.block;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import net.minecraft.util.DyeColor;
import net.minecraft.util.StringIdentifiable;

/**
 * Represents the color of a shulker box.
 */
public enum ShulkerBoxColor implements StringIdentifiable {
	NONE("none", null),
	WHITE("white", DyeColor.WHITE),
	ORANGE("orange", DyeColor.ORANGE),
	MAGENTA("magenta", DyeColor.MAGENTA),
	LIGHT_BLUE("light_blue", DyeColor.LIGHT_BLUE),
	YELLOW("yellow", DyeColor.YELLOW),
	LIME("lime", DyeColor.LIME),
	PINK("pink", DyeColor.PINK),
	GRAY("gray", DyeColor.GRAY),
	LIGHT_GRAY("light_gray", DyeColor.LIGHT_GRAY),
	CYAN("cyan", DyeColor.CYAN),
	PURPLE("purple", DyeColor.PURPLE),
	BLUE("blue", DyeColor.BLUE),
	BROWN("brown", DyeColor.BROWN),
	GREEN("green", DyeColor.GREEN),
	RED("red", DyeColor.RED),
	BLACK("black", DyeColor.BLACK);

	private final String name;
	@Nullable
	private final DyeColor dyeColor;

	ShulkerBoxColor(String name, @Nullable DyeColor dyeColor) {
		this.name = name;
		this.dyeColor = dyeColor;
	}

	@Nullable
	public DyeColor toDyeColor() {
		return this.dyeColor;
	}

	@Override
	public String asString() {
		return this.name;
	}

	public static Optional<ShulkerBoxColor> parse(String string) {
		try {
			return Optional.of(ShulkerBoxColor.valueOf(string));
		} catch (IllegalArgumentException ignored) {
			return Optional.empty();
		}
	}
}
