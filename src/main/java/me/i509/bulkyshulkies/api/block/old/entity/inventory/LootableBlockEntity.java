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

package me.i509.bulkyshulkies.api.block.old.entity.inventory;

import org.checkerframework.checker.nullness.qual.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.util.NbtType;

/**
 * Represents a block entity which can hold a loot table.
 */
public interface LootableBlockEntity {
	Identifier getLootTableId();

	long getLootTableSeed();

	void setLootTable(Identifier id, long seed);

	void checkLootInteraction(@Nullable PlayerEntity playerEntity);

	/**
	 * Deserializes a loot table from a tag.
	 *
	 * @param tag the tag to deserialize from.
	 * @return true if a loot table was present. Otherwise false.
	 */
	default boolean lootTableFromTag(CompoundTag tag) {
		if (tag.contains("LootTable", NbtType.STRING)) {
			final Identifier lootTableId = new Identifier(tag.getString("LootTable"));
			final long lootTableSeed = tag.getLong("LootTableSeed");
			this.setLootTable(lootTableId, lootTableSeed);
			return true;
		}

		return false;
	}

	/**
	 * Serializes the block entity's loot table into a tag.
	 *
	 * @param tag the tag to serialize to
	 * @return true if the loot table was successfully written to the tag. Otherwise false.
	 */
	default boolean lootTableToTag(CompoundTag tag) {
		if (this.getLootTableId() == null) {
			return false;
		}

		tag.putString("LootTable", this.getLootTableId().toString());

		if (this.getLootTableSeed() != 0L) {
			tag.putLong("LootTableSeed", this.getLootTableSeed());
		}

		return true;
	}
}
