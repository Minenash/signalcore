package com.minenash.signalcore.blocks.displays;

import com.minenash.signalcore.SignalCore;
import com.minenash.signalcore.registries.SignalCoreBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class DisplayTileEntity extends BlockEntity {
    public DisplayTileEntity(BlockPos pos, BlockState state) {
        super(SignalCoreBlocks.DISPLAY_TILE_ENTITY, pos, state);
    }

    public static BlockEntityType<DisplayTileEntity> register() {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(SignalCore.MOD_ID, "display_tile"),
                FabricBlockEntityTypeBuilder.create(DisplayTileEntity::new, SignalCoreBlocks.DISPLAY_TILE, SignalCoreBlocks.HEX_DISPLAY_TILE).build());
    }
}
