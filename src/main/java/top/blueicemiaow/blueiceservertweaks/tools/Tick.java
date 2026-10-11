package top.blueicemiaow.blueiceservertweaks.tools;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.UUID;

public class Tick {
    public static final HashMap<UUID, Integer> respawnImmunity = new HashMap<>();
    public static final HashMap<UUID, Integer> k = new HashMap<>();

    public static void update(HashMap<UUID, Integer> map, UUID uuid) {
        if (map.containsKey(uuid)) {
            map.replace(uuid, map.get(uuid) - 1);
            if (map.get(uuid) < 1) {
                map.remove(uuid);
            }
        }
    }

    public static void tick(ServerLevel level) {
        for (ServerPlayer player : level.players()) {
            UUID uuid = player.getUUID();
            update(respawnImmunity, uuid);
            update(k, uuid);
        }
    }
}
