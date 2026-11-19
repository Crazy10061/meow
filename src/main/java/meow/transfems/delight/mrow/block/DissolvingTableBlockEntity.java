package meow.transfems.delight.mrow.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import meow.transfems.delight.mrow.gui.DissolvingTableScreenHandler;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class DissolvingTableBlockEntity extends BlockEntity implements ImplementedContainer, NamedScreenHandlerFactory {
	// x,y,res
	public static final int INVENTORY_SIZE = 3;
	private final DefaultedList<ItemStack> items = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);

	public DissolvingTableBlockEntity(BlockPos worldPosition, BlockState blockState) {
		super(ModBlockEntities.DISSOLVING_TABLE, worldPosition, blockState);
	}

	@Override
	public DefaultedList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void readData(ReadView view) {
		Inventories.readData(view, items);
		super.readData(view);
	}

	@Override
	protected void writeData(WriteView view) {
		Inventories.writeData(view, items);
		super.writeData(view);
	}

	@Override
	public boolean canPlayerUse(PlayerEntity player) {
		return Inventory.canPlayerUse(this, player);
	}

	@Override
	public @NonNull Text getDisplayName() {
		return Text.translatable("gui.dissolving_table");
	}

	@Override
	public @Nullable ScreenHandler createMenu(int syncId, @NonNull PlayerInventory inventory, @NonNull PlayerEntity player) {
		return new DissolvingTableScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, getPos()));
	}
}
