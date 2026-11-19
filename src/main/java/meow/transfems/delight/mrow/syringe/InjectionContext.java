package meow.transfems.delight.mrow.syringe;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;

public record InjectionContext(By by, Entity target, RegistryKey<Block> what, ServerWorld world, MinecraftServer server, Random random) {
	public record By(ByType type, PlayerEntity by) {
		public static class ByType {
			public static final ByType OTHER_PLAYER = new ByType("other_player");
			public static final ByType SELF = new ByType("self");

			private final String id;

			private ByType(String id) {
				this.id = id;
			};

			public String getId() {
				return this.id;
			}
		}
	}
}
