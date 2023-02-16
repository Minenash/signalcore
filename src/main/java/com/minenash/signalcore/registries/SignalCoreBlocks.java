package com.minenash.signalcore.registries;

import com.minenash.signalcore.SignalCore;
import com.minenash.signalcore.blocks.displays.DisplayBlock;
import com.minenash.signalcore.blocks.displays.StrongDetectingDisplayBlock;
import com.minenash.signalcore.blocks.displays.StrongDisplayBlock;
import com.minenash.signalcore.blocks.new_signals.*;
import com.minenash.signalcore.blocks.signals.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class SignalCoreBlocks {

    public static final List<Block> ALL_BLOCKS = new ArrayList<>();
    public static void init() {}

    public static final Block NUMBER_DISPLAY_BLOCK = register("number_display_block", new DisplayBlock());
    public static final Block HEX_DISPLAY_BLOCK = register("hex_display_block", new DisplayBlock());
    public static final Block STRONG_DETECTING_NUMBER_DISPLAY_BLOCK = register("strong_detecting_number_display_block", new StrongDetectingDisplayBlock());
    public static final Block STRONG_DETECTING_HEX_DISPLAY_BLOCK = register("strong_detecting_hex_display_block", new StrongDetectingDisplayBlock());
    public static final Block STRONG_NUMBER_DISPLAY_BLOCK = register("strong_number_display_block", new StrongDisplayBlock());
    public static final Block STRONG_HEX_DISPLAY_BLOCK = register("strong_hex_display_block", new StrongDisplayBlock());
    public static final Block COLOR_DISPLAY_BLOCK = register("color_display_block", new DisplayBlock());

    public static final Block SIGNAL_BLOCK = register("signal_block", new NewSignalBlock());
    public static final Block STRONG_SIGNAL_BLOCK = register("strong_signal_block", new NewStrongSignalBlock());
    public static final Block SIGNAL_CONTAINER_BLOCK = register("signal_container_block", new NewSignalContainerBlock());
    public static final Block DIRECTIONAL_SIGNAL_BLOCK = register("directional_signal_block", new NewDirectionalSignalBlock());
    public static final Block STRONG_DIRECTIONAL_SIGNAL_BLOCK = register("strong_directional_signal_block", new NewStrongDirectionalSignalBlock());

//    public static final Block[] SIGNAL_BLOCKS = registerSignalBlocks("signal_block_", SignalBlock.class);
//    public static final Block[] STRONG_SIGNAL_BLOCKS = registerSignalBlocks("strong_signal_block_", StrongSignalBlock.class);
//    public static final Block[] SIGNAL_CONTAINERS = registerSignalBlocks("signal_container_", SignalContainerBlock.class);
//    public static final Block[] DIRECTIONAL_SIGNAL_BLOCKS = registerSignalBlocks("directional_signal_block_", DirectionalSignalBlock.class);
//    public static final Block[] STRONG_DIRECTIONAL_SIGNAL_BLOCKS = registerSignalBlocks("strong_directional_signal_block_", StrongDirectionalSignalBlock.class);



    private static Block register(String id, Block block) {
        Block b = Registry.register(Registries.BLOCK, new Identifier(SignalCore.MOD_ID, id), block);
        Registry.register(Registries.ITEM, new Identifier(SignalCore.MOD_ID, id), new BlockItem(block, new FabricItemSettings()));
        ALL_BLOCKS.add(b);
        return b;
    }

    private static Block[] registerSignalBlocks(String prefix, Class<? extends Block> block) {
        try {
            Constructor<? extends Block> constructor = block.getConstructor(int.class);
            Block[] blocks = new Block[16];
            for (int i = 0; i < 16; i++)
                blocks[i] = register(prefix + SignalCore.POWER_NAMES[i], constructor.newInstance(i));
            return blocks;
        }
        catch (Exception e){
            e.printStackTrace();
            return new Block[0];
        }
    }
}
