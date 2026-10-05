package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.gameplay.RespawnImmunity;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {
    @WrapOperation(
        method = "hurtServer",
        at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/server/level/ServerPlayer;isInvulnerableTo(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;)Z"
        )
    )
    private boolean hurtServer(ServerPlayer instance, ServerLevel level, DamageSource damageSource, Operation<Boolean> original) {
        return ConfigHelper.get(Config.respawn_immunity_fix) && RespawnImmunity.map.containsKey(instance) || original.call(instance, level, damageSource);
    }
}
