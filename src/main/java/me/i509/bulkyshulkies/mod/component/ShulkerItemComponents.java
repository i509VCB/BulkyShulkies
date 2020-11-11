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

package me.i509.bulkyshulkies.mod.component;

import java.util.Map;

import dev.onyxstudios.cca.api.v3.component.ComponentFactory;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import me.i509.bulkyshulkies.mod.BulkyShulkies;
import me.i509.bulkyshulkies.api.ShulkerBoxType;

import net.minecraft.item.ItemStack;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;

public final class ShulkerItemComponents implements ItemComponentInitializer {
	@Override
	public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
		RegistryEntryAddedCallback.event(BulkyShulkies.SHULKER_BOX_TYPE).register((rawId, id, type) -> {
			this.registerItemStackComponents(registry, type);
		});

		for (ShulkerBoxType type : BulkyShulkies.SHULKER_BOX_TYPE) {
			this.registerItemStackComponents(registry, type);
		}
	}

	private void registerItemStackComponents(ItemComponentFactoryRegistry registry, ShulkerBoxType type) {
		for (Map.Entry<ComponentKey<?>, ComponentFactory<ItemStack, ?>> entry : type.getItemStackComponents().entrySet()) {
			//noinspection unchecked,rawtypes
			registry.registerFor(type.getId(), (ComponentKey) entry.getKey(), entry.getValue());
		}
	}
}
