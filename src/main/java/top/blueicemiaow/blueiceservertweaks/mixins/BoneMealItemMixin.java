package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.api.References;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(BoneMealItem.class)
public abstract class BoneMealItemMixin {
    @WrapMethod(method = "growCrop")
    private static boolean growCrop(ItemStack itemStack, Level level, BlockPos pos, Operation<Boolean> original) {
        if (level instanceof ServerLevel world) {
            BlockState state = world.getBlockState(pos);
            if (ConfigHelper.get(Config.bonemealable_cactus) && state.is(Blocks.CACTUS)
                    || ConfigHelper.get(Config.bonemealable_sugar_cane) && state.is(Blocks.SUGAR_CANE)) {
                Block block = state.getBlock();
                int maxY;
                if (ConfigHelper.get(Config.powerful_bone_meal)) {
                    maxY = world.getMinY() + world.getHeight();
                } else {
                    maxY = pos.getY();
                    while (world.getBlockState(pos.atY(--maxY)).is(block));
                    maxY += 4;
                }
                for (int i = pos.getY() + 1; i < maxY; i++) {
                    BlockPos newPos = pos.atY(i);
                    BlockState newState = world.getBlockState(newPos);
                    if (newState.is(block)) {
                        continue;
                    }
                    if (newState.is(BlockTags.REPLACEABLE)) {
                        world.setBlockAndUpdate(newPos, block.defaultBlockState());
                        itemStack.shrink(1);
                        References.bonemealPos.put(pos, newPos);
                        return true;
                    }
                    break;
                }
            }
        }
        return original.call(itemStack, level, pos);
    }

    @Inject(method = "addGrowthParticles", at = @At("TAIL"))
    private static void addGrowthParticles(LevelAccessor level, BlockPos pos, int count, CallbackInfo ci) {
        if (References.bonemealPos.containsKey(pos)) {
            ParticleUtils.spawnParticleInBlock(level, References.bonemealPos.get(pos), count, ParticleTypes.HAPPY_VILLAGER);
            References.bonemealPos.remove(pos);
        }
    }
}
