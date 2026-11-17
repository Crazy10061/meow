package org.code.meow.mrow.fluid;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.code.meow.Meow;

public class ModFluids {
	public static final FlowableFluid ESTROGEN_SOURCE = register("estrogen_source", new EstrogenFluid.Source());
	public static final FlowableFluid ESTROGEN_FLOWING = register("estrogen_flowing", new EstrogenFluid.Flowing());

	public static final FlowableFluid DISSOLVED_ESTROGEN_SOURCE = register("dissolved_estrogen_source", new DissolvedEstrogenFluid.Source());
	public static final FlowableFluid DISSOLVED_ESTROGEN_FLOWING = register("dissolved_estrogen_flowing", new DissolvedEstrogenFluid.Flowing());

	private static FlowableFluid register(String id, FlowableFluid fluid) {
		return Registry.register(Registries.FLUID, RegistryKey.of(RegistryKeys.FLUID, Meow.id(id)), fluid);
	}

	public static void init() {
		Meow.LOGGER.info("registering ModFluids for mod " + Meow.MOD_ID);
	}
}
