package top.blueicemiaow.blueiceservertweaks.tools;

import com.google.gson.Gson;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.api.Lists;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class PermissionHelper {
    public static final String PREFIX = "config/permissions";
    public static final String SUFFIX = ".json";
    public static final String PERM_CREATE_FATAL = "Unable to create permission file!";
    public static final String PERM_READ_FATAL = "Unable to read permission file!";
    public static final String PERM_WRITE_FATAL = "Unable to write permission file!";

    public static void trim(HashMap<String, Boolean> permission) {
        for (String command : permission.keySet()) {
            if (!Lists.commands.contains(command)) {
                permission.remove(command);
            }
        }
    }

    public static boolean check(ServerPlayer player, String command) {
        return !ConfigHelper.get(Config.permission_system) || Commands.hasPermission(Commands.LEVEL_GAMEMASTERS).test(player.createCommandSourceStack()) || get(player, command);
    }

    public static boolean get(ServerPlayer player, String command) {
        return get(CommandHelper.getEntityInfos(player), command);
    }

    public static void set(ServerPlayer player, String command, boolean permission) {
        set(CommandHelper.getEntityInfos(player), command, permission);
    }

    public static void init(ServerPlayer player) {
        init(CommandHelper.getEntityInfos(player));
    }

    public static Path file(ServerPlayer player) {
        return file(CommandHelper.getEntityInfos(player));
    }

    public static void read(ServerPlayer player) {
        read(CommandHelper.getEntityInfos(player));
    }

    public static void write(ServerPlayer player) {
        write(CommandHelper.getEntityInfos(player));
    }

    private static boolean get(String info, String command) {
        init(info);
        return BlueIceServerTweaks.permissions.get(info).getOrDefault(command, false);
    }

    private static void set(String info, String command, boolean permission) {
        init(info);
        BlueIceServerTweaks.permissions.get(info).put(command, permission);
        trim(BlueIceServerTweaks.permissions.get(info));
        write(info);
    }

    private static void init(String info) {
        if (!BlueIceServerTweaks.permissions.containsKey(info)) {
            read(info);
        }
    }

    private static Path file(String info) {
        return Paths.get("%s/%s%s".formatted(PREFIX, info, SUFFIX));
    }

    @SuppressWarnings("unchecked")
    private static void read(String info) {
        Path path = Paths.get(PREFIX);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                BlueIceServerTweaks.WRAPPER.error(PERM_CREATE_FATAL);
                e.printStackTrace();
                return;
            }
        }
        Path file = file(info);
        if (!Files.exists(file)) {
            HashMap<String, Boolean> permission = new HashMap<>();
            for (String command : Lists.commands) {
                permission.put(command, false);
            }
            BlueIceServerTweaks.permissions.put(info, permission);
            write(info);
        }
        String json;
        try {
            json = Files.readString(file);
        } catch (IOException e) {
            BlueIceServerTweaks.WRAPPER.error(PERM_READ_FATAL);
            e.printStackTrace();
            return;
        }
        Gson gson = new Gson();
        BlueIceServerTweaks.permissions.put(info, gson.fromJson(json, HashMap.class));
        trim(BlueIceServerTweaks.permissions.get(info));
    }

    private static void write(String info) {
        Path file = file(info);
        if (!Files.exists(file)) {
            try {
                Files.createFile(file);
            } catch (IOException e) {
                BlueIceServerTweaks.WRAPPER.error(PERM_CREATE_FATAL);
                e.printStackTrace();
                return;
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(BlueIceServerTweaks.permissions.get(info));
        try {
            Files.writeString(file, json);
        } catch (IOException e) {
            BlueIceServerTweaks.WRAPPER.error(PERM_WRITE_FATAL);
            e.printStackTrace();
        }
    }
}
