package org.code.meow.mrow.syringe;

import net.minecraft.registry.Registry;
import org.code.meow.Meow;
import org.code.meow.mrow.registry.ModRegistries;

public class ModInjectionHandlers {
	public static void init() {
		Meow.LOGGER.info("registering ModInjectionHandlers for mod " + Meow.MOD_ID);

		Registry.register(ModRegistries.INJECTION_HANDLER, Meow.id("lava"), new LavaInjectionHandler());
		Registry.register(ModRegistries.INJECTION_HANDLER, Meow.id("estrogen"), new EstrogenInjectionHandler());
		Registry.register(ModRegistries.INJECTION_HANDLER, Meow.id("dissolved_estrogen"), new DissolvedEstrogenInjectionHandler());
	}
}
