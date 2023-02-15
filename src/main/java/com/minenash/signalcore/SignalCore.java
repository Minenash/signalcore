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

	public static final String[] POWER_NAMES = new String[] {"zero","one","two","three","four","five","six","seven",
			"eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen"};

	private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier(MOD_ID, "signalcore"))
			.icon(() -> new ItemStack(SIGNAL_BLOCKS[15]))
			.entries((enabledFeatures, entries, operatorEnabled) -> {
				for (Block b : ALL_BLOCKS)
					entries.add(b);
			})
			.build();

	@Override
	public void onInitialize() {
		SignalCoreBlocks.init();
//		generate();
	}

	public void generate() {
		Path config = FabricLoader.getInstance().getConfigDir().resolve("generated");
		Path blockstates = config.resolve("blockstates");
		Path block_models = config.resolve("block_models");
		Path item_models = config.resolve("item_models");

		for (String str : new String[]{"directional_signal_block_","strong_directional_signal_block_"}) {
			for (int i = 0; i < 16; i++) {
				String name = str + POWER_NAMES[i];

				write(blockstates.resolve(name + ".json"), """
						{
						  "variants": {
						    "facing=up": { "model": "signalcore:block/%1$s", "x": 270 },
						    "facing=down": { "model": "signalcore:block/%1$s", "x": 90 },
						    "facing=north": { "model": "signalcore:block/%1$s" },
						    "facing=south": { "model": "signalcore:block/%1$s", "y": 180 },
						    "facing=east": { "model": "signalcore:block/%1$s", "y": 90 },
						    "facing=west": { "model": "signalcore:block/%1$s", "y": 270 }
						  }
						}""".formatted(name));

				write(item_models.resolve(name + ".json"), """
						{
						  "parent": "signalcore:block/%s"
						}""".formatted(name));

				if (str.equals("directional_signal_block_"))
					write(block_models.resolve(name + ".json"), """
							{
							  "parent": "signalcore:block/signal_cube_directional",
							  "textures": {
								"base": "signalcore:block/base/signal_%d",
								"number": "signalcore:block/number/%d"
							  }
							}""".formatted(i,i));
				else
					write(block_models.resolve(name + ".json"), """
							{
							  "parent": "signalcore:block/signal_cube_overlay_directional",
							  "textures": {
								"base": "signalcore:block/base/signal_%d",
								"number": "signalcore:block/number/%d",
								"overlay": "signalcore:block/overlay/strong"
							  }
							}""".formatted(i,i));


//				Files.createFile(blockstates.resolve( name + ".json"));
//				Files.writeString(blockstates.resolve(name + ".json"), """
//					{
//					  "variants": {
//					    "": { "model": "signalcore:block/%s" }
//					  }
//					}
//					""".formatted(name));


			}

		}

	}

	public static void write(Path path, String str) {
		try {
			Files.createFile(path);
			Files.writeString(path,str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
