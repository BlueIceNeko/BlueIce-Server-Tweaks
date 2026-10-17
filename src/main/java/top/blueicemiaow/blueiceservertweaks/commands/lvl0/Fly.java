package top.blueicemiaow.blueiceservertweaks.commands.lvl0;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.tools.CommandHelper;
import top.blueicemiaow.blueiceservertweaks.tools.PermissionHelper;
import top.blueicemiaow.blueiceservertweaks.tools.TranslationHelper;

import java.util.HashSet;
import java.util.UUID;

public class Fly {
    public static final HashSet<UUID> fly = new HashSet<>();

    public static void set(ServerPlayer player, boolean enabled) {
        UUID uuid = player.getUUID();
        if (enabled) {
            fly.add(uuid);
        } else {
            fly.remove(uuid);
        }
        update(player, enabled);
    }

    public static void update(ServerPlayer player, boolean enabled) {
        if (!player.isCreative()) {
            player.getAbilities().mayfly = enabled;
            if (enabled) {
                BlueIceServerTweaks.WRAPPER.info("%s%s".formatted(CommandHelper.getEntityInfos(player), TranslationHelper.get("fly_enabled")));
            } else {
                player.getAbilities().flying = false;
                BlueIceServerTweaks.WRAPPER.info("%s%s".formatted(CommandHelper.getEntityInfos(player), TranslationHelper.get("fly_disabled")));
            }
            player.onUpdateAbilities();
        }
    }

    public static void update(ServerPlayer player) {
        update(player, PermissionHelper.check(player, "fly") && Fly.fly.contains(player.getUUID()));
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
        dispatcher.register(Commands.literal("fly").then(Commands.argument("enabled", BoolArgumentType.bool()).executes(arguments -> {
            if (arguments.getSource().getEntity() instanceof ServerPlayer player && PermissionHelper.check(player, "fly")) {
                set(player, BoolArgumentType.getBool(arguments, "enabled"));
            }
            return 0;
        })));
    }
}
