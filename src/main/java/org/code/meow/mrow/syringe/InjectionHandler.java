package org.code.meow.mrow.syringe;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;

public interface InjectionHandler {
	RegistryKey<Block> getTrigger();
	void inject(InjectionContext ctx);
}
