package org.code.meow.mrow.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.ItemModels;
import net.minecraft.client.data.Models;
import net.minecraft.client.render.block.model.BlockModels;
import net.minecraft.client.render.model.ModelBaker;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.code.meow.mrow.block.ModBlocks;
import org.code.meow.mrow.item.ModItems;
import org.code.meow.mrow.item.ModModels;
import org.code.meow.mrow.item.SyringeRenderer;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator gen) {
        gen.registerSimpleCubeAll(ModBlocks.DISSOLVING_TABLE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator gen) {
        DyeColor.VALUES.forEach(col -> ModModels.catEars(gen, ModItems.CAT_EARS.get(col), col.asString() + "_cat_ears"));

        gen.register(ModItems.MEOW, Models.GENERATED);
        gen.register(ModItems.CATNIP, Models.GENERATED);
        gen.register(ModItems.OIL, Models.GENERATED);
        gen.register(ModItems.DISSOLVED_ESTROGEN_BUCKET, Models.GENERATED);
        gen.register(ModItems.ESTROGEN_BUCKET, Models.GENERATED);

        Identifier base = gen.upload(ModItems.SYRINGE, Models.GENERATED);

        gen.output.accept(
            ModItems.SYRINGE,
            ItemModels.special(base, SyringeRenderer.Unbaked.INSTANCE)
        );
    }
}