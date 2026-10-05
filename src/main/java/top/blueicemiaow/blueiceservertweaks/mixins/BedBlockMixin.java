package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(BedBlock.class)
public abstract class BedBlockMixin {
    @WrapOperation(
            method = "destroyOnUse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;Lnet/minecraft/world/phys/Vec3;FZLnet/minecraft/world/level/Level$ExplosionInteraction;)V"
            )
    )
    private void destroyOnUse(Level instance, Entity source, DamageSource damageSource, ExplosionDamageCalculator explosionDamageCalculator, Vec3 pos, float r, boolean fire, Level.ExplosionInteraction blockInteraction, Operation<Void> original) {
        original.call(instance, source, damageSource, explosionDamageCalculator, pos, r, !ConfigHelper.get(Config.prevent_respawn_point_produce_fire), blockInteraction);
    }
}
