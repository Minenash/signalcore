package com.minenash.signalcore.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SignalDisplayBlock extends Block {
    public static final IntProperty POWER = Properties.POWER;

    public SignalDisplayBlock() {
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
            int currentPower = state.get(POWER);
            int receivedPower = world.getReceivedRedstonePower(pos);
            if (currentPower != receivedPower)
                world.setBlockState(pos, state.with(POWER, receivedPower), Block.NOTIFY_LISTENERS);
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(POWER) > 0 && !world.isReceivingRedstonePower(pos))
            world.setBlockState(pos, state.with(POWER, 0), Block.NOTIFY_LISTENERS);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWER);
    }

}
