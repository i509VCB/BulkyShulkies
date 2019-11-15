package me.i509.fabric.cursedshulkerboxes.config;

import com.typesafe.config.ConfigException;
import me.i509.fabric.cursedshulkerboxes.CursedShulkerBox;
import me.i509.fabric.cursedshulkerboxes.util.ThrowableFunction;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.SystemUtil;
import net.minecraft.util.registry.Registry;
import ninja.leaping.configurate.ValueType;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class HoconRecipeTypeAdapter {
    private static final Map<String, ThrowableFunction<CommentedConfigurationNode, Recipe<?>, RecipeParseException>> RECIPE_HANDLERS = SystemUtil.consume(new HashMap<>(), map -> {
        map.put("minecraft:crafting_shaped", HoconRecipeTypeAdapter::handleShaped);
        map.put("minecraft:crafting_shapeless", HoconRecipeTypeAdapter::handleShapeless);
    });
    private static final Logger LOGGER = CursedShulkerBox.getInstance().getLogger();

    public static Recipe<?> handleShapeless(CommentedConfigurationNode node) throws RecipeParseException  {
        return null;
    }


    public static Recipe<?> handleShaped(CommentedConfigurationNode node) throws RecipeParseException {
        CommentedConfigurationNode type = node.getNode("type"); // Already checked for this
        CommentedConfigurationNode pattern = node.getNode("pattern");
        CommentedConfigurationNode key = node.getNode("key");
        CommentedConfigurationNode result = node.getNode("result"); // Already checked for this

        if(pattern.getValueType().equals(ValueType.NULL)) {
            throw new RecipeParseException(pattern.getPath().toString(), "Shaped Recipes require a 'pattern' but it is not present or empty.");
        }

        if(key.getValueType().equals(ValueType.NULL)) {
            throw new RecipeParseException(key.getPath().toString(), "Shaped Recipes require a 'key' but it is not present or empty.");
        }




        return null;
    }

    public static void reloadRecipes(CommentedConfigurationNode recipes) {
        Map<Object, CommentedConfigurationNode> allRecipes = (Map<Object, CommentedConfigurationNode>) recipes.getChildrenMap();
        if(allRecipes.isEmpty()) {
            return;
        }
        // TODO Exception logging the whole way
        for(Map.Entry<Object, CommentedConfigurationNode> entry : allRecipes.entrySet()) {

            try {
                if (entry.getKey() instanceof String) {
                    Identifier targetItem = Identifier.tryParse((String) entry.getKey());

                    if (targetItem != null) {
                        CommentedConfigurationNode value = entry.getValue();

                        CommentedConfigurationNode recipeType = value.getNode("type");
                        if (recipeType.getValueType().equals(ValueType.NULL)) {
                            throw new RecipeParseException(recipeType.getPath().toString(), "Recipes require a 'type' but it is not present or empty.");
                        }

                        if(value.getNode("result").getValueType().equals(ValueType.NULL)) {
                            throw new RecipeParseException(value.getNode("result").getPath().toString(), "Recipes require a 'result' but it is not present or empty.");
                        }

                        HoconRecipeTypeAdapter.handleRecipe(recipeType.getString(), value);
                    }
                }
            } catch (RecipeParseException e) {
                LOGGER.error("Recipe of ID: " + entry.getKey() + " failed to parse...");
                LOGGER.error(e.getPath() + ": " + e.getReason());
            }
        }
    }

    public static void handleRecipe(String type, CommentedConfigurationNode recipeInfo) throws RecipeParseException {
        ThrowableFunction<CommentedConfigurationNode, Recipe<?>, RecipeParseException> recipeHandler = RECIPE_HANDLERS.get(type);

        if(recipeHandler == null) {
            throw new RecipeParseException("The following recipe does not have a registered RECIPE_HANDLER for HOCON: " + type);
        }

        // Just in case, check the registry.
        Registry.RECIPE_SERIALIZER.getOrEmpty(new Identifier(type)).orElseThrow(() -> new ConfigException.BadValue(recipeInfo.getPath().toString(), "Could not find recipe type: '" + type + "' in the registry"));

        Recipe<?> recipe;

        try {
            recipe = recipeHandler.apply(recipeInfo);
        } catch (RecipeParseException throwable) {
            // TODO handle
        }



    }
}
