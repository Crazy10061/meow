package meow.transfems.delight.mrow.item;

import net.minecraft.client.render.item.model.special.SpecialModelTypes;
import meow.transfems.delight.Meow;

public class ModSpecialRenderers {
	public static void init() {
		Meow.LOGGER.info("registering ModSpecialRenderers for mod " + Meow.MOD_ID);

		SpecialModelTypes.ID_MAPPER.put(Meow.id("syringe"), SyringeRenderer.Unbaked.MAP_CODEC);
	}
}
