package org.code.meow.mrow.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.item.SimpleItemStackView;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryView;
import net.minecraft.util.ColorMap;
import org.code.meow.mrow.item.ModItems;
import org.code.meow.mrow.recipe.DissolvingRecipeBuilder;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
	public ModRecipeProvider(FabricPackOutput output, CompletableFuture<RegistryView.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	private DissolvingRecipeBuilder dissolving(Ingredient x, Ingredient y, SimpleItemStackView res, RecipeCategory cat) {
		return DissolvingRecipeBuilder.builder(x, y, res, cat);
	}

	@Override
	protected @NonNull RecipeGenerator createRecipeProvider(RegistryView.WrapperLookup registries, Registerable<Recipe<?>> recipes, Registerable<Advancement> advancements) {
		return new RecipeGenerator(recipes, advancements) {
			@Override
			public void generate() {
				ColorMap.forEachZipped(Items.DYES, ModItems.CAT_EARS, (dye, dyedCatEars) -> {
					createShaped(RecipeCategory.COMBAT, dyedCatEars)
						.input('#', Items.TRIPWIRE)
						.input('$', dye)
						.pattern("# #")
						.pattern("# #")
						.pattern("$ $")
						.group("cat_ears")
						.criterion(getRecipeName(dye), this.conditionsFromItem(dye))
						.offerTo(this.exporter);
				});

				dissolving(
					Ingredient.ofItem(ModItems.ESTROGEN_BUCKET),
					Ingredient.ofItem(ModItems.OIL),
					new SimpleItemStackView(ModItems.DISSOLVED_ESTROGEN_BUCKET, 1),
					RecipeCategory.MISC
				)
					.criterion(getRecipeName(ModItems.ESTROGEN_BUCKET), this.conditionsFromItem(ModItems.ESTROGEN_BUCKET))
					.offerTo(this.exporter);

				offerDyeableRecipes(Items.DYES.values(), ModItems.CAT_EARS.values(), "cat_ears", RecipeCategory.COMBAT);
			}
		};
	}

	@Override
	public String getName() {
		// 😭🥀
		return this.getClass().getSimpleName();
	}
}
