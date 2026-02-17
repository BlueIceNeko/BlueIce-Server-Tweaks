package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.Translation;
import top.blueicemiaow.blueiceservertweaks.Lists;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.FabricLoader;

import java.util.ArrayList;

public class GetModsProcedure {
	public GetModsProcedure() {
		String[] modsL = {"en_us", "zh_cn"};
		String[] modsV = {"==== Mods ====", "==== 模组 ===="};
		Translation mods = new Translation(modsL, modsV);
		Lists.addTranslation("mods", mods);
		String[] countL = {"en_us", "zh_cn"};
		String[] countV = {"Mod Count: ", "模组数量："};
		Translation count = new Translation(countL, countV);
		Lists.addTranslation("count", count);
	}

	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide()) {
			_player.displayClientMessage(Component.literal(Lists.getTranslation("mods").get()), false);
			ArrayList<ModContainer> mods = new ArrayList<>(FabricLoader.getInstance().getAllMods());
			int count = 0;
			for (ModContainer mod : mods) {
				if (!"builtin".equals(mod.getMetadata().getType())) {
					count++;
					_player.displayClientMessage(Component.literal(mod.getMetadata().getId() + " " + mod.getMetadata().getVersion().getFriendlyString()), false);
				}
			}
			_player.displayClientMessage(Component.literal(Lists.getTranslation("count").get() + count), false);
		}
	}
}
