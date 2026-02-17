package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.Lists;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.nbt.TagParser;
import net.minecraft.nbt.CompoundTag;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;

import java.util.ArrayList;

import com.mojang.brigadier.exceptions.CommandSyntaxException;

public class SetSuspiciousStewEffectsProcedure {
	public SetSuspiciousStewEffectsProcedure() {
		UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
			if (hand != player.getUsedItemHand())
				return InteractionResult.PASS;
			execute(level.getBlockState(hitResult.getBlockPos()), player);
			return InteractionResult.PASS;
		});
	}

	public static void execute(BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		ItemStack mainHand = ItemStack.EMPTY;
		ItemStack offHand = ItemStack.EMPTY;
		ItemStack stew = ItemStack.EMPTY;
		if (blockstate.getBlock() == Blocks.FLETCHING_TABLE) {
			mainHand = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy();
			if (mainHand.getItem() == Items.MUSHROOM_STEW && mainHand.getCount() == 1) {
				offHand = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).copy();
				if (offHand.getItem() == Items.POTION) {
					stew = new ItemStack(Items.SUSPICIOUS_STEW);
					CompoundTag potionNbt_ = offHand.getTag();
					if (potionNbt_ != null) {
						String potionNbt_Potion_ = potionNbt_.getString("Potion");
						ArrayList<String> potions = Lists.getPotionList();
						int potionIndex = potions.indexOf(potionNbt_Potion_);
						if (potionIndex == -1) {
							return;
						} else {
							ArrayList<Integer> ids = Lists.getIdList();
							ArrayList<Integer> durations = Lists.getDurationList();
							int stewSnbt_Effects_0_EffectId_ = ids.get(potionIndex);
							int stewSnbt_Effects_0_EffectDuration_ = durations.get(potionIndex);
							String stewSnbt_ = "{Effects:[{EffectId:" + stewSnbt_Effects_0_EffectId_ + ",EffectDuration:" + stewSnbt_Effects_0_EffectDuration_ + "}]}";
							CompoundTag stewNbt_ = null;
							try {
								stewNbt_ = TagParser.parseTag(stewSnbt_);
							} catch (CommandSyntaxException e) {
								e.printStackTrace();
							}
							stew.setTag(stewNbt_);
						}
					}
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = stew;
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
						_setstack = new ItemStack(Items.GLASS_BOTTLE);
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
				}
			}
		}
	}
}
