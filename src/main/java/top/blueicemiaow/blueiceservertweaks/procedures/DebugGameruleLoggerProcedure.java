package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.BlueiceServerTweaksMod;

import net.minecraft.world.entity.Entity;

public class DebugGameruleLoggerProcedure {
	public static void execute(Entity entity, boolean enabled) {
		if (entity == null)
			return;
		String message = "";
		if (enabled) {
			message = " enabled";
		} else {
			message = " disabled";
		}
		message = message + " debug gamerules";
		BlueiceServerTweaksMod.LOGGER.info((PlayerLoggerProcedure.execute(entity) + "" + message));
	}
}
