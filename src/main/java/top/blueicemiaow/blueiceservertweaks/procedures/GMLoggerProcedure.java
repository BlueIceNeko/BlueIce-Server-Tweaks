package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.BlueiceServerTweaksMod;

import net.minecraft.world.entity.Entity;

public class GMLoggerProcedure {
	public static void execute(Entity entity, String gamemode) {
		if (entity == null || gamemode == null)
			return;
		BlueiceServerTweaksMod.LOGGER.info((PlayerLoggerProcedure.execute(entity) + " changed gamemode to: " + gamemode));
	}
}
