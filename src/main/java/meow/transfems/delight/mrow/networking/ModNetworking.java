package meow.transfems.delight.mrow.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EquipmentSlot;
import meow.transfems.delight.Meow;
import meow.transfems.delight.mrow.datagen.ModItemTagProvider;

public class ModNetworking {
	public static void init() {
		Meow.LOGGER.info("registering ModNetworking for " + Meow.MOD_ID);

		PayloadTypeRegistry.serverboundPlay().register(DoubleJumpC2SPayload.TYPE, DoubleJumpC2SPayload.PC);

		ServerPlayNetworking.registerGlobalReceiver(DoubleJumpC2SPayload.TYPE, (_payload, ctx) -> {
			var p = ctx.player();
			var head = p.getEquipment(EquipmentSlot.HEAD);

			if (!head.isIn(ModItemTagProvider.CAT_EARS))
				return;

			head.damage(3, p);
		});
	}
}
