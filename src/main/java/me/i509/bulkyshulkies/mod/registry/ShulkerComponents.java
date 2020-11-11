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

package me.i509.bulkyshulkies.mod.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import me.i509.bulkyshulkies.mod.BulkyShulkies;
import me.i509.bulkyshulkies.api.component.EnderSlabInventory;
import me.i509.bulkyshulkies.api.component.MagnetismCooldownComponent;
import me.i509.bulkyshulkies.api.component.ShulkerBoxColorComponent;

public final class ShulkerComponents {
	public static final ComponentKey<EnderSlabInventory> ENDER_SLAB_INVENTORY = register("ender_slab_inventory", EnderSlabInventory.class);
	public static final ComponentKey<ShulkerBoxColorComponent> SHULKER_BOX_COLOR = register("shulker_box_color", ShulkerBoxColorComponent.class);
	public static final ComponentKey<MagnetismCooldownComponent> MAGNETISM_COOLDOWN = register("magnetism_cooldown", MagnetismCooldownComponent.class);

	private static <C extends ComponentV3> ComponentKey<C> register(String name, Class<C> type) {
		return ComponentRegistryV3.INSTANCE.getOrCreate(BulkyShulkies.id(name), type);
	}

	private ShulkerComponents() {
	}
}
