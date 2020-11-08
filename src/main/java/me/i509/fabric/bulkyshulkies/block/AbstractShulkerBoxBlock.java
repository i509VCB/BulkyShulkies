package me.i509.fabric.bulkyshulkies.block;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import me.i509.fabric.bulkyshulkies.api.DataKey;
import me.i509.fabric.bulkyshulkies.api.ShulkerBoxType;
import me.i509.fabric.bulkyshulkies.api.block.ShulkerBox;
import me.i509.fabric.bulkyshulkies.api.block.entity.ShulkerBoxBlockEntity;
import org.jetbrains.annotations.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AbstractShulkerBoxBlock extends BlockWithEntity implements ShulkerBox {
	private final Map<DataKey<?>, ?> defaultDataValues;
	private final ShulkerBoxType type;

	protected AbstractShulkerBoxBlock(ShulkerBoxType type, Map<DataKey<?>, ?> defaultDataValues, Settings settings) {
		super(settings);
		this.type = type;
		this.defaultDataValues = Collections.unmodifiableMap(new HashMap<>(defaultDataValues)); // Make an immutable copy
	}

	@Override
	public <V> V getValue(DataKey<V> key, World world, BlockPos pos) throws UnsupportedOperationException {
		final BlockEntity blockEntity = world.getBlockEntity(pos);

		if (blockEntity instanceof ShulkerBoxBlockEntity) {
			return ((ShulkerBoxBlockEntity) blockEntity).getValue(key);
		}

		throw new UnsupportedOperationException(); // TODO
	}

	@Override
	public Collection<DataKey<?>> getKeys(World world, BlockPos pos) {
		final BlockEntity blockEntity = world.getBlockEntity(pos);

		if (blockEntity instanceof ShulkerBoxBlockEntity) {
			return ((ShulkerBoxBlockEntity) blockEntity).getKeys();
		}

		throw new UnsupportedOperationException(); // TODO
	}

	@Override
	public Map<DataKey<?>, ?> getValues(World world, BlockPos pos) {
		final BlockEntity blockEntity = world.getBlockEntity(pos);

		if (blockEntity instanceof ShulkerBoxBlockEntity) {
			return ((ShulkerBoxBlockEntity) blockEntity).getValues();
		}

		throw new UnsupportedOperationException(); // TODO
	}

	@Override
	public boolean supports(DataKey<?> key) {
		return this.defaultDataValues.containsKey(key);
	}

	@Override
	public final ShulkerBoxType getType() {
		return this.type;
	}

	@Override
	public final <V> V getDefaultValue(DataKey<V> key) throws UnsupportedOperationException {
		Objects.requireNonNull(key, "Data key cannot be null");

		if (this.defaultDataValues.containsKey(key)) {
			//noinspection unchecked
			return (V) this.defaultDataValues.get(key);
		}

		throw new UnsupportedOperationException(""); // TODO: Message
	}

	@Override
	public final Collection<DataKey<?>> getDefaultKeys() {
		return this.defaultDataValues.keySet();
	}

	@Override
	public final Map<DataKey<?>, ?> getDefaultValues() {
		return this.defaultDataValues;
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		if (world.isClient()) {
			return super.onUse(state, world, pos, player, hand, hit);
		}

		if (this.type.hasInventory()) {
			// TODO:
		}

		return ActionResult.PASS; // TODO: Impl dynamic use actions?
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
		if (type == this.type.getBlockEntityType()) {
			if (world.isClient()) {
				//noinspection unchecked
				return (BlockEntityTicker<T>) this.type.getClientTicker();
			}

			//noinspection unchecked
			return (BlockEntityTicker<T>) this.type.getServerTicker();
		}

		return null;
	}
}
