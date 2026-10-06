package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.PitcherCropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(PitcherCropBlock.class)
public abstract class PitcherCropBlockMixin {
    @WrapOperation(
            method = "performBonemeal",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/PitcherCropBlock;grow(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;I)V"
            )
    )
    private void performBonemeal(PitcherCropBlock instance, ServerLevel level, BlockState lowerState, BlockPos lowerPos, int increase, Operation<Void> original) {
        original.call(instance, level, lowerState, lowerPos, ConfigHelper.get(Config.powerful_bone_meal) ? 4 : increase);
    }
}
