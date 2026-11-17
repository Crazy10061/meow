package org.code.meow.mrow.damaage;

import net.minecraft.entity.damage.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.code.meow.Meow;
import org.code.meow.mrow.syringe.InjectionContext;

import java.util.function.BiConsumer;

public class ModDamageTypes {
	public record InjectionDamageType(RegistryKey<DamageType> self, RegistryKey<DamageType> other) {
		public RegistryKey<DamageType> get(InjectionContext.By.ByType by) {
//			return by == InjectionContext.By.ByType.SELF ? self : (by == InjectionContext.By.ByType.OTHER_PLAYER ? other : null);
			return by == InjectionContext.By.ByType.SELF ? self : other;
		}

		public DamageSource source(InjectionContext ctx) {
			var holder = ctx.target().getEntityWorld()
				.getRegistryManager()
				.getOrThrow(RegistryKeys.DAMAGE_TYPE)
				.getOptional(get(ctx.by().type())).orElseThrow();

			return new DamageSource(holder, ctx.target(), ctx.by().by());
		}

		public void datagen(BiConsumer<Identifier, DamageType> provider) {
			provider.accept(self.getValue(), new DamageType(self.getValue().getPath(), DamageScaling.NEVER, 0f, DamageEffects.BURNING, DeathMessageType.MEOW_ALWAYS_NORMAL));
			provider.accept(other.getValue(), new DamageType(other.getValue().getPath(), DamageScaling.NEVER, 0f, DamageEffects.BURNING, DeathMessageType.MEOW_ALWAYS_NORMAL));
		}
	};

	public static final InjectionDamageType INJECTED_LAVA = reg("injected_lava");
	public static final InjectionDamageType ESTROGEN_OD = reg("estrogen_od");
	public static final InjectionDamageType UNDISSOLVED_ESTROGEN = reg("undissolved_estrogen");

	public static InjectionDamageType reg(String id) {
		return new InjectionDamageType(RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Meow.id("self_" + id)), RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Meow.id("other_" + id)));
	}

	public static void init() {
		Meow.LOGGER.info("registering ModDamageTypes for mod " + Meow.MOD_ID);
	}
}
