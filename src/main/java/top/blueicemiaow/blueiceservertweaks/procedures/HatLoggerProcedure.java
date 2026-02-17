package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.BlueiceServerTweaksMod;

import net.minecraft.world.entity.Entity;

public class HatLoggerProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		BlueiceServerTweaksMod.LOGGER.info((PlayerLoggerProcedure.execute(entity) + " updated its hat"));
	}
}
