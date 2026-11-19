package meow.transfems.delight.mrow.gui;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import meow.transfems.delight.Meow;

public class ModScreens {
	public static void init() {
		Meow.LOGGER.info("registering ModScreens for mod " + Meow.MOD_ID);

		HandledScreens.register(ModScreenHandlers.DISSOLVING_TABLE, DissolvingTableScreen::new);
	}
}
