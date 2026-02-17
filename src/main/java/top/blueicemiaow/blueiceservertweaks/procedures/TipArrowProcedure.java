package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.nbt.CompoundTag;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class TipArrowProcedure {
	public TipArrowProcedure() {
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
		ItemStack arrow = ItemStack.EMPTY;
		if (blockstate.getBlock() == Blocks.FLETCHING_TABLE) {
			mainHand = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).copy();
			if (mainHand.getItem() == Items.ARROW) {
				offHand = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).copy();
				if (offHand.getItem() == Items.POTION) {
					arrow = new ItemStack(Items.TIPPED_ARROW);
					CompoundTag _nbtTag = offHand.getTag();
					if (_nbtTag != null)
						arrow.setTag(_nbtTag.copy());
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack;
						_setstack = arrow;
						_setstack.setCount(mainHand.getCount());
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
						_setstack = new ItemStack(Items.GLASS_BOTTLE);
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
				} else if (offHand.getItem() == Items.GLOWSTONE_DUST) {
					arrow = new ItemStack(Items.SPECTRAL_ARROW);
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = arrow;
						_setstack.setCount(mainHand.getCount());
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
					(entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).shrink(1);
				}
			}
		}
	}
}
