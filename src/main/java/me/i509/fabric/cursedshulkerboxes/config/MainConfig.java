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

package me.i509.fabric.cursedshulkerboxes.config;

import ninja.leaping.configurate.objectmapping.Setting;

public class MainConfig {
	private static final String RECIPE_HEADER =
			"1.0\n"
					+ "This file defines allows defining of recipes via a config.\n"
					+ "The json syntax for recipes should work out of the box.\n"
					+ "Any recipe type which does not have a HOCON recipe handler will be ignored and an error message will be displayed\n"
					+ "If you need help, please contact me on discord here: https://discord.gg/qX7kBWY\n"; // TODO URL At release

	@Setting(comment = "Weather recipes from CottonResources should be used if CottonResources is present.")
	private boolean useCottonResources = true;

	public boolean shouldUseCottonResources() {
		return useCottonResources;
	}
}
