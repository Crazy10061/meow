package meow.transfems.delight.mixin;

import meow.transfems.delight.Meow;
import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(SimpleRegistry.class)
public abstract class SimpleRegistryMixin<T> implements MutableRegistry<T> {
	@Shadow
	private boolean frozen;

	@Shadow
	@Final
	private Map<RegistryKey<T>, RegistryEntry.Reference<T>> keyToEntry;

	@Inject(method = "freeze", at = @At("HEAD"))
	private void freezeIgIdk(CallbackInfoReturnable<Registry<T>> cir) {
		if (this.frozen)
			return;

		keyToEntry.forEach((key, _entry) -> {
			addAlias(Meow.oldId(key.getValue().getPath()), key.getValue());
		});
	}
}
