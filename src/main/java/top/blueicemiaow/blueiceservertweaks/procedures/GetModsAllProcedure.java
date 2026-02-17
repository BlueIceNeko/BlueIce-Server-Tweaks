package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.Translation;
import top.blueicemiaow.blueiceservertweaks.Lists;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.FabricLoader;

import java.util.ArrayList;

public class GetModsAllProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide()) {
			_player.displayClientMessage(Component.literal(Lists.getTranslation("mods").get()), false);
			ArrayList<ModContainer> mods = new ArrayList<>(FabricLoader.getInstance().getAllMods());
			int count = 0;
			for (ModContainer mod : mods) {
				count++;
				_player.displayClientMessage(Component.literal(mod.getMetadata().getId() + " " + mod.getMetadata().getVersion().getFriendlyString() + " (" + mod.getMetadata().getType() + ")"), false);
			}
			_player.displayClientMessage(Component.literal(Lists.getTranslation("count").get() + count), false);
		}
	}
}
