package org.code.meow.mrow.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.world.entity.EquipmentSlot;
import org.code.meow.Meow;
import org.code.meow.mrow.datagen.ModItemTagProvider;

public class ModNetworking {
	public static void init() {
		Meow.LOGGER.info("registering ModNetworking for " + Meow.MOD_ID);

		PayloadTypeRegistry.serverboundPlay().register(DoubleJumpC2SPayload.TYPE, DoubleJumpC2SPayload.PC);

		ServerPlayNetworking.registerGlobalReceiver(DoubleJumpC2SPayload.TYPE, (_payload, ctx) -> {
			var p = ctx.player();
			var head = p.getItemBySlot(EquipmentSlot.HEAD);

			if (!head.is(ModItemTagProvider.CAT_EARS))
				return;

			head.hurtWithoutBreaking(3, p);
		});
	}
}
