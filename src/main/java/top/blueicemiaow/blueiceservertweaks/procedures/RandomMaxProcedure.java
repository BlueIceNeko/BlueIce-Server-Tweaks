package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import java.util.Random;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.IntegerArgumentType;

public class RandomMaxProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		int max = 0;
		String result = "";
		if (RandomCheckProcedure.execute(entity)) {
			Random rng = new Random();
			max = IntegerArgumentType.getInteger(arguments, "max");
			if (max > 0) {
				result = "" + rng.nextInt(max);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(result), false);
				RandomLoggerProcedure.execute(entity, result);
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Invalid argument."), false);
			}
		}
	}
}
