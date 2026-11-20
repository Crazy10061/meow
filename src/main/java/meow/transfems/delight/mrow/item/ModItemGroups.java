package meow.transfems.delight.mrow.item;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import meow.transfems.delight.Meow;
import meow.transfems.delight.mrow.block.ModBlocks;

public class ModItemGroups {
	public static final RegistryKey<ItemGroup> TRANSFEMS_DELIGHT_KEY = RegistryKey.of(
		RegistryKeys.ITEM_GROUP,
		Meow.id("meow")
	);

	public static final RegistryKey<ItemGroup> HRT_KEY = RegistryKey.of(
		RegistryKeys.ITEM_GROUP,
		Meow.id("hrt")
	);

	public static final ItemGroup MEOW = Registry.register(Registries.ITEM_GROUP, TRANSFEMS_DELIGHT_KEY, FabricCreativeModeTab.builder()
		.icon(ModItems.CAT_EARS.purple().entry()::getDefaultStack)
		.displayName(Text.translatable("itemGroup.meow"))
		.entries((params, output) -> {
			output.addAll(ModItems.ALL_ITEMS.stream().map(Item::getDefaultStack).toList());
		})
		.build());

	public static final ItemGroup HRT = Registry.register(Registries.ITEM_GROUP, HRT_KEY, FabricCreativeModeTab.builder()
		.icon(ModItems.ESTROGEN_BUCKET.entry()::getDefaultStack)
		.displayName(Text.translatable("itemGroup.hrt"))
		.entries((params, output) -> {
			output.add(ModItems.ESTROGEN_BUCKET.entry());
			output.add(ModItems.OIL.entry());
			output.add(ModBlocks.DISSOLVING_TABLE.entry());
			output.add(ModItems.DISSOLVED_ESTROGEN_BUCKET.entry());
			output.add(ModItems.SYRINGE.entry());
		})
		.build());

	public static void init() {
		Meow.LOGGER.info("registering ModCreativeTabs for " + Meow.MOD_ID);
	}
}
