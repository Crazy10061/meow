package meow.transfems.delight.mrow.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.CollisionEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import meow.transfems.delight.mrow.block.ModBlocks;
import meow.transfems.delight.mrow.gamerule.ModGameRules;
import meow.transfems.delight.mrow.item.ModItems;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public abstract class EstrogenFluid extends WaterFluid {
	@Override
	public void randomDisplayTick(@NonNull World world, @NonNull BlockPos pos, @NonNull FluidState fluidState, Random random) {
		if (random.nextInt(10) == 0) {
			world.addParticleClient(ParticleTypes.END_ROD, pos.getX() + random.nextDouble(), pos.getY() + random.nextDouble(), pos.getZ() + random.nextDouble(), 0, 0, 0);
		}

		super.randomDisplayTick(world, pos, fluidState, random);
	}


	@Override
	public @Nullable ParticleEffect getParticle() {
		return null;
	}

	@Override
	protected boolean isInfinite(ServerWorld world) {
		return world.getGameRules().getValue(ModGameRules.ESTROGEN_SOURCE_CONVERSION);
	}

	@Override
	public int getMaxFlowDistance(@NonNull WorldView world) {
		return 4;
	}


	@Override
	public int getLevelDecreasePerBlock(@NonNull WorldView world) {
		return 1;
	}

	@Override
	public int getTickRate(@NonNull WorldView world) {
		return 6;
	}

	@Override
	protected float getBlastResistance() {
		return 10.0f;
	}

	@Override
	public @NonNull Optional<SoundEvent> getBucketFillSound() {
		return Optional.of(SoundEvents.ITEM_BUCKET_FILL);
	}

	@Override
	public @NonNull Fluid getFlowing() {
		return ModFluids.ESTROGEN_FLOWING.entry();
	}

	@Override
	public @NonNull Fluid getStill() {
		return ModFluids.ESTROGEN_SOURCE.entry();
	}

	@Override
	public boolean matchesType(@NonNull Fluid other) {
		return other == ModFluids.ESTROGEN_FLOWING.entry() || other == ModFluids.ESTROGEN_SOURCE.entry();
	}

	@Override
	public @NonNull Item getBucketItem() {
		return ModItems.ESTROGEN_BUCKET.entry();
	}

	@Override
	protected void onEntityCollision(@NonNull World world, @NonNull BlockPos pos, @NonNull Entity entity, EntityCollisionHandler collisionHandler) {
		collisionHandler.addEvent(CollisionEvent.EXTINGUISH);
	}

	@Override
	@NonNull
	public BlockState toBlockState(@NonNull FluidState fluidState) {
		return ModBlocks.ESTROGEN_FLUID.entry().getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(fluidState));
	}

	public static class Flowing extends EstrogenFluid {
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

	public static class Source extends EstrogenFluid {
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
