package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.monster.Enderman;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(Enderman.class)
public abstract class EndermanMixin {
    @WrapOperation(
            method = "teleport(DDD)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/Enderman;randomTeleport(DDDZLnet/minecraft/tags/TagKey;)Z"
            )
    )
    private boolean teleport(Enderman instance, double x, double y, double z, boolean b, TagKey tagKey, Operation<Boolean> original) {
        return original.call(instance, x, y, z, b, ConfigHelper.get(Config.enderman_teleport_fix) ? BlueIceServerTweaks.EMPTY_BLOCK : tagKey);
    }

    @ModifyExpressionValue(
            method = "hurtServer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/monster/Enderman;isPassenger()Z"
            )
    )
    private boolean hurtServer(boolean original) {
        return !ConfigHelper.get(Config.enderman_passenger_fix) && original;
    }
}
