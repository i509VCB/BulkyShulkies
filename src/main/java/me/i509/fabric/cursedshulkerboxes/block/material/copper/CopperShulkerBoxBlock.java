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

package me.i509.fabric.cursedshulkerboxes.block.material.copper;

import me.i509.fabric.cursedshulkerboxes.api.block.material.AbstractMaterialBasedShulkerBoxBlock;
import me.i509.fabric.cursedshulkerboxes.registry.ShulkerBlocks;
import me.i509.fabric.cursedshulkerboxes.util.DefaultReturnHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DyeColor;
import net.minecraft.util.SystemUtil;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class CopperShulkerBoxBlock extends AbstractMaterialBasedShulkerBoxBlock {
    private static final Map<DyeColor, Block> COLOR_BLOCK_MAP = SystemUtil.consume(new DefaultReturnHashMap<>(ShulkerBlocks.COPPER_SHULKER_BOX), (map) -> {
        map.put(DyeColor.RED, ShulkerBlocks.RED_COPPER_SHULKER_BOX);
    });

    public CopperShulkerBoxBlock(Settings settings, @Nullable DyeColor color) {
        super(settings, 36, color);
    }

    @Override
    public ItemStack getItemStack(@Nullable DyeColor color) {
        return new ItemStack(COLOR_BLOCK_MAP.get(color)); // TODO Impl
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new CopperShulkerBoxBlockEntity(this.getColor());
    }
}
