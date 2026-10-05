package top.blueicemiaow.blueiceservertweaks.gameplay;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.api.Lists;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;

import java.util.Arrays;
import java.util.Optional;

public class FletchingTable {
    public static boolean setStew(Player entity, Level level, BlockPos pos) {
        if (ConfigHelper.get(Config.fletching_table_set_stew) && check(entity, level, pos)) {
            ServerPlayer player = (ServerPlayer) entity;
            ItemStack mainHand = player.getMainHandItem().copy();
            if (mainHand.is(Items.MUSHROOM_STEW)) {
                ItemStack offHand = player.getOffhandItem().copy();
                if (offHand.is(Items.POTION)) {
                    Optional<Holder<Potion>> potion = offHand.get(DataComponents.POTION_CONTENTS).potion();
                    if (potion.isPresent()) {
                        String potionId = potion.get().getRegisteredName();
                        if (Lists.stews.containsKey(potionId)) {
                            Lists.Stew stewType = Lists.stews.get(potionId);
                            Optional<Holder.Reference<MobEffect>> effect = BuiltInRegistries.MOB_EFFECT.get(Identifier.parse(stewType.id()));
                            if (effect.isPresent()) {
                                ItemStack stew = new ItemStack(Items.SUSPICIOUS_STEW);
                                stew.set(DataComponents.SUSPICIOUS_STEW_EFFECTS, new SuspiciousStewEffects(Arrays.asList(new SuspiciousStewEffects.Entry(effect.get(), stewType.duration()))));
                                stew.setCount(mainHand.getCount());
                                player.setItemInHand(InteractionHand.MAIN_HAND, stew);
                                ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
                                bottle.setCount(1);
                                player.setItemInHand(InteractionHand.OFF_HAND, bottle);
                                player.getInventory().setChanged();
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean tipArrow(Player entity, Level level, BlockPos pos) {
        if (ConfigHelper.get(Config.fletching_table_tip_arrow) && check(entity, level, pos)) {
            ServerPlayer player = (ServerPlayer) entity;
            ItemStack mainHand = player.getMainHandItem().copy();
            if (mainHand.is(Items.ARROW)) {
                ItemStack offHand = player.getOffhandItem().copy();
                if (offHand.is(Items.POTION)) {
                    ItemStack arrow = new ItemStack(Items.TIPPED_ARROW);
                    arrow.set(DataComponents.POTION_CONTENTS, offHand.get(DataComponents.POTION_CONTENTS));
                    arrow.setCount(mainHand.getCount());
                    player.setItemInHand(InteractionHand.MAIN_HAND, arrow);
                    ItemStack bottle = new ItemStack(Items.GLASS_BOTTLE);
                    bottle.setCount(1);
                    player.setItemInHand(InteractionHand.OFF_HAND, bottle);
                    player.getInventory().setChanged();
                    return true;
                } else if (offHand.is(Items.GLOWSTONE_DUST)) {
                    ItemStack arrow = new ItemStack(Items.SPECTRAL_ARROW);
                    arrow.setCount(mainHand.getCount());
                    player.setItemInHand(InteractionHand.MAIN_HAND, arrow);
                    player.getOffhandItem().shrink(1);
                    player.getInventory().setChanged();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean modifyCrossbow(Player entity, Level level, BlockPos pos) {
        if (check(entity, level, pos)) {
            ServerPlayer player = (ServerPlayer) entity;
            if (player.getMainHandItem().is(Items.CROSSBOW)) {
                CompoundTag barrel = player.getMainHandItem().getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
                if (player.isShiftKeyDown() && barrel.getBooleanOr("with_barrel", false)) {
                    barrel.putBoolean("with_barrel", false);
                    player.getMainHandItem().set(DataComponents.CUSTOM_DATA, CustomData.of(barrel));
                    ItemStack rod = new ItemStack(Items.LIGHTNING_ROD.waxed().unaffected());
                    rod.setCount(1);
                    player.addItem(rod);
                    player.getInventory().setChanged();
                    return true;
                }
                if (ConfigHelper.get(Config.fletching_table_crossbow_assemble_barrel) && player.getOffhandItem().is(Items.LIGHTNING_ROD.waxed().unaffected()) && !player.isShiftKeyDown() && !barrel.getBooleanOr("with_barrel", false)) {
                    barrel.putBoolean("with_barrel", true);
                    player.getMainHandItem().set(DataComponents.CUSTOM_DATA, CustomData.of(barrel));
                    player.getOffhandItem().shrink(1);
                    player.getInventory().setChanged();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean check(Player entity, Level level, BlockPos pos) {
        return entity instanceof ServerPlayer && level instanceof ServerLevel && level.getBlockState(pos).is(Blocks.FLETCHING_TABLE);
    }
}
