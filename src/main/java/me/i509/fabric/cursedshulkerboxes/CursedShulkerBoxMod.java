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

import me.i509.fabric.cursedshulkerboxes.api.block.base.AbstractCursedShulkerBoxBlock;
import me.i509.fabric.cursedshulkerboxes.container.ShulkerBoxScrollableContainer;
import me.i509.fabric.cursedshulkerboxes.extension.ShulkerHooks;
import me.i509.fabric.cursedshulkerboxes.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CursedShulkerBoxMod implements ModInitializer {
    public static final String MODID = "cursedshulkerboxes";

    public static Identifier id(String path) {
        return new Identifier(MODID, path);
    }

    @Override
    public void onInitialize() {
        CursedShulkerBox.getInstance();
        ShulkerBlocks.init();
        ShulkerBlockEntities.init();
        ShulkerItems.init();
        ShulkerItemGroups.init();
        ShulkerHooks.init();

        ContainerProviderRegistry.INSTANCE.registerFactory(id("shulkerscrollcontainer"), ((syncId, identifier, player, buf) -> {
            BlockPos pos = buf.readBlockPos();
            Text name = buf.readText();
            World world = player.getEntityWorld();
            return new ShulkerBoxScrollableContainer(syncId, player.inventory, AbstractCursedShulkerBoxBlock.getInventoryStatic(world, pos), name);
        }));
    }
}