package org.code.meow.mrow.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SimpleItemStackView;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.world.World;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public record DissolvingRecipe(SimpleItemStackView res, Ingredient x,
                               Ingredient y) implements Recipe<DissolvingRecipeInput> {
	public static final MapCodec<DissolvingRecipe> C = RecordCodecBuilder.mapCodec(inst -> inst.group(
		SimpleItemStackView.CODEC.fieldOf("res").forGetter(DissolvingRecipe::res),
		Ingredient.CODEC.fieldOf("x").forGetter(DissolvingRecipe::x),
		Ingredient.CODEC.fieldOf("y").forGetter(DissolvingRecipe::y)
	).apply(inst, DissolvingRecipe::new));

	public static final PacketCodec<RegistryByteBuf, DissolvingRecipe> PC = PacketCodec.tuple(
		SimpleItemStackView.PACKET_CODEC,
		DissolvingRecipe::res,
		Ingredient.PACKET_CODEC,
		DissolvingRecipe::x,
		Ingredient.PACKET_CODEC,
		DissolvingRecipe::y,
		DissolvingRecipe::new
	);


	@Override
	public boolean matches(DissolvingRecipeInput input, @NonNull World world) {
		return x.test(input.x()) && y.test(input.y());
	}

	@Override
	public ItemStack craft(DissolvingRecipeInput input) {
		return res.toItemStack();
	}

	@Override
	public boolean showNotification() {
		return true;
	}


	@Override
	public @NonNull String getGroup() {
		return "dissolving";
	}

	@Override
	public RecipeSerializer<? extends Recipe<DissolvingRecipeInput>> getSerializer() {
		return ModRecipeTypes.DISSOLVING_SERIALIZER;
	}

	@Override
	public RecipeType<? extends Recipe<DissolvingRecipeInput>> getType() {
		return ModRecipeTypes.DISSOLVING;
	}

	@Override
	public IngredientPlacement getIngredientPlacement() {
		return IngredientPlacement.NONE;
	}

	@Override
	public boolean isIgnoredInRecipeBook() {
		return true;
	}

	@Override
	public @Nullable RecipeBookCategory getRecipeBookCategory() {
		return null;
	}
}
