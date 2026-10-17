package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.commands.lvl0.Fly;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;
import top.blueicemiaow.blueiceservertweaks.tools.Tick;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {
    @WrapMethod(method = "addEntity")
    private boolean addEntity(Entity entity, Operation<Boolean> original) {
        if (ConfigHelper.get(Config.prevent_rotten_flesh_drop) && entity instanceof ItemEntity item && item.getItem().is(Items.ROTTEN_FLESH)) {
            return false;
        }
        return original.call(entity);
    }

    @Inject(method = "addPlayer", at = @At("HEAD"))
    private void addPlayer(ServerPlayer player, CallbackInfo ci) {
        if (ConfigHelper.get(Config.respawn_immunity_fix)) {
            Tick.respawnImmunity.put(player.getUUID(), 60);
        }
        Fly.update(player);
    }
}
