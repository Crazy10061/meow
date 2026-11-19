package meow.transfems.delight.mrow.item;

import net.minecraft.block.Block;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import meow.transfems.delight.Meow;

public class ModDataComponents {
	public static final ComponentType<RegistryKey<Block>> INSIDE = register(
		"inside",
		ComponentType.<RegistryKey<Block>>builder()
			.codec(RegistryKey.createCodec(RegistryKeys.BLOCK))
			.packetCodec(RegistryKey.createPacketCodec(RegistryKeys.BLOCK))
			.build()
	);

	private static<T> ComponentType<T> register(String id, ComponentType<T> type) {
		return Registry.register(Registries.DATA_COMPONENT_TYPE, RegistryKey.of(RegistryKeys.DATA_COMPONENT_TYPE, Meow.id(id)), type);
	}

	public static void init() {
		Meow.LOGGER.info("registering ModDataComponents for mod " + Meow.MOD_ID);
	}
}
