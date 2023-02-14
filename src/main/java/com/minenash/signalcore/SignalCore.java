package com.minenash.signalcore;

import com.minenash.signalcore.registries.SignalCoreBlocks;
import com.minenash.signalcore.registries.SignalCoreItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static com.minenash.signalcore.registries.SignalCoreBlocks.*;

public class SignalCore implements ModInitializer {
	public static final String MOD_ID = "signalcore";

	private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder(new Identifier(MOD_ID, "signalcore"))
			.icon(() -> new ItemStack(SIGNAL_BLOCK_FIFTEEN))
			.entries((enabledFeatures, entries, operatorEnabled) -> {
				entries.add(SIGNAL_BLOCK_FIFTEEN);
				entries.add(SIGNAL_BLOCK_FOURTEEN);
				entries.add(SIGNAL_BLOCK_THIRTEEN);
				entries.add(SIGNAL_BLOCK_TWELVE);
				entries.add(SIGNAL_BLOCK_ELEVEN);
				entries.add(SIGNAL_BLOCK_TEN);
				entries.add(SIGNAL_BLOCK_NINE);
				entries.add(SIGNAL_BLOCK_EIGHT);
				entries.add(SIGNAL_BLOCK_SEVEN);
				entries.add(SIGNAL_BLOCK_SIX);
				entries.add(SIGNAL_BLOCK_FIVE);
				entries.add(SIGNAL_BLOCK_FOUR);
				entries.add(SIGNAL_BLOCK_THREE);
				entries.add(SIGNAL_BLOCK_TWO);
				entries.add(SIGNAL_BLOCK_ONE);
				entries.add(SIGNAL_BLOCK_ZERO);

				entries.add(SIGNAL_CONTAINER_FIFTEEN);
				entries.add(SIGNAL_CONTAINER_FOURTEEN);
				entries.add(SIGNAL_CONTAINER_THIRTEEN);
				entries.add(SIGNAL_CONTAINER_TWELVE);
				entries.add(SIGNAL_CONTAINER_ELEVEN);
				entries.add(SIGNAL_CONTAINER_TEN);
				entries.add(SIGNAL_CONTAINER_NINE);
				entries.add(SIGNAL_CONTAINER_EIGHT);
				entries.add(SIGNAL_CONTAINER_SEVEN);
				entries.add(SIGNAL_CONTAINER_SIX);
				entries.add(SIGNAL_CONTAINER_FIVE);
				entries.add(SIGNAL_CONTAINER_FOUR);
				entries.add(SIGNAL_CONTAINER_THREE);
				entries.add(SIGNAL_CONTAINER_TWO);
				entries.add(SIGNAL_CONTAINER_ONE);
				entries.add(SIGNAL_CONTAINER_ZERO);

			})
			.build();

	@Override
	public void onInitialize() {
		SignalCoreBlocks.init();
		SignalCoreItems.init();
	}
}
