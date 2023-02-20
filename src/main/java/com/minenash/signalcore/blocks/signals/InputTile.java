package com.minenash.signalcore.blocks.signals;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.minenash.signalcore.blocks.displays.DisplayTile;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import java.util.EnumMap;

@SuppressWarnings("deprecation")
public class InputTile extends StrongSignalBlock {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public static final EnumMap<Direction,VoxelShape> FACING_TO_SHAPE = Maps.newEnumMap( ImmutableMap.of(
            Direction.NORTH, Block.createCuboidShape(3,  3, 15, 13, 13, 16),
            Direction.SOUTH, Block.createCuboidShape(3,  1, 0,  13, 13, 1),
            Direction.EAST,  Block.createCuboidShape(0,  3, 3,   1, 13, 13),
            Direction.WEST,  Block.createCuboidShape(15, 3, 3,  16, 13, 13)));

    public InputTile() {
        super(true, FabricBlockSettings.of(Material.STONE).noCollision().nonOpaque());
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        for (Direction direction : ctx.getPlacementDirections())
            if (direction.getAxis().isHorizontal())
                return this.getDefaultState().with(FACING, direction.getOpposite());

        return null;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return FACING_TO_SHAPE.get(state.get(FACING));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWER, FACING);
    }

    @Override
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return direction == state.get(FACING) ? state.get(POWER) : 0;
    }

}
