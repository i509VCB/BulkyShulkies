package me.i509.fabric.bulkyshulkies.mixin;

import me.i509.fabric.bulkyshulkies.api.SlotDuck;
import net.minecraft.container.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Slot.class)
public class SlotMixin implements SlotDuck {
	@Shadow
	@Mutable
	private int xPosition;

	@Mutable
	@Shadow
	private int yPosition;

	public void setXPosition(int pos) {
		this.xPosition = pos;
	}

	public void setYPosition(int pos) {
		this.yPosition = pos;
	}
}
