package meow.transfems.delight.mrow.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.ItemModels;
import net.minecraft.client.data.Models;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import meow.transfems.delight.mrow.block.ModBlocks;
import meow.transfems.delight.mrow.item.ModItems;
import meow.transfems.delight.mrow.item.ModModels;
import meow.transfems.delight.mrow.item.SyringeRenderer;

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