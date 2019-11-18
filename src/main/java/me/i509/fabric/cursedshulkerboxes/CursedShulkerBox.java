/*
 * MIT License
 *
 * Copyright (c) 2019 i509VCB
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.i509.fabric.cursedshulkerboxes;

import com.google.common.reflect.TypeToken;
import me.i509.fabric.cursedshulkerboxes.api.block.base.AbstractCursedShulkerBoxBlock;
import me.i509.fabric.cursedshulkerboxes.config.MainConfig;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.item.ItemStack;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.DefaultObjectMapperFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.SimpleMessage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CursedShulkerBox {
    private MainConfig mainConf;
    private ConfigurationLoader<CommentedConfigurationNode> mainConfLoader;
    private ConfigurationLoader<CommentedConfigurationNode> recipeConfLoader;

    private CursedShulkerBox() {
        disallowedItems.add((stack) -> Block.getBlockFromItem(stack.getItem()) instanceof ShulkerBoxBlock);
        disallowedItems.add((stack) -> Block.getBlockFromItem(stack.getItem()) instanceof AbstractCursedShulkerBoxBlock);
        /*
        if(!configLocation.exists()) {
            configLocation.mkdirs();
        }
        try {
            if(!configFile.exists()) {
                configFile.createNewFile();
                getLogger().info("Creating config file");
            }

            mainConfLoader = HoconConfigurationLoader.builder()
                    .setFile(configFile).build();

            CommentedConfigurationNode mainConfigRoot = mainConfLoader.load(ConfigurationOptions.defaults().setHeader("HEADER")
                    .setObjectMapperFactory(DefaultObjectMapperFactory.getInstance()).setShouldCopyDefaults(true));
            mainConf = mainConfigRoot.getValue(TypeToken.of(MainConfig.class), new MainConfig());
            mainConfLoader.save(mainConfigRoot);

        } catch (Exception e) {
            e.printStackTrace(); // TODO Fail
        }
        */
    }

    private void reloadRecipes() {

    }

    private static final CursedShulkerBox instance = new CursedShulkerBox();
    private static final File configLocation = new File(FabricLoader.getInstance().getConfigDirectory(), "cursedshulkerboxes");
    private static final File recipesFile = new File(configLocation, "recipes.conf");
    private static final File configFile = new File(configLocation, "cursedshulkers.conf");

    public static CursedShulkerBox getInstance() {
        return instance;
    }

    private List<Predicate<ItemStack>> disallowedItems = new ArrayList<>();

    public Logger getLogger() {
        return LogManager.getLogger(new MessageFactory() {
            @Override
            public Message newMessage(Object message) {
                return new SimpleMessage("[CursedShulkerBoxes] " + message);
            }

            @Override
            public Message newMessage(String message) {
                return new SimpleMessage("[CursedShulkerBoxes] " + message);
            }

            @Override
            public Message newMessage(String message, Object... params) {
                return new SimpleMessage("[CursedShulkerBoxes] " + message + params);
            }
        });
    }

    public MainConfig getConfig() {
        return this.mainConf;
    }

    public void addDisallowedShulkerItem(Predicate<ItemStack> predicate) {
        this.disallowedItems.add(predicate);
    }

    public boolean canInsertItem(ItemStack stack) {
        for (Predicate<ItemStack> disallowedItems : disallowedItems) {
            if(disallowedItems.test(stack)) {
                return false;
            }
        }

        return true;
    }
}
