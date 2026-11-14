package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

import java.util.function.BiConsumer;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin {
    @WrapOperation(
            method = "onTake",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/inventory/ContainerLevelAccess;execute(Ljava/util/function/BiConsumer;)V"
            )
    )
    private void onTake(ContainerLevelAccess instance, BiConsumer<Level, BlockPos> action, Operation<Void> original) {
        if (!ConfigHelper.get(Config.prevent_anvil_break)) {
            original.call(instance, action);
        }
    }

    @WrapMethod(method = "calculateIncreasedRepairCost")
    private static int calculateIncreasedRepairCost(int baseCost, Operation<Integer> original) {
        return ConfigHelper.get(Config.linear_repair_cost) ? baseCost + 1 : original.call(baseCost);
    }
}
