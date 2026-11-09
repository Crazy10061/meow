package org.code.meow.mrow.item;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class CatEars extends Item {
	public CatEars(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (level.isClientSide() && player instanceof LocalPlayer clientPlayer && clientPlayer.isShiftKeyDown()) {
			// todo: maybe make it use our meowlm™  llm?
			clientPlayer.connection.sendChat("meow ^w^");

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}
}
