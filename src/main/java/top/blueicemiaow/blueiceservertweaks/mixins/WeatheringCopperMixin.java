package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import org.spongepowered.asm.mixin.Mixin;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

import java.util.Optional;

@Mixin(WeatheringCopper.class)
public interface WeatheringCopperMixin {
    @WrapMethod(method = "getNext(Lnet/minecraft/world/level/block/Block;)Ljava/util/Optional;")
    private static Optional<Block> getNext(Block block, Operation<Optional<Block>> original) {
        return ConfigHelper.get(Config.prevent_copper_oxidize) ? Optional.of(block) : original.call(block);
    }
}
