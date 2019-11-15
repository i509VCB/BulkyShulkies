package me.i509.fabric.cursedshulkerboxes.config.recipe;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class RecipeResult {
    @Setting
    int count;

    @Setting
    String item;
}
