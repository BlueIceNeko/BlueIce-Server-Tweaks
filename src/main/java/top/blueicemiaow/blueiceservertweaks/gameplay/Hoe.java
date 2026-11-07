package top.blueicemiaow.blueiceservertweaks.gameplay;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.api.Tags;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

public class Hoe {
    public static InteractionResult harvestCrop(Player entity, Level level, InteractionHand hand, BlockHitResult hitResult) {
        if (ConfigHelper.get(Config.hoe_harvest_crop) && check(entity, level, hand)) {
            ServerLevel world = (ServerLevel) level;
            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            if (block instanceof CropBlock crop && crop.isMaxAge(state)) {
                ServerPlayer player = (ServerPlayer) entity;
                Block.getDrops(state, world, pos, null, player, player.getItemInHand(hand)).forEach(player::addItem);
                player.getInventory().setChanged();
                block.spawnDestroyParticles(world, pos, state);
                world.setBlock(pos, state.setValue((IntegerProperty) crop.getStateDefinition().getProperty("age"), 0), 2);
                return InteractionResult.SUCCESS_SERVER;
            }
        }
        return InteractionResult.PASS;
    }

    public static InteractionResult recycleEye(Player entity, Level level, InteractionHand hand, BlockHitResult hitResult) {
        if (ConfigHelper.get(Config.hoe_recycle_ender_eye) && check(entity, level, hand)) {
            ServerLevel world = (ServerLevel) level;
            BlockPos pos = hitResult.getBlockPos();
            BlockState frame = world.getBlockState(pos);
            if (frame.is(Blocks.END_PORTAL_FRAME) && frame.getValue(EndPortalFrameBlock.HAS_EYE)) {
                ServerPlayer player = (ServerPlayer) entity;
                world.setBlock(pos, frame.setValue(EndPortalFrameBlock.HAS_EYE, false), 2);
                ItemStack eye = new ItemStack(Items.ENDER_EYE);
                eye.setCount(1);
                player.addItem(eye);
                player.getInventory().setChanged();
                return InteractionResult.SUCCESS_SERVER;
            }
        }
        return InteractionResult.PASS;
    }

    public static InteractionResult collectBlock(Player entity, Level level, InteractionHand hand, BlockHitResult hitResult) {
        if (ConfigHelper.get(Config.hoe_collect_block) && check(entity, level, hand)) {
            ServerLevel world = (ServerLevel) level;
            BlockPos pos = hitResult.getBlockPos();
            BlockState state = world.getBlockState(pos);
            if (state.is(Tags.COLLECTABLE)) {
                ServerPlayer player = (ServerPlayer) entity;
                ItemStack item = state.getCloneItemStack(world, pos, true);
                if (world.getBlockEntity(pos) instanceof BlockEntity blockEntity) {
                    item.applyComponents(blockEntity.collectComponents());
                    blockEntity.setComponents(DataComponentMap.EMPTY);
                    world.removeBlockEntity(pos);
                }
                player.addItem(item);
                player.getInventory().setChanged();
                world.removeBlock(pos, false);
                return InteractionResult.SUCCESS_SERVER;
            }
        }
        return InteractionResult.PASS;
    }

    public static boolean check(Player entity, Level level, InteractionHand hand) {
        return entity instanceof ServerPlayer player && level instanceof ServerLevel && player.isShiftKeyDown() && player.getItemInHand(hand).is(ItemTags.HOES);
    }
}
