package me.i509.fabric.cursedshulkerboxes.config.type;

import ninja.leaping.configurate.objectmapping.Setting;

public class MainConfig {
    @Setting(value = "true", comment = "Weather recipes from CottonResources should be used if CottonResources is present. ")
    boolean useCottonResources;
}
