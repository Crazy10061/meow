package meow.transfems.delight.mixin;

import net.minecraft.entity.damage.DeathMessageType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DeathMessageType.class)
public enum DeathMessageTypeMixin {
	TRANSFEMS_DELIGHT_ALWAYS_NORMAL("always_normal");

	@Shadow
	DeathMessageTypeMixin(String id) {};
}
