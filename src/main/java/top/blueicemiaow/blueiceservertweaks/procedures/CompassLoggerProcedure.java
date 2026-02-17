package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.BlueiceServerTweaksMod;

import net.minecraft.world.entity.Entity;

public class CompassLoggerProcedure {
	public static void execute(Entity entity, String compass) {
		if (entity == null || compass == null)
			return;
		BlueiceServerTweaksMod.LOGGER.info((PlayerLoggerProcedure.execute(entity) + " generated compass: " + compass));
	}
}
