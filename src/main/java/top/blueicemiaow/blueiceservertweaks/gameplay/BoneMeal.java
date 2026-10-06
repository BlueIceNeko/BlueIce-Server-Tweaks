package top.blueicemiaow.blueiceservertweaks.gameplay;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

public class BoneMeal {
    public static boolean perform(Player entity, Level level, InteractionHand hand, BlockPos pos) {
        if (check(entity, level, hand)) {
            ServerLevel world = (ServerLevel) level;
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
                        ServerPlayer player = (ServerPlayer) entity;
                        if (!player.gameMode().isCreative()) {
                            player.getItemInHand(hand).shrink(1);
                            player.getInventory().setChanged();
                        }
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }

    public static boolean check(Player entity, Level level, InteractionHand hand) {
        return entity instanceof ServerPlayer player && level instanceof ServerLevel && player.getItemInHand(hand).is(Items.BONE_MEAL);
    }
}
