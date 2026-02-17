package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.Translation;
import top.blueicemiaow.blueiceservertweaks.Lists;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class GetMEMProcedure {
	public GetMEMProcedure() {
		String[] memL = {"en_us", "zh_cn"};
		String[] memV = {"==== Memory ====", "==== 内存 ===="};
		Translation mem = new Translation(memL, memV);
		Lists.addTranslation("mem", mem);
		String[] maxL = {"en_us", "zh_cn"};
		String[] maxV = {"Max: ", "最大值："};
		Translation max = new Translation(maxL, maxV);
		Lists.addTranslation("max", max);
		String[] totalL = {"en_us", "zh_cn"};
		String[] totalV = {"Total: ", "总计："};
		Translation total = new Translation(totalL, totalV);
		Lists.addTranslation("total", total);
		String[] freeL = {"en_us", "zh_cn"};
		String[] freeV = {"Free: ", "空闲："};
		Translation free = new Translation(freeL, freeV);
		Lists.addTranslation("free", free);
		String[] usedL = {"en_us", "zh_cn"};
		String[] usedV = {"Used: ", "已使用："};
		Translation used = new Translation(usedL, usedV);
		Lists.addTranslation("used", used);
		String[] percentageL = {"en_us", "zh_cn"};
		String[] percentageV = {"Percentage: ", "百分比："};
		Translation percentage = new Translation(percentageL, percentageV);
		Lists.addTranslation("percentage", percentage);
	}

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
			_player.displayClientMessage(Component.literal(Lists.getTranslation("max").get() + (max / 1024) + "KB"), false);
			_player.displayClientMessage(Component.literal(Lists.getTranslation("total").get() + (total / 1024) + "KB"), false);
			_player.displayClientMessage(Component.literal(Lists.getTranslation("free").get() + (free / 1024) + "KB"), false);
			_player.displayClientMessage(Component.literal(Lists.getTranslation("used").get() + (used / 1024) + "KB"), false);
			_player.displayClientMessage(Component.literal(Lists.getTranslation("percentage").get() + percentage + "%"), false);
		}
	}
}
