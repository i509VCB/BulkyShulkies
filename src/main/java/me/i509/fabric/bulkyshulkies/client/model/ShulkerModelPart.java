package me.i509.fabric.bulkyshulkies.client.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.util.Identifier;

public abstract class ShulkerModelPart extends ModelPart {
	public ShulkerModelPart(int textureWidth, int textureHeight, int textureOffsetU, int textureOffsetV) {
		super(textureWidth, textureHeight, textureOffsetU, textureOffsetV);
	}

	public abstract Identifier getTexture();
}
