package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;

public class CollectBlockProcedure {
	public CollectBlockProcedure() {
		UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
			if (hand != player.getUsedItemHand())
				return InteractionResult.PASS;
			execute(level, hitResult.getBlockPos().getX(), hitResult.getBlockPos().getY(), hitResult.getBlockPos().getZ(), level.getBlockState(hitResult.getBlockPos()), player);
			return InteractionResult.PASS;
		});
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate, Entity entity) {
		if (entity == null)
			return;
		if (entity.isShiftKeyDown() && (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof HoeItem) {
			if (blockstate.is(TagKey.create(Registries.BLOCK, new ResourceLocation("blueice_server_tweaks:destroyable")))) {
				if (blockstate.is(TagKey.create(Registries.BLOCK, new ResourceLocation("blueice_server_tweaks:collectable")))) {
					if (entity instanceof Player _player) {
						ItemStack _setstack = (new ItemStack(blockstate.getBlock()));
						_setstack.setCount(1);
						_player.getInventory().add(_setstack);
					}
				}
				world.destroyBlock(BlockPos.containing(x, y, z), false);
			} else if (blockstate.is(TagKey.create(Registries.BLOCK, new ResourceLocation("blueice_server_tweaks:op_destroyable"))) && entity.hasPermissions(2)) {
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			}
		}
	}
}
