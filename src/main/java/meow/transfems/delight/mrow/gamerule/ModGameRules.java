package meow.transfems.delight.mrow.gamerule;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.rule.GameRule;
import net.minecraft.world.rule.GameRuleCategory;
import meow.transfems.delight.Meow;

public class ModGameRules {
	public static final GameRule<Boolean> ESTROGEN_SOURCE_CONVERSION = reg("estrogen_source_conversion", GameRuleBuilder.forBoolean(false)
		.category(GameRuleCategory.UPDATES)
		.build());

	private static<T> GameRule<T> reg(String id, GameRule<T> rule) {
		return Registry.register(
			Registries.GAME_RULE,
			RegistryKey.of(RegistryKeys.GAME_RULE, Meow.id(id)),
			rule
		);
	}

	public static void init() {
		Meow.LOGGER.info("registering ModGameRules for mod " + Meow.MOD_ID);
	}
}
