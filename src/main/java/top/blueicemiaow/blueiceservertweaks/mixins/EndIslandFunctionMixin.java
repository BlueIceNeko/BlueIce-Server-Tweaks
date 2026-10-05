package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.densityfunction.generator.EndIslandFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(EndIslandFunction.class)
public abstract class EndIslandFunctionMixin {
    @ModifyConstant(method = "getHeightValue", constant = @Constant(floatValue = -100F))
    private static float getHeightValue(float constant, @Local(name = "sectionX", argsOnly = true) int sectionX, @Local(name = "sectionZ", argsOnly = true) int sectionZ) {
        return ConfigHelper.get(Config.end_ring_fix) ? Mth.clamp(100.0F - Mth.sqrt(sectionX * sectionX + sectionZ * sectionZ) * 8.0F, -100.0F, 80.0F) : constant;
    }
}
