package meow.transfems.delight.mrow.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import meow.transfems.delight.mrow.gui.DissolvingTableScreenHandler;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class DissolvingTableBlock extends BlockWithEntity {
	public DissolvingTableBlock(Settings settings) {
		super(settings);
	}

	@Override
	protected @NonNull ActionResult onUse(@NonNull BlockState state, @NonNull World world, @NonNull BlockPos pos, @NonNull PlayerEntity player, @NonNull BlockHitResult hitResult) {
		if (!world.isClient() && world.getBlockEntity(pos) instanceof DissolvingTableBlockEntity dtbe) {
			player.openHandledScreen(dtbe);
		}

		return ActionResult.SUCCESS;
	}

	@Override
	public @Nullable BlockEntity createBlockEntity(@NonNull BlockPos worldPosition, @NonNull BlockState blockState) {
		return new DissolvingTableBlockEntity(worldPosition, blockState);
	}

	@Override
	protected @Nullable NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
		return new SimpleNamedScreenHandlerFactory(
			(syncId, inv, _) -> new DissolvingTableScreenHandler(syncId, inv, ScreenHandlerContext.create(world, pos)), this.getName()
		);
	}
}
