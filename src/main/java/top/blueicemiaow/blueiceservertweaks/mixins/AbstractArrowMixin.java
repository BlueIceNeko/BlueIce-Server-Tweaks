package top.blueicemiaow.blueiceservertweaks.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

import java.util.UUID;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin {
    @Unique
    private void onHit(HitResult hitResult) {
        AbstractArrow arrow = (AbstractArrow) (Object) this;
        UUID uuid = arrow.getUUID();
        if (BlueIceServerTweaks.arrowPayloads.containsKey(uuid) && arrow.getOwner() instanceof LivingEntity owner && arrow.level() instanceof ServerLevel level) {
            int payload = BlueIceServerTweaks.arrowPayloads.get(uuid);
            if (ConfigHelper.get(Config.tnt_arrow) && payload == 0) {
                Vec3 pos = arrow.position();
                level.explode(owner, pos.x, pos.y, pos.z, 4f, Level.ExplosionInteraction.BLOCK);
                if (hitResult instanceof BlockHitResult) {
                    arrow.kill(level);
                }
            } else if (ConfigHelper.get(Config.fire_charge_arrow) && payload == 1) {
                if (hitResult instanceof BlockHitResult blockHitResult) {
                    BlockPos pos = blockHitResult.getBlockPos().relative(blockHitResult.getDirection());
                    if (level.getBlockState(pos).is(BlockTags.REPLACEABLE)) {
                        level.setBlockAndUpdate(pos, Blocks.FIRE.defaultBlockState());
                    }
                } else if (hitResult instanceof EntityHitResult entityHitResult) {
                    entityHitResult.getEntity().igniteForTicks(160);
                }
            } else if (ConfigHelper.get(Config.ender_pearl_arrow) && payload == 2) {
                Vec3 pos = arrow.position();
                owner.teleportTo(pos.x, pos.y, pos.z);
                if (owner instanceof ServerPlayer player) {
                    player.connection.teleport(pos.x, pos.y, pos.z, player.getYRot(), player.getXRot());
                }
            }
            BlueIceServerTweaks.arrowPayloads.remove(uuid);
        }
    }

    @Inject(method = "onHitBlock", at = @At("HEAD"))
    private void onHitBlock(BlockHitResult hitResult, CallbackInfo ci) {
        this.onHit(hitResult);
    }

    @Inject(method = "onHitEntity", at = @At("HEAD"))
    private void onHitEntity(EntityHitResult hitResult, CallbackInfo ci) {
        this.onHit(hitResult);
    }
}
