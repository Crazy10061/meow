package meow.transfems.delight.mrow.registry;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import meow.transfems.delight.Meow;
import meow.transfems.delight.mrow.syringe.InjectionHandler;

public class ModRegistries {
	public static final SimpleRegistry<InjectionHandler> INJECTION_HANDLER = FabricRegistryBuilder.create(RegistryKey.<InjectionHandler>ofRegistry(Meow.id("injection_handler"))).attribute(RegistryAttribute.MODDED).buildAndRegister();

	public static void init() {
		Meow.LOGGER.info("registering ModRegistries for mod " + Meow.MOD_ID);
	}
}
