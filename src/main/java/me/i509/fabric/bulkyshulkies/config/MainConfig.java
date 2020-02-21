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

package me.i509.fabric.bulkyshulkies.config;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.List;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import me.i509.fabric.bulkyshulkies.config.section.ExtensionConfigSection;

@ConfigSerializable
public class MainConfig {
	public static final String HEADER = "This file allows configuration of different features within cursed shulker boxes.\n"
			+ "All options here are done using the HOCON Schema, which you can get more information here: https://docs.spongepowered.org/stable/en/server/getting-started/configuration/hocon.html\n"
			+ "Since HOCON is a superset of JSON, almost all JSON Schemas should work out of the box\n"
			+ "If you need help, please contact me on discord here: https://discord.gg/qX7kBWY";

	@Setting(comment = "Specifies weather Copper, Silver and Platinum recipes should be loaded even if CottonResources/TechReborn/Other mods have all the required materials should be used.\n"
			+ "This Setting shall only go into effect, if the value is true and CottonResources is present\n")
	private boolean useResources = true;

	public boolean shouldUseResources() {
		return useResources;
	}

	@Setting(comment = "Specifies weather platinum shulker box's should be allowed to use their magnetic properties to collection items from a distance.\n"
			+ "Note this will cause an increase in lag, however this can be slightly controlled by the magnetism range.\n")
	private boolean shouldPlatinumUseMagnetism = true;

	public boolean shouldPlatinumUseMagnetism() {
		return shouldPlatinumUseMagnetism;
	}

	@Setting(comment = "Specifies the maximum range in which the platinum shulker box's magnetism no longer can pick up items. \n"
			+ "Note that the larger this number is will result in more lag.\n"
			+ "This cannot be 0 or negative and there is a firm limit of a 16 block max range.\n")
	private int platinumMagnetMaxRange = 6;

	public int getPlatinumMagnetMaxRange() {
		checkArgument(platinumMagnetMaxRange > 0, "Range cannot be zero or lower.");
		checkArgument(platinumMagnetMaxRange <= 16, "The maximum magnetism range for platinum shulker boxes is 16 blocks.");
		return platinumMagnetMaxRange;
	}

	@Setting(comment = "Specifies items which are not allowed to be placed within any shulker box's slots.\n"
			+ "This should be a list of namespaced identifiers, such as mymod:myblock (assuming myblock is registered as a BlockItem within the registry) or mymod:myitem.\n"
			+ "Note this currently does not support tags.\n"
			+ "Items which are not present in the registry will be ignored and warned about in the log\n")
	private List<String> notAllowedInShulkers = new ArrayList<>();

	public List<String> getNotAllowedInShulkers() {
		return notAllowedInShulkers;
	}

	@Setting(comment = "This specifies the Schema Version of this config.\n"
			+ "This setting should not be changed since it will mess up your config settings.\n"
			+ "When the config schema changes, this value will be read and your config updated accordingly automatically.\n"
			+ "If the file's schema is lower than the mod's schema version, the config will be updated.\n"
			+ "If the file's schema is greater than the mod's schema version, the config will be backed up and an error message will be placed in the log.\n"
			+ "Then the config will be set to the new schema version. (We do not try to guestimate the contents of the config with an unknown schema version)\n")
	private double schemaVersion = 1.0;

	public double getSchema() {
		return schemaVersion;
	}

	@Setting(comment = "This species the delay between each magnetism check for the platinum shulker box.\n"
			+ "This only applies if Magnetism is enabled.\n"
			+ "A setting of zero means every single tick, the magnetism will attempt to collect items every single tick, this may cause lag, so it is recommended to increase this value\n")
	private int magnetismTickDelay = 20;

	public int getMagnetismTickDelay() {
		checkArgument(magnetismTickDelay >= 0, "Magnetism delay cannot be less than zero.");
		return magnetismTickDelay;
	}

	@Setting(comment = "Specifies settings for possible extensions which bulky shulkies can hook into.")
	private ExtensionConfigSection extensions = new ExtensionConfigSection();

	public ExtensionConfigSection getExtensions() {
		return this.extensions;
	}
}
