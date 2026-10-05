package top.blueicemiaow.blueiceservertweaks.mixins;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
    @ModifyConstant(method = "getEnchantmentCost", constant = @Constant(intValue = 15))
    private static int getEnchantmentCost(int constant) {
        return ConfigHelper.get(Config.remove_enchantment_power_limit) ? Integer.MAX_VALUE : constant;
    }
}
