package top.blueicemiaow.blueiceservertweaks.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndPortalFrameBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

@Mixin(EnderEyeItem.class)
public abstract class EnderEyeItemMixin {
    @Unique
    private static Direction[] directions(Direction direction) {
        return switch (direction.ordinal()) {
            case 2 -> new Direction[]{Direction.NORTH, Direction.EAST, Direction.WEST, Direction.SOUTH};
            case 3 -> new Direction[]{Direction.SOUTH, Direction.WEST, Direction.EAST, Direction.NORTH};
            case 4 -> new Direction[]{Direction.WEST, Direction.SOUTH, Direction.NORTH, Direction.EAST};
            case 5 -> new Direction[]{Direction.EAST, Direction.NORTH, Direction.SOUTH, Direction.WEST};
            default -> null;
        };
    }

    @Unique
    private static int xOffset(Direction direction) {
        return switch (direction.ordinal()) {
            case 2 -> -1;
            case 3 -> 1;
            default -> 0;
        };
    }

    @Unique
    private static int zOffset(Direction direction) {
        return switch (direction.ordinal()) {
            case 4 -> -1;
            case 5 -> 1;
            default -> 0;
        };
    }

    @Unique
    private static boolean check(BlockState frame, Direction direction) {
        return frame.is(Blocks.END_PORTAL_FRAME) && frame.getValue(EndPortalFrameBlock.HAS_EYE) && frame.getValue(EndPortalFrameBlock.FACING) == direction;
    }

    @WrapOperation(
            method = "useOn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/pattern/BlockPattern;find(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/pattern/BlockPattern$BlockPatternMatch;"
            )
    )
    private BlockPattern.BlockPatternMatch useOn(BlockPattern instance, LevelReader levelReader, BlockPos pos, Operation<BlockPattern.BlockPatternMatch> original) {
        if (ConfigHelper.get(Config.enhanced_end_portal) && levelReader instanceof Level level) {
            Direction[] directions = directions(level.getBlockState(pos).getValue(EndPortalFrameBlock.FACING));
            if (directions == null) {
                return null;
            }
            int xOffset = xOffset(directions[0]);
            int zOffset = zOffset(directions[0]);
            int size = -1;
            for (int i = 1; i < 128; i++) {
                BlockPos frame = new BlockPos(pos.getX() + xOffset * i, pos.getY(), pos.getZ() + zOffset * i);
                if (!check(level.getBlockState(frame), directions[0])) {
                    size += i;
                    break;
                }
            }
            BlockPos a = new BlockPos(pos.getX() + xOffset * size + zOffset, pos.getY(), pos.getZ() + zOffset * size + xOffset);
            for (int i = 1; i < 128; i++) {
                BlockPos frame = new BlockPos(pos.getX() - xOffset * i, pos.getY(), pos.getZ() - zOffset * i);
                if (!check(level.getBlockState(frame), directions[0])) {
                    size += i;
                    break;
                }
            }
            BlockPos b = new BlockPos(a.getX() - xOffset * size + xOffset + zOffset * size - zOffset, pos.getY(), a.getZ() + xOffset * size - xOffset - zOffset * size + zOffset);
            for (int i = 0; i < size; i++) {
                BlockPos frame = new BlockPos(a.getX() + zOffset * i + xOffset, pos.getY(), a.getZ() + xOffset * i + zOffset);
                if (!check(level.getBlockState(frame), directions[1])) {
                    return null;
                }
            }
            for (int i = 0; i < size; i++) {
                BlockPos frame = new BlockPos(b.getX() - zOffset * i - xOffset, pos.getY(), b.getZ() - xOffset * i - zOffset);
                if (!check(level.getBlockState(frame), directions[2])) {
                    return null;
                }
            }
            for (int i = 0; i < size; i++) {
                BlockPos frame = new BlockPos(b.getX() + xOffset * i + zOffset, pos.getY(), b.getZ() + zOffset * i + xOffset);
                if (!check(level.getBlockState(frame), directions[3])) {
                    return null;
                }
            }
            for (int x = Math.min(a.getX(), b.getX()); x < Math.max(a.getX(), b.getX()) + 1; x++) {
                for (int z = Math.min(a.getZ(), b.getZ()); z < Math.max(a.getZ(), b.getZ()) + 1; z++) {
                    BlockPos portal = new BlockPos(x, pos.getY(), z);
                    level.destroyBlock(portal, true);
                    level.setBlock(portal, Blocks.END_PORTAL.defaultBlockState(), 2);
                }
            }
            level.globalLevelEvent(1038, pos.offset(1, 0, 1), 0);
            return null;
        }
        return original.call(instance, levelReader, pos);
    }
}
