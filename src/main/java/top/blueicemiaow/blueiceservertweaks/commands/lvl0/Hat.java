package top.blueicemiaow.blueiceservertweaks.commands.lvl0;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import top.blueicemiaow.blueiceservertweaks.tools.PermissionHelper;

public class Hat {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
        dispatcher.register(Commands.literal("hat").executes(arguments -> {
            if (arguments.getSource().getEntity() instanceof ServerPlayer player && PermissionHelper.check(player, "hat")) {
                ItemStack hat = player.getMainHandItem();
                player.setItemInHand(InteractionHand.MAIN_HAND, player.getItemBySlot(EquipmentSlot.HEAD));
                player.setItemSlot(EquipmentSlot.HEAD, hat);
                player.getInventory().setChanged();
            }
            return 0;
        }));
    }
}
