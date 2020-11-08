package me.i509.fabric.bulkyshulkies.api.block;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import net.minecraft.util.DyeColor;
import net.minecraft.util.StringIdentifiable;

/**
 * Represents the color of a shulker box.
 */
public enum ShulkerBoxColor implements StringIdentifiable {
	NONE("none", null),
	WHITE("white", DyeColor.WHITE),
	ORANGE("orange", DyeColor.ORANGE),
	MAGENTA("magenta", DyeColor.MAGENTA),
	LIGHT_BLUE("light_blue", DyeColor.LIGHT_BLUE),
	YELLOW("yellow", DyeColor.YELLOW),
	LIME("lime", DyeColor.LIME),
	PINK("pink", DyeColor.PINK),
	GRAY("gray", DyeColor.GRAY),
	LIGHT_GRAY("light_gray", DyeColor.LIGHT_GRAY),
	CYAN("cyan", DyeColor.CYAN),
	PURPLE("purple", DyeColor.PURPLE),
	BLUE("blue", DyeColor.BLUE),
	BROWN("brown", DyeColor.BROWN),
	GREEN("green", DyeColor.GREEN),
	RED("red", DyeColor.RED),
	BLACK("black", DyeColor.BLACK);

	private final String name;
	@Nullable
	private final DyeColor dyeColor;

	ShulkerBoxColor(String name, @Nullable DyeColor dyeColor) {
		this.name = name;
		this.dyeColor = dyeColor;
	}

	@Nullable
	public DyeColor toDyeColor() {
		return this.dyeColor;
	}

	@Override
	public String asString() {
		return this.name;
	}

	public static Optional<ShulkerBoxColor> parse(String string) {
		try {
			return Optional.of(ShulkerBoxColor.valueOf(string));
		} catch (IllegalArgumentException ignored) {
			return Optional.empty();
		}
	}
}
