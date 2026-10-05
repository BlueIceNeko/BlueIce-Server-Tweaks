package top.blueicemiaow.blueiceservertweaks.mixins;

import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(Level.class)
public abstract class LevelMixin {
    @ModifyConstant(method = "isInWorldBoundsHorizontal", constant = @Constant(intValue = 30000000))
    private static int isInWorldBoundsHorizontalMax(int constant) {
        return ConfigHelper.get(Config.remove_player_movement_bound) ? 30000128 : constant;
    }

    @ModifyConstant(method = "isInWorldBoundsHorizontal", constant = @Constant(intValue = -30000000))
    private static int isInWorldBoundsHorizontalMin(int constant) {
        return ConfigHelper.get(Config.remove_player_movement_bound) ? -30000128 : constant;
    }
}
