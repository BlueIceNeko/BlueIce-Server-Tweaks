package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.BlueiceServerTweaksMod;

import net.minecraft.world.entity.Entity;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.BoolArgumentType;

public class GammaLoggerProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		String enabled = "";
		if (BoolArgumentType.getBool(arguments, "enabled")) {
			enabled = "enabled";
		} else {
			enabled = "disabled";
		}
		BlueiceServerTweaksMod.LOGGER.info((PlayerLoggerProcedure.execute(entity) + " " + enabled + " infinite night vision"));
	}
}
