package top.blueicemiaow.blueiceservertweaks;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.BlockPos;
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

public class BlueIceServerTweaks implements ModInitializer {
    public static final String MODID = "blueice_server_tweaks";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String PREFIX = "BIST";
    public static final LoggerWrapper WRAPPER = new LoggerWrapper(LOGGER, PREFIX);
    public static final String WILDCARD = "*";

    public static final TagKey<Block> EMPTY_BLOCK = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODID, "empty"));
    public static final TagKey<Block> ENCHANTMENT_POWER_PROVIDER = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODID, "enchantment_power_provider"));
    public static final TagKey<Block> COLLECTABLE = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MODID, "collectable"));

    public static final TagKey<Item> EMPTY_ITEM = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODID, "empty"));
    public static final TagKey<Item> CAN_DISABLE_SHIELD = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODID, "can_disable_shield"));
    public static final TagKey<Item> PIGLIN_SAFE_ARMOR = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODID, "piglin_safe_armor"));

    public static Config config = null;
    public static HashMap<String, HashMap<String, Boolean>> permissions = new HashMap<>();
    public static HashMap<String, LoggerWrapper> wrappers = new HashMap<>();
    public static HashMap<BlockPos, Integer> bookshelfBooks = new HashMap<>();

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
