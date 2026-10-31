package top.blueicemiaow.blueiceservertweaks.api;

import net.minecraft.core.BlockPos;
import top.blueicemiaow.blueiceservertweaks.gameplay.FletchingTable;

import java.util.*;

public class References {
    // Dynamic
    public static final HashMap<UUID, HashMap<String, Boolean>> permissions = new HashMap<>();
    public static final HashMap<String, LoggerWrapper> wrappers = new HashMap<>();
    public static final HashSet<UUID> fly = new HashSet<>();
    public static final HashMap<UUID, Integer> k = new HashMap<>();
    public static final HashMap<UUID, Integer> respawnImmunity = new HashMap<>();
    public static final HashMap<BlockPos, BlockPos> bonemealPos = new HashMap<>();
    public static final HashMap<UUID, Integer> arrowPayloads = new HashMap<>();
    public static final HashMap<BlockPos, Integer> bookCounts = new HashMap<>();

    // Static
    public static final HashSet<String> commands = new HashSet<>(Set.of(
            "compass",
            "craft",
            "fly",
            "goto",
            "hat",
            "head",
            "k",
            "name",
            "painting",
            "sudo"
    ));
    public static final HashMap<String, FletchingTable.Stew> stews = new HashMap<>(Map.ofEntries(
            Map.entry("minecraft:night_vision",         new FletchingTable.Stew("minecraft:night_vision",    3600)),
            Map.entry("minecraft:long_night_vision",    new FletchingTable.Stew("minecraft:night_vision",    9600)),
            Map.entry("minecraft:invisibility",         new FletchingTable.Stew("minecraft:invisibility",    3600)),
            Map.entry("minecraft:long_invisibility",    new FletchingTable.Stew("minecraft:invisibility",    9600)),
            Map.entry("minecraft:leaping",              new FletchingTable.Stew("minecraft:leaping",         3600)),
            Map.entry("minecraft:long_leaping",         new FletchingTable.Stew("minecraft:leaping",         9600)),
            Map.entry("minecraft:fire_resistance",      new FletchingTable.Stew("minecraft:fire_resistance", 3600)),
            Map.entry("minecraft:long_fire_resistance", new FletchingTable.Stew("minecraft:fire_resistance", 9600)),
            Map.entry("minecraft:swiftness",            new FletchingTable.Stew("minecraft:swiftness",       3600)),
            Map.entry("minecraft:long_swiftness",       new FletchingTable.Stew("minecraft:swiftness",       9600)),
            Map.entry("minecraft:slowness",             new FletchingTable.Stew("minecraft:slowness",        1800)),
            Map.entry("minecraft:long_slowness",        new FletchingTable.Stew("minecraft:slowness",        4800)),
            Map.entry("minecraft:water_breathing",      new FletchingTable.Stew("minecraft:water_breathing", 3600)),
            Map.entry("minecraft:long_water_breathing", new FletchingTable.Stew("minecraft:water_breathing", 9600)),
            Map.entry("minecraft:healing",              new FletchingTable.Stew("minecraft:healing",         0)),
            Map.entry("minecraft:harming",              new FletchingTable.Stew("minecraft:harming",         0)),
            Map.entry("minecraft:poison",               new FletchingTable.Stew("minecraft:poison",          900)),
            Map.entry("minecraft:long_poison",          new FletchingTable.Stew("minecraft:poison",          1800)),
            Map.entry("minecraft:regeneration",         new FletchingTable.Stew("minecraft:regeneration",    900)),
            Map.entry("minecraft:long_regeneration",    new FletchingTable.Stew("minecraft:regeneration",    1800)),
            Map.entry("minecraft:strength",             new FletchingTable.Stew("minecraft:strength",        3600)),
            Map.entry("minecraft:long_strength",        new FletchingTable.Stew("minecraft:strength",        9600)),
            Map.entry("minecraft:weakness",             new FletchingTable.Stew("minecraft:weakness",        1800)),
            Map.entry("minecraft:long_weakness",        new FletchingTable.Stew("minecraft:weakness",        4800)),
            Map.entry("minecraft:slow_falling",         new FletchingTable.Stew("minecraft:slow_falling",    1800)),
            Map.entry("minecraft:long_slow_falling",    new FletchingTable.Stew("minecraft:slow_falling",    4800)),
            Map.entry("minecraft:luck",                 new FletchingTable.Stew("minecraft:luck",            6000)),
            Map.entry("minecraft:wind_charged",         new FletchingTable.Stew("minecraft:wind_charged",    3600)),
            Map.entry("minecraft:weaving",              new FletchingTable.Stew("minecraft:weaving",         3600)),
            Map.entry("minecraft:oozing",               new FletchingTable.Stew("minecraft:oozing",          3600)),
            Map.entry("minecraft:infested",             new FletchingTable.Stew("minecraft:infested",        3600))
    ));
    public static final HashMap<String, Translation> translations = new HashMap<>();
}
