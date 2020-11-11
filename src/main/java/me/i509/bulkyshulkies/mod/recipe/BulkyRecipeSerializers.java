/*
 * MIT License
 *
 * Copyright (c) 2019-2020 i509VCB
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

package me.i509.bulkyshulkies.mod.recipe;

import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.registry.Registry;

import me.i509.bulkyshulkies.mod.BulkyShulkies;
import me.i509.bulkyshulkies.mod.recipe.injector.InjectorRecipeSerializer;

public class BulkyRecipeSerializers {
	public static final SpecialRecipeSerializer<AbstractShulkerBoxColoringRecipe> ABSTRACT_SHULKER_COLORING = Registry.register(Registry.RECIPE_SERIALIZER, BulkyShulkies.id("shulker_coloring"), new SpecialRecipeSerializer<>(AbstractShulkerBoxColoringRecipe::new));
	public static final InjectorRecipeSerializer INJECTOR = Registry.register(Registry.RECIPE_SERIALIZER, BulkyShulkies.id("injector"), new InjectorRecipeSerializer());
	public static final AbstractShulkerBoxUpgradeRecipeSerializer UPGRADE = Registry.register(Registry.RECIPE_SERIALIZER, BulkyShulkies.id("upgrade"), new AbstractShulkerBoxUpgradeRecipeSerializer());
	public static final NoNbtRecipeSerializer NO_NBT = Registry.register(Registry.RECIPE_SERIALIZER, BulkyShulkies.id("no_nbt"), new NoNbtRecipeSerializer());
}
