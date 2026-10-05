package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.StrawBedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(StrawBedBlock.class)
public abstract class StrawBedBlockMixin {
    @WrapMethod(method = "destroyOnLeave")
    private void destroyOnLeave(Level level, BlockPos pos, Operation<Void> original) {
        if (!ConfigHelper.get(Config.prevent_straw_bed_break)) {
            original.call(level, pos);
        }
    }

    @WrapMethod(method = "destroyOnUse")
    private InteractionResult destroyOnUse(BlockState state, Level level, BlockPos pos, Player player, Operation<InteractionResult> original) {
        if (ConfigHelper.get(Config.straw_bed_explode)) {
            level.removeBlock(pos, false);
            BlockPos bed = pos.relative((state.getValue(HorizontalDirectionalBlock.FACING)).getOpposite());
            if (level.getBlockState(bed).is(Blocks.STRAW_BED)) {
                level.removeBlock(bed, false);
            }
            Vec3 boom = Vec3.atCenterOf(pos);
            level.explode(null, level.damageSources().badRespawnPointExplosion(boom), null, boom, 3f, !ConfigHelper.get(Config.prevent_respawn_point_produce_fire), Level.ExplosionInteraction.BLOCK);
            return InteractionResult.SUCCESS_SERVER;
        }
        return original.call(state, level, pos, player);
    }
}
