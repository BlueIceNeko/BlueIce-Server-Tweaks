package top.blueicemiaow.blueiceservertweaks.gameplay;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

public class Hoe {
    public static boolean harvestCrop(Player entity, Level level, BlockPos pos) {
        if (ConfigHelper.get(Config.hoe_harvest_crop) && check(entity, level)) {
            ServerLevel world = (ServerLevel) level;
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            if (block instanceof CropBlock crop && crop.isMaxAge(state)) {
                ServerPlayer player = (ServerPlayer) entity;
                Block.getDrops(state, world, pos, null, player, player.getMainHandItem()).forEach(player::addItem);
                player.getInventory().setChanged();
                block.spawnDestroyParticles(world, pos, state);
                world.setBlock(pos, state.setValue(CropBlock.AGE, 0), 2);
                return true;
            }
        }
        return false;
    }

    public static boolean recycleEye(Player entity, Level level, BlockPos pos) {
        if (ConfigHelper.get(Config.hoe_recycle_ender_eye) && check(entity, level)) {
            ServerLevel world = (ServerLevel) level;
            BlockState frame = world.getBlockState(pos);
            if (frame.is(Blocks.END_PORTAL_FRAME) && frame.getValue(EndPortalFrameBlock.HAS_EYE)) {
                ServerPlayer player = (ServerPlayer) entity;
                world.setBlock(pos, frame.setValue(EndPortalFrameBlock.HAS_EYE, false), 2);
                ItemStack eye = new ItemStack(Items.ENDER_EYE);
                eye.setCount(1);
                player.addItem(eye);
                player.getInventory().setChanged();
                return true;
            }
        }
        return false;
    }

    public static boolean collectBlock(Player entity, Level level, BlockPos pos) {
        if (ConfigHelper.get(Config.hoe_collect_block) && check(entity, level)) {
            ServerLevel world = (ServerLevel) level;
            BlockState state = world.getBlockState(pos);
            if (state.is(BlueIceServerTweaks.COLLECTABLE)) {
                ServerPlayer player = (ServerPlayer) entity;
                if (state.is(BlockTags.SHULKER_BOXES)) {
                    Block.getDrops(state, world, pos, world.getBlockEntity(pos)).forEach(player::addItem);
                } else {
                    player.addItem(state.getCloneItemStack(world, pos, true));
                }
                player.getInventory().setChanged();
                world.removeBlock(pos, false);
                return true;
            }
        }
        return false;
    }

    public static boolean check(Player entity, Level level) {
        return entity instanceof ServerPlayer player && level instanceof ServerLevel && player.isShiftKeyDown() && player.getMainHandItem().is(ItemTags.HOES);
    }
}
