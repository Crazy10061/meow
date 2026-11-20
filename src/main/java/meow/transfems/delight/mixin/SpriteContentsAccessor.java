package meow.transfems.delight.mixin;

import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.SpriteContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SpriteContents.class)
public interface SpriteContentsAccessor {
	@Accessor("image")
	public NativeImage transfems_delight$getImage();

	@Accessor("image")
	public void transfems_delight$setImage(NativeImage originalImage);
}
