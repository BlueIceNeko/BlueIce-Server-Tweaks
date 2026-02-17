
package top.blueicemiaow.blueiceservertweaks.command;

import top.blueicemiaow.blueiceservertweaks.procedures.GRunProcedure;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandBuildContext;

import com.mojang.brigadier.CommandDispatcher;

public class GCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
		dispatcher.register(Commands.literal("g").requires(s -> s.hasPermission(2)).then(Commands.argument("item", ItemArgument.item(commandBuildContext)).executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			GRunProcedure.execute(arguments, entity);
			return 0;
		})));
	}
}
