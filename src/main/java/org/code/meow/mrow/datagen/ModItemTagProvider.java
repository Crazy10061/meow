package org.code.meow.mrow.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.code.meow.Meow;
import org.code.meow.mrow.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
	public static final TagKey<Item> REPAIRS_CAT_EARS = TagKey.create(
		Registries.ITEM,
		Meow.id("repairs_cat_ears")
	);

	public static final TagKey<Item> CAT_EARS = TagKey.create(
		Registries.ITEM,
		Meow.id("cat_ears")
	);

	public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
		super(output, registryLookupFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider registries) {
		builder(REPAIRS_CAT_EARS)
			.add(BlockItemIds.TRIPWIRE)
			.add(ItemIds.FEATHER)
			.setReplace(true);

		builder(CAT_EARS)
			.addAll(ModItems.CAT_EARS.map(item -> item.builtInRegistryHolder().key()))
			.setReplace(true);
	}
}
