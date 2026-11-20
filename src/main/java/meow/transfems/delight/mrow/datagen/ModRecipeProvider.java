package meow.transfems.delight.mrow.datagen;

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
import meow.transfems.delight.mrow.item.ModItems;
import meow.transfems.delight.mrow.recipe.DissolvingRecipeBuilder;
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
					createShaped(RecipeCategory.COMBAT, dyedCatEars.entry())
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
					Ingredient.ofItem(ModItems.ESTROGEN_BUCKET.entry()),
					Ingredient.ofItem(ModItems.OIL.entry()),
					new SimpleItemStackView(ModItems.DISSOLVED_ESTROGEN_BUCKET.entry(), 1),
					RecipeCategory.MISC
				)
					.criterion(getRecipeName(ModItems.ESTROGEN_BUCKET.entry()), this.conditionsFromItem(ModItems.ESTROGEN_BUCKET.entry()))
					.offerTo(this.exporter);

				offerDyeableRecipes(Items.DYES.values(), ModItems.CAT_EARS.map(ears -> ears.entry()).values(), "cat_ears", RecipeCategory.COMBAT);
			}
		};
	}

	@Override
	public String getName() {
		// 😭🥀
		return this.getClass().getSimpleName();
	}
}
