package org.code.meow.mrow.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.block.BlockItemKeys;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.ColorMap;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Rarity;
import org.code.meow.Meow;
import org.code.meow.mrow.fluid.ModFluids;
import org.code.meow.mrow.food.ModFoods;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;

public class ModItems {
    public static ArrayList<Item> ALL_ITEMS = new ArrayList<>();

    public static final Item MEOW = registerItem("meow", Item::new, new Item.Settings().rarity(Rarity.EPIC));
    public static final Item CATNIP = registerItem("catnip", Item::new, new Item.Settings().food(ModFoods.CATNIP));
    public static final Item OIL = registerItem("oil", Item::new, new Item.Settings().rarity(Rarity.COMMON));
    public static final Item SYRINGE = registerItem("syringe", SyringeItem::new, new Item.Settings().rarity(Rarity.COMMON)
        .maxCount(1)
        .maxDamage(30)
        .withComponent(ModDataComponents.INSIDE, BlockItemKeys.AIR.block()));

    public static final ColorMap<Item> CAT_EARS = registerColoredHumanoidArmorItem(
        "cat_ears",
        CatEars::new,
        ModArmorMaterials.CAT_EARS,
        EquipmentType.HELMET,
        new Item.Settings()
            .maxCount(1)
            .rarity(Rarity.RARE)
            .attributeModifiers(AttributeModifiersComponent
                .builder()
                .add(EntityAttributes.MOVEMENT_SPEED, new EntityAttributeModifier(
                    Meow.id("armor.cat_ears"),
                    .6,
                    EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ), AttributeModifierSlot.HEAD)
                .build()
            )
    );

    public static final Item ESTROGEN_BUCKET = registerItem("estrogen_bucket", settings -> new BucketItem(ModFluids.ESTROGEN_SOURCE, settings), new Item.Settings().useRemainder(Items.BUCKET).maxCount(1));
    public static final Item DISSOLVED_ESTROGEN_BUCKET = registerItem("dissolved_estrogen_bucket", settings -> new BucketItem(ModFluids.DISSOLVED_ESTROGEN_SOURCE, settings), new Item.Settings().useRemainder(Items.BUCKET).maxCount(1));

    private static<T extends Item> Item registerItem(String name, Function<Item.Settings, T> function, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Meow.id(name));
        T item = function.apply(settings.registryKey(key));

        ALL_ITEMS.add(item);

        return Registry.register(Registries.ITEM, Meow.id(name), item);
    }

    private static<T extends Item> ColorMap<Item> registerColoredHumanoidArmorItem(String baseName, Function<Item.Settings, T> function, Map<DyeColor, ArmorMaterial> materials, EquipmentType type, Item.Settings settings) {
        ColorMap<String> keys = ColorMap
            .addPrefixes(ColorMap.constant(baseName));

        return ColorMap.forItems(keys, (id, color) -> registerItem(id, function, settings.armor(materials.get(color), type).withComponent(DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(type.getEquipmentSlot()).damageOnHurt(false).equipSound(materials.get(color).equipSound()).build())));
    }

    public static void init() {
        Meow.LOGGER.info("registering ModItems for " + Meow.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(ItemGroups.NATURAL).register(output -> {
            output.add(MEOW);
        });

        CreativeModeTabEvents.modifyOutputEvent(ItemGroups.COMBAT).register(output -> {
            CAT_EARS.forEach(output::add);
        });

        CreativeModeTabEvents.modifyOutputEvent(ItemGroups.FOOD_AND_DRINK).register(output -> {
            output.add(CATNIP);
        });
    }

}
