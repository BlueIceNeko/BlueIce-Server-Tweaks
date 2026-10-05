package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.EnchantingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(EnchantingTableBlock.class)
public abstract class EnchantingTableBlockMixin {
    @WrapOperation(
            method = "isValidBookShelf",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z",
                    ordinal = 1
            )
    )
    private static boolean isValidBookShelf1(BlockState instance, TagKey tagKey, Operation<Boolean> original) {
        return original.call(instance, tagKey) || ConfigHelper.get(Config.carpet_transmit_enchantment_power) && original.call(instance, BlockTags.WOOL_CARPETS);
    }

    @WrapOperation(
            method = "isValidBookShelf",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z",
                    ordinal = 0
            )
    )
    private static boolean isValidBookShelf0(BlockState instance, TagKey tagKey, Operation<Boolean> original) {
        return original.call(instance, tagKey) || ConfigHelper.get(Config.chiseled_bookshelf_provide_enchantment_power) && original.call(instance, BlueIceServerTweaks.ENCHANTMENT_POWER_PROVIDER);
    }
}
