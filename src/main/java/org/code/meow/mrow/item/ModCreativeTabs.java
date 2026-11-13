package org.code.meow.mrow.item;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.code.meow.Meow;

public class ModCreativeTabs {
	public static final ResourceKey<CreativeModeTab> MEOW_KEY = ResourceKey.create(
		Registries.CREATIVE_MODE_TAB,
		Meow.id("meow")
	);

	public static final CreativeModeTab MEOW = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MEOW_KEY, FabricCreativeModeTab.builder()
		.icon(ModItems.CAT_EARS.purple()::getDefaultInstance)
		.title(Component.translatable("itemGroup.meow"))
		.displayItems((params, output) -> {
			output.acceptAll(ModItems.ALL_ITEMS.stream().map(Item::getDefaultInstance).toList());
		})
		.build());

	public static void init() {
		Meow.LOGGER.info("registering ModCreativeTabs for " + Meow.MOD_ID);
	}
}
