package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.BoolArgumentType;

public class DebugGameruleRunProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments, Entity entity) {
		boolean enabled = false;
		enabled = BoolArgumentType.getBool(arguments, "enabled");
		world.getLevelData().getGameRules().getRule(GameRules.RULE_DAYLIGHT).set((!enabled), world.getServer());
		world.getLevelData().getGameRules().getRule(GameRules.RULE_WEATHER_CYCLE).set((!enabled), world.getServer());
		world.getLevelData().getGameRules().getRule(GameRules.RULE_KEEPINVENTORY).set(enabled, world.getServer());
		if (enabled) {
			if (world instanceof ServerLevel _level) {
				_level.setDayTime(6000);
				_level.setWeatherParameters(180000, 0, false, false);
			}
			world.getLevelData().getGameRules().getRule(GameRules.RULE_SPAWN_RADIUS).set(0, world.getServer());
		} else {
			world.getLevelData().getGameRules().getRule(GameRules.RULE_SPAWN_RADIUS).set(10, world.getServer());
		}
		DebugGameruleLoggerProcedure.execute(entity, enabled);
	}
}
