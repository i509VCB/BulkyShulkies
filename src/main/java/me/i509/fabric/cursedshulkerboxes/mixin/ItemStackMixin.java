package me.i509.fabric.cursedshulkerboxes.mixin;

import me.i509.fabric.cursedshulkerboxes.api.item.AbstractShulkerHelmet;
import me.i509.fabric.cursedshulkerboxes.api.item.DurabilityBasedProtection;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract Item getItem();

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getLevel(Lnet/minecraft/enchantment/Enchantment;Lnet/minecraft/item/ItemStack;)I"), method = "damage(ILjava/util/Random;Lnet/minecraft/server/network/ServerPlayerEntity;)Z", cancellable = true)
    public void onDamaged(int damageTaken, Random random, ServerPlayerEntity serverPlayerEntity, CallbackInfoReturnable<Boolean> cir) {
        if(this.getItem() instanceof DurabilityBasedProtection) {
            if(!DurabilityBasedProtection.canApplyNoDurability((ItemStack) (Object) this)) { // This check also says if the armor is at minimum durability.
                cir.setReturnValue(false);
            }
        }
    }
}
