package top.blueicemiaow.blueiceservertweaks;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.api.LoggerWrapper;
import top.blueicemiaow.blueiceservertweaks.tools.ConfigHelper;
import top.blueicemiaow.blueiceservertweaks.tools.Register;
import top.blueicemiaow.blueiceservertweaks.tools.TranslationHelper;

import java.util.HashMap;
import java.util.UUID;

public class BlueIceServerTweaks implements ModInitializer {
    public static final String MODID = "blueice_server_tweaks";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String PREFIX = "BIST";
    public static final LoggerWrapper WRAPPER = new LoggerWrapper(LOGGER, PREFIX);
    public static final String WILDCARD = "*";

    public static Config config = null;

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing BlueIce Server Tweaks...");
        TranslationHelper.init();
        ConfigHelper.read();
        WRAPPER.info(TranslationHelper.get("read_config"));
        Register.commands();
        WRAPPER.info(TranslationHelper.get("register_commands"));
        Register.events();
        WRAPPER.info(TranslationHelper.get("register_events"));
        Register.tick();
        WRAPPER.info(TranslationHelper.get("register_tick"));
        Register.post();
        WRAPPER.info(TranslationHelper.get("register_post"));
    }
}
