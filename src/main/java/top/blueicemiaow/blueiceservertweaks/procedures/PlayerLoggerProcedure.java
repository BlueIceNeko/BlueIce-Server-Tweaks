package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.entity.Entity;

public class PlayerLoggerProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		return entity.getDisplayName().getString() + "(" + entity.getStringUUID() + ")";
	}
}
