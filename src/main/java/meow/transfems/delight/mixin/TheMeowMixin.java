package meow.transfems.delight.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class TheMeowMixin {
    @Inject(at = @At("HEAD"), method = "loadWorld")
    private void init(CallbackInfo info) {
        System.out.println("meoww mrrpmeoww nyaaaaa mrraaoww mrrp miaw ,reowww mrroww mrauw mrrrrp :3 uwu meow meow meowww mrow purrrr mrrow Mrrow mrroow mrroow purrrr meoww nyaaaa mrrp purrrr nyaaa nyaaaa mow mrrp nyaaaa nyaaa meoww miaw mrrp purrrr meow nyaaa meow meowwwww meowwww meoww mrrow meoww meoww meoww :3 purrrrrr purrrrrr");
    }
}