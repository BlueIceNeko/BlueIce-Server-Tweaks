package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

public class CraftingRunProcedure {
	public static void execute(double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.hasPermissions(2) || new Object() {
			public int getScore(String score, Entity _ent) {
				Scoreboard _sc = _ent.level().getScoreboard();
				Objective _so = _sc.getObjective(score);
				if (_so != null)
					return _sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).getScore();
				return 0;
			}
		}.getScore("crafting", entity) == 1) {
			if (entity instanceof Player _player) {
				BlockPos _blockPos = BlockPos.containing(x, y, z);
				_player.openMenu(new SimpleMenuProvider((_containerID, _inventory, _entity) -> {
					return new CraftingMenu(_containerID, _inventory, ContainerLevelAccess.create(_player.level(), _blockPos)) {
						@Override
						public boolean stillValid(Player player) {
							return true;
						}
					};
				}, Component.literal((Component.translatable("container.crafting").getString()))));
			}
			CraftingLoggerProcedure.execute(entity);
		}
	}
}
