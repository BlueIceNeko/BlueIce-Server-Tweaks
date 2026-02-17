package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.entity.Entity;

public class GetAllProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		GetOSProcedure.execute(entity);
		GetJVMProcedure.execute(entity);
		GetMEMProcedure.execute(entity);
		GetMCProcedure.execute(entity);
	}
}
