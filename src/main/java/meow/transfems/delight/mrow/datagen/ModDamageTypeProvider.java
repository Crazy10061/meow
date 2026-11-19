package meow.transfems.delight.mrow.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;
import net.minecraft.data.DataOutput;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryView;
import net.minecraft.util.Identifier;
import meow.transfems.delight.mrow.damaage.ModDamageTypes;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ModDamageTypeProvider extends FabricCodecDataProvider<DamageType> {
	public ModDamageTypeProvider(FabricPackOutput packOutput, CompletableFuture<RegistryView.WrapperLookup> registriesFuture) {
		super(packOutput, registriesFuture, DataOutput.OutputType.DATA_PACK, "damage_type", DamageType.CODEC);
	}

	@Override
	protected void configure(BiConsumer<Identifier, DamageType> provider, @NotNull RegistryView.WrapperLookup registryLookup) {
		ModDamageTypes.INJECTED_LAVA.datagen(provider);
		ModDamageTypes.ESTROGEN_OD.datagen(provider);
		ModDamageTypes.UNDISSOLVED_ESTROGEN.datagen(provider);
	}

	@Override
	public @NonNull String getName() {
		return this.getClass().getSimpleName();
	}
}
