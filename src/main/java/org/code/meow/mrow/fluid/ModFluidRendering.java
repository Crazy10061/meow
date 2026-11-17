package org.code.meow.mrow.fluid;

import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.minecraft.client.color.block.BlockColorProviders;
import net.minecraft.client.render.block.model.FluidModel;
import net.minecraft.client.render.model.ModelTexture;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

public class ModFluidRendering {
	public static void init() {
		FluidRenderingRegistry.register(
			ModFluids.ESTROGEN_SOURCE,
			ModFluids.ESTROGEN_FLOWING,
			new FluidModel.Unbaked(
				new ModelTexture(Identifier.ofVanilla("block/water_still")),
				new ModelTexture(Identifier.ofVanilla("block/water_flow")),
				new ModelTexture(Identifier.ofVanilla("block/water_overlay")),
				BlockColorProviders.constant(ColorHelper.getArgb(128, 0xFE, 0xFE, 0xFE))
			)
		);

		FluidRenderingRegistry.register(
			ModFluids.DISSOLVED_ESTROGEN_SOURCE,
			ModFluids.DISSOLVED_ESTROGEN_FLOWING,
			new FluidModel.Unbaked(
				new ModelTexture(Identifier.ofVanilla("block/water_still")),
				new ModelTexture(Identifier.ofVanilla("block/water_flow")),
				new ModelTexture(Identifier.ofVanilla("block/water_overlay")),
				BlockColorProviders.constant(ColorHelper.getArgb(128, 0xFF, 0xFF, 0xFD))
			)
		);
	}
}
