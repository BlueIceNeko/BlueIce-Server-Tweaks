package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.BlueiceServerTweaksMod;

import net.minecraft.world.entity.Entity;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.BoolArgumentType;

public class SetPermissionLoggerProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity, String command) {
		if (entity == null || command == null)
			return;
		String targets = "";
		targets = "[";
		try {
			for (Entity entityiterator : EntityArgument.getEntities(arguments, "player")) {
				targets = targets + PlayerLoggerProcedure.execute(entityiterator) + ", ";
			}
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		targets = targets.substring(0, ((targets).length() - 2)) + "]";
		BlueiceServerTweaksMod.LOGGER.info((PlayerLoggerProcedure.execute(entity) + " set " + command + " permission to: " + BoolArgumentType.getBool(arguments, "permission") + " for: " + targets));
	}
}
