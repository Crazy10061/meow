package org.code.meow.mrow.gui;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.code.meow.Meow;

public class ModScreenHandlers {
	public static final ScreenHandlerType<DissolvingTableScreenHandler> DISSOLVING_TABLE = register("dissolving_table", DissolvingTableScreenHandler::new);

	private static<T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType.Factory<T> constr) {
		return Registry.register(Registries.SCREEN_HANDLER, RegistryKey.of(RegistryKeys.SCREEN_HANDLER, Meow.id(id)), new ScreenHandlerType<>(constr, FeatureSet.empty()));
	}
	public static void init() {
		Meow.LOGGER.info("registering ModScreenHandlers for " + Meow.MOD_ID);
	}
}
