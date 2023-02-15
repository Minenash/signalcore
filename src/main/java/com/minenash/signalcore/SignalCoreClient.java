package com.minenash.signalcore;

import com.minenash.signalcore.registries.SignalCoreBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public class SignalCoreClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        for (Block b : SignalCoreBlocks.ALL_BLOCKS)
            BlockRenderLayerMap.INSTANCE.putBlock(b, RenderLayer.getTranslucent());
    }

}
