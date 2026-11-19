package meow.transfems.delight.mrow.syringe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockKeys;
import net.minecraft.entity.Entity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import meow.transfems.delight.mrow.damaage.ModDamageTypes;

public class LavaInjectionHandler implements InjectionHandler {
	@Override
	public RegistryKey<Block> getTrigger() {
		return BlockKeys.LAVA;
	}

	@Override
	public void inject(InjectionContext ctx) {
		Entity target = ctx.target();
		ServerWorld world = ctx.world();

		target.damage(world, ModDamageTypes.INJECTED_LAVA.source(ctx), 3);
	}
}
