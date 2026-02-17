package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.BlueiceServerTweaksMod;

import net.minecraft.world.entity.Entity;

public class FreeToLoggerProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String enabled = "";
		BlueiceServerTweaksMod.LOGGER.info(("Server free camera teleport request from " + PlayerLoggerProcedure.execute(entity) + " has been passed to /goto"));
	}
}
