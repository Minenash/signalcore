package com.minenash.signalcore.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SignalContainerBlock extends Block {

    private final int POWER;

    public SignalContainerBlock(int power) {
        super(FabricBlockSettings.copy(Blocks.REDSTONE_BLOCK));
        POWER = power;
    }

    public int getPOWER() {
        return POWER;
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return POWER;
    }
}
