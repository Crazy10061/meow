package org.code.meow.mrow.item;

import com.google.common.collect.Maps;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;
import org.code.meow.mrow.datagen.ModItemTagProvider;

import java.util.Map;

public class ModArmorMaterials {
	public static final ArmorMaterial CAT_EARS = new ArmorMaterial(
		50, Maps.newEnumMap(Map.of(ArmorType.HELMET, 6)),
		11,
		SoundEvents.CAT_PURR_BABY,
		1.0f,
		0.01f,
		ModItemTagProvider.REPAIRS_CAT_EARS,
		EquipmentAssets.DIAMOND
	);
}
