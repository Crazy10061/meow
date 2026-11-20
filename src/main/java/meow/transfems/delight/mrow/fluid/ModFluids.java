package meow.transfems.delight.mrow.fluid;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import meow.transfems.delight.Meow;

import meow.transfems.delight.util.REWK;

public class ModFluids {
	public static final REWK<Fluid> ESTROGEN_SOURCE = register("estrogen_source", new EstrogenFluid.Source());
	public static final REWK<Fluid> ESTROGEN_FLOWING = register("estrogen_flowing", new EstrogenFluid.Flowing());

	public static final REWK<Fluid> DISSOLVED_ESTROGEN_SOURCE = register("dissolved_estrogen_source", new DissolvedEstrogenFluid.Source());
	public static final REWK<Fluid> DISSOLVED_ESTROGEN_FLOWING = register("dissolved_estrogen_flowing", new DissolvedEstrogenFluid.Flowing());

	private static REWK<Fluid> register(String id, Fluid fluid) {
		RegistryKey<Fluid> key = RegistryKey.of(RegistryKeys.FLUID, Meow.id(id));

		return new REWK(Registry.register(Registries.FLUID, key, fluid), key);
	}

	public static void init() {
		Meow.LOGGER.info("registering ModFluids for mod " + Meow.MOD_ID);
	}
}
