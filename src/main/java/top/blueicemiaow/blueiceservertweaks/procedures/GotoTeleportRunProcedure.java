package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class GotoTeleportRunProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		double gotoX = 0;
		double gotoY = 0;
		double gotoZ = 0;
		if (entity.hasPermissions(2) || new Object() {
			public int getScore(String score, Entity _ent) {
				Scoreboard _sc = _ent.level().getScoreboard();
				Objective _so = _sc.getObjective(score);
				if (_so != null)
					return _sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).getScore();
				return 0;
			}
		}.getScore("goto", entity) == 1) {
			try {
				gotoX = BlockPosArgument.getLoadedBlockPos(arguments, "location").getX() + 0.5;
			} catch (CommandSyntaxException e) {
				e.printStackTrace();
				return;
			}
			try {
				gotoY = BlockPosArgument.getLoadedBlockPos(arguments, "location").getY();
			} catch (CommandSyntaxException e) {
				e.printStackTrace();
				return;
			}
			try {
				gotoZ = BlockPosArgument.getLoadedBlockPos(arguments, "location").getZ() + 0.5;
			} catch (CommandSyntaxException e) {
				e.printStackTrace();
				return;
			}
			entity.teleportTo(gotoX, gotoY, gotoZ);
			if (entity instanceof ServerPlayer _serverPlayer)
				_serverPlayer.connection.teleport(gotoX, gotoY, gotoZ, _serverPlayer.getYRot(), _serverPlayer.getXRot());
			GotoLoggerProcedure.execute(entity, "(" + gotoX + ", " + gotoY + ", " + gotoZ + ")");
		}
	}
}
