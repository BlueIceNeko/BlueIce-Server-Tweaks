package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import java.util.ArrayList;

public class KTickProcedure {
	public KTickProcedure() {
		ServerTickEvents.END_WORLD_TICK.register((level) -> {
			execute(level);
		});
	}

	public static void execute(LevelAccessor world) {
		int k_info = 0;
		for (Entity entityiterator : new ArrayList<>(world.players())) {
			k_info = new Object() {
				public int getScore(String score, Entity _ent) {
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective(score);
					if (_so != null)
						return _sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).getScore();
					return 0;
				}
			}.getScore("k_infos", entityiterator);
			if (k_info > 0) {
				{
					Entity _ent = entityiterator;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective("k_infos");
					if (_so == null)
						_so = _sc.addObjective("k_infos", ObjectiveCriteria.DUMMY, Component.literal("k_infos"), ObjectiveCriteria.RenderType.INTEGER);
					_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(k_info - 1);
				}
			}
		}
	}
}
