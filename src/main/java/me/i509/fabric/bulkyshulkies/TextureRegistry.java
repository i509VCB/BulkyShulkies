package me.i509.fabric.bulkyshulkies;

import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.SimpleRegistry;

public class TextureRegistry {
	public static final class TextureKeys {
		public static final Identifier COPPER = BulkyShulkiesMod.id("copper");
		public static final Identifier IRON = BulkyShulkiesMod.id("iron");
		public static final Identifier SILVER = BulkyShulkiesMod.id("silver");
		public static final Identifier GOLD = BulkyShulkiesMod.id("gold");
	}

	public static final SimpleRegistry<ShulkerTextureData> MODELED = new SimpleRegistry<>();

	public static final ShulkerTextureData COPPER = MODELED.add(TextureKeys.COPPER, new ShulkerTextureData("copper"));
	public static final ShulkerTextureData IRON = MODELED.add(TextureKeys.IRON, new ShulkerTextureData("iron"));
	public static final ShulkerTextureData SILVER = MODELED.add(TextureKeys.SILVER, new ShulkerTextureData("silver"));
	public static final ShulkerTextureData GOLD = MODELED.add(TextureKeys.GOLD, new ShulkerTextureData("gold"));

	public static class ShulkerTextureData {
		private final String baseName;

		public ShulkerTextureData(String baseName) {
			this.baseName = baseName;
		}

		public Identifier getTexture(DyeColor color) {
			if (color == null) {
				return BulkyShulkiesMod.id("textures/be/shulker/" + baseName + "/shulker");
			}

			return BulkyShulkiesMod.id("textures/be/shulker/" + baseName + "/shulker_" + color.getName());
		}

		public String getBaseName() {
			return baseName;
		}
	}

	static {
		Identifier nullId = BulkyShulkiesMod.id("null");
		MODELED.add(nullId, new ShulkerTextureData("null"));
	}
}
