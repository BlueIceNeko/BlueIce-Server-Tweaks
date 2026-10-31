package top.blueicemiaow.blueiceservertweaks.tools;

import com.google.gson.Gson;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.api.References;

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

    public static boolean check(ServerPlayer player, String command) {
        return !ConfigHelper.get(Config.permission_system) || Commands.hasPermission(Commands.LEVEL_GAMEMASTERS).test(player.createCommandSourceStack()) || get(player, command);
    }

    public static boolean get(ServerPlayer player, String command) {
        init(player);
        return References.permissions.get(player.getUUID()).getOrDefault(command, false);
    }

    public static void set(ServerPlayer player, String command, boolean permission) {
        init(player);
        References.permissions.get(player.getUUID()).put(command, permission);
        write(player);
    }

    public static void init(ServerPlayer player) {
        if (!References.permissions.containsKey(player.getUUID())) {
            read(player);
        }
    }

    @SuppressWarnings("unchecked")
    public static void read(ServerPlayer player) {
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
        String info = CommandHelper.getEntityInfos(player);
        Path file = Paths.get("%s/%s%s".formatted(PREFIX, info, SUFFIX));
        if (!Files.exists(file)) {
            References.permissions.put(player.getUUID(), new HashMap<>());
            return;
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
        References.permissions.put(player.getUUID(), gson.fromJson(json, HashMap.class));
    }

    public static void write(ServerPlayer player) {
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
        String info = CommandHelper.getEntityInfos(player);
        Path file = Paths.get("%s/%s%s".formatted(PREFIX, info, SUFFIX));
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
        String json = gson.toJson(References.permissions.get(player.getUUID()));
        try {
            Files.writeString(file, json);
        } catch (IOException e) {
            BlueIceServerTweaks.WRAPPER.error(PERM_WRITE_FATAL);
            e.printStackTrace();
        }
    }
}
