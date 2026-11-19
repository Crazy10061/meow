package meow.transfems.delight.loot;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.RegistryKey;
import meow.transfems.delight.mrow.item.ModItems;

public class ModLootTableModifications {
	public static final ImmutableList<RegistryKey<LootTable>> VILLAGE_HOUSE_SOURCES = ImmutableList.of(LootTables.VILLAGE_DESERT_HOUSE_CHEST, LootTables.VILLAGE_PLAINS_CHEST, LootTables.VILLAGE_SNOWY_HOUSE_CHEST, LootTables.VILLAGE_TAIGA_HOUSE_CHEST, LootTables.VILLAGE_SAVANNA_HOUSE_CHEST);

	public static void init() {
		LootTableEvents.MODIFY.register(((key, tableBuilder, source, holder) -> {
			if (source.isBuiltin() && VILLAGE_HOUSE_SOURCES.contains(key)) {
				LootPool.Builder pool = LootPool.builder()
					.add(ModItems.CAT_EARS.map(item -> {
						return ItemEntry.builder(item)
							.weight(1)
							.quality(2)
							.build();
					}).values());

				tableBuilder.pool(pool);
			}
		}));
	}
}
