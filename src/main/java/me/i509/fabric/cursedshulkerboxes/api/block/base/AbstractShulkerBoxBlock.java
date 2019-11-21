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

package me.i509.fabric.cursedshulkerboxes.api.block.base;

import me.i509.fabric.cursedshulkerboxes.CursedShulkerBoxMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.container.Container;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateFactory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.loot.context.LootContextParameters;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

public abstract class AbstractShulkerBoxBlock extends BlockWithEntity implements BaseShulkerBlock {
    protected static final Identifier CONTENTS = new Identifier("contents");
    protected static final SingleTypePropertyRetriever<SidedInventory> INVENTORY_RETRIEVER = blockEntity -> blockEntity;

    protected DyeColor color;
    protected BaseShulkerBlockEntity.AnimationStatus animationStage;
    protected final int slotCount;

    protected AbstractShulkerBoxBlock(Settings settings, int slotCount, @Nullable DyeColor color) {
        super(settings);
        this.color = color;
        this.slotCount = slotCount;
        this.setDefaultState(getStateFactory().getDefaultState().with(FACING, Direction.UP));
    }

    @Override
    public abstract BlockEntity createBlockEntity(BlockView blockView);

    @Override
    @Environment(EnvType.CLIENT)
    public boolean hasBlockEntityBreakingRender(BlockState blockState) {
        return true;
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    /*
    @Override
    public boolean activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        if (world.isClient) {
            return true;
        } else if (player.isSpectator()) {
            return true;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(blockPos);
            if (blockEntity instanceof AbstractCursedShulkerBoxBlockEntity) {
                Direction facing = blockState.get(FACING);
                AbstractCursedShulkerBoxBlockEntity cursedBlockEntity = (AbstractCursedShulkerBoxBlockEntity) blockEntity;
                boolean shouldOpen;
                if(cursedBlockEntity.getAnimationStage() == AnimationStatus.CLOSED) {
                    Box openBox = getOpenBox(facing);
                    shouldOpen = world.doesNotCollide(openBox.offset(blockPos.offset(facing)));
                } else {
                    shouldOpen = true;
                }
                //if (this.isObstructionFree(cursedBlockEntity, facing, world, blockPos)) {
                if (shouldOpen) {
                    if (cursedBlockEntity.checkUnlocked(player)) {
                        cursedBlockEntity.checkLootInteraction(player);
                        ContainerProviderRegistry.INSTANCE.openContainer(CursedShulkerBoxMod.id("shulkerscrollcontainer"), player, (packetByteBuf -> {
                            packetByteBuf.writeBlockPos(blockPos);
                            packetByteBuf.writeText(cursedBlockEntity.getDisplayName());
                        }));
                        player.incrementStat(Stats.OPEN_SHULKER_BOX);
                    }
                }

                return true;
            } else {
                return false;
            }
        }
    }*/

    public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return true;
        } else if (player.isSpectator()) {
            return true;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof AbstractShulkerBoxBlockEntity) {
                Direction direction = state.get(FACING);
                AbstractShulkerBoxBlockEntity shulkerBoxBlockEntity = (AbstractShulkerBoxBlockEntity)blockEntity;
                boolean bl2;
                if (shulkerBoxBlockEntity.getAnimationStage() == BaseShulkerBlockEntity.AnimationStatus.CLOSED) {
                    Box box = VoxelShapes
                            .fullCube()
                            .getBoundingBox()
                            .stretch((double)(0.5F * (float)direction.getOffsetX()), (double)(0.5F * (float)direction.getOffsetY()), (double)(0.5F * (float)direction.getOffsetZ()))
                            .shrink((double)direction.getOffsetX(), (double)direction.getOffsetY(), (double)direction.getOffsetZ());
                    bl2 = world.doesNotCollide(box.offset(pos.offset(direction)));
                } else {
                    bl2 = true;
                }

                if (bl2) {
                    //player.openContainer(shulkerBoxBlockEntity);
                    ContainerProviderRegistry.INSTANCE.openContainer(CursedShulkerBoxMod.id("shulkerscrollcontainer"), player, (packetByteBuf -> {
                        packetByteBuf.writeBlockPos(pos);
                        packetByteBuf.writeText(shulkerBoxBlockEntity.getDisplayName());
                    }));
                    player.incrementStat(Stats.OPEN_SHULKER_BOX);
                }

                return true;
            } else {
                return false;
            }
        }
    }

    public boolean isObstructionFree(BaseShulkerBlockEntity blockEntity, Direction facing, World world, BlockPos blockPos) {
        if (blockEntity.getAnimationStage() == BaseShulkerBlockEntity.AnimationStatus.CLOSED) {
            Box openBox = getOpenBox(facing);
            return world.doesNotCollide(openBox.offset(blockPos.offset(facing)));
        } else {
            return true;
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext placementContext) {
        return this.getDefaultState().with(FACING, placementContext.getSide());
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactoryBuilder) {
        stateFactoryBuilder.add(FACING);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void buildTooltip(ItemStack itemStack, @Nullable BlockView blockView, List<Text> list, TooltipContext tooltipContext) {
        super.buildTooltip(itemStack, blockView, list, tooltipContext);
        CompoundTag blockEntityTag = itemStack.getSubTag("BlockEntityTag");
        if (blockEntityTag != null) {
            if (blockEntityTag.containsKey("LootTable", NbtType.STRING)) {
                list.add(new LiteralText("???????"));
            }

            if (blockEntityTag.containsKey("Items", NbtType.LIST)) {
                DefaultedList<ItemStack> itemStacks = DefaultedList.ofSize(this.slotCount, ItemStack.EMPTY);
                Inventories.fromTag(blockEntityTag, itemStacks);
                int currentPosition = 0;
                int filledSlots = 0;
                Iterator<ItemStack> itemIterator = itemStacks.iterator();

                while(itemIterator.hasNext()) {
                    ItemStack stack = itemIterator.next();
                    if (!stack.isEmpty()) {
                        ++filledSlots;
                        if (currentPosition <= 4) {
                            ++currentPosition;
                            Text text_1 = stack.getName().deepCopy();
                            text_1.append(" x").append(String.valueOf(stack.getCount()));
                            list.add(text_1);
                        }
                    }
                }

                if (filledSlots - currentPosition > 0) {
                    list.add((new TranslatableText("container.shulkerBox.more", new Object[]{filledSlots - currentPosition})).formatted(Formatting.ITALIC));
                }
            }
        }

    }

    @Override
    public void onBreak(World world, BlockPos blockPos, BlockState blockState, PlayerEntity playerEntity) {
        BlockEntity blockEntity = world.getBlockEntity(blockPos);

        if (blockEntity instanceof AbstractShulkerBoxBlockEntity) {
            AbstractShulkerBoxBlockEntity abstractShulkerBoxBlockEntity = (AbstractShulkerBoxBlockEntity)blockEntity;

            if (!world.isClient && playerEntity.isCreative() && !abstractShulkerBoxBlockEntity.isInvEmpty()) {
                ItemStack stack = getItemStack(this.getColor());
                CompoundTag serializedInventory = abstractShulkerBoxBlockEntity.serializeInventory(new CompoundTag());

                if (!serializedInventory.isEmpty()) {
                    stack.putSubTag("BlockEntityTag", serializedInventory);
                }

                if (abstractShulkerBoxBlockEntity.hasCustomName()) {
                    stack.setCustomName(abstractShulkerBoxBlockEntity.getCustomName());
                }

                ItemEntity itemEntity = new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), stack);
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
            } else {
                abstractShulkerBoxBlockEntity.checkLootInteraction(playerEntity);
            }
        }

        super.onBreak(world, blockPos, blockState, playerEntity);
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState blockState, net.minecraft.world.loot.context.LootContext.Builder builder) {
        BlockEntity blockEntity = builder.getNullable(LootContextParameters.BLOCK_ENTITY);
        if (blockEntity instanceof AbstractShulkerBoxBlockEntity) {
            AbstractShulkerBoxBlockEntity abstractShulkerBoxBlockEntity = (AbstractShulkerBoxBlockEntity)blockEntity;
            builder = builder.putDrop(CONTENTS, (lootContext, consumer) -> {
                for(int inventoryPos = 0; inventoryPos < abstractShulkerBoxBlockEntity.getInvSize(); ++inventoryPos) {
                    consumer.accept(abstractShulkerBoxBlockEntity.getInvStack(inventoryPos));
                }
            });
        }

        return super.getDroppedStacks(blockState, builder);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public ItemStack getPickStack(BlockView blockView, BlockPos blockPos, BlockState blockState) {
        ItemStack pickStack = super.getPickStack(blockView, blockPos, blockState);
        AbstractShulkerBoxBlockEntity tallShulkerBoxBlockEntity = (AbstractShulkerBoxBlockEntity)blockView.getBlockEntity(blockPos);
        CompoundTag serializedInventory = tallShulkerBoxBlockEntity.serializeInventory(new CompoundTag());
        if (!serializedInventory.isEmpty()) {
            pickStack.putSubTag("BlockEntityTag", serializedInventory);
        }

        return pickStack;
    }

    @Override
    public void onPlaced(World world, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
        if (itemStack.hasCustomName()) {
            BlockEntity blockEntity = world.getBlockEntity(blockPos);
            if (blockEntity instanceof AbstractShulkerBoxBlockEntity) {
                ((AbstractShulkerBoxBlockEntity)blockEntity).setCustomName(itemStack.getName());
            }
        }
    }

    @Override
    public void onBlockRemoved(BlockState blockState, World world, BlockPos blockPos, BlockState otherBlockState, boolean boolean_1) {
        if (blockState.getBlock() != otherBlockState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(blockPos);
            if (blockEntity instanceof AbstractShulkerBoxBlockEntity) {
                world.updateHorizontalAdjacent(blockPos, blockState.getBlock());
            }

            super.onBlockRemoved(blockState, world, blockPos, otherBlockState, boolean_1);
        }
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState blockState) {
        return PistonBehavior.DESTROY;
    }

    @Override
    public boolean isOpaque(BlockState blockState) {
        return false;
    }

    @Override
    public boolean hasComparatorOutput(BlockState blockState) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState blockState, World world, BlockPos blockPos) {
        return Container.calculateComparatorOutput((Inventory)world.getBlockEntity(blockPos));
    }

    public DyeColor getColor() {
        return this.color;
    }

    @Override
    public BlockState rotate(BlockState blockState, BlockRotation blockRotation) {
        return blockState.with(FACING, blockRotation.rotate(blockState.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState blockState, BlockMirror blockMirror) {
        return blockState.rotate(blockMirror.getRotation(blockState.get(FACING)));
    }

    public interface SingleTypePropertyRetriever<T> {
        T getFromShulker(AbstractShulkerBoxBlockEntity blockEntity);
    }

    @Nullable
    @Environment(EnvType.CLIENT)
    public static DyeColor getColor(Item item) {
        return getColor(Block.getBlockFromItem(item));
    }

    @Nullable
    @Environment(EnvType.CLIENT)
    public static DyeColor getColor(@Nullable Block block) {
        return block instanceof BaseShulkerBlock ? ((BaseShulkerBlock)block).getColor() : null;
    }

    public static SidedInventory getInventoryStatic(IWorld world, BlockPos pos) {
        return retrieve(world.getBlockState(pos), world, pos, INVENTORY_RETRIEVER);
    }

    private static <T> T retrieve(BlockState clickedState, IWorld world, BlockPos clickedPos, SingleTypePropertyRetriever<T> propertyRetriever) {
        BlockEntity clickedBlockEntity = world.getBlockEntity(clickedPos);
        if (!(clickedBlockEntity instanceof AbstractShulkerBoxBlockEntity)) {
            return null;
        }

        AbstractShulkerBoxBlockEntity abstractShulkerBoxBlockEntity = (AbstractShulkerBoxBlockEntity) clickedBlockEntity;
        return propertyRetriever.getFromShulker(abstractShulkerBoxBlockEntity);
    }
}