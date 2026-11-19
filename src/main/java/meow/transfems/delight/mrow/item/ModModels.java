package meow.transfems.delight.mrow.item;

import net.minecraft.client.data.*;
import net.minecraft.client.render.model.ModelTexture;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import meow.transfems.delight.Meow;

import java.util.Optional;

public class ModModels {
	public static final TextureKey ZERO = TextureKey.of("0");

	public static final Model CAT_EARS = new Model(
		Optional.of(Meow.id("item/cat_ears_base")),
		Optional.empty(),
		ZERO,
		TextureKey.PARTICLE
	);

	public static void catEars(ItemModelGenerator gen, Item item, String texture) {
		ModelTexture mat = new ModelTexture(Meow.id("item/" + texture));
		TextureMap mapping = new TextureMap()
			.put(ZERO, mat)
			.put(TextureKey.PARTICLE, mat);

		Identifier model = CAT_EARS.upload(item, mapping, gen.modelCollector);

		gen.output.accept(item, ItemModels.basic(model));
	}
}
