package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CropBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin {
    @WrapMethod(method = "hasSufficientLight")
    private static boolean hasSufficientLight(LevelReader level, BlockPos pos, Operation<Boolean> original) {
        return ConfigHelper.get(Config.remove_crop_light_restriction) || original.call(level, pos);
    }

    @WrapOperation(
            method = "randomTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;getRawBrightness(Lnet/minecraft/core/BlockPos;I)I"
            )
    )
    private int randomTick(ServerLevel instance, BlockPos blockPos, int i, Operation<Integer> original) {
        return ConfigHelper.get(Config.remove_crop_light_restriction) ? 15 : original.call(instance, blockPos, i);
    }

    @WrapOperation(
            method = "growCrops",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Math;min(II)I"
            )
    )
    private int growCrops(int a, int b, Operation<Integer> original) {
        return ConfigHelper.get(Config.powerful_bone_meal) ? a : original.call(a, b);
    }
}
