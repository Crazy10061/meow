package meow.transfems.delight.mrow.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

public record DissolvingRecipeInput(ItemStack x, ItemStack y) implements RecipeInput {
	@Override
	public ItemStack getStackInSlot(int index) {
		return switch (index) {
			case 0 -> x;
			case 1 -> y;
			default -> ItemStack.EMPTY;
		};
	}

	@Override
	public int size() {
		return 2;
	}
}
