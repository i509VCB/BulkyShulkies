/*
 * MIT License
 *
 * Copyright (c) 2020 i509VCB
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

package me.i509.bulkyshulkies.mod.config.section;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;
import org.spongepowered.configurate.BasicConfigurationNode;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import me.i509.bulkyshulkies.api.ShulkerBoxType;
import me.i509.bulkyshulkies.api.config.section.MagnetismSection;

public final class MagnetismSectionImpl implements MagnetismSection {
	public static final TypeSerializer<MagnetismSection> SERIALIZER = new Serializer();
	private Map<ShulkerBoxType, Entry> entries = new HashMap<>();

	@Override
	@Range(from = 0, to = 32)
	public int getShulkerBoxRange(ShulkerBoxType type) {
		final Entry entry = this.entries.get(type);

		return entry != null ? entry.range : 0;
	}

	@Override
	@Range(from = 0, to = Integer.MAX_VALUE)
	public int getTickDelay(ShulkerBoxType type) {
		final Entry entry = this.entries.get(type);

		return entry != null ? entry.delay : 0;
	}

	@Override
	public boolean isMagnetismEnabled(ShulkerBoxType type) {
		return this.entries.containsKey(type);
	}

	@ConfigSerializable
	public static final class Entry {
		@Setting
		final int range;
		@Setting
		final int delay;

		private Entry(int range, int delay) {
			this.range = range;
			this.delay = delay;
		}
	}

	private static final class Serializer implements TypeSerializer<MagnetismSection> {
		@Override
		public MagnetismSection deserialize(Type type, ConfigurationNode node) throws SerializationException {
			if (node.isMap()) {
				final MagnetismSectionImpl section = new MagnetismSectionImpl();

				for (Map.Entry<Object, ? extends ConfigurationNode> entry : node.childrenMap().entrySet()) {
					final BasicConfigurationNode keyNode = BasicConfigurationNode.root(node.options());
					final ShulkerBoxType key = keyNode.set(entry.getKey()).get(ShulkerBoxType.class);

					section.entries.put(key, entry.getValue().get(Entry.class));
				}

				return section;
			}

			return null;
		}

		@Override
		public void serialize(Type type, @Nullable MagnetismSection obj, ConfigurationNode node) throws SerializationException {
			if (!(obj instanceof MagnetismSectionImpl)) {
				throw new IllegalArgumentException("Invalid implementation!");
			}

			node.set(((MagnetismSectionImpl) obj).entries);
		}
	}
}
