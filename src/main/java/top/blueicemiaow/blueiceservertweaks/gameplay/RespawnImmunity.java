package top.blueicemiaow.blueiceservertweaks.gameplay;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;

public class RespawnImmunity {
    public static void tick(ServerLevel level) {
        for (ServerPlayer player : level.players()) {
            if (map.containsKey(player)) {
                map.replace(player, map.get(player) - 1);
                if (map.get(player) < 1) {
                    map.remove(player);
                }
            }
        }
    }

    public static HashMap<ServerPlayer, Integer> map = new HashMap<>();
}
