package org.code.meow.mrow.item;

import com.google.common.collect.Maps;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.DyeColor;
import org.code.meow.mrow.datagen.ModEquipmentAssets;
import org.code.meow.mrow.datagen.ModItemTagProvider;

import java.util.Map;

public class ModArmorMaterials {
	public static final Map<DyeColor, ArmorMaterial> CAT_EARS = ModEquipmentAssets.makeEnumMap(DyeColor.class, color -> new ArmorMaterial(
		50, Maps.newEnumMap(Map.of(EquipmentType.HELMET, 6)),
		11,
		SoundEvents.ENTITY_BABY_CAT_PURR,
		1.0f,
		0.01f,
		ModItemTagProvider.REPAIRS_CAT_EARS,
		ModEquipmentAssets.CAT_EARS.get(color)
	));
}
