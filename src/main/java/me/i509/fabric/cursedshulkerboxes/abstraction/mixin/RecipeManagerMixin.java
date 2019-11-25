/*
 * MIT License
 *
 * Copyright (c) 2019 i509VCB
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.i509.fabric.cursedshulkerboxes.abstraction.mixin;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.JsonOps;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.Identifier;

import me.i509.fabric.cursedshulkerboxes.CursedShulkerBox;
import me.i509.fabric.cursedshulkerboxes.abstraction.HoconOps;
import me.i509.fabric.cursedshulkerboxes.abstraction.recipe.HoconRecipeReloadEvent;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {
	@Inject(at = @At(value = "INVOKE"), method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V")
	public void onRegisterRecipe(Map<Identifier, JsonObject> recipes, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
		Map<Identifier, CommentedConfigurationNode> hoconRecipes = new HashMap<>();
		HoconRecipeReloadEvent.EVENT.invoker().getRecipes(hoconRecipes::put);

		for (Map.Entry<Identifier, CommentedConfigurationNode> entry : hoconRecipes.entrySet()) {
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
