package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.BlueiceServerTweaksMod;

import net.minecraft.world.entity.Entity;

public class NameLoggerProcedure {
	public static void execute(Entity entity, String name) {
		if (entity == null || name == null)
			return;
		BlueiceServerTweaksMod.LOGGER.info((PlayerLoggerProcedure.execute(entity) + " renamed mainhand item to: " + name));
	}
}
