package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.Lists;

import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import java.util.ArrayList;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;

public class SetPermissionRunProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		ArrayList<String> commands = Lists.getCommandList();
		String command = StringArgumentType.getString(arguments, "command");
		int permission = 0;
		if (BoolArgumentType.getBool(arguments, "permission")) {
			permission = 1;
		}
		if (commands.contains(command)) {
			try {
				for (Entity entityiterator : EntityArgument.getEntities(arguments, "player")) {
					Entity _ent = entityiterator;
					Scoreboard _sc = _ent.level().getScoreboard();
					Objective _so = _sc.getObjective(command);
					if (_so == null)
						_so = _sc.addObjective(command, ObjectiveCriteria.DUMMY, Component.literal(command), ObjectiveCriteria.RenderType.INTEGER);
					_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(permission);
				}
			} catch (CommandSyntaxException e) {
				e.printStackTrace();
			}
			SetPermissionLoggerProcedure.execute(arguments, entity, "/" + command);
		} else if (command.equals(".")) {
			try {
				for (Entity entityiterator : EntityArgument.getEntities(arguments, "player")) {
					Entity _ent = entityiterator;
					Scoreboard _sc = _ent.level().getScoreboard();
					for (String commanditerator : commands) {
						Objective _so = _sc.getObjective(commanditerator);
						if (_so == null)
							_so = _sc.addObjective(commanditerator, ObjectiveCriteria.DUMMY, Component.literal(commanditerator), ObjectiveCriteria.RenderType.INTEGER);
						_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(permission);
					}
				}
			} catch (CommandSyntaxException e) {
				e.printStackTrace();
			}
			SetPermissionLoggerProcedure.execute(arguments, entity, "all");
		}
	}

	public static boolean checkPermission(Entity entity, String command) {
		if (entity == null)
			return false;
		return (entity.hasPermissions(2) || new Object() {
			public int getScore(String score, Entity _ent) {
				Scoreboard _sc = _ent.level().getScoreboard();
				Objective _so = _sc.getObjective(score);
				if (_so != null)
					return _sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).getScore();
				return 0;
			}
		}.getScore(command, entity) == 1);
	}

	public static void setPermission(Entity entity, String command, boolean booleanPermission) {
		ArrayList<String> commands = Lists.getCommandList();
		int permission = 0;
		if (booleanPermission) {
			permission = 1;
		}
		if (commands.contains(command)) {
			Entity _ent = entity;
			Scoreboard _sc = _ent.level().getScoreboard();
			Objective _so = _sc.getObjective(command);
			if (_so == null)
				_so = _sc.addObjective(command, ObjectiveCriteria.DUMMY, Component.literal(command), ObjectiveCriteria.RenderType.INTEGER);
			_sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).setScore(permission);
		}
	}
}
