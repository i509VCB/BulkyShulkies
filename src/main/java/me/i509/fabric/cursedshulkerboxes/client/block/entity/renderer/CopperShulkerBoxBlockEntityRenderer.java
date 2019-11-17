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

package me.i509.fabric.cursedshulkerboxes.client.block.entity.renderer;

import me.i509.fabric.cursedshulkerboxes.CursedShulkerBoxMod;
import me.i509.fabric.cursedshulkerboxes.block.material.copper.CopperShulkerBoxBlockEntity;
import net.minecraft.client.render.entity.model.ShulkerEntityModel;
import net.minecraft.util.Identifier;

public class CopperShulkerBoxBlockEntityRenderer extends AbstractMaterialBasedShulkerBlockEntityRenderer<CopperShulkerBoxBlockEntity> {
    private static final Identifier SKIN = CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker.png");
    private static final Identifier[] SKIN_COLOR = new Identifier[] {
            CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker_white.png"),
            CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker_orange.png"),
            CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker_magenta.png"),
            CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker_light_blue.png"),
            CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker_yellow.png"),
            CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker_lime.png"),
            CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker_pink.png"),
            CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker_gray.png"),
            CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker_light_gray.png"),
            CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker_cyan.png"),
            CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker_purple.png"),
            CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker_blue.png"),
            CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker_brown.png"),
            CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker_green.png"),
            CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker_red.png"),
            CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker_black.png")
    };

    public CopperShulkerBoxBlockEntityRenderer(ShulkerEntityModel model) {
        super(model);
    }

    @Override
    public Identifier getSkin() {
        return SKIN;
    }

    @Override
    public Identifier[] getSkinColors() {
        return SKIN_COLOR;
    }
}
