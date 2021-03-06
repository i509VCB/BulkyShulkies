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

package me.i509.bulkyshulkies.mod.component;

import java.util.Map;

import dev.onyxstudios.cca.api.v3.block.BlockComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.block.BlockComponentInitializer;
import dev.onyxstudios.cca.api.v3.component.ComponentFactory;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;

import net.minecraft.block.entity.BlockEntity;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;

import me.i509.bulkyshulkies.api.ShulkerBoxType;
import me.i509.bulkyshulkies.api.block.entity.ShulkerBoxBlockEntity;
import me.i509.bulkyshulkies.mod.BulkyShulkiesImpl;

public final class ShulkerBlockComponents implements BlockComponentInitializer {
	@Override
	public void registerBlockComponentFactories(BlockComponentFactoryRegistry registry) {
		RegistryEntryAddedCallback.event(BulkyShulkiesImpl.SHULKER_BOX_TYPE).register((rawId, id, type) -> {
			this.registerBlockEntityComponents(registry, type);
		});

		for (ShulkerBoxType type : BulkyShulkiesImpl.SHULKER_BOX_TYPE) {
			this.registerBlockEntityComponents(registry, type);
		}
	}

	private void registerBlockEntityComponents(BlockComponentFactoryRegistry registry, ShulkerBoxType type) {
		for (Map.Entry<ComponentKey<?>, ComponentFactory<? extends ShulkerBoxBlockEntity, ?>> entry : type.getBlockEntityComponents().entrySet()) {
			// TODO: Test me
			//noinspection unchecked,rawtypes
			registry.beginRegistration(BlockEntity.class, entry.getKey())
					.filter(clz -> clz.isAssignableFrom(ShulkerBoxBlockEntity.class))
					.end((ComponentFactory) entry.getValue());
		}
	}
}
