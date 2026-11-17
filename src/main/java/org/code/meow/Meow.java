package org.code.meow;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.code.meow.config.MeowConfig;
import org.code.meow.data.ModDataAttachments;
import org.code.meow.effect.ModEffects;
import org.code.meow.loot.ModLootTableModifications;
import org.code.meow.mrow.block.ModBlockEntities;
import org.code.meow.mrow.block.ModBlocks;
import org.code.meow.mrow.damaage.ModDamageTypes;
import org.code.meow.mrow.fluid.ModFluids;
import org.code.meow.mrow.gamerule.ModGameRules;
import org.code.meow.mrow.gui.ModScreenHandlers;
import org.code.meow.mrow.item.ModDataComponents;
import org.code.meow.mrow.item.ModItems;
import org.code.meow.mrow.networking.ModNetworking;
import org.code.meow.mrow.recipe.ModRecipeTypes;
import org.code.meow.mrow.registry.ModRegistries;
import org.code.meow.mrow.syringe.ModInjectionHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.nio.file.Paths;

public class Meow implements ModInitializer {
    public static final String MOD_ID = "meow";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final MeowConfig CONF = MeowConfig.createToml(Paths.get("conf"), "", Meow.MOD_ID, MeowConfig.class);

    @Override
    public void onInitialize() {
        ModNetworking.init();
        ModGameRules.init();
        ModItems.init();
        ModRecipeTypes.init();
        ModFluids.init();
        ModBlocks.init();
        ModScreenHandlers.init();
        ModRegistries.init();
        ModDataComponents.init();
        ModBlockEntities.init();
        ModInjectionHandlers.init();
        ModDamageTypes.init();
        ModEffects.init();
        ModDataAttachments.init();
        ModEffects.init();
        ModLootTableModifications.init();

        if (FabricLoader.getInstance().isDevelopmentEnvironment())
            MixinEnvironment.getCurrentEnvironment().audit();

        Meow.LOGGER.error("Lexi, please datagen your item definitions in the ModModelProvider we have it for a reason, its not that hard 😭🥀");
    }

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
