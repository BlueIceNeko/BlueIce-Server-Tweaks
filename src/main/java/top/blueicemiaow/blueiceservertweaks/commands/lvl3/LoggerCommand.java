package top.blueicemiaow.blueiceservertweaks.commands.lvl3;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.blueicemiaow.blueiceservertweaks.api.LoggerWrapper;
import top.blueicemiaow.blueiceservertweaks.tools.CommandHelper;

import java.util.HashMap;

public class LoggerCommand {
    public static final Logger logger = LogManager.getLogger();
    public static final LoggerWrapper console = new LoggerWrapper(logger, "CONSOLE");
    public static final HashMap<String, LoggerWrapper> wrappers = new HashMap<>();

    public static LoggerWrapper getWrapper(CommandContext<CommandSourceStack> arguments) {
        Entity entity = arguments.getSource().getEntity();
        return entity == null ? console : wrappers.computeIfAbsent(CommandHelper.getEntityInfos(entity), info -> new LoggerWrapper(logger, info));
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
