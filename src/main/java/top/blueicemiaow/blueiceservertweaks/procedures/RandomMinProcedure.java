package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;

import java.util.Random;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.IntegerArgumentType;

public class RandomMinProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		int max = 0;
		int min = 0;
		String result = "";
		if (RandomCheckProcedure.execute(entity)) {
			Random rng = new Random();
			max = IntegerArgumentType.getInteger(arguments, "max");
			min = IntegerArgumentType.getInteger(arguments, "min");
			if (max > min) {
				result = "" + (rng.nextInt(max - min) + min);
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal(("" + (rng.nextInt(max - min) + min))), false);
				RandomLoggerProcedure.execute(entity, result);
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Invalid argument."), false);
			}
		}
	}
}
