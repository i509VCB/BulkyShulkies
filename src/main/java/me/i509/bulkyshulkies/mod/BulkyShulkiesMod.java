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

package me.i509.bulkyshulkies.mod;

import me.i509.bulkyshulkies.api.BulkyShulkies;
import me.i509.bulkyshulkies.mod.registry.ShulkerNetworking;
import me.i509.bulkyshulkies.mod.registry.ShulkerRegistries;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.network.PacketContext;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;

public final class BulkyShulkiesMod implements ModInitializer {
	public static final String MODID = "bulkyshulkies";

	@Override
	public void onInitialize() {
		BulkyShulkiesImpl.getInstance();
		ShulkerRegistries.init();
		C2SNetworking.init();

		ServerSidePacketRegistry.INSTANCE.register(ShulkerNetworking.HELMET_OPEN, this::onOpenHelmet);
	}

	private void onOpenHelmet(PacketContext context, PacketByteBuf packetByteBuf) {
		final PlayerEntity player = context.getPlayer();

		context.getTaskQueue().execute(() -> {
			if (player.isSpectator()) {
				return;
			}

			BulkyShulkies.openShulkerBoxHelmet(((ServerPlayerEntity) player));
		});
	}
}
