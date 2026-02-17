package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;

public class HatRunProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		ItemStack hat = ItemStack.EMPTY;
		if (entity.hasPermissions(2) || new Object() {
			public int getScore(String score, Entity _ent) {
				Scoreboard _sc = _ent.level().getScoreboard();
				Objective _so = _sc.getObjective(score);
				if (_so != null)
					return _sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).getScore();
				return 0;
			}
		}.getScore("hat", entity) == 1) {
			hat = entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY;
			if ((hat.getItem() != ItemStack.EMPTY.getItem()) && (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem() == ItemStack.EMPTY.getItem()) {
				if (entity instanceof Player _player) {
					_player.getInventory().armor.set(3, hat);
					_player.getInventory().setChanged();
				} else if (entity instanceof LivingEntity _living) {
					_living.setItemSlot(EquipmentSlot.HEAD, hat);
				}
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack = ItemStack.EMPTY;
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
				HatLoggerProcedure.execute(entity);
			}
		}
	}
}
