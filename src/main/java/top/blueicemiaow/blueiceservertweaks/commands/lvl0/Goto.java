package top.blueicemiaow.blueiceservertweaks.commands.lvl0;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.tools.CommandHelper;
import top.blueicemiaow.blueiceservertweaks.tools.PermissionHelper;
import top.blueicemiaow.blueiceservertweaks.tools.TranslationHelper;

public class Goto {
    public static void run(ServerPlayer player, double x, double y, double z, float yaw, float pitch) {
        player.teleportTo(x, y, z);
        player.setYRot(yaw);
        player.setXRot(pitch);
        player.connection.teleport(x, y, z, yaw, pitch);
        BlueIceServerTweaks.WRAPPER.info("%s%s%s".formatted(CommandHelper.getEntityInfos(player), TranslationHelper.get("goto"), CommandHelper.getLocationString(x, y, z)));
    }

    public static void run(ServerPlayer player, double x, double y, double z) {
        run(player, x, y, z, player.getYRot(), player.getXRot());
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
        dispatcher.register(Commands.literal("goto").then(Commands.argument("location", BlockPosArgument.blockPos()).executes(arguments -> {
            if (arguments.getSource().getEntity() instanceof ServerPlayer player && PermissionHelper.check(player, "goto")) {
                try {
                    BlockPos pos = BlockPosArgument.getLoadedBlockPos(arguments, "location");
                    run(player, 0.5 + pos.getX(), pos.getY(), 0.5 + pos.getZ());
                } catch (CommandSyntaxException e) {
                    e.printStackTrace();
                }
            }
            return 0;
        })).then(Commands.argument("target", EntityArgument.entity()).executes(arguments -> {
            if (arguments.getSource().getEntity() instanceof ServerPlayer player && PermissionHelper.check(player, "goto")) {
                try {
                    Entity entity = EntityArgument.getEntity(arguments, "target");
                    run(player, entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());
                } catch (CommandSyntaxException e) {
                    e.printStackTrace();
                }
            }
            return 0;
        })));
    }
}
