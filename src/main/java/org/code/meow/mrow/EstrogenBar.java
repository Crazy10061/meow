package org.code.meow.mrow;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Colors;
import net.minecraft.util.math.ColorHelper;
import org.code.meow.data.EstrogenInfo;
import org.code.meow.data.ModDataAttachments;

public class EstrogenBar implements HudElement {
	@Override
	public void extractRenderState(DrawContext ctx, RenderTickCounter tickCounter) {
		MinecraftClient client = MinecraftClient.getInstance();
		EstrogenInfo info = client.player.getAttachedOrCreate(ModDataAttachments.ESTROGEN_INFO);

		if (!info.startedHRT()) {
			return;
		}

		int startX = 20;
		int startY = 20;
		int endX = 20;
		int endY = 120;

		long timeSince = client.world.getDimensionTime() - info.lastInjection();
		long decay = 28800L/* * 7*/;
		float frac = 1.0f - (float)Math.clamp(timeSince, 0, decay) / (float)decay;
		int filled = Math.round(120 * frac);

		ctx.enableScissor(startX, startY + (endY - filled), startX + startX, startY + endY);

		ctx.fillGradient(startX, startY, startX + endX, startY + endY, Colors.PURPLE, ColorHelper.add(Colors.PURPLE, ColorHelper.getArgb(255, 128, 0, 128)));

		ctx.disableScissor();

		ctx.drawStrokedRectangle(20, 20, 20, 120, ColorHelper.getArgb(255, 128, 128, 128));
	}
}
