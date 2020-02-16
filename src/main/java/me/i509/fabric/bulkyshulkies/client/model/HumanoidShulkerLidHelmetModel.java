package me.i509.fabric.bulkyshulkies.client.model;

import net.minecraft.client.model.ModelPart;

public class HumanoidShulkerLidHelmetModel extends AbstractShulkerLidHelmetModel {
	public HumanoidShulkerLidHelmetModel() {
		super(new ModelPart(9, 9, 0, 0));
		this.lid.addCuboid(-8, -12, -8, 16, 6, 16);
		this.lid.setPivot(0, 24, 0);
	}
}
