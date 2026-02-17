
package top.blueicemiaow.blueiceservertweaks.command;

import top.blueicemiaow.blueiceservertweaks.procedures.GetOSProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.GetModsProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.GetModsAllProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.GetMEMProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.GetMCProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.GetMBProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.GetJVMProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.GetGBProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.GetBProcedure;
import top.blueicemiaow.blueiceservertweaks.procedures.GetAllProcedure;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandBuildContext;

import com.mojang.brigadier.CommandDispatcher;

public class GetInfosCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
		dispatcher.register(Commands.literal("getinfos").requires(s -> s.hasPermission(3)).executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			GetAllProcedure.execute(entity);
			return 0;
		}).then(Commands.literal("all").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			GetAllProcedure.execute(entity);
			return 0;
		})).then(Commands.literal("OS").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			GetOSProcedure.execute(entity);
			return 0;
		})).then(Commands.literal("JVM").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			GetJVMProcedure.execute(entity);
			return 0;
		})).then(Commands.literal("memory").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			GetMEMProcedure.execute(entity);
			return 0;
		}).then(Commands.literal("B").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			GetBProcedure.execute(entity);
			return 0;
		})).then(Commands.literal("KB").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			GetMEMProcedure.execute(entity);
			return 0;
		})).then(Commands.literal("MB").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			GetMBProcedure.execute(entity);
			return 0;
		})).then(Commands.literal("GB").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			GetGBProcedure.execute(entity);
			return 0;
		}))).then(Commands.literal("Minecraft").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			GetMCProcedure.execute(entity);
			return 0;
		})).then(Commands.literal("mods").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			GetModsProcedure.execute(entity);
			return 0;
		}).then(Commands.literal("all").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			GetModsAllProcedure.execute(entity);
			return 0;
		}))));
	}
}
