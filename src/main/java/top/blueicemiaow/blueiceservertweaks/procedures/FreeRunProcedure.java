package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;

public class FreeRunProcedure {
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
		}.getScore("free", entity) == 1) {
			if (!(new Object() {
				public boolean checkGamemode(Entity _ent) {
					if (_ent instanceof ServerPlayer _serverPlayer) {
						return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
					} else if (_ent.level().isClientSide() && _ent instanceof Player _player) {
						return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null
								&& Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SPECTATOR;
					}
					return false;
				}
			}.checkGamemode(entity))) {
				Scoreboard _sc = entity.level().getScoreboard();
				Objective _so = null;
				_so = _sc.getObjective("free_x");
				if (_so == null)
					_so = _sc.addObjective("free_x", ObjectiveCriteria.DUMMY, Component.literal("free_x"), ObjectiveCriteria.RenderType.INTEGER);
				_sc.getOrCreatePlayerScore(entity.getScoreboardName(), _so).setScore((int) x);
				_so = _sc.getObjective("free_y");
				if (_so == null)
					_so = _sc.addObjective("free_y", ObjectiveCriteria.DUMMY, Component.literal("free_y"), ObjectiveCriteria.RenderType.INTEGER);
				_sc.getOrCreatePlayerScore(entity.getScoreboardName(), _so).setScore((int) y);
				_so = _sc.getObjective("free_z");
				if (_so == null)
					_so = _sc.addObjective("free_z", ObjectiveCriteria.DUMMY, Component.literal("free_z"), ObjectiveCriteria.RenderType.INTEGER);
				_sc.getOrCreatePlayerScore(entity.getScoreboardName(), _so).setScore((int) z);
				if (entity instanceof ServerPlayer _player)
					_player.setGameMode(GameType.SPECTATOR);
			}
			FreeLoggerProcedure.execute(entity);
		}
	}
}
