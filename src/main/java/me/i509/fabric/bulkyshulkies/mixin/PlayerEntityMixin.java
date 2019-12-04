package me.i509.fabric.bulkyshulkies.mixin;

import me.i509.fabric.bulkyshulkies.api.player.EnderSlabAccess;
import me.i509.fabric.bulkyshulkies.inventory.EnderSlabInventory;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements EnderSlabAccess {
	protected EnderSlabInventory enderSlabInventory = new EnderSlabInventory();

	@Override
	public EnderSlabInventory getEnderSlabInventory() {
		return enderSlabInventory;
	}

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerAbilities;deserialize(Lnet/minecraft/nbt/CompoundTag;)V"), method = "readCustomDataFromTag")
	private void onReadCustomData(CompoundTag tag, CallbackInfo ci) {
		if (tag.contains("EnderSlabItems", 9)) {
			this.enderSlabInventory.readTags(tag.getList("EnderSlabItems", NbtType.COMPOUND));
		}
	}

	@Inject(at = @At("HEAD"), method = "writeCustomDataToTag")
	private void onWriteCustomData(CompoundTag tag, CallbackInfo ci) {
		tag.put("EnderSlabItems", this.enderSlabInventory.getTags());
	}
}
