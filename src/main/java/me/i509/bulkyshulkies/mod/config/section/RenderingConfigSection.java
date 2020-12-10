/*
 * MIT License
 *
 * Copyright (c) 2019, 2020 i509VCB
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

package me.i509.bulkyshulkies.mod.config.section;

import java.util.ArrayList;
import java.util.List;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class RenderingConfigSection {
	@Setting(comment = "Due to the shulker helmet being hardcoded to render properly on the vanilla entities which support armor, most modded entities which have armor will not be rendered correctly.\n"
			+ "However Bulky Shulkies will make a best attempt to register and render the helmet's rendering properly for modded entities specified in the list.\n"
			+ "All entries in this list must be an Entity Type. For example \"minecraft:creeper\" (namespace:path).\n"
			+ "Entity types must be specified as an identifier which is [a-z0-9_.-] in the namespace and [a-z0-9/._-] in the path. Invalid entries warned in the log and are ignored\n"
			+ "All vanilla entities which use the shulker helmet renderer are ignored if specified in this list. As of 1.16.2-rc1, this includes:\n"
			+ "Armor stands, Giants, Piglins (regular and brute), players, skeleton-like entities, zombie-like entities and zombie villagers."
			+ "Any entities specified in this list MUST be a living entity in order to render the helmet properly. Entries which refer to non-living entities may cause the game to crash. Use at your own risk"
	)
	private List<String> helmetRenderingAdditions = new ArrayList<>();

	public List<String> getHelmetRenderingAdditions() {
		return this.helmetRenderingAdditions;
	}
}
