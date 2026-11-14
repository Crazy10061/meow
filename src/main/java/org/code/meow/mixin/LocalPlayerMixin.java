package org.code.meow.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Abilities;
import org.code.meow.mrow.datagen.ModItemTagProvider;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
@Debug(export = true)
public class LocalPlayerMixin {
	@Unique
	private boolean meow$hasUsedDoubleJump = false;

	@Unique
	private boolean meow$releasedJump = false;

	@Unique
	private boolean meow$isWearingCatEars() {
		return ((LocalPlayer)(Object)this).getItemBySlot(EquipmentSlot.HEAD).is(ModItemTagProvider.CAT_EARS);
	}

	@Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;aiStep()V", shift = At.Shift.BEFORE))
	private void meow$handleDoubleJump(CallbackInfo ci) {
		var p = ((LocalPlayer)(Object)this);

		if (p.onGround()) meow$hasUsedDoubleJump = false;

		if (!p.input.keyPresses.jump()) {
			meow$releasedJump = true;
		} else if (p.onGround()) {
			meow$releasedJump = false;
		}

		if (!p.onGround() && meow$isWearingCatEars() && p.input.keyPresses.jump() && meow$releasedJump && !meow$hasUsedDoubleJump) {
			p.jumpFromGround();
			meow$hasUsedDoubleJump = true;
			meow$releasedJump = false;
		}
	}

	@Definition(id = "abilities", local = @Local(type = Abilities.class, name = "abilities"))
	@Definition(id = "flying", field = "Lnet/minecraft/world/entity/player/Abilities;flying:Z")
	@Expression("abilities.flying")
	@WrapOperation(method = "aiStep", at = @At("MIXINEXTRAS:EXPRESSION"))
	private boolean canFly(Abilities instance, Operation<Boolean> original) {
		boolean isWearingCatEars = ((LocalPlayer)(Object)this).getItemBySlot(EquipmentSlot.HEAD).is(ModItemTagProvider.CAT_EARS);

		return !isWearingCatEars && original.call(instance);
	}
}
