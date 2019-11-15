package me.i509.fabric.cursedshulkerboxes.config.recipe;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.jetbrains.annotations.Nullable;

@ConfigSerializable
public class RecipeIngredient {
    @Setting
    String item;

    @Setting
    String tag;

    @Nullable
    public String getItem() {
        return item;
    }

    public String getTag() {
        return tag;
    }
}
