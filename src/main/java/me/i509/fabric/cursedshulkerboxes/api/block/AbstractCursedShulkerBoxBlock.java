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

package me.i509.fabric.cursedshulkerboxes.api.block;

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
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.container.Container;
import net.minecraft.entity.EntityContext;
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
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.loot.context.LootContextParameters;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;

public abstract class AbstractCursedShulkerBoxBlock extends BlockWithEntity {
    public static DirectionProperty FACING = Properties.FACING;
    protected static final Identifier CONTENTS = new Identifier("contents");
    private static final PropertyRetriever<SidedInventory> INVENTORY_RETRIEVER = blockEntity -> blockEntity;

    protected DyeColor color;
    protected ShulkerBoxBlockEntity.AnimationStage animationStage;
    protected final int slotCount;

    protected AbstractCursedShulkerBoxBlock(Settings settings, int slotCount, @Nullable DyeColor color) {
        super(settings);
        this.color = color;
        this.slotCount = slotCount;
        this.setDefaultState(this.stateFactory.getDefaultState().with(FACING, Direction.UP));
    }

    @Nullable
    @Environment(EnvType.CLIENT)
    public static DyeColor getColor(Item item) {
        return getColor(Block.getBlockFromItem(item));
    }

    @Nullable
    @Environment(EnvType.CLIENT)
    public static DyeColor getColor(@Nullable Block block) {
        return block instanceof AbstractCursedShulkerBoxBlock ? ((AbstractCursedShulkerBoxBlock)block).getColor() : null;
    }

    public static SidedInventory getInventoryStatic(IWorld world, BlockPos pos) {
        return retrieve(world.getBlockState(pos), world, pos, INVENTORY_RETRIEVER);
    }

    private static <T> T retrieve(BlockState clickedState, IWorld world, BlockPos clickedPos, PropertyRetriever<T> propertyRetriever) {
        BlockEntity clickedBlockEntity = world.getBlockEntity(clickedPos);
        if (!(clickedBlockEntity instanceof AbstractCursedShulkerBoxBlockEntity)) {
            return null;
        }

        AbstractCursedShulkerBoxBlockEntity abstractCursedShulkerBoxBlockEntity = (AbstractCursedShulkerBoxBlockEntity) clickedBlockEntity;
        return propertyRetriever.getFromShulker(abstractCursedShulkerBoxBlockEntity);
    }

    /**
     * Gets the ItemStack from the color
     */
    protected abstract ItemStack getItemStack(DyeColor color);

    public abstract Box getOpenBox(Direction facing);

    @Override
    public abstract BlockEntity createBlockEntity(BlockView blockView);

    @Override
    public boolean canSuffocate(BlockState blockState, BlockView blockView, BlockPos blockPos) {
        return true;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean hasBlockEntityBreakingRender(BlockState blockState) {
        return true;
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult_1) {
        if (world.isClient) {
            return true;
        } else if (player.isSpectator()) {
            return true;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(blockPos);
            if (blockEntity instanceof AbstractCursedShulkerBoxBlockEntity) {
                Direction facing = blockState.get(FACING);
                AbstractCursedShulkerBoxBlockEntity abstractCursedShulkerBoxBlockEntity = (AbstractCursedShulkerBoxBlockEntity)blockEntity;
                boolean isNotObstructed;
                if (abstractCursedShulkerBoxBlockEntity.getAnimationStage() == ShulkerBoxBlockEntity.AnimationStage.CLOSED) {
                    Box openBox = getOpenBox(facing);
                    isNotObstructed = world.doesNotCollide(openBox.offset(blockPos.offset(facing)));
                } else {
                    isNotObstructed = true;
                }

                if (isNotObstructed) {
                    if (abstractCursedShulkerBoxBlockEntity.checkUnlocked(player)) {
                        abstractCursedShulkerBoxBlockEntity.checkLootInteraction(player);
                        ContainerProviderRegistry.INSTANCE.openContainer(CursedShulkerBoxMod.id("shulkerscrollcontainer"), player, (packetByteBuf -> {
                            packetByteBuf.writeBlockPos(blockPos);
                            packetByteBuf.writeText(abstractCursedShulkerBoxBlockEntity.getDisplayName());
                        }));
                        player.incrementStat(Stats.OPEN_SHULKER_BOX);
                    }
                }

                return true;
            } else {
                return false;
            }
        }
    }

    public BlockState getPlacementState(ItemPlacementContext placementContext) {
        return this.getDefaultState().with(FACING, placementContext.getSide());
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactoryBuilder) {
        stateFactoryBuilder.add(FACING);
    }

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

    public void onBreak(World world, BlockPos blockPos, BlockState blockState, PlayerEntity playerEntity) {
        BlockEntity blockEntity = world.getBlockEntity(blockPos);

        if (blockEntity instanceof AbstractCursedShulkerBoxBlockEntity) {
            AbstractCursedShulkerBoxBlockEntity abstractCursedShulkerBoxBlockEntity = (AbstractCursedShulkerBoxBlockEntity)blockEntity;

            if (!world.isClient && playerEntity.isCreative() && !abstractCursedShulkerBoxBlockEntity.isInvEmpty()) {
                ItemStack stack = getItemStack(this.getColor()); // TODO
                CompoundTag serializedInventory = abstractCursedShulkerBoxBlockEntity.serializeInventory(new CompoundTag());

                if (!serializedInventory.isEmpty()) {
                    stack.putSubTag("BlockEntityTag", serializedInventory);
                }

                if (abstractCursedShulkerBoxBlockEntity.hasCustomName()) {
                    stack.setCustomName(abstractCursedShulkerBoxBlockEntity.getCustomName());
                }

                ItemEntity itemEntity = new ItemEntity(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), stack);
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
            } else {
                abstractCursedShulkerBoxBlockEntity.checkLootInteraction(playerEntity);
            }
        }

        super.onBreak(world, blockPos, blockState, playerEntity);
    }

    public List<ItemStack> getDroppedStacks(BlockState blockState, net.minecraft.world.loot.context.LootContext.Builder builder) {
        BlockEntity blockEntity = builder.getNullable(LootContextParameters.BLOCK_ENTITY);
        if (blockEntity instanceof AbstractCursedShulkerBoxBlockEntity) {
            AbstractCursedShulkerBoxBlockEntity abstractCursedShulkerBoxBlockEntity = (AbstractCursedShulkerBoxBlockEntity)blockEntity;
            builder = builder.putDrop(CONTENTS, (lootContext, consumer) -> {
                for(int inventoryPos = 0; inventoryPos < abstractCursedShulkerBoxBlockEntity.getInvSize(); ++inventoryPos) {
                    consumer.accept(abstractCursedShulkerBoxBlockEntity.getInvStack(inventoryPos));
                }
            });
        }

        return super.getDroppedStacks(blockState, builder);
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getPickStack(BlockView blockView, BlockPos blockPos, BlockState blockState) {
        ItemStack pickStack = super.getPickStack(blockView, blockPos, blockState);
        AbstractCursedShulkerBoxBlockEntity tallShulkerBoxBlockEntity = (AbstractCursedShulkerBoxBlockEntity)blockView.getBlockEntity(blockPos);
        CompoundTag serializedInventory = tallShulkerBoxBlockEntity.serializeInventory(new CompoundTag());
        if (!serializedInventory.isEmpty()) {
            pickStack.putSubTag("BlockEntityTag", serializedInventory);
        }

        return pickStack;
    }

    public void onPlaced(World world, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
        if (itemStack.hasCustomName()) {
            BlockEntity blockEntity = world.getBlockEntity(blockPos);
            if (blockEntity instanceof AbstractCursedShulkerBoxBlockEntity) {
                ((AbstractCursedShulkerBoxBlockEntity)blockEntity).setCustomName(itemStack.getName());
            }
        }
    }

    public void onBlockRemoved(BlockState blockState, World world, BlockPos blockPos, BlockState otherBlockState, boolean boolean_1) {
        if (blockState.getBlock() != otherBlockState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(blockPos);
            if (blockEntity instanceof AbstractCursedShulkerBoxBlockEntity) {
                world.updateHorizontalAdjacent(blockPos, blockState.getBlock());
            }

            super.onBlockRemoved(blockState, world, blockPos, otherBlockState, boolean_1);
        }
    }

    public PistonBehavior getPistonBehavior(BlockState blockState) {
        return PistonBehavior.DESTROY;
    }

    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, EntityContext entityContext) {
        BlockEntity blockEntity = blockView.getBlockEntity(blockPos);
        return blockEntity instanceof AbstractCursedShulkerBoxBlockEntity ? VoxelShapes.cuboid(((AbstractCursedShulkerBoxBlockEntity)blockEntity).getBoundingBox(blockState)) : VoxelShapes.fullCube();
    }

    public boolean isOpaque(BlockState blockState_1) {
        return false;
    }

    public boolean hasComparatorOutput(BlockState blockState_1) {
        return true;
    }

    public int getComparatorOutput(BlockState blockState, World world, BlockPos blockPos) {
        return Container.calculateComparatorOutput((Inventory)world.getBlockEntity(blockPos));
    }

    public DyeColor getColor() {
        return this.color;
    }

    public BlockState rotate(BlockState blockState, BlockRotation blockRotation) {
        return blockState.with(FACING, blockRotation.rotate(blockState.get(FACING)));
    }

    public BlockState mirror(BlockState blockState, BlockMirror blockMirror) {
        return blockState.rotate(blockMirror.getRotation(blockState.get(FACING)));
    }

    interface PropertyRetriever<T> {
        T getFromShulker(AbstractCursedShulkerBoxBlockEntity blockEntity);
    }
}
