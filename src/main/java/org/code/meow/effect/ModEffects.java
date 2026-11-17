package org.code.meow.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import org.code.meow.Meow;

import java.util.function.Supplier;

public class ModEffects {
	public static final RegistryEntry.Reference<StatusEffect> GENDER_EUPHORIA = reg("euphoria", GenderEuphoriaEffect::new);

	private static RegistryEntry.Reference<StatusEffect> reg(String id, Supplier<StatusEffect> effectConstr) {
		return Registry.registerReference(Registries.STATUS_EFFECT, RegistryKey.of(RegistryKeys.STATUS_EFFECT, Meow.id(id)), effectConstr.get());
	}

	public static void init() {
		Meow.LOGGER.info("registering ModEffects for mod " + Meow.MOD_ID);
	}
}
