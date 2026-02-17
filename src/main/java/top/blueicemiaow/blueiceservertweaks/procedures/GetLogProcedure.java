package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class GetLogProcedure {
	public static String execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return "";
		String log = PlayerLoggerProcedure.execute(entity) + ": ";
		try {
			log += MessageArgument.getMessage(arguments, "log").getString();
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		return log;
	}
}
