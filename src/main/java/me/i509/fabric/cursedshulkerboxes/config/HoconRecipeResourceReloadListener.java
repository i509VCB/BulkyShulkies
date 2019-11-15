package me.i509.fabric.cursedshulkerboxes.config;

import com.google.common.reflect.TypeToken;
import me.i509.fabric.cursedshulkerboxes.CursedShulkerBoxMod;
import me.i509.fabric.cursedshulkerboxes.config.recipe.SerializedShapedCraftingRecipe;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.objectmapping.DefaultObjectMapperFactory;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class HoconRecipeResourceReloadListener implements IdentifiableResourceReloadListener {
    private static final String HEADER = "HD";
    /*
    private static final ConfigurationOptions LOADER_OPTIONS = ConfigurationOptions.defaults()
            .setHeader(HEADER)
            .setSerializers(TypeSerializers.getDefaultSerializers().newChild()
                    .registerType(TypeToken.of(SerializedShapedCraftingRecipe.class), new SerializedShapedCraftingRecipe.Serializer())
            );

    */
    @Override
    public Identifier getFabricId() {
        return CursedShulkerBoxMod.id("resource_listener");
    }

    @Override
    public CompletableFuture<Void> reload(Synchronizer synchronizer, ResourceManager resourceManager, Profiler profiler, Profiler profiler1, Executor executor, Executor executor1) {
        //HoconConfigurationLoader.builder().getDefaultOptions().setObjectMapperFactory(DefaultObjectMapperFactory.getInstance().)
        return null;
    }
}
