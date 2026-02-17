package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.Translation;
import top.blueicemiaow.blueiceservertweaks.Lists;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.FabricLoader;

import java.util.Optional;

public class GetMCProcedure {
	public GetMCProcedure() {
		String[] mcL = {"en_us", "zh_cn"};
		String[] mcV = {"==== Minecraft ====", "==== Minecraft ===="};
		Translation mc = new Translation(mcL, mcV);
		Lists.addTranslation("mc", mc);
	}

	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide()) {
			String[] ids = {"minecraft", "fabricloader", "fabric-api", "carpet", "carpet-tis-addition", "gca", "pca-1_20_1", "lithium", "starlight", "blueice_server_tweaks"};
			ModContainer mod = null;
			Optional<ModContainer> _mod = null;
			_player.displayClientMessage(Component.literal(Lists.getTranslation("mc").get()), false);
			for (String id : ids) {
				_mod = FabricLoader.getInstance().getModContainer(id);
				if (_mod.isPresent()) {
					mod = _mod.get();
					_player.displayClientMessage(Component.literal(id + " " + mod.getMetadata().getVersion().getFriendlyString()), false);
				} else {
					_player.displayClientMessage(Component.literal(id + " not found"), false);
				}
			}
		}
	}
}
