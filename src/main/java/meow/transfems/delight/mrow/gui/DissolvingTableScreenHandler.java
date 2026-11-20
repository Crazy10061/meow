package meow.transfems.delight.mrow.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.DropType;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.world.ServerWorld;
import meow.transfems.delight.mrow.block.ModBlocks;
import meow.transfems.delight.mrow.recipe.DissolvingRecipe;
import meow.transfems.delight.mrow.recipe.DissolvingRecipeInput;
import meow.transfems.delight.mrow.recipe.ModRecipeTypes;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Optional;

public class DissolvingTableScreenHandler extends ScreenHandler {
	private static final int INPUT_SLOTS_COUNT = 2;
	private static final int RESULT_SLOT = 0;
	private static final int INPUT_SLOTS_START = RESULT_SLOT + 1; // 1
	private static final int INPUT_SLOTS_END = INPUT_SLOTS_START + INPUT_SLOTS_COUNT; // 3
	private static final int INVENTORY_START = INPUT_SLOTS_END; // 3
	private static final int INVENTORY_END = INVENTORY_START + 27; // 30
	private static final int HOTBAR_START = INVENTORY_END; // 30
	private static final int HOTBAR_END = HOTBAR_START + 9; // 39

	private @NotNull PlayerInventory inv;

	private final Inventory input = new SimpleInventory(2) {
		@Override
		public void markDirty() {
			super.markDirty();
			DissolvingTableScreenHandler.this.onContentChanged(this);
		}
	};

	private final CraftingResultInventory out = new CraftingResultInventory();

	private final ScreenHandlerContext ctx;

	public DissolvingTableScreenHandler(int containerId, @NonNull PlayerInventory inv) {
		this(containerId, inv, ScreenHandlerContext.EMPTY);
	}

	public DissolvingTableScreenHandler(int containerId, PlayerInventory inv, ScreenHandlerContext ctx) {
		super(ModScreenHandlers.DISSOLVING_TABLE, containerId);

		this.inv = inv;
		this.ctx = ctx;

		inv.onOpen(inv.player);

		addSlots();

		addPlayerSlots(inv, 8, 84);
	}

	@Override
	public void onContentChanged(@NonNull Inventory container) {
		super.onContentChanged(container);

		if (container != this.input)
			return;

		this.ctx.run((level, _pos) -> {
			if (level instanceof ServerWorld serverWorld)
				this.inputsChanged(serverWorld);
		});
	}

	public void inputsChanged(ServerWorld world) {
		DissolvingRecipeInput recipeInput = new DissolvingRecipeInput(this.input.getStack(0), this.input.getStack(1));
		Optional<RecipeEntry<DissolvingRecipe>> maybeRecipe = world.getRecipeManager().getFirstMatch(ModRecipeTypes.DISSOLVING, recipeInput, world);

		if (maybeRecipe.isPresent()) {
			RecipeEntry<DissolvingRecipe> holder = maybeRecipe.get();
			DissolvingRecipe recipe = holder.value();

			this.out.setLastRecipe(holder);
			this.out.setStack(0, recipe.craft(recipeInput));
		} else {
			this.out.setLastRecipe(null);
			this.out.setStack(0, ItemStack.EMPTY);
		}
	}

	private void addSlots() {
		this.addSlot(new Slot(this.input, 0, 56, 17));
		this.addSlot(new Slot(this.input, 1, 56, 53));

		this.addSlot(new DissolvingTableResultSlot(this, this.out, 0, 116, 35));
	}

	@Override
	public @NonNull ItemStack quickMove(@NonNull PlayerEntity player, int slotIndex) {
		Slot slot = this.slots.get(slotIndex);

		//noinspection ConstantValue
		if (slot == null || !slot.hasStack()) {
			return ItemStack.EMPTY;
		}

		ItemStack stack = slot.getStack();
		ItemStack clicked = stack.copy();

		if (slotIndex == RESULT_SLOT) {
			stack.getItem().onCraftByPlayer(stack, player);

			if (!this.insertItem(stack, INVENTORY_START, HOTBAR_END, true)) {
				return ItemStack.EMPTY;
			}

			slot.onQuickTransfer(stack, clicked);
		} else if (slotIndex >= INVENTORY_START && slotIndex < HOTBAR_END) {
			if (!this.insertItem(stack, INPUT_SLOTS_START, INPUT_SLOTS_END, false)) {
				if (slotIndex < HOTBAR_START) {
					if (!this.insertItem(stack, HOTBAR_START, HOTBAR_END, false)) {
						return ItemStack.EMPTY;
					}
				} else if (!this.insertItem(stack, INVENTORY_START, INVENTORY_END, false)) {
					return ItemStack.EMPTY;
				}
			}
		} else if (!this.insertItem(stack, INVENTORY_START, HOTBAR_END, false)) {
			return ItemStack.EMPTY;
		}

		if (stack.isEmpty()) {
			slot.setStackNoCallbacks(ItemStack.EMPTY);
		} else {
			slot.markDirty();
		}

		if (stack.getCount() == clicked.getCount()) {
			return ItemStack.EMPTY;
		}

		slot.onTakeItem(player, stack);

		if (slotIndex == RESULT_SLOT) {
			player.dropItem(stack, false, DropType.PREDICTED);
		}

		return clicked;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return canUse(ctx, player, ModBlocks.DISSOLVING_TABLE.entry());
	}

	public void onTake(PlayerEntity player, ItemStack carried) {
		carried.onCraftByPlayer(player, carried.getCount());
		this.out.unlockLastRecipe(player, List.of(this.input.getStack(0), this.input.getStack(1)));
		this.input.removeStack(0, 1);
		this.input.removeStack(1, 1);
	}

	@Override
	public void onClosed(@NonNull PlayerEntity player) {
		super.onClosed(player);

		this.ctx.run((_level, _pos) -> this.dropInventory(player, this.input));
	}

	@Override
	public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
		return slot.inventory != this.out && super.canInsertIntoSlot(stack, slot);
	}
}
