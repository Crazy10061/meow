package meow.transfems.delight.mrow.recipe;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import meow.transfems.delight.Meow;

public class ModRecipeTypes {
	public static final RecipeSerializer<DissolvingRecipe> DISSOLVING_SERIALIZER = registerSerializer(
		"dissolving",
		new RecipeSerializer<>(DissolvingRecipe.C, DissolvingRecipe.PC)
	);

	public static final RecipeType<DissolvingRecipe> DISSOLVING = registerType(
		"dissolving",
		new RecipeType<>() {}
	);

	private static<T extends Recipe<?>> RecipeType<T> registerType(String id, RecipeType<T> type) {
		return Registry.register(Registries.RECIPE_TYPE, RegistryKey.of(RegistryKeys.RECIPE_TYPE, Meow.id(id)), type);
	}

	private static<T extends Recipe<?>> RecipeSerializer<T> registerSerializer(String id, RecipeSerializer<T> type) {
		return Registry.register(Registries.RECIPE_SERIALIZER, RegistryKey.of(RegistryKeys.RECIPE_SERIALIZER, Meow.id(id)), type);
	}

	public static void init() {
		Meow.LOGGER.info("registering ModRecipeTypes for mod " + Meow.MOD_ID);
	}
}
