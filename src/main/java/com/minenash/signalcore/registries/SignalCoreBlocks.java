package com.minenash.signalcore.registries;

import com.minenash.signalcore.SignalCore;
import com.minenash.signalcore.blocks.SignalBlock;
import com.minenash.signalcore.blocks.SignalContainer;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
public class SignalCoreBlocks {

    public static void init() {}

    public static final Block SIGNAL_BLOCK_ZERO = registerBlock("zero", 0);
    public static final Block SIGNAL_BLOCK_ONE  = registerBlock("one", 1);
    public static final Block SIGNAL_BLOCK_TWO  = registerBlock("two", 2);
    public static final Block SIGNAL_BLOCK_THREE = registerBlock("three", 3);
    public static final Block SIGNAL_BLOCK_FOUR = registerBlock("four", 4);
    public static final Block SIGNAL_BLOCK_FIVE = registerBlock("five", 5);
    public static final Block SIGNAL_BLOCK_SIX = registerBlock("six", 6);
    public static final Block SIGNAL_BLOCK_SEVEN = registerBlock("seven", 7);
    public static final Block SIGNAL_BLOCK_EIGHT = registerBlock("eight", 8);
    public static final Block SIGNAL_BLOCK_NINE = registerBlock("nine", 9);
    public static final Block SIGNAL_BLOCK_TEN = registerBlock("ten", 10);
    public static final Block SIGNAL_BLOCK_ELEVEN = registerBlock("eleven", 11);
    public static final Block SIGNAL_BLOCK_TWELVE = registerBlock("twelve", 12);
    public static final Block SIGNAL_BLOCK_THIRTEEN = registerBlock("thirteen", 13);
    public static final Block SIGNAL_BLOCK_FOURTEEN = registerBlock("fourteen", 14);
    public static final Block SIGNAL_BLOCK_FIFTEEN = registerBlock("fifteen", 15);

    public static final Block SIGNAL_CONTAINER_ZERO = registerContainer("zero", 0);
    public static final Block SIGNAL_CONTAINER_ONE  = registerContainer("one", 1);
    public static final Block SIGNAL_CONTAINER_TWO  = registerContainer("two", 2);
    public static final Block SIGNAL_CONTAINER_THREE = registerContainer("three", 3);
    public static final Block SIGNAL_CONTAINER_FOUR = registerContainer("four", 4);
    public static final Block SIGNAL_CONTAINER_FIVE = registerContainer("five", 5);
    public static final Block SIGNAL_CONTAINER_SIX = registerContainer("six", 6);
    public static final Block SIGNAL_CONTAINER_SEVEN = registerContainer("seven", 7);
    public static final Block SIGNAL_CONTAINER_EIGHT = registerContainer("eight", 8);
    public static final Block SIGNAL_CONTAINER_NINE = registerContainer("nine", 9);
    public static final Block SIGNAL_CONTAINER_TEN = registerContainer("ten", 10);
    public static final Block SIGNAL_CONTAINER_ELEVEN = registerContainer("eleven", 11);
    public static final Block SIGNAL_CONTAINER_TWELVE = registerContainer("twelve", 12);
    public static final Block SIGNAL_CONTAINER_THIRTEEN = registerContainer("thirteen", 13);
    public static final Block SIGNAL_CONTAINER_FOURTEEN = registerContainer("fourteen", 14);
    public static final Block SIGNAL_CONTAINER_FIFTEEN = registerContainer("fifteen", 15);

    private static Block registerBlock(String powerName, int power) {
        return Registry.register(Registries.BLOCK, new Identifier(SignalCore.MOD_ID, "signal_block_" + powerName), new SignalBlock(power));
    }
    private static Block registerContainer(String powerName, int power) {
        return Registry.register(Registries.BLOCK, new Identifier(SignalCore.MOD_ID, "signal_container_" + powerName), new SignalContainer(power));
    }

}
