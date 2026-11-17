package org.code.meow.mrow.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.code.meow.Meow;

public class ModBlockEntities {
	public static final BlockEntityType<DissolvingTableBlockEntity> DISSOLVING_TABLE = register("dissolving_table", DissolvingTableBlockEntity::new, ModBlocks.DISSOLVING_TABLE);

	private static<T extends BlockEntity> BlockEntityType<T> register(
		String name,
		FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
		Block... blocks
	) {
		Identifier id = Meow.id(name);

		return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
	}

	public static void init() {
		Meow.LOGGER.info("registering ModBlockEntities for mod " + Meow.MOD_ID);
	}
}
