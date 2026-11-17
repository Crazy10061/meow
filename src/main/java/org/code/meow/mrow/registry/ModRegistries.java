package org.code.meow.mrow.registry;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import org.code.meow.Meow;
import org.code.meow.mrow.syringe.InjectionHandler;

public class ModRegistries {
	public static final SimpleRegistry<InjectionHandler> INJECTION_HANDLER = FabricRegistryBuilder.create(RegistryKey.<InjectionHandler>ofRegistry(Meow.id("injection_handler"))).attribute(RegistryAttribute.MODDED).buildAndRegister();

	public static void init() {
		Meow.LOGGER.info("registering ModRegistries for mod " + Meow.MOD_ID);
	}
}
