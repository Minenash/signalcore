package com.minenash.signalcore.blocks.displays;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.tick.TickPriority;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class DisplayTile extends Block {
    public static final IntProperty POWER = Properties.POWER;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final EnumMap<Direction,VoxelShape> FACING_TO_SHAPE = Maps.newEnumMap( ImmutableMap.of(
            Direction.NORTH, Block.createCuboidShape(3,  3, 15, 13, 13, 16),
            Direction.SOUTH, Block.createCuboidShape(3,  3, 0,  13, 13, 1),
            Direction.EAST,  Block.createCuboidShape(0,  3, 3,   1, 13, 13),
            Direction.WEST,  Block.createCuboidShape(15, 3, 3,  16, 13, 13)));

    public DisplayTile() {
        super(FabricBlockSettings.of(Material.REDSTONE_LAMP).noCollision().nonOpaque());
        this.setDefaultState(this.getDefaultState().with(POWER, 0).with(FACING, Direction.NORTH));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        ctx.getWorld().scheduleBlockTick(ctx.getBlockPos(), this, 1);

        for (Direction direction : ctx.getPlacementDirections())
            if (direction.getAxis().isHorizontal())
                return this.getDefaultState().with(FACING, direction.getOpposite());

        return null;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return FACING_TO_SHAPE.get(state.get(FACING));
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient) {
            int receivedPower = world.getReceivedRedstonePower( pos.offset( state.get(FACING).getOpposite() ));
            if (state.get(POWER) != receivedPower)
                world.setBlockState(pos, state.with(POWER, receivedPower), Block.NOTIFY_LISTENERS);
            world.scheduleBlockTick(pos, this, 1);
        }
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWER, FACING);
    }

}
