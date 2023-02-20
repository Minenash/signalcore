package com.minenash.signalcore.registries;

import com.minenash.signalcore.SignalCore;
import com.minenash.signalcore.blocks.displays.*;
import com.minenash.signalcore.blocks.signals.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class SignalCoreBlocks {

    public static final List<Block> ALL_BLOCKS = new ArrayList<>();
    public static void init() {}

    public static final Block NUMBER_DISPLAY_BLOCK = register("display_block", new DisplayBlock());
    public static final Block HEX_DISPLAY_BLOCK = register("hex_display_block", new DisplayBlock());
    public static final Block STRONG_DETECTING_NUMBER_DISPLAY_BLOCK = register("strong_detecting_display_block", new StrongDetectingDisplayBlock());
    public static final Block STRONG_DETECTING_HEX_DISPLAY_BLOCK = register("strong_detecting_hex_display_block", new StrongDetectingDisplayBlock());
    public static final Block STRONG_NUMBER_DISPLAY_BLOCK = register("strong_display_block", new StrongDisplayBlock());
    public static final Block STRONG_HEX_DISPLAY_BLOCK = register("strong_hex_display_block", new StrongDisplayBlock());

    public static final Block DISPLAY_TILE = register("display_tile", new DisplayTile());
    public static final Block HEX_DISPLAY_TILE = register("hex_display_tile", new DisplayTile());
    public static final Block BINARY_DISPLAY_TILE = register("binary_display_tile", new BinaryDisplayTile());
    public static final BlockEntityType<DisplayTileEntity> DISPLAY_TILE_ENTITY = DisplayTileEntity.register();

    public static final Block COLOR_DISPLAY_BLOCK = register("color_display_block", new DisplayBlock());

    private static final boolean debug = true;

    public static final Block SIGNAL_BLOCK = register("signal_block", new SignalBlock(debug));
    public static final Block STRONG_SIGNAL_BLOCK = register("strong_signal_block", new StrongSignalBlock(debug));
    public static final Block SIGNAL_CONTAINER_BLOCK = register("signal_container_block", new SignalContainerBlock(debug));
    public static final Block DIRECTIONAL_SIGNAL_BLOCK = register("directional_signal_block", new DirectionalSignalBlock(debug));
    public static final Block STRONG_DIRECTIONAL_SIGNAL_BLOCK = register("strong_directional_signal_block", new StrongDirectionalSignalBlock(debug));

    public static final Block INPUT_TILE = register("input_tile", new InputTile());
    public static final Block HEX_INPUT_TILE = register("hex_input_tile", new InputTile());
    public static final Block BINARY_INPUT_TILE = register("binary_input_tile", new BinaryInputTile());

    private static Block register(String id, Block block) {
        Block b = Registry.register(Registries.BLOCK, new Identifier(SignalCore.MOD_ID, id), block);
        Registry.register(Registries.ITEM, new Identifier(SignalCore.MOD_ID, id), new TooltippedBlockItem(block));
        ALL_BLOCKS.add(b);
        return b;
    }

    public static class TooltippedBlockItem extends BlockItem {
        public TooltippedBlockItem(Block block) {
            super(block, new FabricItemSettings());
        }

        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
            for (String s : I18n.translate(getBlock().getTranslationKey() + ".tooltip").split("\n"))
                tooltip.add(Text.literal("§7" + s));
        }
    }
}
