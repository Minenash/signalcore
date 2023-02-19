package com.minenash.signalcore.blocks.displays;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.minenash.signalcore.SignalCore;
import com.minenash.signalcore.registries.SignalCoreBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;

@SuppressWarnings("deprecation")
public class DisplayTile extends Block implements BlockEntityProvider {
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
        for (Direction direction : ctx.getPlacementDirections())
            if (direction.getAxis().isHorizontal())
                return this.getDefaultState().with(FACING, direction.getOpposite());

        return null;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return FACING_TO_SHAPE.get(state.get(FACING));
    }

    public static <T extends BlockEntity>  void tick(World world, BlockPos pos, BlockState state, T _blockEntity) {
        if (world.isClient) return;

        int receivedPower = world.getReceivedRedstonePower( pos.offset( state.get(FACING).getOpposite() ));
        if (state.get(POWER) != receivedPower)
            world.setBlockState(pos, state.with(POWER, receivedPower), Block.NOTIFY_LISTENERS);
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWER, FACING);
    }


    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DisplayTileEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient || type != SignalCoreBlocks.DISPLAY_TILE_ENTITY ? null : DisplayTile::tick;
    }



}
