package org.code.meow.mrow.block;

import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.code.meow.Meow;
import org.code.meow.mrow.fluid.ModFluids;
import org.code.meow.mrow.item.ModItems;

import java.util.function.Function;

public class ModBlocks {
	public static final Block ESTROGEN_FLUID = register("estrogen", props -> new FluidBlock(ModFluids.ESTROGEN_SOURCE, props), AbstractBlock.Settings.copy(Blocks.WATER));
	public static final Block DISSOLVED_ESTROGEN_FLUID = register("dissolved_estrogen", props -> new FluidBlock(ModFluids.DISSOLVED_ESTROGEN_SOURCE, props), AbstractBlock.Settings.copy(ESTROGEN_FLUID));

	public static final Block DISSOLVING_TABLE = registerWithItem("dissolving_table", DissolvingTableBlock::new, AbstractBlock.Settings.create());

	private static Block register(String id, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings) {
		RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Meow.id(id));

		return Registry.register(Registries.BLOCK, key, blockFactory.apply(settings.registryKey(key)));
	}

	private static Block registerWithItem(String id, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings) {
		RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Meow.id(id));
		RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Meow.id(id));

		Block block = blockFactory.apply(settings.registryKey(blockKey));
		Registry.register(Registries.BLOCK, blockKey, block);
		ModItems.ALL_ITEMS.add(Registry.register(Registries.ITEM, key, new BlockItem(block, new Item.Settings().registryKey(key).useBlockPrefixedTranslationKey())));

		return block;
	}

	public static void init() {
		Meow.LOGGER.info("registering ModBlocks for mod " + Meow.MOD_ID);
	}
}
