package org.code.meow.mrow.item;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import org.code.meow.Meow;
import org.code.meow.mrow.block.ModBlocks;

public class ModItemGroups {
	public static final RegistryKey<ItemGroup> MEOW_KEY = RegistryKey.of(
		RegistryKeys.ITEM_GROUP,
		Meow.id("meow")
	);

	public static final RegistryKey<ItemGroup> HRT_KEY = RegistryKey.of(
		RegistryKeys.ITEM_GROUP,
		Meow.id("hrt")
	);

	public static final ItemGroup MEOW = Registry.register(Registries.ITEM_GROUP, MEOW_KEY, FabricCreativeModeTab.builder()
		.icon(ModItems.CAT_EARS.purple()::getDefaultStack)
		.displayName(Text.translatable("itemGroup.meow"))
		.entries((params, output) -> {
			output.addAll(ModItems.ALL_ITEMS.stream().map(Item::getDefaultStack).toList());
		})
		.build());

	public static final ItemGroup HRT = Registry.register(Registries.ITEM_GROUP, HRT_KEY, FabricCreativeModeTab.builder()
		.icon(ModItems.ESTROGEN_BUCKET::getDefaultStack)
		.displayName(Text.translatable("itemGroup.hrt"))
		.entries((params, output) -> {
			output.add(ModItems.ESTROGEN_BUCKET);
			output.add(ModItems.OIL);
			output.add(ModBlocks.DISSOLVING_TABLE.asItem());
			output.add(ModItems.DISSOLVED_ESTROGEN_BUCKET);
			output.add(ModItems.SYRINGE);
		})
		.build());

	public static void init() {
		Meow.LOGGER.info("registering ModCreativeTabs for " + Meow.MOD_ID);
	}
}
