package com.minenash.signalcore.blocks.signals;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class SignalContainerBlock extends SignalBlock {

    public SignalContainerBlock(boolean debug) {
        super(debug);
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) { return false; }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return 0;
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) { return true; }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return state.get(POWER);
    }


}
