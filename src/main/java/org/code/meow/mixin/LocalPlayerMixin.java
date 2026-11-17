package org.code.meow.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerAbilities;
import org.code.meow.mrow.datagen.ModItemTagProvider;
import org.code.meow.mrow.networking.DoubleJumpC2SPayload;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
@Debug(export = true)
public class LocalPlayerMixin {
	@Unique
	private boolean meow$hasUsedDoubleJump = false;

	@Unique
	private boolean meow$releasedJump = false;

	@Unique
	private boolean meow$isWearingCatEars() {
		return ((ClientPlayerEntity)(Object)this).getEquipment(EquipmentSlot.HEAD).isIn(ModItemTagProvider.CAT_EARS);
	}

	@Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;tickMovement()V", shift = At.Shift.BEFORE))
	private void meow$handleDoubleJump(CallbackInfo ci) {
		var p = (ClientPlayerEntity)(Object)this;
		var ears = p.getEquipment(EquipmentSlot.HEAD);

		if (p.isCreative() || p.isSpectator()) return;

		if (p.isOnGround()) meow$hasUsedDoubleJump = false;

		if (!p.input.playerInput.jump()) {
			meow$releasedJump = true;
		} else if (p.isOnGround()) {
			meow$releasedJump = false;
		}

		if (!p.isOnGround() && meow$isWearingCatEars() && p.input.playerInput.jump() && meow$releasedJump && !meow$hasUsedDoubleJump && !ears.willBreakNextUse()) {
			p.jump();

			ClientPlayNetworking.send(new DoubleJumpC2SPayload());

			meow$hasUsedDoubleJump = true;
			meow$releasedJump = false;
		}
	}

	@Definition(id = "abilities", local = @Local(type = PlayerAbilities.class, name = "abilities"))
	@Definition(id = "flying", field = "Lnet/minecraft/entity/player/PlayerAbilities;flying:Z")
	@Expression("abilities.flying")
	@WrapOperation(method = "tickMovement", at = @At("MIXINEXTRAS:EXPRESSION"))
	private boolean canFly(PlayerAbilities instance, Operation<Boolean> original) {
		var p = (ClientPlayerEntity)(Object)this;
		boolean isWearingCatEars = p.getEquipment(EquipmentSlot.HEAD).isIn(ModItemTagProvider.CAT_EARS);

		return (p.isCreative() || p.isSpectator()) ? original.call(instance) : (!isWearingCatEars && original.call(instance));
	}
}