package me.i509.fabric.bulkyshulkies.api.component;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;

import net.minecraft.inventory.Inventory;

public interface EnderSlabInventory extends AutoSyncedComponent, PlayerComponent<EnderSlabInventory>, ComponentV3, Inventory {
}
