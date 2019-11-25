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

package me.i509.fabric.cursedshulkerboxes.abstraction.recipe;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.commented.SimpleCommentedConfigurationNode;

import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@FunctionalInterface
public interface HoconRecipeReloadEvent {
	Event<HoconRecipeReloadEvent> EVENT = EventFactory.createArrayBacked(HoconRecipeReloadEvent.class, (callbacks) -> (recipeBiConsumer) -> {
		for (HoconRecipeReloadEvent callback : callbacks) {
			callback.getRecipes(recipeBiConsumer);
		}
	});

	void getRecipes(BiConsumer<Identifier, CommentedConfigurationNode> recipe);

	static void exampleInvoke() {
		Map<Identifier, CommentedConfigurationNode> recipes = new HashMap<>();
		EVENT.invoker().getRecipes(recipes::put);
	}

	HoconRecipeReloadEvent recipese = (recipes) -> {
		recipes.accept(new Identifier("test:recipe"), SimpleCommentedConfigurationNode.root());
	};
}
