package top.blueicemiaow.blueiceservertweaks.tools;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.level.gamerules.GameRules;
import top.blueicemiaow.blueiceservertweaks.mixins.GossipTypeMixin;

public class Post {
    public static void majorPositive(boolean enabled) {
        GossipTypeMixin majorPositive = (GossipTypeMixin) (Object) GossipType.MAJOR_POSITIVE;
        int value = enabled ? 100 : 20;
        majorPositive.setMax(value);
        majorPositive.setDecayPerTransfer(value);
    }

    public static void debugGamerule(ServerLevel level, boolean enabled) {
        MinecraftServer server = level.getServer();
        GameRules gamerules = level.getGameRules();
        gamerules.set(GameRules.ADVANCE_TIME, !enabled, server);
        gamerules.set(GameRules.ADVANCE_WEATHER, !enabled, server);
        gamerules.set(GameRules.KEEP_INVENTORY, enabled, server);
        gamerules.set(GameRules.RESPAWN_RADIUS, enabled ? 0 : 10, server);
        if (enabled) {
            level.dimensionTypeRegistration().value().defaultClock().ifPresent(clock -> level.clockManager().setTotalTicks(clock, 6000));
            level.resetWeatherCycle();
            server.setDifficulty(Difficulty.HARD, true);
        }
    }
}
