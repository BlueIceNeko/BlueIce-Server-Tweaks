package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealSource;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(MushroomBlock.class)
public abstract class MushroomBlockMixin {
    @WrapOperation(
            method = "canSurvive",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/LevelReader;getRawBrightness(Lnet/minecraft/core/BlockPos;I)I"
            )
    )
    private int canSurvive(LevelReader instance, BlockPos blockPos, int i, Operation<Integer> original) {
        return ConfigHelper.get(Config.remove_mushroom_light_restriction) ? 0 : original.call(instance, blockPos, i);
    }

    @WrapMethod(method = "isBonemealSuccess")
    private boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state, BonemealSource source, Operation<Boolean> original) {
        return ConfigHelper.get(Config.powerful_bone_meal) || original.call(level, random, pos, state, source);
    }
}
