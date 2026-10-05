package top.blueicemiaow.blueiceservertweaks.commands.lvl0;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import top.blueicemiaow.blueiceservertweaks.tools.PermissionHelper;

public class Craft {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
        dispatcher.register(Commands.literal("craft").executes(arguments -> {
            if (arguments.getSource().getEntity() instanceof ServerPlayer player && PermissionHelper.check(player, "craft")) {
                BlockPos blockPos = BlockPos.containing(arguments.getSource().getPosition());
                player.openMenu(new SimpleMenuProvider((container, inventory, entity) -> {
                    return new CraftingMenu(container, inventory, ContainerLevelAccess.create(player.level(), blockPos)) {
                        @Override
                        public boolean stillValid(Player player) {
                            return true;
                        }
                    };
                }, Component.translatable("container.crafting")));
            }
            return 0;
        }));
    }
}