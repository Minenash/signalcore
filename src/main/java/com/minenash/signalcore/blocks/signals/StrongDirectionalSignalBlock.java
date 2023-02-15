package com.minenash.signalcore.blocks.signals;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class StrongDirectionalSignalBlock extends Block {
    public static final DirectionProperty FACING = Properties.FACING;

    private final int POWER;

    public StrongDirectionalSignalBlock(int power) {
        super(FabricBlockSettings.of(Material.STONE));
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
        POWER = power;
    }

    public int getPOWER() {
        return POWER;
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        for (Direction direction : Direction.values())
            world.updateNeighborsAlways(pos.offset(direction), this);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        for (Direction direction : Direction.values())
            world.updateNeighborsAlways(pos.offset(direction), this);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite());
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return direction == state.get(FACING).getOpposite() ? POWER : 0;
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return direction == state.get(FACING).getOpposite() ? POWER : 0;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

}
