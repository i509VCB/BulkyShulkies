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

package me.i509.bulkyshulkies.mod.config.section.quickshulker;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class QuickShulkerSection {
	@Setting(comment = "Whether the copper shulker box can be opened using quick shulker.\n")
	private boolean canOpenCopper = true;

	@Setting(comment = "Whether the iron shulker box can be opened using quick shulker.\n")
	private boolean canOpenIron = true;

	@Setting(comment = "Whether the silver shulker box can be opened using quick shulker.\n")
	private boolean canOpenSilver = true;

	@Setting(comment = "Whether the gold shulker box can be opened using quick shulker.\n")
	private boolean canOpenGold = true;

	@Setting(comment = "Whether the diamond shulker box can be opened using quick shulker.\n")
	private boolean canOpenDiamond = true;

	@Setting(comment = "Whether the obsidian shulker box can be opened using quick shulker.\n")
	private boolean canOpenObsidian = true;

	@Setting(comment = "Whether the platinum shulker box can be opened using quick shulker.\n")
	private boolean canOpenPlatinum = true;

	@Setting(comment = "Whether the slab shulker box can be opened using quick shulker.\n")
	private boolean canOpenSlab = true;

	@Setting(comment = "Whether the missing texture shulker box can be opened using quick shulker.\n")
	private boolean canOpenMissingTex = true;

	@Setting(comment = "Whether the ender slab can be opened using quick shulker.\n")
	private boolean canOpenEnderSlab = true;

	@Setting(comment = "Whether the stair shulker box can be opened using quick shulker\n")
	private boolean canOpenStair = true;

	public boolean canOpenCopper() {
		return this.canOpenCopper;
	}

	public boolean canOpenIron() {
		return this.canOpenIron;
	}

	public boolean canOpenSilver() {
		return this.canOpenSilver;
	}

	public boolean canOpenGold() {
		return this.canOpenGold;
	}

	public boolean canOpenDiamond() {
		return this.canOpenDiamond;
	}

	public boolean canOpenObsidian() {
		return this.canOpenObsidian;
	}

	public boolean canOpenPlatinum() {
		return this.canOpenPlatinum;
	}

	public boolean canOpenSlab() {
		return this.canOpenSlab;
	}

	public boolean canOpenMissingTex() {
		return this.canOpenMissingTex;
	}

	public boolean canOpenEnderSlab() {
		return this.canOpenEnderSlab;
	}

	public boolean canOpenStair() {
		return this.canOpenStair;
	}
}
