package com.minenash.signalcore;

import com.minenash.signalcore.registries.SignalCoreBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.minenash.signalcore.registries.SignalCoreBlocks.*;

public class SignalCore implements ModInitializer {
	public static final String MOD_ID = "signalcore";

	private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier(MOD_ID, "signalcore"))
			.icon(() -> new ItemStack(SIGNAL_BLOCKS[15]))
			.entries((enabledFeatures, entries, operatorEnabled) -> {
				entries.add(SIGNAL_DISPLAY_BLOCK);

				for (Block b : SIGNAL_BLOCKS) entries.add(b);
				for (Block b : STRONG_SIGNAL_BLOCKS) entries.add(b);
				for (Block b : SIGNAL_CONTAINERS) entries.add(b);
			})
			.build();

	@Override
	public void onInitialize() {
		SignalCoreBlocks.init();
	}

	public void generate() {
		Path config = FabricLoader.getInstance().getConfigDir().resolve("generated");
		Path blockstates = config.resolve("blockstates");
		Path block_models = config.resolve("block_models");
		Path item_models = config.resolve("item_models");

		for (int i = 0; i < 16; i++) {
			String block = "signal_display_block_" + powerNames[i];

			try {
//				Files.createFile(blockstates.resolve(block + ".json"));
//				Files.writeString(blockstates.resolve(block + ".json"), """
//					{
//					  "variants": {
//					    "": { "model": "signalcore:block/%s" }
//					  }
//					}
//					""".formatted(block));

				Files.createFile(block_models.resolve(block + ".json"));
				Files.writeString(block_models.resolve(block + ".json"), """
					{
					  "parent": "block/cube_all",
					  "textures": {
					    "all": "signalcore:block/%s"
					  }
					}
					""".formatted(block));

//				Files.createFile(item_models.resolve(block + ".json"));
//				Files.writeString(item_models.resolve(block + ".json"), """
//					{
//					  "parent": "signalcore:block/%s"
//					}
//					""".formatted(block));

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}
}
