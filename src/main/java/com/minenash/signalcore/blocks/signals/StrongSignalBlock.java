package com.minenash.signalcore.blocks.signals;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class StrongSignalBlock extends SignalBlock {

    public StrongSignalBlock(boolean debug) {
        super(debug);
    }
    public StrongSignalBlock(boolean debug, AbstractBlock.Settings settings) {
        super(debug, settings);
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

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(POWER);
    }


}
