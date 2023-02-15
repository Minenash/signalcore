package com.minenash.signalcore.blocks.displays;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StrongDisplayBlock extends DisplayBlock {

    @Override
    protected int getReceivedPower(World world, BlockPos pos) {
        return world.getReceivedStrongRedstonePower(pos);
    }


}
