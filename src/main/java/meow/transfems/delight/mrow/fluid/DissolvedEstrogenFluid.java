package meow.transfems.delight.mrow.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import meow.transfems.delight.mrow.block.ModBlocks;
import meow.transfems.delight.mrow.item.ModItems;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public abstract class DissolvedEstrogenFluid extends EstrogenFluid {
	@Override
	public @NonNull Fluid getFlowing() {
		return ModFluids.DISSOLVED_ESTROGEN_FLOWING.entry();
	}

	@Override
	public @NonNull Fluid getStill() {
		return ModFluids.DISSOLVED_ESTROGEN_SOURCE.entry();
	}

	@Override
	public boolean matchesType(@NonNull Fluid other) {
		return other == ModFluids.DISSOLVED_ESTROGEN_FLOWING.entry() || other == ModFluids.DISSOLVED_ESTROGEN_SOURCE.entry();
	}

	@Override
	public @NonNull Item getBucketItem() {
		return ModItems.DISSOLVED_ESTROGEN_BUCKET.entry();
	}

	@Override
	@NonNull
	public BlockState toBlockState(@NonNull FluidState fluidState) {
		return ModBlocks.DISSOLVED_ESTROGEN_FLUID.entry().getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(fluidState));
	}

	public static class Flowing extends DissolvedEstrogenFluid {
		@Override
		protected void appendProperties(@NotNull StateManager.Builder<Fluid, FluidState> builder) {
			super.appendProperties(builder);
			builder.add(LEVEL);
		}

		@Override
		public int getLevel(FluidState fluidState) {
			return fluidState.get(LEVEL);
		}

		@Override
		public boolean isStill(@NonNull FluidState fluidState) {
			return false;
		}
	}

	public static class Source extends DissolvedEstrogenFluid {
		@Override
		public boolean isStill(@NonNull FluidState fluidState) {
			return true;
		}

		@Override
		public int getLevel(@NonNull FluidState fluidState) {
			return 8;
		}
	}
}
