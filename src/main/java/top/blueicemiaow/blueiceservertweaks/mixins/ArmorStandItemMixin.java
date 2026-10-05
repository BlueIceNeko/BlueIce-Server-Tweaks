package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ArmorStandItem;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(ArmorStandItem.class)
public class ArmorStandItemMixin {
    @Inject(
            method = "useOn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/decoration/ArmorStand;snapTo(DDDFF)V"
            )
    )
    private void useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir, @Local(name = "entity") ArmorStand entity) {
        entity.setShowArms(ConfigHelper.get(Config.armor_stand_show_arms));
    }
}
