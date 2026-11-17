package org.code.meow.mrow;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;
import org.code.meow.Meow;
import org.code.meow.mrow.fluid.ModFluidRendering;
import org.code.meow.mrow.gui.ModScreens;
import org.code.meow.mrow.item.ModItemGroups;
import org.code.meow.mrow.item.ModDataComponents;
import org.code.meow.mrow.item.ModItems;
import org.code.meow.mrow.item.ModSpecialRenderers;

public class MeowClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModItemGroups.init();
        ModScreens.init();
        ModFluidRendering.init();
        ModSpecialRenderers.init();

        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, Meow.id("estrogen_bar"), new EstrogenBar());

        ItemTooltipCallback.EVENT.register((stack, context, type, tooltip) -> {
            if (stack.is(ModItems.SYRINGE))
                tooltip.add(Text.translatable("item.meow.syringe.inside", Text.translatable(Util.createTranslationKey("block", stack.get(ModDataComponents.INSIDE).getValue()))).formatted(Formatting.GOLD, Formatting.BOLD));
        });
    }
}
