package meow.transfems.delight.mrow.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryView;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.TagKey;
import meow.transfems.delight.Meow;
import meow.transfems.delight.mrow.fluid.ModFluids;

import java.util.concurrent.CompletableFuture;

public class ModFluidTagProvider extends FabricTagsProvider.FluidTagsProvider {
	public static final TagKey<Fluid> ESTROGEN = TagKey.of(
		RegistryKeys.FLUID,
		Meow.id("estrogen")
	);

	public ModFluidTagProvider(FabricPackOutput output, CompletableFuture<RegistryView.WrapperLookup> registryLookupFuture) {
		super(output, registryLookupFuture);
	}

	@Override
	protected void configure(RegistryView.WrapperLookup registries) {
		builder(ESTROGEN)
			.add(ModFluids.ESTROGEN_SOURCE.key())
			.add(ModFluids.ESTROGEN_FLOWING.key())
			.add(ModFluids.DISSOLVED_ESTROGEN_SOURCE.key())
			.add(ModFluids.DISSOLVED_ESTROGEN_FLOWING.key())
			.setReplace(false);

		builder(FluidTags.WATER)
			.add(ModFluids.ESTROGEN_SOURCE.key())
			.add(ModFluids.ESTROGEN_FLOWING.key())
			.add(ModFluids.DISSOLVED_ESTROGEN_SOURCE.key())
			.add(ModFluids.DISSOLVED_ESTROGEN_FLOWING.key())
			.setReplace(false);

		builder(FluidTags.ENTITY_FLOATABLE)
			.add(ModFluids.ESTROGEN_SOURCE.key())
			.add(ModFluids.ESTROGEN_FLOWING.key())
			.add(ModFluids.DISSOLVED_ESTROGEN_SOURCE.key())
			.add(ModFluids.DISSOLVED_ESTROGEN_FLOWING.key())
			.setReplace(false);
	}
}
