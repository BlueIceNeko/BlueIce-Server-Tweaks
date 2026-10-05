package top.blueicemiaow.blueiceservertweaks.commands.lvl3;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.software.os.OperatingSystem;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.tools.CommandHelper;
import top.blueicemiaow.blueiceservertweaks.tools.TranslationHelper;

import java.util.ArrayList;
import java.util.Optional;

public class GetInfos {
    public static String cpu() {
        return cpu(2);
    }

    public static String cpu(int i) {
        CentralProcessor cpu = new SystemInfo().getHardware().getProcessor();
        int multiplier;
        String unit;
        switch (i) {
            case 0 -> {
                multiplier = 1;
                unit = "Hz";
            }
            case 1 -> {
                multiplier = 1000;
                unit = "KHz";
            }
            case 3 -> {
                multiplier = 1000000000;
                unit = "GHz";
            }
            default -> {
                multiplier = 1000000;
                unit = "MHz";
            }
        }
        return "%s\n%s%s\n%s%d/%d/%d\n%s%.2f%s".formatted(
                TranslationHelper.get("cpu"),
                TranslationHelper.get("name"), cpu.getProcessorIdentifier().getName(),
                TranslationHelper.get("core"), cpu.getPhysicalPackageCount(), cpu.getPhysicalProcessorCount(), cpu.getLogicalProcessorCount(),
                TranslationHelper.get("freq"), 1d * cpu.getMaxFreq() / multiplier, unit
        );
    }

    public static String os() {
        return os(2);
    }

    public static String os(int i) {
        OperatingSystem os = new SystemInfo().getOperatingSystem();
        int multiplier;
        String unit;
        switch (i) {
            case 0 -> {
                multiplier = 1;
                unit = "s";
            }
            case 1 -> {
                multiplier = 60;
                unit = "m";
            }
            case 3 -> {
                multiplier = 86400;
                unit = "d";
            }
            default -> {
                multiplier = 3600;
                unit = "h";
            }
        }
        return "%s\n%s%s\n%s%s\n%s%.2f%s".formatted(
                TranslationHelper.get("os"),
                TranslationHelper.get("name"), os.getFamily(),
                TranslationHelper.get("version"), os.getVersionInfo().toString(),
                TranslationHelper.get("uptime"), 1d * os.getSystemUptime() / multiplier, unit
        );
    }

    public static String mem() {
        return mem(2);
    }

    public static String mem(int i) {
        Runtime runtime = Runtime.getRuntime();
        long max = runtime.maxMemory();
        long total = runtime.totalMemory();
        long free = runtime.freeMemory();
        long used = total - free;
        int multiplier;
        String unit;
        switch (i) {
            case 0 -> {
                multiplier = 1;
                unit = "B";
            }
            case 1 -> {
                multiplier = 1024;
                unit = "KiB";
            }
            case 3 -> {
                multiplier = 1073741824;
                unit = "GiB";
            }
            default -> {
                multiplier = 1048576;
                unit = "MiB";
            }
        }
        return "%s\n%s%.2f%s\n%s%.2f%s\n%s%.2f%s\n%s%.2f%s\n%s%.2f%c".formatted(
                TranslationHelper.get("mem"),
                TranslationHelper.get("max"), 1d * max / multiplier, unit,
                TranslationHelper.get("total"), 1d * total / multiplier, unit,
                TranslationHelper.get("free"), 1d * free / multiplier, unit,
                TranslationHelper.get("used"), 1d * used / multiplier, unit,
                TranslationHelper.get("percentage"), 100d * used / total, '%'
        );
    }

    public static String jvm() {
        return "%s\n%s%s\n%s%s\n%s%s".formatted(
                TranslationHelper.get("jvm"),
                TranslationHelper.get("name"), System.getProperty("java.vm.name"),
                TranslationHelper.get("version"), System.getProperty("java.version"),
                TranslationHelper.get("vendor"), System.getProperty("java.vendor")
        );
    }

    public static String mc() {
        return "%s\n%s\n%s\n%s\n%s".formatted(TranslationHelper.get("mc"),
                mc("minecraft", "Minecraft"),
                mc("fabricloader", "Fabric Loader"),
                mc("fabric-api", "Fabric API"),
                mc(BlueIceServerTweaks.MODID, "BlueIce Server Tweaks")
        );
    }

    public static String mc(String id, String name) {
        Optional<ModContainer> mod = FabricLoader.getInstance().getModContainer(id);
        return "%s %s".formatted(name, mod.isPresent() ? mod.get().getMetadata().getVersion().getFriendlyString() : "not found");
    }

    public static String mods() {
        ArrayList<ModContainer> mods = new ArrayList<>(FabricLoader.getInstance().getAllMods());
        StringBuilder r = new StringBuilder();
        for (ModContainer mod : mods) {
            r.append("%s %s\n".formatted(mod.getMetadata().getId(), mod.getMetadata().getVersion().getFriendlyString()));
        }
        return "%s\n%s%s%d".formatted(TranslationHelper.get("mods"), r.toString(), TranslationHelper.get("count"), mods.size());
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
        dispatcher.register(Commands.literal("getinfos").requires(Commands.hasPermission(Commands.LEVEL_ADMINS)).executes(arguments -> {
            CommandHelper.log(arguments, cpu());
            CommandHelper.log(arguments, os());
            CommandHelper.log(arguments, mem());
            CommandHelper.log(arguments, jvm());
            CommandHelper.log(arguments, mc());
            return 0;
        }).then(Commands.literal("cpu").executes(arguments -> {
            CommandHelper.log(arguments, cpu());
            return 0;
        }).then(Commands.literal("Hz").executes(arguments -> {
            CommandHelper.log(arguments, cpu(0));
            return 0;
        })).then(Commands.literal("KHz").executes(arguments -> {
            CommandHelper.log(arguments, cpu(1));
            return 0;
        })).then(Commands.literal("MHz").executes(arguments -> {
            CommandHelper.log(arguments, cpu(2));
            return 0;
        })).then(Commands.literal("GHz").executes(arguments -> {
            CommandHelper.log(arguments, cpu(3));
            return 0;
        }))).then(Commands.literal("os").executes(arguments -> {
            CommandHelper.log(arguments, os());
            return 0;
        }).then(Commands.literal("s").executes(arguments -> {
            CommandHelper.log(arguments, os(0));
            return 0;
        })).then(Commands.literal("m").executes(arguments -> {
            CommandHelper.log(arguments, os(1));
            return 0;
        })).then(Commands.literal("h").executes(arguments -> {
            CommandHelper.log(arguments, os(2));
            return 0;
        })).then(Commands.literal("d").executes(arguments -> {
            CommandHelper.log(arguments, os(3));
            return 0;
        }))).then(Commands.literal("mem").executes(arguments -> {
            CommandHelper.log(arguments, mem());
            return 0;
        }).then(Commands.literal("B").executes(arguments -> {
            CommandHelper.log(arguments, mem(0));
            return 0;
        })).then(Commands.literal("KiB").executes(arguments -> {
            CommandHelper.log(arguments, mem(1));
            return 0;
        })).then(Commands.literal("MiB").executes(arguments -> {
            CommandHelper.log(arguments, mem(2));
            return 0;
        })).then(Commands.literal("GiB").executes(arguments -> {
            CommandHelper.log(arguments, mem(3));
            return 0;
        }))).then(Commands.literal("jvm").executes(arguments -> {
            CommandHelper.log(arguments, jvm());
            return 0;
        })).then(Commands.literal("mc").executes(arguments -> {
            CommandHelper.log(arguments, mc());
            return 0;
        })).then(Commands.literal("mods").executes(arguments -> {
            CommandHelper.log(arguments, mods());
            return 0;
        })));
    }
}
