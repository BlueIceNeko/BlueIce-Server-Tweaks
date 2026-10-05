package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealSource;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(SaplingBlock.class)
public abstract class SaplingBlockMixin {
    @WrapMethod(method = "isBonemealSuccess")
    private boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source, Operation<Boolean> original) {
        return ConfigHelper.get(Config.powerful_bone_meal) || original.call(level, random, pos, state, source);
    }

    @WrapOperation(
            method = "performBonemeal",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/SaplingBlock;advanceTree(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/util/RandomSource;)V"
            )
    )
    private void performBonemeal(SaplingBlock instance, ServerLevel level, BlockPos pos, BlockState state, RandomSource random, Operation<Void> original) {
        original.call(instance, level, pos, ConfigHelper.get(Config.powerful_bone_meal) ? state.setValue(SaplingBlock.STAGE, 1) : state, random);
    }
}
