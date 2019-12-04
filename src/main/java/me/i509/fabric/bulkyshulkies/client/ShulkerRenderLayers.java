package me.i509.fabric.bulkyshulkies.client;

import me.i509.fabric.bulkyshulkies.BulkyShulkiesMod;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ShulkerRenderLayers {
	public static final Identifier SHULKER_BOXES_ATLAS_TEXTURE = BulkyShulkiesMod.id("textures/atlas/shulker_boxes.png");

	public static final class Copper {
		public static final SpriteIdentifier UNCOLORED = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker"));
		public static final SpriteIdentifier WHITE = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_white"));
		public static final SpriteIdentifier ORANGE = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_orange"));
		public static final SpriteIdentifier MAGENTA = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_magenta"));
		public static final SpriteIdentifier LIGHT_BLUE = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_light_blue"));
		public static final SpriteIdentifier YELLOW = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_yellow"));
		public static final SpriteIdentifier LIME = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_lime"));
		public static final SpriteIdentifier PINK = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_pink"));
		public static final SpriteIdentifier GRAY = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_gray"));
		public static final SpriteIdentifier LIGHT_GRAY = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_light_gray"));
		public static final SpriteIdentifier CYAN = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_cyan"));
		public static final SpriteIdentifier PURPLE = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_purple"));
		public static final SpriteIdentifier BLUE = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_blue"));
		public static final SpriteIdentifier BROWN = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_brown"));
		public static final SpriteIdentifier GREEN = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_green"));
		public static final SpriteIdentifier RED = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_red"));
		public static final SpriteIdentifier BLACK = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, BulkyShulkiesMod.id("textures/be/shulker/copper/shulker_black"));

		public static void registerTextures(Consumer<SpriteIdentifier> consumer) {
			consumer.accept(UNCOLORED);
			consumer.accept(WHITE);
			consumer.accept(ORANGE);
			consumer.accept(MAGENTA);
			consumer.accept(LIGHT_BLUE);
			consumer.accept(YELLOW);
			consumer.accept(LIME);
			consumer.accept(PINK);
			consumer.accept(GRAY);
			consumer.accept(LIGHT_GRAY);
			consumer.accept(CYAN);
			consumer.accept(PURPLE);
			consumer.accept(BLUE);
			consumer.accept(BROWN);
			consumer.accept(GREEN);
			consumer.accept(RED);
			consumer.accept(BLACK);
		}
	}
}
