package meow.transfems.delight.mrow.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockItemKeys;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import meow.transfems.delight.Meow;
import meow.transfems.delight.mrow.registry.ModRegistries;
import meow.transfems.delight.mrow.syringe.InjectionContext;
import meow.transfems.delight.mrow.syringe.InjectionHandler;
import org.jspecify.annotations.NonNull;

public class SyringeItem extends Item {
	public SyringeItem(Item.Settings settings) {
		super(settings);
	}

	@Override
	public @NonNull ActionResult use(@NonNull World level, @NonNull PlayerEntity player, @NonNull Hand hand) {
		if (level.isClient())
			return super.use(level, player, hand);

		ItemStack stack = player.getStackInHand(hand);
		RegistryKey<Block> what = stack.get(ModDataComponents.INSIDE);

		if (player.isSneaking()) {
			for (InjectionHandler handler : ModRegistries.INJECTION_HANDLER) {
				var serverLevel = (ServerWorld)level;
				var serverPlayer = (ServerPlayerEntity)player;

				if (handler.getTrigger().equals(what)) {
					handler.inject(new InjectionContext(
						new InjectionContext.By(
							InjectionContext.By.ByType.SELF,
							serverPlayer
						),
						serverPlayer,
						what,
						serverLevel,
						serverLevel.getServer(),
						Random.create()
					));

					stack.set(ModDataComponents.INSIDE, BlockItemKeys.AIR.block());
					stack.damage(1, player);
				}
			}
		}

		HitResult result = player.raycast(3.0f, 0.0f, true);

		if (result instanceof BlockHitResult blockResult) {
			BlockPos pos = blockResult.getBlockPos();
			BlockState state = level.getBlockState(pos);
			Meow.LOGGER.info(state.getBlock().getRegistryEntry().registryKey().getValue().toString());

			if (!state.getFluidState().is(Fluids.EMPTY)) {
				Block block = state.getFluidState().getBlockState().getBlock();

				stack.set(ModDataComponents.INSIDE, block.getRegistryEntry().registryKey());
			}
		} else if (result instanceof EntityHitResult entityResult) {
			for (InjectionHandler handler : ModRegistries.INJECTION_HANDLER) {
				var serverLevel = (ServerWorld)level;

				if (handler.getTrigger().equals(what)) {
					handler.inject(new InjectionContext(
						new InjectionContext.By(
							InjectionContext.By.ByType.OTHER_PLAYER,
							player
						),
						entityResult.getEntity(),
						what,
						serverLevel,
						serverLevel.getServer(),
						Random.create()
					));

					stack.set(ModDataComponents.INSIDE, BlockItemKeys.AIR.block());
					stack.damage(1, player);
				}
			}
		}

		return super.use(level, player, hand);
	}
}
