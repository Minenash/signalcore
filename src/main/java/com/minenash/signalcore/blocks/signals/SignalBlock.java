package com.minenash.signalcore.blocks.signals;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class SignalBlock extends Block {
    public static final IntProperty POWER = Properties.POWER;
    public final boolean debug;

    public SignalBlock(boolean debug) {
        super(FabricBlockSettings.of(Material.STONE));
        this.debug = debug;
        this.setDefaultState(this.getDefaultState().with(POWER, 0));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (debug && !world.isClient) {
            world.setBlockState(pos, state.cycle(POWER), Block.NOTIFY_NEIGHBORS);
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.CONSUME;
        }
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) { return true; }


    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(POWER);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWER);
    }



}
