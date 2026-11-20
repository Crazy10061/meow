package meow.transfems.delight.mrow.block;

import meow.transfems.delight.util.REWK;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import meow.transfems.delight.Meow;
import meow.transfems.delight.mrow.fluid.ModFluids;
import meow.transfems.delight.mrow.item.ModItems;

import java.util.function.Function;

public class ModBlocks {
	public static final REWK<Block> ESTROGEN_FLUID = register("estrogen", props -> new FluidBlock((FlowableFluid)ModFluids.ESTROGEN_SOURCE.entry(), props), AbstractBlock.Settings.copy(Blocks.WATER));
	public static final REWK<Block> DISSOLVED_ESTROGEN_FLUID = register("dissolved_estrogen", props -> new FluidBlock((FlowableFluid)ModFluids.DISSOLVED_ESTROGEN_SOURCE.entry(), props), AbstractBlock.Settings.copy(ESTROGEN_FLUID.entry()));

	public static final REWK<Block> DISSOLVING_TABLE = registerWithItem("dissolving_table", DissolvingTableBlock::new, AbstractBlock.Settings.create());
	public static final REWK<Block> TRANSFEM_FLAG = registerWithItem("transfem_flag", FlagBlock::new, AbstractBlock.Settings.create().breakInstantly());
	public static final REWK<Block> TRANS_FLAG = registerWithItem("trans_flag", FlagBlock::new, AbstractBlock.Settings.create().breakInstantly());
	public static final REWK<Block> TRANSMASC_FLAG = registerWithItem("transmasc_flag", FlagBlock::new, AbstractBlock.Settings.create().breakInstantly());

	private static REWK<Block> register(String id, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings) {
		RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Meow.id(id));

		return new REWK<>(Registry.register(Registries.BLOCK, key, blockFactory.apply(settings.registryKey(key))), key);
	}

	private static REWK<Block> registerWithItem(String id, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings) {
		RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Meow.id(id));
		RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Meow.id(id));

		Block block = blockFactory.apply(settings.registryKey(blockKey));
		Registry.register(Registries.BLOCK, blockKey, block);
		ModItems.ALL_ITEMS.add(Registry.register(Registries.ITEM, key, new BlockItem(block, new Item.Settings().registryKey(key).useBlockPrefixedTranslationKey())));

		return new REWK<>(block, blockKey);
	}

	public static void init() {
		Meow.LOGGER.info("registering ModBlocks for mod " + Meow.MOD_ID);
	}
}
