package me.i509.fabric.cursedshulkerboxes.api.resource;

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceReloadListenerKeys;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloadListener;
import net.minecraft.resource.ResourceType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class HoconDataLoader implements IdentifiableResourceReloadListener {

    public HoconDataLoader(CommentedConfigurationNode root) {

    }

    public CompletableFuture<Void> reload(ResourceReloadListener.Synchronizer synchronizer, ResourceManager resourceManager, Profiler profiler, Profiler profiler1, Executor executor, Executor executor1) {
        try {
            //if(resourceManager instanceof E){}
            resourceManager.getAllResources(ResourceReloadListenerKeys.RECIPES);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Identifier getFabricId() {
        return null;
    }

    public void e() throws IOException {
        CommentedConfigurationNode root = HoconConfigurationLoader.builder().build().load();
        net.fabricmc.fabric.api.resource.ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new HoconDataLoader(root));
    }
}
