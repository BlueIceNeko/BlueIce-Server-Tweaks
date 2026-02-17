package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.BlueiceServerTweaksMod;

import net.minecraft.world.entity.Entity;

public class GotoLoggerProcedure {
	public static void execute(Entity entity, String xyz) {
		if (entity == null || xyz == null)
			return;
		BlueiceServerTweaksMod.LOGGER.info((PlayerLoggerProcedure.execute(entity) + " teleported to: " + xyz));
	}
}
