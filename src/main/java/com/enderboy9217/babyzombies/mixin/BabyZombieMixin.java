package com.enderboy9217.babyzombies.mixin;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombieEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntity.class)
public class BabyZombieMixin {

    @Final
    @Unique
    private static EntityAttributeModifier BABY_REDUCTION = new EntityAttributeModifier("Baby Reduction", -0.5, EntityAttributeModifier.Operation.MULTIPLY_BASE);

    @Inject(method="setBaby", at=@At("TAIL"))
    public void setBabyStats(boolean baby, CallbackInfo ci) {
        ZombieEntity zombie = (ZombieEntity) (Object) this;

        if (baby && zombie.getWorld() != null && !zombie.getWorld().isClient) {
            zombie.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).addPersistentModifier(BABY_REDUCTION);
            zombie.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).addPersistentModifier(BABY_REDUCTION);
            zombie.setHealth(zombie.getMaxHealth());
        }
    }
}