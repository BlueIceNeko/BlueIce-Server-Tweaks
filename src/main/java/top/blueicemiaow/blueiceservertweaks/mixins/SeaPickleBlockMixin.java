package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealSource;
import net.minecraft.world.level.block.SeaPickleBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(SeaPickleBlock.class)
public abstract class SeaPickleBlockMixin {
    @WrapMethod(method = "isValidBonemealTarget")
    private boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, BonemealSource source, Operation<Boolean> original) {
        return ConfigHelper.get(Config.powerful_bone_meal) || original.call(level, pos, state, source);
    }
}
