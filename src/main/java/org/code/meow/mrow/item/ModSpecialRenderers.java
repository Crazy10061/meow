package org.code.meow.mrow.item;

import net.minecraft.client.render.item.model.special.SpecialModelTypes;
import org.code.meow.Meow;

public class ModSpecialRenderers {
	public static void init() {
		Meow.LOGGER.info("registering ModSpecialRenderers for mod " + Meow.MOD_ID);

		SpecialModelTypes.ID_MAPPER.put(Meow.id("syringe"), SyringeRenderer.Unbaked.MAP_CODEC);
	}
}
