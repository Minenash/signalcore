package com.minenash.signalcore.registries;

import com.minenash.signalcore.SignalCore;
import com.minenash.signalcore.blocks.SignalBlock;
import com.minenash.signalcore.blocks.SignalContainerBlock;
import com.minenash.signalcore.blocks.SignalDisplayBlock;
import com.minenash.signalcore.blocks.StrongSignalBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("unused")
public class SignalCoreBlocks {

    public static final String[] powerNames = new String[] {"zero","one","two","three","four","five","six","seven",
            "eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen"};

    public static void init() {}

    public static final Block SIGNAL_DISPLAY_BLOCK = register("signal_display_block", new SignalDisplayBlock());

    public static final Block[] SIGNAL_BLOCKS = registerSignalBlocks("signal_block_", SignalBlock.class);
    public static final Block[] STRONG_SIGNAL_BLOCKS = registerSignalBlocks("strong_signal_block_", StrongSignalBlock.class);
    public static final Block[] SIGNAL_CONTAINERS = registerSignalBlocks("signal_container_", SignalContainerBlock.class);


    private static Block register(String id, Block block) {
        Block b = Registry.register(Registries.BLOCK, new Identifier(SignalCore.MOD_ID, id), block);
        Registry.register(Registries.ITEM, new Identifier(SignalCore.MOD_ID, id), new BlockItem(block, new FabricItemSettings()));
        return b;
    }

    private static Block[] registerSignalBlocks(String prefix, Class<? extends Block> block) {
        try {
            Constructor<? extends Block> constructor = block.getConstructor(int.class);
            Block[] blocks = new Block[16];
            for (int i = 0; i < 16; i++)
                blocks[i] = register(prefix + powerNames[i], constructor.newInstance(i));
            return blocks;
        }
        catch (Exception e){
            e.printStackTrace();
            return new Block[0];
        }
    }
}
