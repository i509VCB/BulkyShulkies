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

package me.i509.bulkyshulkies.api.component;

import java.util.Objects;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import me.i509.bulkyshulkies.api.block.ShulkerBoxColor;

import net.minecraft.nbt.CompoundTag;

import net.fabricmc.fabric.api.util.NbtType;

public final class ShulkerBoxColorComponent implements ComponentV3, AutoSyncedComponent {
	private ShulkerBoxColor color = ShulkerBoxColor.NONE;

	public ShulkerBoxColor getColor() {
		return this.color;
	}

	public void setColor(ShulkerBoxColor color) {
		this.color = Objects.requireNonNull(color);
	}

	@Override
	public void readFromNbt(CompoundTag tag) {
		if (tag.contains("ShulkerBoxColor", NbtType.STRING)) {
			this.color = ShulkerBoxColor.valueOf(tag.getString("ShulkerBoxColor"));
		}
	}

	@Override
	public void writeToNbt(CompoundTag tag) {
		tag.putString("ShulkerBoxColor", this.color.name());
	}
}
