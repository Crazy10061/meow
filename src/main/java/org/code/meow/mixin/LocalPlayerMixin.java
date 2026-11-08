package org.code.meow.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Abilities;
import org.code.meow.Meow;
import org.code.meow.mrow.datagen.ModItemTagProvider;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LocalPlayer.class)
@Debug(export = true)
public class LocalPlayerMixin {
	@Definition(id = "onGround", method = "Lnet/minecraft/client/player/LocalPlayer;onGround()Z")
	@Expression("this.onGround()")
	@ModifyExpressionValue(method = "aiStep", at = @At("MIXINEXTRAS:EXPRESSION"))
	private boolean canJump(boolean original) {
		boolean isWearingCatEars = ((LocalPlayer)(Object)this).getItemBySlot(EquipmentSlot.HEAD).is(ModItemTagProvider.CAT_EARS);

		return !isWearingCatEars && original;
	}

	@Definition(id = "abilities", local = @Local(type = Abilities.class, name = "abilities"))
	@Definition(id = "flying", field = "Lnet/minecraft/world/entity/player/Abilities;flying:Z")
	@Expression("abilities.flying")
	@WrapOperation(method = "aiStep", at = @At("MIXINEXTRAS:EXPRESSION"))
	private boolean canFly(Abilities instance, Operation<Boolean> original) {
		boolean isWearingCatEars = ((LocalPlayer)(Object)this).getItemBySlot(EquipmentSlot.HEAD).is(ModItemTagProvider.CAT_EARS);

		return isWearingCatEars && !original.call(instance);
	}
}
