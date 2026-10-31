package top.blueicemiaow.blueiceservertweaks.api;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;

public class Tags {
    public static final TagKey<Block> EMPTY_BLOCK = TagKey.create(               Registries.BLOCK, getIdentifier("empty"));
    public static final TagKey<Block> ENCHANTMENT_POWER_PROVIDER = TagKey.create(Registries.BLOCK, getIdentifier("enchantment_power_provider"));
    public static final TagKey<Block> COLLECTABLE = TagKey.create(               Registries.BLOCK, getIdentifier("collectable"));

    public static final TagKey<Item>  EMPTY_ITEM = TagKey.create(                Registries.ITEM,  getIdentifier("empty"));
    public static final TagKey<Item>  CAN_DISABLE_SHIELD = TagKey.create(        Registries.ITEM,  getIdentifier("can_disable_shield"));
    public static final TagKey<Item>  PIGLIN_SAFE_ARMOR = TagKey.create(         Registries.ITEM,  getIdentifier("piglin_safe_armor"));

    public static Identifier getIdentifier(String key) {
        return Identifier.fromNamespaceAndPath(BlueIceServerTweaks.MODID, key);
    }
}
