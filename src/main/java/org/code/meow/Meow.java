package org.code.meow;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import org.code.meow.mrow.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Meow implements ModInitializer {
    public static final String MOD_ID = "meow";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final ImmutableList<ResourceKey<LootTable>> VILLAGE_HOUSE_SOURCES = ImmutableList.of(BuiltInLootTables.VILLAGE_DESERT_HOUSE, BuiltInLootTables.VILLAGE_PLAINS_HOUSE, BuiltInLootTables.VILLAGE_SNOWY_HOUSE, BuiltInLootTables.VILLAGE_TAIGA_HOUSE, BuiltInLootTables.VILLAGE_SAVANNA_HOUSE);

    @Override
    public void onInitialize() {
        ModItems.init();

        LootTableEvents.MODIFY.register(((key, tableBuilder, source, holder) -> {
            if (source.isBuiltin() && VILLAGE_HOUSE_SOURCES.contains(key)) {
                LootPool.Builder pool = LootPool.lootPool()
                    .addAll(ModItems.CAT_EARS.map(item -> {
                        return LootItem.lootTableItem(item)
                            .setWeight(1)
                            .setQuality(2);
                    }).asList());

                tableBuilder.withPool(pool);
            }
        }));
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
