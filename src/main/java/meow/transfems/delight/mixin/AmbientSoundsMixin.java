package meow.transfems.delight.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.attribute.AmbientSounds;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;
import java.util.Optional;

@Mixin(AmbientSounds.class)
@Debug(export = true)
public class AmbientSoundsMixin {
	@ModifyExpressionValue(method = "<clinit>", at = @At(value = "NEW", target = "(Ljava/util/Optional;Ljava/util/Optional;Ljava/util/List;)Lnet/minecraft/world/attribute/AmbientSounds;", ordinal = 1))
	private static AmbientSounds caveSettings(AmbientSounds original) {
		return new AmbientSounds(Optional.of(SoundEvents.ENTITY_BABY_CAT_PURR), Optional.of(BiomeMoodSound.CAVE), List.of(new BiomeAdditionsSound(SoundEvents.ENTITY_BABY_CAT_PURR, 100)));
	}
}
