/*
 * MIT License
 *
 * Copyright (c) 2019, 2020 i509VCB
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

package me.i509.bulkyshulkies.mod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import ca.stellardrift.confabricate.Confabricate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.loader.ConfigurationLoader;
import org.spongepowered.configurate.serialize.TypeSerializerCollection;

import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import me.i509.bulkyshulkies.api.ShulkerBoxType;
import me.i509.bulkyshulkies.api.block.old.base.OldShulkerBox;
import me.i509.bulkyshulkies.api.config.IntegrationServerConfig;
import me.i509.bulkyshulkies.api.config.section.MagnetismSection;
import me.i509.bulkyshulkies.mod.block.old.ender.EnderSlabBlock;
import me.i509.bulkyshulkies.mod.config.ServerConfigImpl;
import me.i509.bulkyshulkies.mod.config.section.MagnetismSectionImpl;

public final class BulkyShulkiesImpl {
	public static final ModContainer MOD_CONTAINER = null;
	public static final RegistryKey<Registry<ShulkerBoxType>> SHULKER_BOX_TYPE_KEY = RegistryKey.ofRegistry(BulkyShulkiesImpl.id("shulker_box_type"));
	public static final Registry<ShulkerBoxType> SHULKER_BOX_TYPE = FabricRegistryBuilder.createSimple(ShulkerBoxType.class, BulkyShulkiesImpl.id("shulker_box_type")).buildAndRegister();
	private static final Logger LOGGER = LogManager.getLogger(BulkyShulkiesImpl.class);

	private static final BulkyShulkiesImpl INSTANCE;
	private static List<Predicate<ItemStack>> disallowedItems = new ArrayList<>();

	private MainConfig mainConf;
	private ConfigurationLoader<CommentedConfigurationNode> mainConfLoader;

	private BulkyShulkiesImpl() throws IOException {
		BulkyShulkiesImpl.disallowedItems.add((stack) -> Block.getBlockFromItem(stack.getItem()) instanceof ShulkerBoxBlock);
		BulkyShulkiesImpl.disallowedItems.add((stack) -> Block.getBlockFromItem(stack.getItem()) instanceof OldShulkerBox);

		Path configLocation = FabricLoader.getInstance().getConfigDir().resolve("bulkyshulkies");
		Path configFile = configLocation.resolve("bulkyshulkies.conf");

		if (!Files.exists(configLocation)) {
			Files.createDirectories(configLocation);
		}

		if (!Files.exists(configFile)) {
			Files.createFile(configFile);
		}

		try {
			this.mainConfLoader = HoconConfigurationLoader.builder().path(configFile).build();

			CommentedConfigurationNode mainConfigRoot = this.mainConfLoader.load(ConfigurationOptions.defaults().header(MainConfig.HEADER).shouldCopyDefaults(true));

			this.mainConf = mainConfigRoot.get(MAIN_CONFIG_TYPE_TOKEN, new MainConfig());
			this.mainConfLoader.save(mainConfigRoot);
		} catch (Exception e) {
			e.printStackTrace(); // Well some invalid syntax
			return;
		}

		for (String id : this.mainConf.getNotAllowedInShulkers()) {
			try {
				Identifier identifier = new Identifier(id);
				Optional<Item> item = Registry.ITEM.getOrEmpty(identifier);

				if (!item.isPresent()) {
					this.getLogger().error("Tried to register item: " + identifier.toString() + " to the shulker box blacklist, but it is not present within the ITEM registry.");
					continue;
				}

				BulkyShulkiesImpl.addDisallowedShulkerItem((stack) -> Registry.ITEM.getId(stack.getItem()).equals(identifier));
			} catch (InvalidIdentifierException e) {
				this.getLogger().error("Item: " + id + " is not a valid identifier, so it failed to be registered into the shulker box blacklist.", e);
			}
		}
	}

	public static BulkyShulkiesImpl getInstance() {
		return BulkyShulkiesImpl.INSTANCE;
	}

	public static Identifier id(String path) {
		return new Identifier(BulkyShulkiesMod.MODID, path);
	}

	public static ServerConfigImpl getServerConfig() {
		return null;
	}

	// TODO: Impl
	public static IntegrationServerConfig getIntegrationServerConfig() {
		return null;
	}

	public static HoconConfigurationLoader createLoader(String name) {
		return HoconConfigurationLoader.builder()
				.path(FabricLoader.getInstance().getConfigDir().resolve("bulkyshulkies").resolve(name + ".conf"))
				.defaultOptions(createOptions())
				.build();
	}

	private static ConfigurationOptions createOptions() {
		return Confabricate.confabricateOptions().serializers(
				TypeSerializerCollection.builder()
						.register(MagnetismSection.class, MagnetismSectionImpl.SERIALIZER)
						.build()
		);
	}

	public Logger getLogger() {
		return BulkyShulkiesImpl.LOGGER;
	}

	public MainConfig getConfig() {
		return this.mainConf;
	}

	public static void addDisallowedShulkerItem(Predicate<ItemStack> predicate) {
		BulkyShulkiesImpl.disallowedItems.add(predicate);
	}

	public boolean canInsertItem(ItemStack stack) {
		if (stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() instanceof EnderSlabBlock) {
			return true;
		}

		for (Predicate<ItemStack> disallowedItems : BulkyShulkiesImpl.disallowedItems) {
			if (disallowedItems.test(stack)) {
				return false;
			}
		}

		return true;
	}

	static {
		BulkyShulkiesImpl instance;

		try {
			instance = new BulkyShulkiesImpl();
		} catch (IOException e) {
			e.printStackTrace();
			instance = null;
		}

		INSTANCE = instance;
	}
}
