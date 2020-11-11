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

package me.i509.bulkyshulkies.api.item;

import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.network.PacketByteBuf;

public class HelmetTrackedDataStage implements TrackedDataHandler<ShulkerBoxBlockEntity.AnimationStage> {
	private HelmetTrackedDataStage() {
	}

	public static final TrackedDataHandler<ShulkerBoxBlockEntity.AnimationStage> INSTANCE = new HelmetTrackedDataStage();

	@Override
	public void write(PacketByteBuf packetByteBuf, ShulkerBoxBlockEntity.AnimationStage animationStage) {
		packetByteBuf.writeInt(animationStage.ordinal());
	}

	@Override
	public ShulkerBoxBlockEntity.AnimationStage read(PacketByteBuf packetByteBuf) {
		return ShulkerBoxBlockEntity.AnimationStage.values()[packetByteBuf.readInt()];
	}

	@Override
	public ShulkerBoxBlockEntity.AnimationStage copy(ShulkerBoxBlockEntity.AnimationStage animationStage) {
		switch (animationStage) {
		case CLOSED:
			return ShulkerBoxBlockEntity.AnimationStage.CLOSED;
		case OPENING:
			return ShulkerBoxBlockEntity.AnimationStage.OPENING;
		case OPENED:
			return ShulkerBoxBlockEntity.AnimationStage.OPENED;
		case CLOSING:
			return ShulkerBoxBlockEntity.AnimationStage.CLOSING;
		default:
			throw new IllegalStateException("Tried to copy invalid Shulker AnimationStage.");
		}
	}
}
