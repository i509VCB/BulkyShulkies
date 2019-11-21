package me.i509.fabric.cursedshulkerboxes.recipe;

import com.google.gson.JsonObject;
import me.i509.fabric.cursedshulkerboxes.CursedShulkerBoxMod;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public class InfuserRecipeSerializer implements RecipeSerializer<InfuserRecipe> {
    public static final InfuserRecipeSerializer INSTANCE = new InfuserRecipeSerializer();
    public static final Identifier ID = CursedShulkerBoxMod.id("infuser");

    private InfuserRecipeSerializer() {
        // NO-OP
    }

    @Override
    public InfuserRecipe read(Identifier id, JsonObject json) {
        return null;
    }

    @Override
    public InfuserRecipe read(Identifier id, PacketByteBuf buf) {
        return null;
    }

    @Override
    public void write(PacketByteBuf buf, InfuserRecipe recipe) {

    }
}
