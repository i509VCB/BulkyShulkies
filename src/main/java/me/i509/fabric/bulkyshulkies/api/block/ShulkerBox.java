package me.i509.fabric.bulkyshulkies.api.block;

import me.i509.fabric.bulkyshulkies.api.ShulkerBoxType;
import me.i509.fabric.bulkyshulkies.api.data.PositionedDataHolder;

public interface ShulkerBox extends PositionedDataHolder {
	ShulkerBoxType getType();
}
