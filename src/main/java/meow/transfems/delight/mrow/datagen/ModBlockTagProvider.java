package meow.transfems.delight.mrow.datagen;

import meow.transfems.delight.mrow.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.registry.RegistryView;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
	public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<RegistryView.WrapperLookup> registryLookupFuture) {
		super(output, registryLookupFuture);
	}

	@Override
	protected void configure(RegistryView.WrapperLookup registries) {
		builder(BlockTags.PICKAXE_MINEABLE)
			.add(ModBlocks.DISSOLVING_TABLE.key())
			.setReplace(false);
	};
}
