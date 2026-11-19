package meow.transfems.delight.mrow.item;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class CatEars extends Item {
	public CatEars(Item.Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World level, PlayerEntity player, Hand hand) {
		if (level.isClient() && player instanceof ClientPlayerEntity clientPlayer && clientPlayer.isSneaking()) {
			// todo: maybe make it use our meowlm™  llm?
			clientPlayer.networkHandler.sendChatMessage("meow ^w^");

			return ActionResult.SUCCESS;
		}

		return super.use(level, player, hand);
	}
}
