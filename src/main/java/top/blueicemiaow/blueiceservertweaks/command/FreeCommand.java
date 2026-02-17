
package top.blueicemiaow.blueiceservertweaks.command;

import top.blueicemiaow.blueiceservertweaks.procedures.FreeToLocationRunProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.FreeToEntityRunProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.FreeRunProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.FreeBackRunProcedure;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandBuildContext;

import com.mojang.brigadier.CommandDispatcher;

public class FreeCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
		dispatcher.register(Commands.literal("free")

				.executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					FreeRunProcedure.execute(x, y, z, entity);
					return 0;
				}).then(Commands.literal("back").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					FreeBackRunProcedure.execute(world, x, y, z, entity);
					return 0;
				})).then(Commands.literal("to").then(Commands.argument("entity", EntityArgument.entity()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					FreeToEntityRunProcedure.execute(arguments, entity);
					return 0;
				})).then(Commands.argument("location", BlockPosArgument.blockPos()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					FreeToLocationRunProcedure.execute(arguments, entity);
					return 0;
				}))));
	}
}
