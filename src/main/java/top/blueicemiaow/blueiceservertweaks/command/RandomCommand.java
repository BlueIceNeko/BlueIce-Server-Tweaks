
package top.blueicemiaow.blueiceservertweaks.command;

import top.blueicemiaow.blueiceservertweaks.procedures.RandomRunProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.RandomMinProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.RandomMaxProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.RandomLongProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.RandomIntProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.RandomDoubleProcedure;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandBuildContext;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.CommandDispatcher;

public class RandomCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
		dispatcher.register(Commands.literal("random")

				.executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					RandomRunProcedure.execute(entity);
					return 0;
				}).then(Commands.literal("double").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					RandomDoubleProcedure.execute(entity);
					return 0;
				})).then(Commands.literal("long").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					RandomLongProcedure.execute(entity);
					return 0;
				})).then(Commands.literal("int").executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					RandomIntProcedure.execute(entity);
					return 0;
				}).then(Commands.argument("max", IntegerArgumentType.integer()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					RandomMaxProcedure.execute(arguments, entity);
					return 0;
				}).then(Commands.argument("min", IntegerArgumentType.integer()).executes(arguments -> {
					ServerLevel world = arguments.getSource().getLevel();
					double x = arguments.getSource().getPosition().x();
					double y = arguments.getSource().getPosition().y();
					double z = arguments.getSource().getPosition().z();
					Entity entity = arguments.getSource().getEntity();
					Direction direction = Direction.DOWN;
					if (entity != null)
						direction = entity.getDirection();

					RandomMinProcedure.execute(arguments, entity);
					return 0;
				})))));
	}
}
