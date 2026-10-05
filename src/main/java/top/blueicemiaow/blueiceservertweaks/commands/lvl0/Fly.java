package top.blueicemiaow.blueiceservertweaks.commands.lvl0;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.tools.CommandHelper;
import top.blueicemiaow.blueiceservertweaks.tools.PermissionHelper;
import top.blueicemiaow.blueiceservertweaks.tools.TranslationHelper;

public class Fly {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
        dispatcher.register(Commands.literal("fly").then(Commands.argument("enabled", BoolArgumentType.bool()).executes(arguments -> {
            if (arguments.getSource().getEntity() instanceof ServerPlayer player && PermissionHelper.check(player, "fly")) {
                boolean enabled = BoolArgumentType.getBool(arguments, "enabled");
                if (player.gameMode.getGameModeForPlayer() == GameType.CREATIVE) {
                    player.getAbilities().mayfly = true;
                } else {
                    player.getAbilities().mayfly = enabled;
                    if (enabled) {
                        BlueIceServerTweaks.WRAPPER.info("%s%s".formatted(CommandHelper.getEntityInfos(player), TranslationHelper.get("fly_enabled")));
                    } else {
                        player.getAbilities().flying = false;
                        BlueIceServerTweaks.WRAPPER.info("%s%s".formatted(CommandHelper.getEntityInfos(player), TranslationHelper.get("fly_disabled")));
                    }
                }
                player.onUpdateAbilities();
            }
            return 0;
        })));
    }
}