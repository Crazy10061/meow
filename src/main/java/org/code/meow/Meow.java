package org.code.meow;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.code.meow.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Meow implements ModInitializer {

    public static final String MOD_ID = "meow";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
