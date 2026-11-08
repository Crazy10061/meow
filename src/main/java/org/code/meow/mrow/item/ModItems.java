package org.code.meow.mrow.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.ColorCollection;
import org.code.meow.Meow;

import java.util.function.Function;

public class ModItems {
    public static final Item MEOW = registerItem("meow", Item::new, new Item.Properties().rarity(Rarity.EPIC));

    public static final ColorCollection<Item> CAT_EARS = registerColoredItem(
        "cat_ears",
        CatEars::new,
        new Item.Properties()
            .stacksTo(1)
            .rarity(Rarity.RARE)
            .humanoidArmor(ModArmorMaterials.CAT_EARS, ArmorType.HELMET)
            .attributes(ItemAttributeModifiers
                .builder()
                .add(Attributes.MOVEMENT_SPEED, new AttributeModifier(
                    Meow.id("armor.cat_ears"),
                    .6,
                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ), EquipmentSlotGroup.HEAD)
                .build()
            )
    );

    private static<T extends Item> Item registerItem(String name, Function<Item.Properties, T> function, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Meow.id(name));
        return Registry.register(BuiltInRegistries.ITEM, Meow.id(name),
                function.apply(properties.setId(key)));
    }

    private static<T extends Item> ColorCollection<Item> registerColoredItem(String baseName, Function<Item.Properties, T> function, Item.Properties props) {
        ColorCollection<String> keys = ColorCollection
            .prefixWithColor(ColorCollection.create(baseName));

        return ColorCollection.registerItems(keys, (id, color) -> registerItem(id, function, props));
    }

    public static void registerModItems() {
        Meow.LOGGER.info("registering ModItems for " + Meow.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(output -> {
            output.accept(MEOW);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            CAT_EARS.forEach(output::accept);
        });
    }

}
