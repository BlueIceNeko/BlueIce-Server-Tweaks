package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.BlueiceServerTweaksMod;

import net.minecraft.world.entity.Entity;

public class HeadLoggerProcedure {
	public static void execute(Entity entity, String skullOwner) {
		if (entity == null || skullOwner == null)
			return;
		BlueiceServerTweaksMod.LOGGER.info((PlayerLoggerProcedure.execute(entity) + " got the head of " + skullOwner));
	}
}
