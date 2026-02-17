package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.client.Minecraft;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class FreeToEntityRunProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		Entity target = null;
		String gotoCommand = "";
		if (new Object() {
			public boolean checkGamemode(Entity entity) {
				if (entity instanceof ServerPlayer _serverPlayer) {
					return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
				} else if (entity.level().isClientSide() && entity instanceof Player _player) {
					return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SPECTATOR;
				}
				return false;
			}
		}.checkGamemode(entity)) {
			target = new Object() {
				public Entity getEntity() {
					try {
						return EntityArgument.getEntity(arguments, "entity");
					} catch (CommandSyntaxException e) {
						e.printStackTrace();
						return null;
					}
				}
			}.getEntity();
			gotoCommand = "goto " + Math.round(target.getX()) + " " + Math.round(target.getY()) + " " + Math.round(target.getZ());
			if (!entity.level().isClientSide() && entity.getServer() != null) {
				entity.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, entity.position(), entity.getRotationVector(), entity.level() instanceof ServerLevel ? (ServerLevel) entity.level() : null,
						entity.hasPermissions(2) ? 2 : 0, entity.getName().getString(), entity.getDisplayName(), entity.level().getServer(), entity), gotoCommand);
			}
			FreeToLoggerProcedure.execute(entity);
		}
	}
}
