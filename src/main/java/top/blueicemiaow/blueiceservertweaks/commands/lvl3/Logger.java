package top.blueicemiaow.blueiceservertweaks.commands.lvl3;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.Entity;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.LoggerWrapper;
import top.blueicemiaow.blueiceservertweaks.tools.CommandHelper;

public class Logger {
    public static LoggerWrapper getWrapper(CommandContext<CommandSourceStack> arguments) {
        String info = arguments.getSource().getEntity() instanceof Entity entity ? CommandHelper.getEntityInfos(entity) : "CONSOLE";
        if (BlueIceServerTweaks.wrappers.containsKey(info)) {
            return BlueIceServerTweaks.wrappers.get(info);
        }
        LoggerWrapper wrapper = new LoggerWrapper(BlueIceServerTweaks.LOGGER, info);
        BlueIceServerTweaks.wrappers.put(info, wrapper);
        return wrapper;
    }

    public static String getLog(CommandContext<CommandSourceStack> arguments) {
        return StringArgumentType.getString(arguments, "log");
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
        dispatcher.register(Commands.literal("logger").requires(Commands.hasPermission(Commands.LEVEL_ADMINS)).then(Commands.literal("info").then(Commands.argument("log", StringArgumentType.greedyString()).executes(arguments -> {
            getWrapper(arguments).info(getLog(arguments));
            return 0;
        }))).then(Commands.literal("warn").then(Commands.argument("log", StringArgumentType.greedyString()).executes(arguments -> {
            getWrapper(arguments).warn(getLog(arguments));
            return 0;
        }))).then(Commands.literal("error").then(Commands.argument("log", StringArgumentType.greedyString()).executes(arguments -> {
            getWrapper(arguments).error(getLog(arguments));
            return 0;
        }))).then(Commands.argument("log", StringArgumentType.greedyString()).executes(arguments -> {
            getWrapper(arguments).info(getLog(arguments));
            return 0;
        })));
    }
}
