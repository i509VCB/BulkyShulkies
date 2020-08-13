package me.i509.fabric.bulkyshulkies.config.section;

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
