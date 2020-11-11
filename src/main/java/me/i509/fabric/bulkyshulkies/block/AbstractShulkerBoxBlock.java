package me.i509.fabric.bulkyshulkies.block;

import me.i509.fabric.bulkyshulkies.api.ShulkerBoxType;
import me.i509.fabric.bulkyshulkies.api.block.ShulkerBox;
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
	private final ShulkerBoxType type;

	protected AbstractShulkerBoxBlock(ShulkerBoxType type, Settings settings) {
		super(settings);
		this.type = type;
	}

	@Override
	public final ShulkerBoxType getType() {
		return this.type;
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
