package org.code.meow.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import org.jspecify.annotations.Nullable;

public class GenderEuphoriaEffect extends StatusEffect {
	public GenderEuphoriaEffect() {
		super(StatusEffectCategory.BENEFICIAL, 0xFF00);
	}

	@Override
	public void applyInstantEffect(ServerWorld world, @Nullable Entity source, @Nullable Entity owner, LivingEntity mob, int amplification, double scale) {
		mob.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 1, 2 << amplification, true, false, false, null));

		super.applyInstantEffect(world, source, owner, mob, amplification, scale);
	}


	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}

	@Override
	public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
		// TODO: idk, add more functionality
		return super.applyUpdateEffect(world, entity, amplifier);
	}
}
