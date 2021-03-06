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

package me.i509.bulkyshulkies.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import dev.onyxstudios.cca.api.v3.component.ComponentFactory;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import me.i509.bulkyshulkies.mod.BulkyShulkiesImpl;
import me.i509.bulkyshulkies.api.block.ShulkerBox;
import me.i509.bulkyshulkies.api.block.entity.ShulkerBoxBlockEntity;
import me.i509.bulkyshulkies.api.block.old.entity.inventory.ShulkerBlockEntity;
import me.i509.bulkyshulkies.mod.block.entity.InventoryShulkerBoxBlockEntity;
import me.i509.bulkyshulkies.mod.block.entity.SimpleShulkerBoxBlockEntity;
import me.i509.bulkyshulkies.mod.item.ShulkerBlockItem;
import org.jetbrains.annotations.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

/**
 * Represents a type of shulker box.
 */
public final class ShulkerBoxType {
	private final Identifier id;
	private final Map<ComponentKey<?>, ComponentFactory<? extends ShulkerBoxBlockEntity, ?>> blockEntityComponents;
	private final Map<ComponentKey<?>, ComponentFactory<ItemStack, ?>> itemStackComponents;
	private final DirectionProperty directionProperty;
	private final boolean inventory;
	@Nullable
	private final BlockEntityTicker<? extends ShulkerBoxBlockEntity> clientTicker;
	@Nullable
	private final BlockEntityTicker<? extends ShulkerBoxBlockEntity> serverTicker;
	private final AbstractBlock.Settings blockSettings;
	private final Item.Settings itemSettings;
	private final BlockEntityType<? extends ShulkerBlockEntity> blockEntityType;
	private final Block block;
	private final boolean allowInsertionIntoShulkerBox;

	public static ShulkerBoxType.Builder builder() {
		return new ShulkerBoxType.Builder();
	}

	private ShulkerBoxType(
			Identifier id, Map<ComponentKey<?>,
			ComponentFactory<? extends ShulkerBoxBlockEntity, ?>> blockEntityComponents,
			Map<ComponentKey<?>, ComponentFactory<ItemStack, ?>> itemStackComponents,
			DirectionProperty directionProperty,
			boolean inventory,
			@Nullable BlockEntityTicker<? extends ShulkerBoxBlockEntity> clientTicker,
			@Nullable BlockEntityTicker<? extends ShulkerBoxBlockEntity> serverTicker,
			AbstractBlock.Settings blockSettings,
			Item.Settings itemSettings,
			boolean allowInsertionIntoShulkerBox) {
		this.id = id;
		this.blockEntityComponents = Collections.unmodifiableMap(new HashMap<>(blockEntityComponents));
		this.itemStackComponents = Collections.unmodifiableMap(new HashMap<>(itemStackComponents));
		this.directionProperty = directionProperty;
		this.inventory = inventory;
		this.clientTicker = clientTicker;
		this.serverTicker = serverTicker;
		this.blockSettings = blockSettings;
		this.itemSettings = itemSettings;
		this.allowInsertionIntoShulkerBox = allowInsertionIntoShulkerBox;
		this.block = null; // TODO

		if (inventory) {
			//noinspection unchecked
			this.blockEntityType = (BlockEntityType<? extends ShulkerBlockEntity>) FabricBlockEntityTypeBuilder.create(this::createInventoryBlockEntity, this.block).build();
		} else {
			//noinspection unchecked
			this.blockEntityType = (BlockEntityType<? extends ShulkerBlockEntity>) FabricBlockEntityTypeBuilder.create(this::createSimpleBlockEntity, this.block).build();
		}
	}

	public Identifier getId() {
		return this.id;
	}

	public <B extends BlockEntity & ShulkerBlockEntity> BlockEntityType<B> getBlockEntityType() {
		//noinspection unchecked
		return (BlockEntityType<B>) this.blockEntityType;
	}

	@Nullable
	public BlockEntityTicker<? extends ShulkerBlockEntity> getClientTicker() {
		//noinspection unchecked
		return (BlockEntityTicker<? extends ShulkerBlockEntity>) this.clientTicker;
	}

	@Nullable
	public BlockEntityTicker<? extends ShulkerBlockEntity> getServerTicker() {
		//noinspection unchecked
		return (BlockEntityTicker<? extends ShulkerBlockEntity>) this.serverTicker;
	}

	public boolean hasInventory() {
		return this.inventory;
	}

	void register() {
		Registry.register(BulkyShulkiesImpl.SHULKER_BOX_TYPE, this.id, this);
		Registry.register(Registry.BLOCK, this.id, this.block);
		Registry.register(Registry.ITEM, this.id, new ShulkerBlockItem(this, this.itemSettings));
		Registry.register(Registry.BLOCK_ENTITY_TYPE, this.id, this.getBlockEntityType());
	}

	public <B extends Block & ShulkerBox> B getBlock() {
		//noinspection unchecked
		return (B) this.block;
	}

	public DirectionProperty getDirectionProperty() {
		return this.directionProperty;
	}

	public Map<ComponentKey<?>, ComponentFactory<ItemStack, ?>> getItemStackComponents() {
		return this.itemStackComponents;
	}

	public Map<ComponentKey<?>, ComponentFactory<? extends ShulkerBoxBlockEntity, ?>> getBlockEntityComponents() {
		return this.blockEntityComponents;
	}

	public BlockState getBlockState(Direction facing) {
		return this.block.getDefaultState().with(this.getDirectionProperty(), facing);
	}

	public Item getItem() {
		return this.block.asItem();
	}

	public ItemStack createItemStack() {
		return this.block.asItem().getDefaultStack();
	}

	public ItemStack createItemStack(Consumer<ItemStack> postProcessor) {
		Objects.requireNonNull(postProcessor, "Post processor cannot be null");
		final ItemStack stack = this.createItemStack();
		postProcessor.accept(stack);
		return stack;
	}

	public ShulkerBoxBlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return (ShulkerBoxBlockEntity) this.blockEntityType.instantiate(pos, state);
	}

	private SimpleShulkerBoxBlockEntity createSimpleBlockEntity(BlockPos pos, BlockState state) {
		return new SimpleShulkerBoxBlockEntity(this, this.blockEntityType, pos, state);
	}

	private InventoryShulkerBoxBlockEntity createInventoryBlockEntity(BlockPos pos, BlockState state) {
		return new InventoryShulkerBoxBlockEntity(this, null, -1, this.blockEntityType, pos, state);
	}

	public boolean canInsertIntoShulkerBox() {
		return this.allowInsertionIntoShulkerBox;
	}

	public static final class Builder {
		private Identifier id;
		private boolean inventory;
		private DirectionProperty directionProperty;
		@Nullable
		private BlockEntityTicker<? extends ShulkerBoxBlockEntity> clientTicker;
		@Nullable
		private BlockEntityTicker<? extends ShulkerBoxBlockEntity> serverTicker;
		private AbstractBlock.Settings blockSettings;
		private Item.Settings itemSettings;
		private final Map<ComponentKey<?>, ComponentFactory<? extends ShulkerBoxBlockEntity, ?>> blockEntityComponents = new HashMap<>();
		private final Map<ComponentKey<?>, ComponentFactory<ItemStack, ?>> itemStackComponents = new HashMap<>();
		private boolean allowInsertionIntoShulkerBox;

		private Builder() {
		}

		public Builder id(Identifier id) {
			this.id = Objects.requireNonNull(id);
			return this;
		}

		public <C extends ComponentV3> Builder attachToItemStack(ComponentKey<C> key, ComponentFactory<ItemStack, ?> factory) {
			Objects.requireNonNull(key);
			Objects.requireNonNull(factory);
			this.itemStackComponents.put(key, factory);
			return this;
		}

		public <C extends ComponentV3> Builder attachToBlockEntity(ComponentKey<C> key, ComponentFactory<? extends ShulkerBoxBlockEntity, ?> factory) {
			Objects.requireNonNull(key);
			Objects.requireNonNull(factory);
			this.blockEntityComponents.put(key, factory);
			return this;
		}

		public Builder directionProperty(DirectionProperty property) {
			this.directionProperty = Objects.requireNonNull(property);
			return this;
		}

		/**
		 * Specifies that this type of shulker box will have an inventory.
		 *
		 * @return this builder
		 */
		public Builder inventory() {
			this.inventory = true;
			return this;
		}

		/**
		 * Specifies that this type of shulker box may be inserted into another shulker box.
		 *
		 * <p><b>You are not recommended to use this builder parameter unless you know what you are doing!</b>
		 *
		 * @return this builder
		 */
		public Builder allowInsertionIntoShulkerBox() {
			this.allowInsertionIntoShulkerBox = true;
			return this;
		}

		public <B extends BlockEntity & ShulkerBoxBlockEntity> Builder clientTicker(BlockEntityTicker<B> ticker) {
			this.clientTicker = Objects.requireNonNull(ticker);
			return this;
		}

		public <B extends BlockEntity & ShulkerBoxBlockEntity> Builder serverTicker(BlockEntityTicker<B> ticker) {
			this.serverTicker = Objects.requireNonNull(ticker);
			return this;
		}

		public Builder blockSettings(AbstractBlock.Settings settings) {
			this.blockSettings = Objects.requireNonNull(settings);
			return this;
		}

		public Builder itemSettings(Item.Settings settings) {
			this.itemSettings = Objects.requireNonNull(settings);
			return this;
		}

		public ShulkerBoxType buildAndRegister() {
			Objects.requireNonNull(this.id);
			Objects.requireNonNull(this.directionProperty);
			Objects.requireNonNull(this.blockSettings);
			Objects.requireNonNull(this.itemSettings);

			final ShulkerBoxType type = new ShulkerBoxType(
					this.id,
					this.blockEntityComponents,
					this.itemStackComponents,
					this.directionProperty,
					this.inventory,
					this.clientTicker,
					this.serverTicker,
					this.blockSettings,
					this.itemSettings,
					this.allowInsertionIntoShulkerBox);

			type.register();
			return type;
		}
	}
}
