package top.blueicemiaow.blueiceservertweaks.api;

import java.util.*;

public class Lists {
    public static HashSet<String> commands = new HashSet<>(Set.of(
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

    public record Stew(String id, int duration) {}

    public static HashMap<String, Stew> stews = new HashMap<>(Map.ofEntries(
            Map.entry("minecraft:night_vision",         new Stew("minecraft:night_vision",    3600)),
            Map.entry("minecraft:long_night_vision",    new Stew("minecraft:night_vision",    9600)),
            Map.entry("minecraft:invisibility",         new Stew("minecraft:invisibility",    3600)),
            Map.entry("minecraft:long_invisibility",    new Stew("minecraft:invisibility",    9600)),
            Map.entry("minecraft:leaping",              new Stew("minecraft:leaping",         3600)),
            Map.entry("minecraft:long_leaping",         new Stew("minecraft:leaping",         9600)),
            Map.entry("minecraft:fire_resistance",      new Stew("minecraft:fire_resistance", 3600)),
            Map.entry("minecraft:long_fire_resistance", new Stew("minecraft:fire_resistance", 9600)),
            Map.entry("minecraft:swiftness",            new Stew("minecraft:swiftness",       3600)),
            Map.entry("minecraft:long_swiftness",       new Stew("minecraft:swiftness",       9600)),
            Map.entry("minecraft:slowness",             new Stew("minecraft:slowness",        1800)),
            Map.entry("minecraft:long_slowness",        new Stew("minecraft:slowness",        4800)),
            Map.entry("minecraft:water_breathing",      new Stew("minecraft:water_breathing", 3600)),
            Map.entry("minecraft:long_water_breathing", new Stew("minecraft:water_breathing", 9600)),
            Map.entry("minecraft:healing",              new Stew("minecraft:healing",         1)),
            Map.entry("minecraft:harming",              new Stew("minecraft:harming",         1)),
            Map.entry("minecraft:poison",               new Stew("minecraft:poison",          900)),
            Map.entry("minecraft:long_poison",          new Stew("minecraft:poison",          1800)),
            Map.entry("minecraft:regeneration",         new Stew("minecraft:regeneration",    900)),
            Map.entry("minecraft:long_regeneration",    new Stew("minecraft:regeneration",    1800)),
            Map.entry("minecraft:strength",             new Stew("minecraft:strength",        3600)),
            Map.entry("minecraft:long_strength",        new Stew("minecraft:strength",        9600)),
            Map.entry("minecraft:weakness",             new Stew("minecraft:weakness",        1800)),
            Map.entry("minecraft:long_weakness",        new Stew("minecraft:weakness",        4800)),
            Map.entry("minecraft:slow_falling",         new Stew("minecraft:slow_falling",    1800)),
            Map.entry("minecraft:long_slow_falling",    new Stew("minecraft:slow_falling",    4800)),
            Map.entry("minecraft:luck",                 new Stew("minecraft:luck",            6000))
    ));

    public static HashMap<String, Translation> translations = new HashMap<>();
}
