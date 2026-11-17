package org.code.meow.mrow.gui;

import net.minecraft.client.gui.screen.ingame.HandledScreens;
import org.code.meow.Meow;

public class ModScreens {
	public static void init() {
		Meow.LOGGER.info("registering ModScreens for mod " + Meow.MOD_ID);

		HandledScreens.register(ModScreenHandlers.DISSOLVING_TABLE, DissolvingTableScreen::new);
	}
}
