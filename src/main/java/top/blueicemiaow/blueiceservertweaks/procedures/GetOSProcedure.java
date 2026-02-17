package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.Translation;
import top.blueicemiaow.blueiceservertweaks.Lists;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class GetOSProcedure {
	public GetOSProcedure() {
		String[] osL = {"en_us", "zh_cn"};
		String[] osV = {"==== Operating System ====", "==== 操作系统 ===="};
		Translation os = new Translation(osL, osV);
		Lists.addTranslation("os", os);
		String[] nameL = {"en_us", "zh_cn"};
		String[] nameV = {"Name: ", "名称："};
		Translation name = new Translation(nameL, nameV);
		Lists.addTranslation("name", name);
		String[] versionL = {"en_us", "zh_cn"};
		String[] versionV = {"Version: ", "版本："};
		Translation version = new Translation(versionL, versionV);
		Lists.addTranslation("version", version);
		String[] archL = {"en_us", "zh_cn"};
		String[] archV = {"Architecture: ", "架构："};
		Translation arch = new Translation(archL, archV);
		Lists.addTranslation("arch", arch);
	}

	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide()) {
			_player.displayClientMessage(Component.literal(Lists.getTranslation("os").get()), false);
			_player.displayClientMessage(Component.literal(Lists.getTranslation("name").get() + System.getProperty("os.name")), false);
			_player.displayClientMessage(Component.literal(Lists.getTranslation("version").get() + System.getProperty("os.version")), false);
			_player.displayClientMessage(Component.literal(Lists.getTranslation("arch").get() + System.getProperty("os.arch")), false);
		}
	}
}
