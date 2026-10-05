package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
    @WrapOperation(
            method = "dampensVibrations",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/tags/TagKey;)Z"
            )
    )
    private boolean dampensVibrations(ItemStack instance, TagKey tagKey, Operation<Boolean> original) {
        return original.call(instance, tagKey) || ConfigHelper.get(Config.prevent_cushion_item_entity_produce_vibration) && original.call(instance, ItemTags.CUSHIONS);
    }
}
