/*
 * MIT License
 *
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

package me.i509.fabric.cursedshulkerboxes.abstraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.datafixers.types.Type;
import ninja.leaping.configurate.ValueType;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.commented.SimpleCommentedConfigurationNode;

public class HoconOps implements DynamicOps<CommentedConfigurationNode> {
	public static final HoconOps INSTANCE = new HoconOps();

	protected HoconOps() {
		// NO-OP
	}

	@Override
	public CommentedConfigurationNode empty() {
		return SimpleCommentedConfigurationNode.root();
	}

	@Override
	public Type<?> getType(CommentedConfigurationNode input) {
		if (input == null) {
			throw new NullPointerException("input is null");
		}

		switch (input.getValueType()) {
		case SCALAR:
			Object value = input.getValue();

			if (value instanceof String) {
				return DSL.string();
			} else if (value instanceof Boolean) {
				return DSL.bool();
			} else if (value instanceof Short) {
				return DSL.shortType();
			} else if (value instanceof Integer) {
				return DSL.intType();
			} else if (value instanceof Long) {
				return DSL.longType();
			} else if (value instanceof Float) {
				return DSL.floatType();
			} else if (value instanceof Double) {
				return DSL.doubleType();
			} else if (value instanceof Byte) {
				return DSL.byteType();
			} else {
				throw new IllegalArgumentException("Scalar value '" + input + "' has an unknown type: " + value.getClass().getName());
			}
		case MAP:
			return DSL.compoundList(DSL.remainderType(), DSL.remainderType());
		case LIST:
			return DSL.list(DSL.remainderType());
		case NULL:
			return DSL.nilType();
		default:
			throw new IllegalArgumentException("Value type '" + input + "' has an unknown type: " + input.getValue().getClass().getName());
		}
	}

	@Override
	public Optional<Number> getNumberValue(CommentedConfigurationNode input) {
		if (input.getValueType().equals(ValueType.SCALAR)) {
			if (input.getValue() instanceof Number) {
				return Optional.of((Number) input.getValue());
			} else if (input.getValue() instanceof Boolean) {
				return Optional.of(input.getBoolean() ? 1 : 0);
			}
		}

		return Optional.empty();
	}

	@Override
	public CommentedConfigurationNode createNumeric(Number i) {
		return SimpleCommentedConfigurationNode.root().setValue(i);
	}

	@Override
	public Optional<String> getStringValue(CommentedConfigurationNode input) {
		if (input.getValueType().equals(ValueType.SCALAR)) {
			if (input.getValue() instanceof String) {
				return Optional.of(input.getString());
			}
		}

		return Optional.empty();
	}

	@Override
	public CommentedConfigurationNode createString(String value) {
		return SimpleCommentedConfigurationNode.root().setValue(value);
	}

	@Override
	public CommentedConfigurationNode mergeInto(CommentedConfigurationNode input, CommentedConfigurationNode value) {
		return value.mergeValuesFrom(input);
	}

	@Override
	public CommentedConfigurationNode mergeInto(CommentedConfigurationNode input, CommentedConfigurationNode key, CommentedConfigurationNode value) {
		Map<Object, CommentedConfigurationNode> immutableInputChildrenMap = (Map<Object, CommentedConfigurationNode>) input.getChildrenMap(); // This is Immutable when it comes out.
		Map<Object, CommentedConfigurationNode> mutableChildMap = new HashMap<>();

		if (input.getValueType().equals(ValueType.MAP)) {
			mutableChildMap.putAll(immutableInputChildrenMap);
		} else if (input.getValueType() != ValueType.NULL) {
			return input;
		}

		mutableChildMap.put(key.getKey(), value);
		return SimpleCommentedConfigurationNode.root().setValue(mutableChildMap);
	}

	@Override
	public CommentedConfigurationNode merge(CommentedConfigurationNode first, CommentedConfigurationNode second) {
		return first.mergeValuesFrom(second);
	}

	@Override
	public Optional<Map<CommentedConfigurationNode, CommentedConfigurationNode>> getMapValues(CommentedConfigurationNode input) {
		if (input.getValueType().equals(ValueType.MAP)) {
			ImmutableMap.Builder<CommentedConfigurationNode, CommentedConfigurationNode> builder = ImmutableMap.builder();

			for (Map.Entry<Object, ? extends CommentedConfigurationNode> entry : input.getChildrenMap().entrySet()) {
				builder.put(SimpleCommentedConfigurationNode.root().setValue(entry.getKey()), SimpleCommentedConfigurationNode.root().setValue(entry.getValue()));
			}

			return Optional.of(builder.build());
		}

		return Optional.empty();
	}

	@Override
	public CommentedConfigurationNode createMap(Map<CommentedConfigurationNode, CommentedConfigurationNode> map) {
		Map<Object, CommentedConfigurationNode> resultMap = new HashMap<>();

		for (Map.Entry<CommentedConfigurationNode, CommentedConfigurationNode> entry : map.entrySet()) {
			resultMap.put(entry.getKey().getPath(), entry.getValue());
		}

		return SimpleCommentedConfigurationNode.root().setValue(resultMap);
	}

	@Override
	public Optional<Stream<CommentedConfigurationNode>> getStream(CommentedConfigurationNode input) {
		if (input.getValueType().equals(ValueType.LIST)) {
			Stream<CommentedConfigurationNode> stream = (Stream<CommentedConfigurationNode>) input.getChildrenList().stream();
			return Optional.of(stream);
		}

		return Optional.empty();
	}

	@Override
	public CommentedConfigurationNode createList(Stream<CommentedConfigurationNode> input) {
		List<CommentedConfigurationNode> list = new ArrayList<>();

		for (CommentedConfigurationNode node : input.collect(Collectors.toList())) {
			list.add(node);
		}

		return SimpleCommentedConfigurationNode.root().setValue(list);
	}

	@Override
	public CommentedConfigurationNode remove(CommentedConfigurationNode input, String key) {
		if (input.getValueType().equals(ValueType.MAP)) {
			Map<Object, ? extends CommentedConfigurationNode> immutableChildMap = input.getChildrenMap();
			Map<Object, ? extends CommentedConfigurationNode> mutableChildMap = new HashMap<>(immutableChildMap);
			mutableChildMap.remove(key);
			return input.setValue(mutableChildMap);
		}

		return input;
	}

	@Override
	public String toString() {
		return "HOCON";
	}
}
