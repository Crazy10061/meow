package meow.transfems.delight.mrow.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.BlockItemKeys;
import net.minecraft.item.Item;
import net.minecraft.item.ItemKeys;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryView;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.DyeColor;
import meow.transfems.delight.Meow;
import meow.transfems.delight.mrow.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
	public static final TagKey<Item> REPAIRS_CAT_EARS = TagKey.of(
		RegistryKeys.ITEM,
		Meow.id("repairs_cat_ears")
	);

	public static final TagKey<Item> CAT_EARS = TagKey.of(
		RegistryKeys.ITEM,
		Meow.id("cat_ears")
	);

	public ModItemTagProvider(FabricPackOutput output, CompletableFuture<RegistryView.WrapperLookup> registryLookupFuture) {
		super(output, registryLookupFuture);
	}

	@Override
	protected void configure(RegistryView.WrapperLookup registries) {
		builder(REPAIRS_CAT_EARS)
			.add(BlockItemKeys.TRIPWIRE.item())
			.add(ItemKeys.FEATHER)
			.setReplace(true);

		builder(CAT_EARS)
			.addColors(ModItems.CAT_EARS.map(item -> item.key()))
			.setReplace(true);

		builder(ConventionalItemTags.DYED)
			.addColors(ModItems.CAT_EARS.map(item -> item.key()))
			.setReplace(true);

		DyeColor.VALUES.forEach(col -> {
			builder(ConventionalItemTags.COLOR_DYED.get(col))
				.add(ModItems.CAT_EARS.get(col).key())
				.setReplace(false);
		});

		builder(ItemTags.HEAD_ARMOR)
			.addTag(ModItemTagProvider.CAT_EARS)
			.setReplace(false);

		builder(ConventionalItemTags.BUCKETS)
			.add(ModItems.ESTROGEN_BUCKET.key())
			.add(ModItems.DISSOLVED_ESTROGEN_BUCKET.key())
			.setReplace(false);
	}
}
