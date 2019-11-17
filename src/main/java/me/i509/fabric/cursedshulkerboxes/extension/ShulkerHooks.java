package me.i509.fabric.cursedshulkerboxes.extension;

import me.i509.fabric.cursedshulkerboxes.CursedShulkerBox;
import net.fabricmc.loader.api.FabricLoader;

public class ShulkerHooks {
    private static final FabricLoader LOADER = FabricLoader.getInstance();
    private static boolean isCottonEnabled;

    private ShulkerHooks() {}
    public static void init() {}

    static {
        if(LOADER.isModLoaded("cotton-resources")) {
            if (CursedShulkerBox.getInstance().getConfig().shouldUseCottonResources()) {
                isCottonEnabled = true;
            } else {
                isCottonEnabled = false;
            }
        }
    }

    public static boolean isCottonEnabled() {
        return isCottonEnabled;
    }
}
