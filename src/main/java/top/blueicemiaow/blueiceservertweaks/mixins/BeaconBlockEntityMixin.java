package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityMixin {
    @ModifyConstant(method = "applyEffects", constant = @Constant(intValue = 10, ordinal = 0))
    private static int applyEffects0(int constant) {
        return ConfigHelper.get(Config.larger_beacon_range) ? 32 : constant;
    }

    @ModifyConstant(method = "applyEffects", constant = @Constant(intValue = 10, ordinal = 1))
    private static int applyEffects1(int constant) {
        return ConfigHelper.get(Config.larger_beacon_range) ? 16 : constant;
    }

    @WrapOperation(
            method = "applyEffects",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;getHeight()I"
            )
    )
    private static int applyEffects(Level instance, Operation<Integer> original) {
        return ConfigHelper.get(Config.larger_beacon_range) ? Integer.MAX_VALUE : original.call(instance);
    }
}
