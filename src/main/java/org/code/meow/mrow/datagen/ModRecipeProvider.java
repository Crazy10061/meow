package org.code.meow.mrow.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.ColorCollection;
import org.code.meow.mrow.item.ModItems;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
	public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
		return new RecipeProvider(recipes, advancements) {
			@Override
			public void buildRecipes() {
				ColorCollection.zipApply(Items.DYE, ModItems.CAT_EARS, (dye, dyedCatEars) -> {
					shaped(RecipeCategory.COMBAT, dyedCatEars)
						.define('#', Items.STRING)
						.define('$', dye)
						.pattern("# #")
						.pattern("# #")
						.pattern("$ $")
						.group("cat_ears")
						.unlockedBy(getHasName(dye), this.has(dye))
						.save(this.output);
				});

				colorItemWithDye(Items.DYE.asList(), ModItems.CAT_EARS.asList(), "cat_ears", RecipeCategory.COMBAT);
			}
		};
	}

	@Override
	public String getName() {
		// 😭🥀
		return this.getClass().getSimpleName();
	}
}
