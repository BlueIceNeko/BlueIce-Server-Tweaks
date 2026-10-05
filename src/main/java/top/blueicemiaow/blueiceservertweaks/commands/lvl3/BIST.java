package top.blueicemiaow.blueiceservertweaks.commands.lvl3;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.tools.Post;
import top.blueicemiaow.blueiceservertweaks.tools.CommandHelper;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;
import top.blueicemiaow.blueiceservertweaks.tools.TranslationHelper;

public class BIST {
    private static final String SET = "set";
    private static final String UPDATE = "update";
    private static final String ENABLED = "enabled";
    private static final String LANGUAGE = "language";

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
        LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("bist").requires(Commands.hasPermission(Commands.LEVEL_ADMINS)).then(Commands.literal(LANGUAGE).then(Commands.argument(LANGUAGE, StringArgumentType.word()).executes(arguments -> {
            BlueIceServerTweaks.config.language = StringArgumentType.getString(arguments, LANGUAGE);
            ConfigHelper.write();
            CommandHelper.log(arguments, "%s%s%s".formatted(LANGUAGE, TranslationHelper.get(SET), BlueIceServerTweaks.config.language), TranslationHelper.get(UPDATE));
            return 0;
        }))).then(Commands.literal("reload").executes(arguments -> {
            ConfigHelper.read();
            CommandHelper.log(arguments, TranslationHelper.get("read_config"), TranslationHelper.get(UPDATE));
            return 0;
        })).then(Commands.literal(BlueIceServerTweaks.WILDCARD).then(Commands.argument(ENABLED, BoolArgumentType.bool()).executes(arguments -> {
            boolean enabled = BoolArgumentType.getBool(arguments, ENABLED);
            for (String function : Config.defaultConfig.functions.keySet()) {
                set(arguments, function, enabled);
            }
            return 0;
        })));
        for (String function : Config.defaultConfig.functions.keySet()) {
            builder.then(Commands.literal(function).then(Commands.argument(ENABLED, BoolArgumentType.bool()).executes(arguments -> {
                boolean enabled = BoolArgumentType.getBool(arguments, ENABLED);
                set(arguments, function, enabled);
                return 0;
            })));
        }
        dispatcher.register(builder);
    }

    public static void set(CommandContext<CommandSourceStack> arguments, String function, boolean enabled) {
        ConfigHelper.set(function, enabled);
        ConfigHelper.write();
        CommandHelper.log(arguments, "%s(%s)%s%b".formatted(TranslationHelper.get(function), function, TranslationHelper.get(SET), ConfigHelper.get(function)), TranslationHelper.get(UPDATE));
        if (Config.experiments.contains(function)) {
            CommandHelper.log(arguments, TranslationHelper.get("experiment"));
        }
        if (Config.requiresRestart.contains(function)) {
            CommandHelper.log(arguments, TranslationHelper.get("require_restart"));
        }
        if (Config.hasPost.contains(function)) {
            switch (function) {
                case Config.villager_major_positive_fix -> Post.majorPositive(enabled);
                case Config.debug_gamerule -> Post.debugGamerule(arguments.getSource().getLevel(), enabled);
            }
        }
    }
}
