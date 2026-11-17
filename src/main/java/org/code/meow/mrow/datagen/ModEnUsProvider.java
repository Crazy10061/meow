package org.code.meow.mrow.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryView;
import net.minecraft.util.ColorMap;
import org.code.meow.effect.ModEffects;
import org.code.meow.mrow.block.ModBlocks;
import org.code.meow.mrow.damaage.ModDamageTypes;
import org.code.meow.mrow.gamerule.ModGameRules;
import org.code.meow.mrow.item.ModItemGroups;
import org.code.meow.mrow.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModEnUsProvider extends FabricLanguageProvider {
	public ModEnUsProvider(FabricPackOutput packOutput, CompletableFuture<RegistryView.WrapperLookup> registryLookup) {
		super(packOutput, "en_us", registryLookup);
	}

	public void add(TranslationBuilder t, ModDamageTypes.InjectionDamageType damageType, String selfTxt, String otherTxt) {
		t.add("death.normal." + damageType.self().getValue().getPath(), selfTxt);
		t.add("death.normal." + damageType.other().getValue().getPath(), otherTxt);
	}

	@Override
	public void generateTranslations(@NotNull RegistryView.WrapperLookup registryLookup, TranslationBuilder t) {
		ColorMap.COLORS.forEach(color -> {
			Item ears = ModItems.CAT_EARS.get(color);

			t.add(ears, I18n.translate("color.minecraft." + color.asString()) + " Cat Ears");
		});

		t.addCreativeModeTab(ModItemGroups.MEOW_KEY, "Meow");
		t.addCreativeModeTab(ModItemGroups.HRT_KEY, "HRT");

		t.add(ModItems.MEOW, "Meow");
		t.add(ModItems.ESTROGEN_BUCKET, "Bucket of Estrogen");
		t.add(ModItems.DISSOLVED_ESTROGEN_BUCKET, "Bucket of Dissolved Estrogen");
		t.add(ModItems.OIL, "Oil");
		t.add(ModItems.CATNIP, "Catnip");
		t.add(ModItems.SYRINGE, "Syringe");

		t.add(ModGameRules.ESTROGEN_SOURCE_CONVERSION.getTranslationKey(), "Estrogen Source Conversion");

		t.add(ModBlocks.DISSOLVING_TABLE, "Dissolving Table");
		t.add(ModBlocks.DISSOLVED_ESTROGEN_FLUID, "Dissolved Estrogen");
		t.add(ModBlocks.ESTROGEN_FLUID, "Estrogen");

		t.add(ModEffects.GENDER_EUPHORIA.value().getTranslationKey(), "Gender Euphoria");

		t.add("gui.dissolving_table", "Dissolving Table");
		t.add("item.meow.syringe.inside", "Contains %s");

		add(t, ModDamageTypes.INJECTED_LAVA, "%s injected themselves with lava", "%s was injected by %s with lava");
		add(t, ModDamageTypes.ESTROGEN_OD, "%s overdosed on estrogen", "%s overdosed %s on estrogen");
		add(t, ModDamageTypes.UNDISSOLVED_ESTROGEN, "%s forgot to dissolve the estrogen", "%s was forcefemmed by %s with undissolved estrogen");
	}
}
