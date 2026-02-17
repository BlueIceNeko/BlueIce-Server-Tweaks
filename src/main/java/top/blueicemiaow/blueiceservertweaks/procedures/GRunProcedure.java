package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.commands.arguments.item.ItemInput;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class GRunProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player) {
			ItemInput item = ItemArgument.getItem(arguments, "item");
			try {
				_player.getInventory().add(item.createItemStack(item.getItem().getMaxStackSize(), false));
			} catch (CommandSyntaxException e) {
				e.printStackTrace();
			}
			GLoggerProcedure.execute(arguments, entity);
		}
	}
}
