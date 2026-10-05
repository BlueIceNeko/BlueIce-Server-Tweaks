package top.blueicemiaow.blueiceservertweaks.tools;

import com.google.gson.Gson;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigHelper {
    public static final String PREFIX = "config";
    public static final String CFG = "config/bist.json";
    public static final String CFG_CREATE_FATAL = "Unable to create config file!";
    public static final String CFG_READ_FATAL = "Unable to read config file!";
    public static final String CFG_WRITE_FATAL = "Unable to write config file!";

    public static boolean get(String function) {
        return BlueIceServerTweaks.config != null && BlueIceServerTweaks.config.get(function);
    }

    public static void set(String function, boolean enabled) {
        BlueIceServerTweaks.config.set(function, enabled);
    }

    public static void read() {
        Path path = Paths.get(PREFIX);
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                BlueIceServerTweaks.WRAPPER.error(CFG_CREATE_FATAL);
                e.printStackTrace();
                return;
            }
        }
        Path file = Paths.get(CFG);
        if (!Files.exists(file)) {
            BlueIceServerTweaks.config = Config.defaultConfig;
            write();
        }
        String json;
        try {
            json = Files.readString(file);
        } catch (IOException e) {
            BlueIceServerTweaks.WRAPPER.fatal(CFG_READ_FATAL);
            e.printStackTrace();
            throw new RuntimeException(CFG_READ_FATAL);
        }
        Gson gson = new Gson();
        BlueIceServerTweaks.config = gson.fromJson(json, Config.class);
    }

    public static void write() {
        Path file = Paths.get(CFG);
        if (!Files.exists(file)) {
            try {
                Files.createFile(file);
            } catch (IOException e) {
                BlueIceServerTweaks.WRAPPER.fatal(CFG_CREATE_FATAL);
                e.printStackTrace();
                throw new RuntimeException(CFG_CREATE_FATAL);
            }
        }
        Gson gson = new Gson();
        String json = gson.toJson(BlueIceServerTweaks.config);
        try {
            Files.writeString(file, json);
        } catch (IOException e) {
            BlueIceServerTweaks.WRAPPER.fatal(CFG_WRITE_FATAL);
            e.printStackTrace();
            throw new RuntimeException(CFG_WRITE_FATAL);
        }
    }
}
