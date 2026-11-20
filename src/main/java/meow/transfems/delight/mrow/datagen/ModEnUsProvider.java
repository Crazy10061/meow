package meow.transfems.delight.mrow.datagen;

import meow.transfems.delight.util.REWK;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryView;
import net.minecraft.util.ColorMap;
import meow.transfems.delight.effect.ModEffects;
import meow.transfems.delight.mrow.block.ModBlocks;
import meow.transfems.delight.mrow.damaage.ModDamageTypes;
import meow.transfems.delight.mrow.gamerule.ModGameRules;
import meow.transfems.delight.mrow.item.ModItemGroups;
import meow.transfems.delight.mrow.item.ModItems;
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
			REWK<Item> ears = ModItems.CAT_EARS.get(color);

			t.add(ears.entry(), I18n.translate("color.minecraft." + color.asString()) + " Cat Ears");
		});

		t.addCreativeModeTab(ModItemGroups.TRANSFEMS_DELIGHT_KEY, "Transfems Delight");
		t.addCreativeModeTab(ModItemGroups.HRT_KEY, "HRT");

		t.add(ModItems.MEOW.entry(), "Meow");
		t.add(ModItems.ESTROGEN_BUCKET.entry(), "Bucket of Estrogen");
		t.add(ModItems.DISSOLVED_ESTROGEN_BUCKET.entry(), "Bucket of Dissolved Estrogen");
		t.add(ModItems.OIL.entry(), "Oil");
		t.add(ModItems.CATNIP.entry(), "Catnip");
		t.add(ModItems.SYRINGE.entry(), "Syringe");

		t.add(ModGameRules.ESTROGEN_SOURCE_CONVERSION.getTranslationKey(), "Estrogen Source Conversion");

		t.add(ModBlocks.DISSOLVING_TABLE.entry(), "Dissolving Table");
		t.add(ModBlocks.DISSOLVED_ESTROGEN_FLUID.entry(), "Dissolved Estrogen");
		t.add(ModBlocks.TRANS_FLAG.entry(), "Transgender Flag");
		t.add(ModBlocks.TRANSFEM_FLAG.entry(), "Transfeminine Flag");
		t.add(ModBlocks.TRANSMASC_FLAG.entry(), "Transmasculine Flag");
		t.add(ModBlocks.ESTROGEN_FLUID.entry(), "Estrogen");

		t.add(ModEffects.GENDER_EUPHORIA.value().getTranslationKey(), "Gender Euphoria");

		t.add("gui.dissolving_table", "Dissolving Table");
		t.add("item.meow.syringe.inside", "Contains %s");

		add(t, ModDamageTypes.INJECTED_LAVA, "%s injected themselves with lava", "%s was injected by %s with lava");
		add(t, ModDamageTypes.ESTROGEN_OD, "%s overdosed on estrogen", "%s overdosed %s on estrogen");
		add(t, ModDamageTypes.UNDISSOLVED_ESTROGEN, "%s forgot to dissolve the estrogen", "%s was forcefemmed by %s with undissolved estrogen");
	}
}
