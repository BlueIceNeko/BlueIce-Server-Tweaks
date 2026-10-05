package top.blueicemiaow.blueiceservertweaks.tools;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.Config;

public class CommandHelper {
    public static boolean log(Entity entity, String log) {
        BlueIceServerTweaks.WRAPPER.info(log);
        if (entity instanceof ServerPlayer player && !player.level().isClientSide()) {
            player.sendSystemMessage(Component.literal(log), false);
            return true;
        }
        return false;
    }

    public static boolean log(Entity entity, String log, String logPlayer) {
        if (CommandHelper.log(entity, log)) {
            BlueIceServerTweaks.WRAPPER.info(CommandHelper.getEntityInfos(entity) + logPlayer);
            return true;
        }
        return false;
    }

    public static boolean log(CommandContext<CommandSourceStack> arguments, String log) {
        return log(arguments.getSource().getEntity(), log);
    }

    public static boolean log(CommandContext<CommandSourceStack> arguments, String log, String logPlayer) {
        return log(arguments.getSource().getEntity(), log, logPlayer);
    }

    public static boolean isSudoEnabled() {
        return ConfigHelper.get(Config.permission_system) && ConfigHelper.get(Config.sudo);
    }

    public static String getEntityInfos(Entity entity) {
        if (entity == null) {
            return "";
        }
        return "%s(%s)".formatted(entity.getDisplayName().getString(), entity.getStringUUID());
    }

    public static String getLocationString(BlockPos location) {
        return getLocationString(location.getX(), location.getY(), location.getZ());
    }

    public static String getLocationString(int x, int y, int z) {
        return "(%d, %d, %d)".formatted(x, y, z);
    }

    public static String getLocationString(double x, double y, double z) {
        return "(%f, %f, %f)".formatted(x, y, z);
    }
}
