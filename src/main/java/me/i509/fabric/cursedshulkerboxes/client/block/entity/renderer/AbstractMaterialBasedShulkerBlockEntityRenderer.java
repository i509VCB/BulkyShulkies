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
import me.i509.fabric.cursedshulkerboxes.api.block.base.AbstractShulkerBoxBlock;
import me.i509.fabric.cursedshulkerboxes.api.block.base.BaseShulkerBlock;
import me.i509.fabric.cursedshulkerboxes.api.block.material.AbstractMaterialBasedShulkerBoxBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.model.ShulkerEntityModel;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public abstract class AbstractMaterialBasedShulkerBlockEntityRenderer<BE extends AbstractMaterialBasedShulkerBoxBlockEntity> extends BlockEntityRenderer<BE> {
    protected ShulkerEntityModel<ShulkerEntity> model;

    protected AbstractMaterialBasedShulkerBlockEntityRenderer(ShulkerEntityModel model) {
        this.model = model;
    }

    public abstract Identifier getSkin();

    public abstract Identifier[] getSkinColors();

    public void render(AbstractMaterialBasedShulkerBoxBlockEntity blockEntity, double x, double y, double z, float float_1, int int_1) {
        Direction facing = Direction.UP;

        if (blockEntity.hasWorld()) {
            BlockState blockState = this.getWorld().getBlockState(blockEntity.getPos());

            if (blockState.getBlock() instanceof AbstractShulkerBoxBlock) {
                facing = blockState.get(BaseShulkerBlock.FACING);
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
                this.bindTexture(getSkin());
            } else {
                this.bindTexture(getSkinColors()[color.getId()]);
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
        switch (facing) {
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
