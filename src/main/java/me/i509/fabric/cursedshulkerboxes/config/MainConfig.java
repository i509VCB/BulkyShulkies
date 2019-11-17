package me.i509.fabric.cursedshulkerboxes.config;

import ninja.leaping.configurate.objectmapping.Setting;

public class MainConfig {
    private static final String RECIPE_HEADER =
            "1.0\n" +
                    "This file defines allows defining of recipes via a config.\n" +
                    "The json syntax for recipes should work out of the box.\n" +
                    "Any recipe type which does not have a HOCON recipe handler will be ignored and an error message will be displayed\n" +
                    "If you need help, please contact me on discord here: [URL HERE]\n"; // TODO URL At release

    @Setting(comment = "Weather recipes from CottonResources should be used if CottonResources is present.")
    private boolean useCottonResources = true;

    public boolean shouldUseCottonResources() {
        return useCottonResources;
    }
}
