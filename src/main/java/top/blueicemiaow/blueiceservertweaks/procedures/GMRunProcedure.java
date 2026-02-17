package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class GMRunProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		String gamemode = "";
		gamemode = (new Object() {
			public String getMessage() {
				try {
					return MessageArgument.getMessage(arguments, "gamemode").getString();
				} catch (CommandSyntaxException ignored) {
					return "";
				}
			}
		}).getMessage();
		if (gamemode.equals("0") || gamemode.equals("s")) {
			if (entity instanceof ServerPlayer _player)
				_player.setGameMode(GameType.SURVIVAL);
			GMLoggerProcedure.execute(entity, "Survival");
		} else if (gamemode.equals("1") || gamemode.equals("c")) {
			if (entity instanceof ServerPlayer _player)
				_player.setGameMode(GameType.CREATIVE);
			GMLoggerProcedure.execute(entity, "Creative");
		} else if (gamemode.equals("2") || gamemode.equals("a")) {
			if (entity instanceof ServerPlayer _player)
				_player.setGameMode(GameType.ADVENTURE);
			GMLoggerProcedure.execute(entity, "Adventure");
		} else if (gamemode.equals("3")) {
			if (entity instanceof ServerPlayer _player)
				_player.setGameMode(GameType.SPECTATOR);
			GMLoggerProcedure.execute(entity, "Spectator");
		}
	}
}
