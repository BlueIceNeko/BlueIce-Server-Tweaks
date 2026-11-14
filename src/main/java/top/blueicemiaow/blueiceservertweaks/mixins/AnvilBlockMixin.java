package top.blueicemiaow.blueiceservertweaks.mixins;

import net.minecraft.world.level.block.AnvilBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(AnvilBlock.class)
public abstract class AnvilBlockMixin {
    @ModifyConstant(method = "falling", constant = @Constant(intValue = 40))
    private int falling(int constant) {
        return ConfigHelper.get(Config.remove_anvil_damage_limit) ? Integer.MAX_VALUE : constant;
    }
}
