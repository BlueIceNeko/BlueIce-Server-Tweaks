package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.Translation;
import top.blueicemiaow.blueiceservertweaks.Lists;

import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class KRunProcedure {
	public KRunProcedure() {
		String[] languages = {"en_us", "zh_cn"};
		String[] values = {"If you want to kill yourself, issue /k again in 10 seconds.", "如果你想自杀，在10秒内再次执行/k."};
		Translation k_warning = new Translation(languages, values);
		Lists.addTranslation("k_warning", k_warning);
	}

	public static void execute(Entity entity) {
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
		}.getScore("k", entity) == 1) {
			if (new Object() {
				public int getScore(String score, Entity _ent) {
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective(score);
					if (_so != null)
						return _sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).getScore();
					return 0;
				}
			}.getScore("k_infos", entity) > 0) {
				{
					Entity _ent = entity;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective("k_infos");
					if (_so == null)
						_so = _sc.addObjective("k_infos", ObjectiveCriteria.DUMMY, Component.literal("k_infos"), ObjectiveCriteria.RenderType.INTEGER);
					_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(0);
				}
				entity.kill();
				KLoggerProcedure.execute(entity);
			} else {
				{
					Entity _ent = entity;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective("k_infos");
					if (_so == null)
						_so = _sc.addObjective("k_infos", ObjectiveCriteria.DUMMY, Component.literal("k_infos"), ObjectiveCriteria.RenderType.INTEGER);
					_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(200);
				}
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(Lists.getTranslation("k_warning").get(Translation.serverLanguage)), false);
			}
		}
	}
}
