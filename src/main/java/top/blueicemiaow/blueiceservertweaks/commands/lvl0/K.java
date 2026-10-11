package top.blueicemiaow.blueiceservertweaks.commands.lvl0;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.tools.CommandHelper;
import top.blueicemiaow.blueiceservertweaks.tools.PermissionHelper;
import top.blueicemiaow.blueiceservertweaks.tools.Tick;
import top.blueicemiaow.blueiceservertweaks.tools.TranslationHelper;

import java.util.HashMap;
import java.util.UUID;

public class K {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
        dispatcher.register(Commands.literal("k").executes(arguments -> {
            if (arguments.getSource().getEntity() instanceof ServerPlayer player && PermissionHelper.check(player, "k")) {
                HashMap<UUID, Integer> map = Tick.k;
                UUID uuid = player.getUUID();
                if (map.containsKey(uuid)) {
                    map.remove(uuid);
                    player.kill(player.level());
                    BlueIceServerTweaks.WRAPPER.info("%s%s".formatted(CommandHelper.getEntityInfos(player), TranslationHelper.get("killed")));
                } else {
                    map.put(uuid, 100);
                    player.sendSystemMessage(Component.literal(TranslationHelper.get("tick")), false);
                }
            }
            return 0;
        }));
    }
}
