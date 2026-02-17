package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.BlueiceServerTweaksMod;

import net.minecraft.world.entity.Entity;

public class FreeLoggerProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String enabled = "";
		BlueiceServerTweaksMod.LOGGER.info((PlayerLoggerProcedure.execute(entity) + " enabled server free camera"));
	}
}
