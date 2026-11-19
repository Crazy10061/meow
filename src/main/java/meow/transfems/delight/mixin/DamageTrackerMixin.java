package meow.transfems.delight.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTracker;
import net.minecraft.entity.damage.DeathMessageType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageTracker.class)
public class DamageTrackerMixin {
	@Definition(id = "messageType", local = @Local(type = DeathMessageType.class, name = "messageType"))
	@Definition(id = "FALL_VARIANTS", field = "Lnet/minecraft/entity/damage/DeathMessageType;FALL_VARIANTS:Lnet/minecraft/entity/damage/DeathMessageType;")
	@Expression("messageType == FALL_VARIANTS")
	@Inject(method = "getDeathMessage", at = @At(value = "MIXINEXTRAS:EXPRESSION", shift = At.Shift.BEFORE), cancellable = true)
	private void getDeathMessage(CallbackInfoReturnable<Text> cir, @Local(name = "messageType") DeathMessageType messageType, @Local(name = "killingSource") DamageSource killingSource) {
		if (messageType.asString().equals("always_normal")) {
			var direct = killingSource.getSource();
			var causing = killingSource.getAttacker();

			cir.setReturnValue(Text.translatable("death.normal." + killingSource.getName(), direct == null ? null : direct.getDisplayName(), causing == null ? null : causing.getDisplayName()));
		}
	}
}
