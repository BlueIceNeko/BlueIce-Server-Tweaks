package top.blueicemiaow.blueiceservertweaks.commands.lvl0;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.component.LodestoneTracker;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.tools.CommandHelper;
import top.blueicemiaow.blueiceservertweaks.tools.PermissionHelper;
import top.blueicemiaow.blueiceservertweaks.tools.TranslationHelper;

import java.util.Arrays;
import java.util.Optional;

public class Compass {
    public static void run(ServerPlayer player, BlockPos location, String name) {
        if (player.getMainHandItem().is(Items.COMPASS)) {
            String locationString = CommandHelper.getLocationString(location);
            player.getMainHandItem().set(DataComponents.LODESTONE_TRACKER, new LodestoneTracker(Optional.of(GlobalPos.of(player.level().dimension(), location)), false));
            player.getMainHandItem().set(DataComponents.LORE, new ItemLore(Arrays.asList(Component.literal(TranslationHelper.get("location") + locationString))));
            if (name != null) {
                player.getMainHandItem().set(DataComponents.CUSTOM_NAME, Component.literal(name));
            }
            player.getInventory().setChanged();
            BlueIceServerTweaks.WRAPPER.info("%s%s%s".formatted(CommandHelper.getEntityInfos(player), TranslationHelper.get("get"), locationString));
        }
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
        dispatcher.register(Commands.literal("compass").then(Commands.argument("location", BlockPosArgument.blockPos()).then(Commands.argument("name", StringArgumentType.greedyString()).executes(arguments -> {
            if (arguments.getSource().getEntity() instanceof ServerPlayer player && PermissionHelper.check(player, "compass")) {
                run(player, BlockPosArgument.getLoadedBlockPos(arguments, "location"), StringArgumentType.getString(arguments, "name"));
            }
            return 0;
        })).executes(arguments -> {
            if (arguments.getSource().getEntity() instanceof ServerPlayer player && PermissionHelper.check(player, "compass")) {
                run(player, BlockPosArgument.getLoadedBlockPos(arguments, "location"), null);
            }
            return 0;
        })));
    }
}
