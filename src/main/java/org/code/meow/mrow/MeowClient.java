package org.code.meow.mrow;

import net.fabricmc.api.ClientModInitializer;
import org.code.meow.mrow.item.ModCreativeTabs;

public class MeowClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModCreativeTabs.init();
    }
}
