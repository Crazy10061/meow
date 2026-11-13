package org.code.meow.mrow.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.ColorCollection;
import org.code.meow.mrow.item.ModCreativeTabs;
import org.code.meow.mrow.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModEnUsProvider extends FabricLanguageProvider {
	public ModEnUsProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(packOutput, "en_us", registryLookup);
	}

	@Override
	public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder t) {
		ColorCollection.VALUES.forEach(color -> {
			Item ears = ModItems.CAT_EARS.pick(color);

			t.add(ears, I18n.get("color.minecraft." + color.getName()) + " Cat Ears");
		});

		t.add(ModItems.MEOW, "Meow");
		t.addCreativeModeTab(ModCreativeTabs.MEOW_KEY, "Meow");
	}
}
