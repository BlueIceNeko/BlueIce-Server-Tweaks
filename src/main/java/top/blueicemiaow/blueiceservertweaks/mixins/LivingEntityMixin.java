package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Weapon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract ItemStack getMainHandItem();

    @WrapMethod(method = "getSecondsToDisableBlocking")
    private float getSecondsToDisableBlocking(Operation<Float> original) {
        return ConfigHelper.get(Config.enhanced_can_disable_shield) && this.getMainHandItem().is(BlueIceServerTweaks.CAN_DISABLE_SHIELD) ? Weapon.AXE_DISABLES_BLOCKING_FOR_SECONDS : original.call();
    }
}
