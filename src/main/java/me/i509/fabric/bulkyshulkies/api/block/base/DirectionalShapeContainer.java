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

package me.i509.fabric.bulkyshulkies.api.block.base;

import java.util.Map;
import java.util.function.BiFunction;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;

import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class DirectionalShapeContainer {
	private static final Direction[] HORIZONTAL = new Direction[] {
		Direction.NORTH,
		Direction.SOUTH,
		Direction.EAST,
		Direction.WEST
	};

	private static final Direction[] VERTICAL = new Direction[] {
		Direction.UP,
		Direction.DOWN
	};

	private final ForwardingMap<Direction, VoxelShape> shapes;

	public DirectionalShapeContainer(ImmutableMap<Direction, VoxelShape> built) {
		this.shapes = new ForwardingMap<Direction, VoxelShape>() {
			@Override
			protected Map<Direction, VoxelShape> delegate() {
				return built;
			}

			@Override
			public VoxelShape get(Object direction) {
				VoxelShape v = super.get(direction);

				if (v == null) {
					return VoxelShapes.fullCube(); // Fallback
				}

				return v;
			}
		};
	}

	public static DirectionalShapeContainer createAll(double animation, BiFunction<Double, Direction, VoxelShape> shapeFunction) {
		ImmutableMap.Builder<Direction, VoxelShape> builder = ImmutableMap.builder();

		for (Direction value : Direction.values()) {
			builder.put(value, shapeFunction.apply(animation, value));
		}

		return new DirectionalShapeContainer(builder.build());
	}

	public static DirectionalShapeContainer createHorizontal(double animation, BiFunction<Double, Direction, VoxelShape> shapeFunction) {
		ImmutableMap.Builder<Direction, VoxelShape> builder = ImmutableMap.builder();

		for (Direction value : DirectionalShapeContainer.HORIZONTAL) {
			builder.put(value, shapeFunction.apply(animation, value));
		}

		return new DirectionalShapeContainer(builder.build());
	}

	public VoxelShape get(Direction direction) {
		return this.shapes.get(direction);
	}
}
