package org.code.meow.mixin;

import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.SpriteContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SpriteContents.class)
public interface SpriteContentsAccessor {
	@Accessor("image")
	public NativeImage meow$getImage();

	@Accessor("image")
	public void meow$setImage(NativeImage originalImage);
}
