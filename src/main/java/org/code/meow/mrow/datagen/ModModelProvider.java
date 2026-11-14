package org.code.meow.mrow.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.item.DyeColor;
import org.code.meow.mrow.item.ModItems;
import org.code.meow.mrow.item.ModModels;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.MEOW, ModelTemplates.FLAT_ITEM);

        DyeColor.VALUES.forEach(col -> ModModels.catEars(itemModelGenerators, ModItems.CAT_EARS.pick(col), col.getName() + "_cat_ears"));
    }
}