package me.i509.fabric.bulkyshulkies.block.material.platinum;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.ItemEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Box;

import me.i509.fabric.bulkyshulkies.BulkyShulkies;
import me.i509.fabric.bulkyshulkies.api.block.Abstract1X1ShulkerBoxBE;
import me.i509.fabric.bulkyshulkies.block.ShulkerBoxConstants;
import me.i509.fabric.bulkyshulkies.api.event.MagnetismCollectionCallback;
import me.i509.fabric.bulkyshulkies.registry.ShulkerBlockEntities;

public class PlatinumShulkerBoxBE extends Abstract1X1ShulkerBoxBE {
	private int lastMagnetTick = 0;

	public PlatinumShulkerBoxBE(@Nullable DyeColor color) {
		super(ShulkerBlockEntities.PLATINUM_SHULKER_BOX, ShulkerBoxConstants.PLATINUM_SLOT_COUNT, color);
	}

	public PlatinumShulkerBoxBE() {
		this(null);
	}

	public void tick() {
		super.tick();

		if (BulkyShulkies.getInstance().getConfig().shouldPlatinumUseMagnetism()) {
			tickMagnets();
		}
	}

	private void tickMagnets() {
		if (this.hasWorld() && !this.getWorld().isClient) {
			if (lastMagnetTick >= BulkyShulkies.getInstance().getConfig().getMagnetismTickDelay()) {
				this.lastMagnetTick = 0;
				int range = BulkyShulkies.getInstance().getConfig().getPlatinumMagnetMaxRange();
				Box box = new Box(this.getPos()).stretch(range, range, range);
				List<ItemEntity> entities = this.getWorld().getNonSpectatingEntities(ItemEntity.class, box);
				MagnetismCollectionCallback.EVENT.invoker().onMagnetismTick(entities, this.getWorld(), this.getPos(), this);
				// TODO: attempt to insert the items into the box
			}

			this.lastMagnetTick++;
		}
	}

	@Override
	protected Text getContainerName() {
		return new TranslatableText("container.platinumShulkerBox");
	}
}
