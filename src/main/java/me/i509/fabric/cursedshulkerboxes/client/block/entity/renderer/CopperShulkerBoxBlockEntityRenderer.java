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

import com.mojang.blaze3d.platform.GlStateManager;
import me.i509.fabric.cursedshulkerboxes.CursedShulkerBoxMod;
import me.i509.fabric.cursedshulkerboxes.block.material.copper.CopperShulkerBoxBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.model.ShulkerEntityModel;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class CopperShulkerBoxBlockEntityRenderer extends BlockEntityRenderer<CopperShulkerBoxBlockEntity> {
    public static final Identifier SKIN = CursedShulkerBoxMod.id("textures/blockentity/shulker/copper/shulker.png");
    public static final Identifier[] SKIN_COLOR = new Identifier[]{
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

    private final ShulkerEntityModel model;

    public CopperShulkerBoxBlockEntityRenderer(ShulkerEntityModel model) {
        this.model = model;
    }

    public void render(CopperShulkerBoxBlockEntity blockEntity, double x, double y, double z, float float_1, int int_1) {
        Direction direction_1 = Direction.UP;

        if (blockEntity.hasWorld()) {
            BlockState blockState = this.getWorld().getBlockState(blockEntity.getPos());

            if (blockState.getBlock() instanceof ShulkerBoxBlock) {
                direction_1 = (Direction) blockState.get(ShulkerBoxBlock.FACING);
            }
        }

        GlStateManager.enableDepthTest();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        GlStateManager.disableCull();
        if (int_1 >= 0) {
            this.bindTexture(CopperShulkerBoxBlockEntityRenderer.DESTROY_STAGE_TEXTURES[int_1]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scalef(4.0F, 4.0F, 1.0F);
            GlStateManager.translatef(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        } else {
            DyeColor color = blockEntity.getColor();

            if (color == null) {
                this.bindTexture(SKIN);
            } else {
                this.bindTexture(SKIN_COLOR[color.getId()]);
            }
        }

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        if (int_1 < 0) {
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        }

        GlStateManager.translatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GlStateManager.scalef(1.0F, -1.0F, -1.0F);
        GlStateManager.translatef(0.0F, 1.0F, 0.0F);
        float float_2 = 0.9995F;

        GlStateManager.scalef(0.9995F, 0.9995F, 0.9995F);
        GlStateManager.translatef(0.0F, -1.0F, 0.0F);
        switch (direction_1) {
            case DOWN:
                GlStateManager.translatef(0.0F, 2.0F, 0.0F);
                GlStateManager.rotatef(180.0F, 1.0F, 0.0F, 0.0F);
            case UP:
            default:
                break;
            case NORTH:
                GlStateManager.translatef(0.0F, 1.0F, 1.0F);
                GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotatef(180.0F, 0.0F, 0.0F, 1.0F);
                break;
            case SOUTH:
                GlStateManager.translatef(0.0F, 1.0F, -1.0F);
                GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
                break;
            case WEST:
                GlStateManager.translatef(-1.0F, 1.0F, 0.0F);
                GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotatef(-90.0F, 0.0F, 0.0F, 1.0F);
                break;
            case EAST:
                GlStateManager.translatef(1.0F, 1.0F, 0.0F);
                GlStateManager.rotatef(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotatef(90.0F, 0.0F, 0.0F, 1.0F);
        }

        this.model.method_2831().render(0.0625F);
        GlStateManager.translatef(0.0F, -blockEntity.getAnimationProgress(float_1) * 0.5F, 0.0F);
        GlStateManager.rotatef(270.0F * blockEntity.getAnimationProgress(float_1), 0.0F, 1.0F, 0.0F);
        this.model.method_2829().render(0.0625F);
        GlStateManager.enableCull();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (int_1 >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }
}
