package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.BlueiceServerTweaksMod;

import net.minecraft.world.entity.Entity;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.context.CommandContext;

public class LoggerRunProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		BlueiceServerTweaksMod.LOGGER.info(GetLogProcedure.execute(arguments, entity));
	}
}
