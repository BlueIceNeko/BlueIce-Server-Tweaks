package top.blueicemiaow.blueiceservertweaks.mixins;

import net.minecraft.world.entity.monster.Ghast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(Ghast.class)
public abstract class GhastMixin {
    @ModifyConstant(method = "leashSnapDistance", constant = @Constant(doubleValue = 16))
    private double leashSnapDistance(double constant) {
        return ConfigHelper.get(Config.remove_lead_length_limit) ? Double.MAX_VALUE : constant;
    }
}
