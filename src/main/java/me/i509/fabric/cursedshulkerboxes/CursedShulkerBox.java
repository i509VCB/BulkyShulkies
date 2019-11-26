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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.DefaultObjectMapperFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.AbstractMessageFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;

import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.item.ItemStack;

import net.fabricmc.loader.api.FabricLoader;

import me.i509.fabric.cursedshulkerboxes.api.block.base.BaseShulkerBlock;
import me.i509.fabric.cursedshulkerboxes.config.MainConfig;

public class CursedShulkerBox {
	private MainConfig mainConf;
	private ConfigurationLoader<CommentedConfigurationNode> mainConfLoader;
	private ConfigurationLoader<CommentedConfigurationNode> recipeConfLoader;

	private CursedShulkerBox() throws IOException {
		System.out.println(FabricLoader.getInstance().getConfigDirectory().toString());

		disallowedItems.add((stack) -> Block.getBlockFromItem(stack.getItem()) instanceof ShulkerBoxBlock);
		disallowedItems.add((stack) -> Block.getBlockFromItem(stack.getItem()) instanceof BaseShulkerBlock);

		Path configLocations = FabricLoader.getInstance().getConfigDirectory().toPath().resolve("cursedshulkerboxes");
		Path configFiles = configLocations.resolve("cursedshulkers.conf");

		Files.isDirectory(configLocations);

		if (!Files.exists(configLocations)) {
			Files.createDirectories(configLocations);
		}

		if (!Files.exists(configFiles)) {
			Files.createFile(configFiles);
		}

		try {
			mainConfLoader = HoconConfigurationLoader.builder()
					.setPath(configFiles).build();

			CommentedConfigurationNode mainConfigRoot = mainConfLoader.load(ConfigurationOptions.defaults().setHeader("HEADER")
					.setObjectMapperFactory(DefaultObjectMapperFactory.getInstance()).setShouldCopyDefaults(true));
			mainConf = mainConfigRoot.getValue(TypeToken.of(MainConfig.class), new MainConfig());
			mainConfLoader.save(mainConfigRoot);
		} catch (Exception e) {
			e.printStackTrace(); // TODO Fail
		}
	}

	private void reloadRecipes() {
		// TODO Impl
	}

	private static final CursedShulkerBox instance;

	private static final Path configLocation = FabricLoader.getInstance().getConfigDirectory().toPath().resolve("cursedshulkerboxes");
	private static final Path recipesFile = configLocation.resolve("recipes.conf");
	private static final Path configFile = configLocation.resolve("cursedshulkers.conf");

	public static CursedShulkerBox getInstance() {
		return instance;
	}

	private List<Predicate<ItemStack>> disallowedItems = new ArrayList<>();

	public Logger getLogger() {
		return LogManager.getLogger(new AbstractMessageFactory() {
			@Override
			public Message newMessage(String message, Object... params) {
				return new SimpleMessage("[CursedShulkerBoxes] " + message);
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
			if (disallowedItems.test(stack)) {
				return false;
			}
		}

		return true;
	}

	static {
		CursedShulkerBox tmp;

		try {
			tmp = new CursedShulkerBox();
		} catch (IOException e) {
			e.printStackTrace();
			tmp = null;
		}

		instance = tmp;
	}
}
