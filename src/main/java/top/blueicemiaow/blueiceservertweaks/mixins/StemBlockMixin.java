package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.block.StemBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(StemBlock.class)
public abstract class StemBlockMixin {
    @WrapOperation(
            method = "performBonemeal",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Math;min(II)I"
            )
    )
    private int performBonemeal(int a, int b, Operation<Integer> original) {
        return ConfigHelper.get(Config.powerful_bone_meal) ? a : original.call(a, b);
    }
}
