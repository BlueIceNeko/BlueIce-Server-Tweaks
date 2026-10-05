package top.blueicemiaow.blueiceservertweaks.commands.lvl0;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.server.level.ServerPlayer;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.tools.CommandHelper;
import top.blueicemiaow.blueiceservertweaks.tools.PermissionHelper;
import top.blueicemiaow.blueiceservertweaks.tools.TranslationHelper;

public class Goto {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
        dispatcher.register(Commands.literal("goto").then(Commands.argument("location", BlockPosArgument.blockPos()).executes(arguments -> {
            if (arguments.getSource().getEntity() instanceof ServerPlayer player && PermissionHelper.check(player, "goto")) {
                float x = 0.5f, z = 0.5f;
                int y;
                try {
                    x += BlockPosArgument.getLoadedBlockPos(arguments, "location").getX();
                } catch (CommandSyntaxException e) {
                    e.printStackTrace();
                    return 0;
                }
                try {
                    z += BlockPosArgument.getLoadedBlockPos(arguments, "location").getZ();
                } catch (CommandSyntaxException e) {
                    e.printStackTrace();
                    return 0;
                }
                try {
                    y = BlockPosArgument.getLoadedBlockPos(arguments, "location").getY();
                } catch (CommandSyntaxException e) {
                    e.printStackTrace();
                    return 0;
                }
                player.teleportTo(x, y, z);
                player.connection.teleport(x, y, z, player.getYRot(), player.getXRot());
                BlueIceServerTweaks.WRAPPER.info("%s%s%s".formatted(CommandHelper.getEntityInfos(player), TranslationHelper.get("goto"), CommandHelper.getLocationString(x, y, z)));
            }
            return 0;
        })));
    }
}
