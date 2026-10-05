package top.blueicemiaow.blueiceservertweaks.commands.lvl2;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.tools.CommandHelper;
import top.blueicemiaow.blueiceservertweaks.tools.TranslationHelper;

public class GM {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
        dispatcher.register(Commands.literal("gm").requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS)).then(Commands.argument("gamemode", StringArgumentType.word()).executes(arguments -> {
            if (arguments.getSource().getEntity() instanceof ServerPlayer player) {
                String gamemode = StringArgumentType.getString(arguments, "gamemode");
                if (gamemode.equals("0") || gamemode.equals("s") || gamemode.equals(GameType.SURVIVAL.getName())) {
                    player.setGameMode(GameType.SURVIVAL);
                    log(player, GameType.SURVIVAL);
                } else if (gamemode.equals("1") || gamemode.equals("c") || gamemode.equals(GameType.CREATIVE.getName())) {
                    player.setGameMode(GameType.CREATIVE);
                    log(player, GameType.CREATIVE);
                } else if (gamemode.equals("2") || gamemode.equals("a") || gamemode.equals(GameType.ADVENTURE.getName())) {
                    player.setGameMode(GameType.ADVENTURE);
                    log(player, GameType.ADVENTURE);
                } else if (gamemode.equals("3") || gamemode.equals(GameType.SPECTATOR.getName())) {
                    player.setGameMode(GameType.SPECTATOR);
                    log(player, GameType.SPECTATOR);
                } else {
                    player.sendSystemMessage(Component.literal("%s%s".formatted(TranslationHelper.get("unknown_gamemode"), gamemode)), false);
                }
            }
            return 0;
        })));
    }

    public static void log(ServerPlayer player, GameType gamemode) {
        BlueIceServerTweaks.WRAPPER.info("%s%s%s".formatted(CommandHelper.getEntityInfos(player), TranslationHelper.get("gm"), gamemode.getName()));
    }
}
