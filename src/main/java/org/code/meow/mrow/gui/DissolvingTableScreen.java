package org.code.meow.mrow.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.RenderPipelines;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.code.meow.Meow;

public class DissolvingTableScreen extends HandledScreen<DissolvingTableScreenHandler> {
	public static final Identifier TEX = Meow.id("textures/gui/dissolving_table.png");

	public DissolvingTableScreen(DissolvingTableScreenHandler handler, PlayerInventory inventory, Text displayName) {
		super(handler, inventory, displayName);

		this.titleX = (this.backgroundWidth - this.textRenderer.getWidth(this.title)) / 2;
	}


	@Override
	public void renderBackground(DrawContext ctx, int mouseX, int mouseY, float a) {
		super.renderBackground(ctx, mouseX, mouseY, a);
		ctx.drawTexture(RenderPipelines.GUI_TEXTURED, TEX, this.x, this.y, 0.0f, 0.0f, this.backgroundWidth, this.backgroundHeight, field_52802, field_52803);
	}
}
