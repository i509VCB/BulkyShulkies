package me.i509.fabric.cursedshulkerboxes.config.recipe;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializer;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;
import java.util.Map;

@ConfigSerializable
public class SerializedShapedCraftingRecipe extends SerializedRecipe {
    @Setting
    private String type;

    @Setting
    private String[] pattern;

    @Setting
    private Map<String, RecipeIngredient> key = new HashMap<>();

    @Setting
    private RecipeResult result = new RecipeResult();

    public String getType() {
        return type;
    }

    public String[] getPattern() {
        return pattern;
    }

    public Map<String, RecipeIngredient> getKeys() {
        return key;
    }
}
