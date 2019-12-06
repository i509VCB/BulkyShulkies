package me.i509.fabric.bulkyshulkies.recipe.injector;

import com.google.gson.JsonObject;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public class InjectorRecipeSerializer implements RecipeSerializer<InjectorRecipe> {
	@Override
	public InjectorRecipe read(Identifier id, JsonObject json) {
		return null;
	}

	@Override
	public InjectorRecipe read(Identifier id, PacketByteBuf buf) {
		return null;
	}

	@Override
	public void write(PacketByteBuf buf, InjectorRecipe recipe) {
		// TODO
	}
}
