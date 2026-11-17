package org.code.meow.mrow.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.jspecify.annotations.NonNull;

public class DissolvingTableResultSlot extends Slot {
	private final DissolvingTableScreenHandler handler;

	public DissolvingTableResultSlot(DissolvingTableScreenHandler handler, Inventory container, int slot, int x, int y) {
		super(container, slot, x, y);
		this.handler = handler;
	}

	@Override
	public void onTakeItem(@NonNull PlayerEntity player, @NonNull ItemStack carried) {
		this.handler.onTake(player, carried);
	}

	@Override
	public boolean canInsert(ItemStack itemStack) {
		return false;
	}


	@Override
	public boolean disablesDynamicDisplay() {
		return true;
	}
}
