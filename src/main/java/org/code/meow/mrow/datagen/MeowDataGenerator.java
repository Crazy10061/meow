package org.code.meow.mrow.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.code.meow.mrow.item.ModSpecialRenderers;

public class MeowDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        var pack = fabricDataGenerator.createPack();

        ModSpecialRenderers.init();

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModEnUsProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModFluidTagProvider::new);
        pack.addProvider(ModDamageTypeProvider::new);
    }
}
