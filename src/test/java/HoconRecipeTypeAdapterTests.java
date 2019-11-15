import me.i509.fabric.cursedshulkerboxes.config.HoconRecipeTypeAdapter;
import me.i509.fabric.cursedshulkerboxes.config.RecipeParseException;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class HoconRecipeTypeAdapterTests {

    HoconConfigurationLoader loader = HoconConfigurationLoader.builder().setFile(new File("F://test.conf")).build();
    CommentedConfigurationNode root = loader.load();

    public HoconRecipeTypeAdapterTests() throws IOException {
    }

    @Test
    void testShapedRecipeItem() {
        root.getChildrenMap().forEach((key, value) -> System.out.println(key.toString() + "--" + value.toString()));
        System.out.println(root.getNode("pineapple").toString());
    }


    @Test
    @DisplayName("Verify example ShapedRecipe with 'item' does not fail to be converted to a Recipe")
    void testShapedRecipeLoad() throws RecipeParseException {
        HoconRecipeTypeAdapter.handleShaped(root);
    }

    @Test
    @DisplayName("Verify Non-existant Recipe types are thrown")
    void testNoTypeHandler() {
        Assertions.assertThrows(RecipeParseException.class, () -> {
            HoconRecipeTypeAdapter.handleRecipe("non_existant_test_recipe_type", root);
        }, "The following recipe does not have a registered RECIPE_HANDLER for HOCON: non_existant_test_recipe_type");
    }
}
