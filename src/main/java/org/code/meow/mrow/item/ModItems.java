package org.code.meow.mrow.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.block.ColorCollection;
import org.code.meow.Meow;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

public class ModItems {
    public static ArrayList<Item> ALL_ITEMS = new ArrayList<>();

    public static final Item MEOW = registerItem("meow", Item::new, new Item.Properties().rarity(Rarity.EPIC));

    public static final ColorCollection<Item> CAT_EARS = registerColoredHumanoidArmorItem(
        "cat_ears",
        CatEars::new,
        ModArmorMaterials.CAT_EARS,
        ArmorType.HELMET,
        new Item.Properties()
            .stacksTo(1)
            .rarity(Rarity.RARE)
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
        T item = function.apply(properties.setId(key));

        ALL_ITEMS.add(item);

        return Registry.register(BuiltInRegistries.ITEM, Meow.id(name),
                item);
    }

    private static<T extends Item> ColorCollection<Item> registerColoredHumanoidArmorItem(String baseName, Function<Item.Properties, T> function, Map<DyeColor, ArmorMaterial> materials, ArmorType type, Item.Properties props) {
        ColorCollection<String> keys = ColorCollection
            .prefixWithColor(ColorCollection.create(baseName));

        return ColorCollection.registerItems(keys, (id, color) -> registerItem(id, function, props.humanoidArmor(materials.get(color), type).component(DataComponents.EQUIPPABLE, Equippable.builder(type.getSlot()).setEquipSound(materials.get(color).equipSound()).build())));
    }

    public static void init() {
        Meow.LOGGER.info("registering ModItems for " + Meow.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(output -> {
            output.accept(MEOW);
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            CAT_EARS.forEach(output::accept);
        });
    }

}
