package com.minenash.signalcore.blocks.displays;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.minenash.signalcore.registries.SignalCoreBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;

@SuppressWarnings("deprecation")
public class BinaryDisplayTile extends Block implements BlockEntityProvider {
    public static final BooleanProperty ON = BooleanProperty.of("on");
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public BinaryDisplayTile() {
        super(FabricBlockSettings.of(Material.REDSTONE_LAMP).noCollision().nonOpaque());
        this.setDefaultState(this.getDefaultState().with(ON, false).with(FACING, Direction.NORTH));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        for (Direction direction : ctx.getPlacementDirections())
            if (direction.getAxis().isHorizontal())
                return this.getDefaultState().with(FACING, direction.getOpposite());

        return null;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return DisplayTile.FACING_TO_SHAPE.get(state.get(FACING));
    }

    public static <T extends BlockEntity>  void tick(World world, BlockPos pos, BlockState state, T _blockEntity) {
        if (world.isClient) return;

        boolean isOn = world.getReceivedRedstonePower( pos.offset( state.get(FACING).getOpposite() )) > 0;
        if (state.get(ON) != isOn)
            world.setBlockState(pos, state.with(ON, isOn), Block.NOTIFY_LISTENERS);
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ON, FACING);
    }


    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DisplayTileEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient || type != SignalCoreBlocks.DISPLAY_TILE_ENTITY ? null : BinaryDisplayTile::tick;
    }



}
