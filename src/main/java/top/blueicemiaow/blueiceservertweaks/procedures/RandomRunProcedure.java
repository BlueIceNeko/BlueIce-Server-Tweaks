package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class RandomRunProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String result = "";
		if (RandomCheckProcedure.execute(entity)) {
			result = "" + Math.random();
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(result), false);
			RandomLoggerProcedure.execute(entity, result);
		}
	}
}
