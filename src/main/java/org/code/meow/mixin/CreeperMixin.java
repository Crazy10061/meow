package org.code.meow.mixin;

import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import org.code.meow.mrow.datagen.ModItemTagProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(Creeper.class)
public class CreeperMixin {
	@Inject(method = "registerGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/goal/AvoidEntityGoal;<init>(Lnet/minecraft/world/entity/PathfinderMob;Ljava/lang/Class;FDD)V", ordinal = 0))
	private void registerGoals(CallbackInfo ci) {
		Creeper self = (Creeper)(Object)this;

		self.getGoalSelector().addGoal(
			3,
			new AvoidEntityGoal<>(
				self,
				Player.class,
				livingEntity -> livingEntity instanceof Player player && player.getItemBySlot(EquipmentSlot.HEAD).is(ModItemTagProvider.CAT_EARS),
				6.0F,
				1.0F,
				1.2,
				EntitySelector.NO_CREATIVE_OR_SPECTATOR
			)
		);
	}
}
