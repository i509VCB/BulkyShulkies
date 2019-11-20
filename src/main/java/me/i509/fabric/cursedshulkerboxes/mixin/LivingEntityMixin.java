package me.i509.fabric.cursedshulkerboxes.mixin;

import me.i509.fabric.cursedshulkerboxes.api.item.AbstractShulkerHelmet;
import me.i509.fabric.cursedshulkerboxes.api.item.DurabilityBasedProtection;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract Iterable<ItemStack> getArmorItems();

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/DamageUtil;getDamageLeft(FFF)F"), method = "applyArmorToDamage")
    public float onApplyArmorToDamage(float damageTaken, float armorPoints, float v) {
        float realArmorPoints = armorPoints; // Originally this is the input armor points.
        Iterator<ItemStack> armorItems = this.getArmorItems().iterator();
        do {
            ItemStack stack = armorItems.next();
            if(stack.getItem() instanceof AbstractShulkerHelmet) {
                if(!DurabilityBasedProtection.canApplyNoDurability(stack)) {
                    realArmorPoints-=((ArmorItem) stack.getItem()).getProtection(); // Here we remove the armor points logically supplied by the server. TODO Maybe change armor point icon in that context.
                }
            }
        } while (armorItems.hasNext());

        return DamageUtil.getDamageLeft(damageTaken, realArmorPoints, v);
    }

    @Inject(at = @At("HEAD"), method = "initDataTracker")
    protected void onInitDataTracker(CallbackInfo ci) {
        //getDataTracker().startTracking(AbstractShulkerHelmet.SHULKER_HELMET_STAGE, ShulkerBoxBlockEntity.AnimationStage.CLOSED); // TODO figure out datatracking for shulker helmet.
    }
}
