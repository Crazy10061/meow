package meow.transfems.delight.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import meow.transfems.delight.mrow.datagen.ModItemTagProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public class CreeperMixin {
	@Inject(method = "initGoals", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/FleeEntityGoal;<init>(Lnet/minecraft/entity/mob/PathAwareEntity;Ljava/lang/Class;FDD)V", ordinal = 0))
	private void initGoals(CallbackInfo ci) {
		CreeperEntity self = (CreeperEntity)(Object)this;

		self.getGoalSelector().add(
			3,
			new FleeEntityGoal<>(
				self,
				PlayerEntity.class,
				livingEntity -> livingEntity instanceof PlayerEntity player && player.getEquipment(EquipmentSlot.HEAD).isIn(ModItemTagProvider.CAT_EARS),
				6.0F,
				1.0F,
				1.2,
				EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR
			)
		);
	}
}
