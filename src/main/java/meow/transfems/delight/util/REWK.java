package meow.transfems.delight.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.ColorMap;
import net.minecraft.util.DyeColor;

import java.util.function.BiFunction;

/// RegistryEntryWithKey
public record REWK<T>(T entry, RegistryKey<T> key) {
	public static<Id, T extends Item> ColorMap<REWK<T>> forREWKItems(final ColorMap<Id> ids, final BiFunction<Id, DyeColor, REWK<T>> itemFactory) {
		return ColorMap.mapZipped(ColorMap.COLORS, ids, (color, id) -> itemFactory.apply(id, color));
	}
}
