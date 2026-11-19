package meow.transfems.delight.mrow.datagen;

import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.DyeColor;
import meow.transfems.delight.Meow;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public interface ModEquipmentAssets {
	Map<DyeColor, RegistryKey<EquipmentAsset>> CAT_EARS = makeEnumMap(DyeColor.class, color -> RegistryKey.of(
		EquipmentAssetKeys.REGISTRY_KEY,
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
