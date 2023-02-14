package com.minenash.signalcore.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class StrongSignalBlock extends Block {

    private final int POWER;

    public StrongSignalBlock(int power) {
        super(FabricBlockSettings.copy(Blocks.REDSTONE_BLOCK));
        POWER = power;
    }

    public int getPOWER() {
        return POWER;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return POWER;
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return POWER;
    }

}
