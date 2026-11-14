package org.code.meow.mrow.item;

import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import org.code.meow.Meow;

import java.util.Optional;

public class ModModels {
	public static final TextureSlot ZERO = TextureSlot.create("0");

	public static final ModelTemplate CAT_EARS = new ModelTemplate(
		Optional.of(Meow.id("item/cat_ears_base")),
		Optional.empty(),
		ZERO,
		TextureSlot.PARTICLE
	);

	public static void catEars(ItemModelGenerators generators, Item item, String texture) {
		Material mat = new Material(Meow.id("item/" + texture));
		TextureMapping mapping = new TextureMapping()
			.put(ZERO, mat)
			.put(TextureSlot.PARTICLE, mat);

		Identifier model = CAT_EARS.create(item, mapping, generators.modelOutput);
		generators.itemModelOutput.accept(item, ItemModelUtils.plainModel(model));
	}
}
