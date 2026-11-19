package meow.transfems.delight.mrow.recipe;

import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.data.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeAdvancementBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.item.SimpleItemStackView;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class DissolvingRecipeBuilder implements CraftingRecipeJsonBuilder {
	private final RecipeAdvancementBuilder advancementBuilder = new RecipeAdvancementBuilder();
	private final @NotNull Ingredient x;
	private final @NotNull Ingredient y;
	private final @NotNull SimpleItemStackView res;
	private final @NotNull RecipeCategory recipeCategory;

	private DissolvingRecipeBuilder(@NotNull Ingredient x, @NotNull Ingredient y, @NotNull SimpleItemStackView res, @NotNull RecipeCategory recipeCategory) {
		this.x = x;
		this.y = y;
		this.res = res;
		this.recipeCategory = recipeCategory;
	}

	public static DissolvingRecipeBuilder builder(Ingredient x, Ingredient y, SimpleItemStackView res, RecipeCategory category) {
		return new DissolvingRecipeBuilder(x, y, res, category);
	}

	@Override
	public CraftingRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> criterion) {
		this.advancementBuilder.addCriterion(name, criterion);
		return this;
	}

	@Override
	public CraftingRecipeJsonBuilder group(@Nullable String group) {
		return this;
	}

	@Override
	public @Nullable RegistryKey<Recipe<?>> getRegistryKey() {
		return CraftingRecipeJsonBuilder.getItemId(res);
	}

	@Override
	public void offerTo(RecipeExporter exporter, @NonNull RegistryKey<Recipe<?>> key) {
		DissolvingRecipe recipe = new DissolvingRecipe(res, x, y);

		exporter.accept(key, recipe, this.advancementBuilder.createAdvancementEntry(exporter, key, recipeCategory));
	}
}
