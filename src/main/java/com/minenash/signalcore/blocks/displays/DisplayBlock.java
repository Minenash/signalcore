package com.minenash.signalcore.blocks.displays;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DisplayBlock extends Block {
    public static final IntProperty POWER = Properties.POWER;

    public DisplayBlock() {
        super(FabricBlockSettings.of(Material.REDSTONE_LAMP));
        this.setDefaultState(this.getDefaultState().with(POWER, 0));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(POWER, ctx.getWorld().getReceivedRedstonePower(ctx.getBlockPos()));
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient) {
            int receivedPower = getReceivedPower(world, pos);
            if (state.get(POWER) != receivedPower)
                world.setBlockState(pos, state.with(POWER, receivedPower), Block.NOTIFY_LISTENERS);
        }
    }

    protected int getReceivedPower(World world, BlockPos pos) {
        return world.getReceivedRedstonePower(pos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWER);
    }

}
