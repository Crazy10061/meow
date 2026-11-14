package org.code.meow.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.attribute.AmbientAdditionsSettings;
import net.minecraft.world.attribute.AmbientMoodSettings;
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
		return new AmbientSounds(Optional.of(SoundEvents.CAT_PURR_BABY), Optional.of(AmbientMoodSettings.LEGACY_CAVE_SETTINGS), List.of(new AmbientAdditionsSettings(SoundEvents.CAT_PURR_BABY, 100)));
	}
}
