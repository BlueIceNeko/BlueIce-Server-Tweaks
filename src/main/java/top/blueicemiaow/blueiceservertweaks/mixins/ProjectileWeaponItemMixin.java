package top.blueicemiaow.blueiceservertweaks.mixins;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(ProjectileWeaponItem.class)
public abstract class ProjectileWeaponItemMixin {
    @Unique
    private static void load(LivingEntity shooter, AbstractArrow arrow, int payload) {
        BlueIceServerTweaks.arrowPayloads.put(arrow.getUUID(), payload);
        if (shooter instanceof ServerPlayer player) {
            if (!player.gameMode().isCreative()) {
                player.getOffhandItem().shrink(1);
                player.getInventory().setChanged();
            }
        } else {
            shooter.getOffhandItem().shrink(1);
        }
    }

    @Inject(method = "createProjectile", at = @At("RETURN"))
    private void createProjectile(Level level, LivingEntity shooter, ItemStack weapon, ItemStack projectile, boolean isCrit, CallbackInfoReturnable<Projectile> cir) {
        AbstractArrow arrow = (AbstractArrow) cir.getReturnValue();
        ItemStack payload = shooter.getOffhandItem();
        if (ConfigHelper.get(Config.tnt_arrow) && payload.is(Items.TNT)) {
            load(shooter, arrow, 0);
        } else if (ConfigHelper.get(Config.fire_charge_arrow) && payload.is(Items.FIRE_CHARGE)) {
            load(shooter, arrow, 1);
        } else if (ConfigHelper.get(Config.ender_pearl_arrow) && payload.is(Items.ENDER_PEARL)) {
            load(shooter, arrow, 2);
        }
    }
}
