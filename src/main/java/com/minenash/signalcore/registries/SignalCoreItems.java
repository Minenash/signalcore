package com.minenash.signalcore.registries;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

@SuppressWarnings("unused")
public class SignalCoreItems {

    public static void init() {}

    public static final Item SIGNAL_BLOCK_ZERO = register(SignalCoreBlocks.SIGNAL_BLOCK_ZERO);
    public static final Item SIGNAL_BLOCK_ONE  = register(SignalCoreBlocks.SIGNAL_BLOCK_ONE);
    public static final Item SIGNAL_BLOCK_TWO  = register(SignalCoreBlocks.SIGNAL_BLOCK_TWO);
    public static final Item SIGNAL_BLOCK_THREE = register(SignalCoreBlocks.SIGNAL_BLOCK_THREE);
    public static final Item SIGNAL_BLOCK_FOUR = register(SignalCoreBlocks.SIGNAL_BLOCK_FOUR);
    public static final Item SIGNAL_BLOCK_FIVE = register(SignalCoreBlocks.SIGNAL_BLOCK_FIVE);
    public static final Item SIGNAL_BLOCK_SIX = register(SignalCoreBlocks.SIGNAL_BLOCK_SIX);
    public static final Item SIGNAL_BLOCK_SEVEN = register(SignalCoreBlocks.SIGNAL_BLOCK_SEVEN);
    public static final Item SIGNAL_BLOCK_EIGHT = register(SignalCoreBlocks.SIGNAL_BLOCK_EIGHT);
    public static final Item SIGNAL_BLOCK_NINE = register(SignalCoreBlocks.SIGNAL_BLOCK_NINE);
    public static final Item SIGNAL_BLOCK_TEN = register(SignalCoreBlocks.SIGNAL_BLOCK_TEN);
    public static final Item SIGNAL_BLOCK_ELEVEN = register(SignalCoreBlocks.SIGNAL_BLOCK_ELEVEN);
    public static final Item SIGNAL_BLOCK_TWELVE = register(SignalCoreBlocks.SIGNAL_BLOCK_TWELVE);
    public static final Item SIGNAL_BLOCK_THIRTEEN = register(SignalCoreBlocks.SIGNAL_BLOCK_THIRTEEN);
    public static final Item SIGNAL_BLOCK_FOURTEEN = register(SignalCoreBlocks.SIGNAL_BLOCK_FOURTEEN);
    public static final Item SIGNAL_BLOCK_FIFTEEN = register(SignalCoreBlocks.SIGNAL_BLOCK_FIFTEEN);

    public static final Item SIGNAL_CONTAINER_ZERO = register(SignalCoreBlocks.SIGNAL_CONTAINER_ZERO);
    public static final Item SIGNAL_CONTAINER_ONE  = register(SignalCoreBlocks.SIGNAL_CONTAINER_ONE);
    public static final Item SIGNAL_CONTAINER_TWO  = register(SignalCoreBlocks.SIGNAL_CONTAINER_TWO);
    public static final Item SIGNAL_CONTAINER_THREE = register(SignalCoreBlocks.SIGNAL_CONTAINER_THREE);
    public static final Item SIGNAL_CONTAINER_FOUR = register(SignalCoreBlocks.SIGNAL_CONTAINER_FOUR);
    public static final Item SIGNAL_CONTAINER_FIVE = register(SignalCoreBlocks.SIGNAL_CONTAINER_FIVE);
    public static final Item SIGNAL_CONTAINER_SIX = register(SignalCoreBlocks.SIGNAL_CONTAINER_SIX);
    public static final Item SIGNAL_CONTAINER_SEVEN = register(SignalCoreBlocks.SIGNAL_CONTAINER_SEVEN);
    public static final Item SIGNAL_CONTAINER_EIGHT = register(SignalCoreBlocks.SIGNAL_CONTAINER_EIGHT);
    public static final Item SIGNAL_CONTAINER_NINE = register(SignalCoreBlocks.SIGNAL_CONTAINER_NINE);
    public static final Item SIGNAL_CONTAINER_TEN = register(SignalCoreBlocks.SIGNAL_CONTAINER_TEN);
    public static final Item SIGNAL_CONTAINER_ELEVEN = register(SignalCoreBlocks.SIGNAL_CONTAINER_ELEVEN);
    public static final Item SIGNAL_CONTAINER_TWELVE = register(SignalCoreBlocks.SIGNAL_CONTAINER_TWELVE);
    public static final Item SIGNAL_CONTAINER_THIRTEEN = register(SignalCoreBlocks.SIGNAL_CONTAINER_THIRTEEN);
    public static final Item SIGNAL_CONTAINER_FOURTEEN = register(SignalCoreBlocks.SIGNAL_CONTAINER_FOURTEEN);
    public static final Item SIGNAL_CONTAINER_FIFTEEN = register(SignalCoreBlocks.SIGNAL_CONTAINER_FIFTEEN);

    private static Item register(Block block) {
        return Registry.register(Registries.ITEM, Registries.BLOCK.getId(block), new BlockItem(block, new FabricItemSettings()));
    }

}
