package org.code.meow.mrow.syringe;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.RegistryKey;
import org.code.meow.mrow.block.ModBlocks;
import org.code.meow.mrow.damaage.ModDamageTypes;

public class EstrogenInjectionHandler implements InjectionHandler {
	@Override
	public RegistryKey<Block> getTrigger() {
		return ModBlocks.ESTROGEN_FLUID.getSettings().blockId();
	}

	@Override
	public void inject(InjectionContext ctx) {
		var roll = ctx.random().nextDouble();

		if (roll < 0.1) {
			if (ctx.target() instanceof LivingEntity livingEntity) {
				livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 30, 3, true, false, false, null));
			}
		} else if (roll < 0.2) {
			if (ctx.target() instanceof LivingEntity livingEntity) {
				livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BAD_OMEN, 30, 1, true, false, false, null));
			}
		} else {
			ctx.target().damage(ctx.world(), ModDamageTypes.UNDISSOLVED_ESTROGEN.source(ctx), 6);
		}

		// also apply same effect as dissolved
		new DissolvedEstrogenInjectionHandler().inject(ctx);
	}
}
