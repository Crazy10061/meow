package org.code.meow.mrow.block;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * A simple {@link Inventory} implementation with only default methods + an item list getter.
 *
 * @author Juuz
 */
public interface ImplementedContainer extends Inventory {
	/**
	 * Retrieves the item list of this container.
	 * Must return the same instance every time it's called.
	 */
	DefaultedList<ItemStack> getItems();

	/**
	 * Creates a container from the item list.
	 */
	static ImplementedContainer of(DefaultedList<ItemStack> items) {
		return () -> items;
	}

	/**
	 * Creates a new container with the specified size.
	 */
	static ImplementedContainer ofSize(int size) {
		return of(DefaultedList.ofSize(size, ItemStack.EMPTY));
	}

	/**
	 * Returns the container size.
	 */
	@Override
	default int size() {
		return this.getItems().size();
	}

	/**
	 * Checks if the container is empty.
	 * @return true if this container has only empty stacks, false otherwise.
	 */
	@Override
	default boolean isEmpty() {
		for (int i = 0; i < this.size(); i++) {
			ItemStack stack = this.getStack(i);

			if (!stack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Retrieves the item in the slot.
	 */
	@Override
	default ItemStack getStack(int slot) {
		return this.getItems().get(slot);
	}

	/**
	 * Removes items from a container slot.
	 * @param slot  The slot to remove from.
	 * @param count How many items to remove. If there are fewer items in the slot than what are requested,
	 *              takes all items in that slot.
	 */
	@Override
	default ItemStack removeStack(int slot, int count) {
		ItemStack result = Inventories.splitStack(this.getItems(), slot, count);

		if (!result.isEmpty()) {
			this.markDirty();
		}

		return result;
	}

	/**
	 * Removes all items from a container slot.
	 * @param slot The slot to remove from.
	 */
	@Override
	default ItemStack removeStack(int slot) {
		return Inventories.removeStack(this.getItems(), slot);
	}

	/**
	 * Replaces the current stack in an container slot with the provided stack.
	 * @param slot  The container slot of which to replace the item stack.
	 * @param stack The replacing item stack. If the stack is too big for
	 *              this container ({@link Inventory#getMaxCountPerStack()}}),
	 *              it gets resized to this container's maximum amount.
	 */
	@Override
	default void setStack(int slot, ItemStack stack) {
		this.getItems().set(slot, stack);

		stack.capCount(this.getMaxCount(stack));

		this.markDirty();
	}

	/**
	 * Clears the container.
	 */
	@Override
	default void clear() {
		this.getItems().clear();
	}

	/**
	 * Marks that the state has changed.
	 * Must be called after changes in the container, so that the game can properly save
	 * the container contents and notify neighboring blocks of container changes.
	 */
	@Override
	default void markDirty() {
		// Override if you want behavior.
	}

	/**
	 * @return true if the player can use the container, false otherwise.
	 */
	@Override
	default boolean canPlayerUse(PlayerEntity player) {
		return true;
	}
}
