package me.i509.fabric.cursedshulkerboxes.abstraction.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.JsonOps;
import me.i509.fabric.cursedshulkerboxes.CursedShulkerBox;
import me.i509.fabric.cursedshulkerboxes.abstraction.HoconOps;
import me.i509.fabric.cursedshulkerboxes.abstraction.recipe.HoconRecipeReloadEvent;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
    @Inject(at = @At(value = "INVOKE"), method = "method_20705(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V")
    public void onRegisterRecipe(Map<Identifier, JsonObject> recipes, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        Map<Identifier, CommentedConfigurationNode> hoconRecipes = new HashMap<>();
        HoconRecipeReloadEvent.EVENT.invoker().getRecipes(hoconRecipes::put);

        for(Map.Entry<Identifier, CommentedConfigurationNode> entry : hoconRecipes.entrySet()) {
            try {
                checkNotNull(entry.getKey());
                JsonElement jsonRecipeElement = Dynamic.convert(HoconOps.INSTANCE, JsonOps.INSTANCE, entry.getValue());

                if (!jsonRecipeElement.isJsonObject()) {
                    // this definitely means an issue with recipe that was inputted.
                    throw new Exception("HoconOps could not properly convert the recipe '" + entry.getKey().toString() + "' to a JsonObject for the RecipeSerializers.");
                }

                recipes.put(entry.getKey(), jsonRecipeElement.getAsJsonObject());
            } catch (Exception e) {
                CursedShulkerBox.getInstance().getLogger().error(e.getMessage());
            }
        }
    }
}
