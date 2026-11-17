package org.code.meow.mrow.syringe;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.RegistryKey;
import org.code.meow.Meow;
import org.code.meow.data.EstrogenInfo;
import org.code.meow.data.ModDataAttachments;
import org.code.meow.effect.ModEffects;
import org.code.meow.mrow.block.ModBlocks;
import org.code.meow.mrow.damaage.ModDamageTypes;

public class DissolvedEstrogenInjectionHandler implements InjectionHandler {
	@Override
	public RegistryKey<Block> getTrigger() {
		return ModBlocks.DISSOLVED_ESTROGEN_FLUID.getSettings().blockId();
	}

	@Override
	public void inject(InjectionContext ctx) {
		if (ctx.target() instanceof LivingEntity livingEntity) {
			EstrogenInfo info = livingEntity.getAttachedOrCreate(ModDataAttachments.ESTROGEN_INFO);

			int amplifier = info.startedHRT() ? Math.min(255, info.injections()) : 1;
			livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.GENDER_EUPHORIA, 28800, amplifier, true, true, true, null));
		}

		long gameTime = ctx.world().getDimensionTime();

		EstrogenInfo oldInfo = ctx.target().getAttachedOrCreate(ModDataAttachments.ESTROGEN_INFO);

		long timeSinceLastInjection = gameTime - oldInfo.lastInjection();

		if (timeSinceLastInjection < Meow.CONF.estrogenDecay && oldInfo.startedHRT())
			ctx.target().damage(ctx.world(), ModDamageTypes.ESTROGEN_OD.source(ctx), 20.0f);

		EstrogenInfo newInfo = new EstrogenInfo(true, oldInfo.injections() + 1, gameTime);

		ctx.target().setAttached(ModDataAttachments.ESTROGEN_INFO, newInfo);
	}
}
