package meow.transfems.delight;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import meow.transfems.delight.config.MeowConfig;
import meow.transfems.delight.data.ModDataAttachments;
import meow.transfems.delight.effect.ModEffects;
import meow.transfems.delight.loot.ModLootTableModifications;
import meow.transfems.delight.mrow.block.ModBlockEntities;
import meow.transfems.delight.mrow.block.ModBlocks;
import meow.transfems.delight.mrow.damaage.ModDamageTypes;
import meow.transfems.delight.mrow.fluid.ModFluids;
import meow.transfems.delight.mrow.gamerule.ModGameRules;
import meow.transfems.delight.mrow.gui.ModScreenHandlers;
import meow.transfems.delight.mrow.item.ModDataComponents;
import meow.transfems.delight.mrow.item.ModItems;
import meow.transfems.delight.mrow.networking.ModNetworking;
import meow.transfems.delight.mrow.recipe.ModRecipeTypes;
import meow.transfems.delight.mrow.registry.ModRegistries;
import meow.transfems.delight.mrow.syringe.ModInjectionHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.nio.file.Paths;

public class Meow implements ModInitializer {
    public static final String MOD_ID = "transfems_delight";
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
    public static Identifier oldId(String path) { return Identifier.of("meow", path); }
}
