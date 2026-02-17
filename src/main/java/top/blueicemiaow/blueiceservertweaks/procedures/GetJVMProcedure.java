package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.Translation;
import top.blueicemiaow.blueiceservertweaks.Lists;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class GetJVMProcedure {
	public GetJVMProcedure() {
		String[] javaL = {"en_us", "zh_cn"};
		String[] javaV = {"==== Java Virtual Machine ====", "==== Java虚拟机 ===="};
		Translation java = new Translation(javaL, javaV);
		Lists.addTranslation("java", java);
		String[] vm_nameL = {"en_us", "zh_cn"};
		String[] vm_nameV = {"Name: ", "名称："};
		Translation vm_name = new Translation(vm_nameL, vm_nameV);
		Lists.addTranslation("vm_name", vm_name);
		String[] versionL = {"en_us", "zh_cn"};
		String[] versionV = {"Version: ", "版本："};
		Translation version = new Translation(versionL, versionV);
		Lists.addTranslation("version_j", version);
		String[] vendorL = {"en_us", "zh_cn"};
		String[] vendorV = {"Vendor: ", "供应商："};
		Translation vendor = new Translation(vendorL, vendorV);
		Lists.addTranslation("vendor", vendor);
	}

	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide()) {
			_player.displayClientMessage(Component.literal(Lists.getTranslation("java").get()), false);
			_player.displayClientMessage(Component.literal(Lists.getTranslation("vm_name").get() + System.getProperty("java.vm.name")), false);
			_player.displayClientMessage(Component.literal(Lists.getTranslation("version_j").get() + System.getProperty("java.version")), false);
			_player.displayClientMessage(Component.literal(Lists.getTranslation("vendor").get() + System.getProperty("java.vendor")), false);
		}
	}
}
