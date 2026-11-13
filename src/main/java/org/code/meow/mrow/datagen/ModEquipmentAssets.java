package org.code.meow.mrow.datagen;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import org.code.meow.Meow;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public interface ModEquipmentAssets {
	Map<DyeColor, ResourceKey<EquipmentAsset>> CAT_EARS = makeEnumMap(DyeColor.class, color -> ResourceKey.create(
		EquipmentAssets.ROOT_ID,
		Meow.id(color + "_cat_ears")
	));

	public static <K extends Enum<K>, V> Map<K, V> makeEnumMap(final Class<K> keyType, final Function<K, V> function) {
		EnumMap<K, V> map = new EnumMap<>(keyType);

		for (K key : (K[])keyType.getEnumConstants()) {
			map.put(key, function.apply(key));
		}

		return map;
	}
}
