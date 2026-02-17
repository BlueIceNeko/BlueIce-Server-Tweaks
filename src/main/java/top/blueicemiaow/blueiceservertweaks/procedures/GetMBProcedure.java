package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.Translation;
import top.blueicemiaow.blueiceservertweaks.Lists;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class GetMBProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide()) {
			Runtime runtime = Runtime.getRuntime();
			long max = runtime.maxMemory();
			long total = runtime.totalMemory();
			long free = runtime.freeMemory();
			long used = total - free;
			double percentage = Math.round(10000.0 * used / total) / 100.0;
			_player.displayClientMessage(Component.literal(Lists.getTranslation("mem").get()), false);
			_player.displayClientMessage(Component.literal(Lists.getTranslation("max").get() + (max / 1048576) + "MB"), false);
			_player.displayClientMessage(Component.literal(Lists.getTranslation("total").get() + (total / 1048576) + "MB"), false);
			_player.displayClientMessage(Component.literal(Lists.getTranslation("free").get() + (free / 1048576) + "MB"), false);
			_player.displayClientMessage(Component.literal(Lists.getTranslation("used").get() + (used / 1048576) + "MB"), false);
			_player.displayClientMessage(Component.literal(Lists.getTranslation("percentage").get() + percentage + "%"), false);
		}
	}
}
