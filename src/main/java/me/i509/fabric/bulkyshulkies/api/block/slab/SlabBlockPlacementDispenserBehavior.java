package me.i509.fabric.bulkyshulkies.api.block.slab;

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.AutomaticItemPlacementContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class SlabBlockPlacementDispenserBehavior extends FallibleItemDispenserBehavior {
	// TODO: Make this result in the actual orientation of the slab block.
	protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
		this.success = false;
		Item item = stack.getItem();

		if (item instanceof BlockItem) {
			Direction dispenserFacing = pointer.getBlockState().get(DispenserBlock.FACING);
			BlockPos blockPos = pointer.getBlockPos().offset(dispenserFacing);
			Direction direction2 = pointer.getWorld().isAir(blockPos.down()) ? dispenserFacing : Direction.UP;
			this.success = ((BlockItem) item).place(new AutomaticItemPlacementContext(pointer.getWorld(), blockPos, dispenserFacing, stack, direction2)) == ActionResult.SUCCESS;
		}

		return stack;
	}
}
