package me.i509.fabric.cursedshulkerboxes.abstraction.recipe;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.Identifier;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.commented.SimpleCommentedConfigurationNode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@FunctionalInterface
public interface HoconRecipeReloadEvent {
    Event<HoconRecipeReloadEvent> EVENT = EventFactory.createArrayBacked(HoconRecipeReloadEvent.class, (callbacks) -> (recipeBiConsumer) -> {
        for(HoconRecipeReloadEvent callback : callbacks) {
            callback.getRecipes(recipeBiConsumer);
        }
    });

    void getRecipes(BiConsumer<Identifier, CommentedConfigurationNode> recipe);

    static void exampleInvoke() {
        Map<Identifier, CommentedConfigurationNode> recipes = new HashMap<>();
        EVENT.invoker().getRecipes(recipes::put);
    }

    HoconRecipeReloadEvent recipese = (recipes) -> {
        recipes.accept(new Identifier("test:recipe"), SimpleCommentedConfigurationNode.root());
    };
}
