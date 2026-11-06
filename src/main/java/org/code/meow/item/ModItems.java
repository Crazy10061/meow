package org.code.meow.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import org.code.meow.Meow;

import java.util.function.Function;

public class ModItems {

    public static final Item MEOW = registerItem("meow", Item::new, new Item.Properties().rarity(Rarity.EPIC));


    private static Item registerItem(String name, Function<Item.Properties, Item> function, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Meow.MOD_ID, name));
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(Meow.MOD_ID, name),
                function.apply(properties.setId(key)));
    }

    public static void registerModItems() {
        Meow.LOGGER.info("Registering ModItems");

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(output -> {
            output.accept(MEOW);
        });
    }

}
