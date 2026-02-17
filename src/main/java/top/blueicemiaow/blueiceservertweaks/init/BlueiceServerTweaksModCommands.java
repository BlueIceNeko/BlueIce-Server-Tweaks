
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package top.blueicemiaow.blueiceservertweaks.init;

import top.blueicemiaow.blueiceservertweaks.command.SetPermissionCommand;
import top.blueicemiaow.blueiceservertweaks.command.SetLanguageCommand;
import top.blueicemiaow.blueiceservertweaks.command.RandomCommand;
import top.blueicemiaow.blueiceservertweaks.command.PaintingCommand;
import top.blueicemiaow.blueiceservertweaks.command.NameCommand;
import top.blueicemiaow.blueiceservertweaks.command.LoggerCommand;
import top.blueicemiaow.blueiceservertweaks.command.KCommand;
import top.blueicemiaow.blueiceservertweaks.command.HeadCommand;
import top.blueicemiaow.blueiceservertweaks.command.HatCommand;
import top.blueicemiaow.blueiceservertweaks.command.GotoTeleportCommand;
import top.blueicemiaow.blueiceservertweaks.command.GetInfosCommand;
import top.blueicemiaow.blueiceservertweaks.command.GammaCommand;
import top.blueicemiaow.blueiceservertweaks.command.GMCommand;
import top.blueicemiaow.blueiceservertweaks.command.GCommand;
import top.blueicemiaow.blueiceservertweaks.command.FreeCommand;
import top.blueicemiaow.blueiceservertweaks.command.FlyCommand;
import top.blueicemiaow.blueiceservertweaks.command.DebugGameruleCommand;
import top.blueicemiaow.blueiceservertweaks.command.CraftingCommand;
import top.blueicemiaow.blueiceservertweaks.command.CompassGiveCommand;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class BlueiceServerTweaksModCommands {
	public static void load() {
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			GMCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			GotoTeleportCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			CompassGiveCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			SetPermissionCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			RandomCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			CraftingCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			KCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			DebugGameruleCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			FlyCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			SetLanguageCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			HeadCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			FreeCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			GammaCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			HatCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			GetInfosCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			LoggerCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			PaintingCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			NameCommand.register(dispatcher, commandBuildContext, environment);
		});
		CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext, environment) -> {
			GCommand.register(dispatcher, commandBuildContext, environment);
		});
	}
}
