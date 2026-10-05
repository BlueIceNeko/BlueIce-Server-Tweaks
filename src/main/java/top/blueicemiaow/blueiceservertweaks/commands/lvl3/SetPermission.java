package top.blueicemiaow.blueiceservertweaks.commands.lvl3;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.Lists;
import top.blueicemiaow.blueiceservertweaks.tools.CommandHelper;
import top.blueicemiaow.blueiceservertweaks.tools.PermissionHelper;
import top.blueicemiaow.blueiceservertweaks.tools.TranslationHelper;

public class SetPermission {
    private static final String SET = "set";
    private static final String UPDATE = "update_permission";
    private static final String GET = "get";
    private static final String PERMISSION = "permission";
    private static final String PLAYER = "player";

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
        LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("setperm").requires(Commands.hasPermission(Commands.LEVEL_ADMINS)).then(Commands.argument(PLAYER, EntityArgument.player()).then(Commands.literal(BlueIceServerTweaks.WILDCARD).then(Commands.argument(PERMISSION, BoolArgumentType.bool()).executes(arguments -> {
            ServerPlayer player = EntityArgument.getPlayer(arguments, PLAYER);
            boolean permission = BoolArgumentType.getBool(arguments, PERMISSION);
            for (String command : Lists.commands) {
                PermissionHelper.set(player, command, permission);
            }
            CommandHelper.log(arguments, "%s%s%s%b".formatted(CommandHelper.getEntityInfos(player), TranslationHelper.get("of_all"), TranslationHelper.get(SET), permission), TranslationHelper.get(UPDATE));
            return 0;
        }))));
        for (String command : Lists.commands) {
            builder.then(Commands.argument(PLAYER, EntityArgument.player()).then(Commands.literal(command).executes(arguments -> {
                ServerPlayer player = EntityArgument.getPlayer(arguments, PLAYER);
                CommandHelper.log(arguments, "%s%s%s%s%b".formatted(CommandHelper.getEntityInfos(player), TranslationHelper.get("of"), command, TranslationHelper.get(GET), PermissionHelper.get(player, command)));
                return 0;
            }).then(Commands.argument(PERMISSION, BoolArgumentType.bool()).executes(arguments -> {
                ServerPlayer player = EntityArgument.getPlayer(arguments, PLAYER);
                boolean permission = BoolArgumentType.getBool(arguments, PERMISSION);
                PermissionHelper.set(player, command, permission);
                CommandHelper.log(arguments, "%s%s%s%s%b".formatted(CommandHelper.getEntityInfos(player), TranslationHelper.get("of"), command, TranslationHelper.get(SET), permission), TranslationHelper.get(UPDATE));
                return 0;
            }))));
        }
        dispatcher.register(builder);
    }
}
