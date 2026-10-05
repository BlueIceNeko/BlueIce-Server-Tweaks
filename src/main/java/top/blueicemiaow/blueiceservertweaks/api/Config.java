package top.blueicemiaow.blueiceservertweaks.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Config {
    public Config(
            HashMap<String, Boolean> functions,
            String language
    ) {
        this.functions = functions;
        this.language = language;
    }

    public static final String enhanced_can_disable_shield = "enhanced_can_disable_shield";
    public static final String enhanced_is_wearing_safe_armor = "enhanced_is_wearing_safe_armor";
    public static final String enhanced_end_portal = "enhanced_end_portal";
    public static final String prevent_rotten_flesh_drop = "prevent_rotten_flesh_drop";
    public static final String prevent_nether_portal_break = "prevent_nether_portal_break";
    public static final String prevent_straw_bed_break = "prevent_straw_bed_break";
    public static final String prevent_copper_oxidize = "prevent_copper_oxidize";
    public static final String prevent_farmland_be_trampled = "prevent_farmland_be_trampled";
    public static final String prevent_respawn_point_produce_fire = "prevent_respawn_point_produce_fire";
    public static final String prevent_cushion_item_entity_produce_vibration = "prevent_cushion_item_entity_produce_vibration";
    public static final String remove_enchantment_power_limit = "remove_enchantment_power_limit";
    public static final String remove_crop_light_restriction = "remove_crop_light_restriction";
    public static final String remove_player_movement_bound = "remove_player_movement_bound";
    public static final String tripwire_hook_fix = "tripwire_hook_fix";
    public static final String respawn_immunity_fix = "respawn_immunity_fix";
    public static final String end_ring_fix = "end_ring_fix";
    public static final String enderman_passenger_fix = "enderman_passenger_fix";
    public static final String enderman_teleport_fix = "enderman_teleport_fix";
    public static final String shulker_teleport_fix = "shulker_teleport_fix";
    public static final String villager_major_positive_fix = "villager_major_positive_fix";
    public static final String debug_gamerule = "debug_gamerule";
    public static final String fletching_table_set_stew = "fletching_table_set_stew";
    public static final String fletching_table_tip_arrow = "fletching_table_tip_arrow";
    public static final String fletching_table_crossbow_assemble_barrel = "fletching_table_crossbow_assemble_barrel";
    public static final String straw_bed_explode = "straw_bed_explode";
    public static final String carpet_transmit_enchantment_power = "carpet_transmit_enchantment_power";
    public static final String larger_beacon_range = "larger_beacon_range";
    public static final String armor_stand_show_arms = "armor_stand_show_arms";
    public static final String powerful_bone_meal = "powerful_bone_meal";
    public static final String hoe_harvest_crop = "hoe_harvest_crop";
    public static final String hoe_recycle_ender_eye = "hoe_recycle_ender_eye";
    public static final String hoe_collect_block = "hoe_collect_block";
    public static final String chiseled_bookshelf_provide_enchantment_power = "chiseled_bookshelf_provide_enchantment_power";
    public static final String permission_system = "permission_system";
    public static final String sudo = "sudo";

    public static final HashSet<String> experiments = new HashSet<>(Set.of(
            enhanced_end_portal,
            hoe_collect_block,
            chiseled_bookshelf_provide_enchantment_power
    ));

    public static final HashSet<String> requiresRestart = new HashSet<>(Set.of(
            permission_system,
            sudo
    ));

    public static final HashSet<String> hasPost = new HashSet<>(Set.of(
            villager_major_positive_fix,
            debug_gamerule
    ));

    public static final Config defaultConfig = new Config(
            new HashMap<>(Map.ofEntries(
                    Map.entry(enhanced_can_disable_shield, false),
                    Map.entry(enhanced_is_wearing_safe_armor, false),
                    Map.entry(enhanced_end_portal, false),
                    Map.entry(prevent_rotten_flesh_drop, false),
                    Map.entry(prevent_nether_portal_break, false),
                    Map.entry(prevent_straw_bed_break, false),
                    Map.entry(prevent_copper_oxidize, false),
                    Map.entry(prevent_farmland_be_trampled, false),
                    Map.entry(prevent_respawn_point_produce_fire, false),
                    Map.entry(prevent_cushion_item_entity_produce_vibration, false),
                    Map.entry(remove_enchantment_power_limit, false),
                    Map.entry(remove_crop_light_restriction, false),
                    Map.entry(remove_player_movement_bound, false),
                    Map.entry(tripwire_hook_fix, false),
                    Map.entry(respawn_immunity_fix, false),
                    Map.entry(end_ring_fix, false),
                    Map.entry(enderman_passenger_fix, false),
                    Map.entry(enderman_teleport_fix, false),
                    Map.entry(shulker_teleport_fix, false),
                    Map.entry(villager_major_positive_fix, false),
                    Map.entry(debug_gamerule, false),
                    Map.entry(fletching_table_set_stew, false),
                    Map.entry(fletching_table_tip_arrow, false),
                    Map.entry(fletching_table_crossbow_assemble_barrel, false),
                    Map.entry(straw_bed_explode, false),
                    Map.entry(carpet_transmit_enchantment_power, false),
                    Map.entry(larger_beacon_range, false),
                    Map.entry(armor_stand_show_arms, false),
                    Map.entry(powerful_bone_meal, false),
                    Map.entry(hoe_harvest_crop, false),
                    Map.entry(hoe_recycle_ender_eye, false),
                    Map.entry(hoe_collect_block, false),
                    Map.entry(chiseled_bookshelf_provide_enchantment_power, false),
                    Map.entry(permission_system, true),
                    Map.entry(sudo, true)
            )),
            Translation.defaultLanguage
    );

    public HashMap<String, Boolean> functions;

    public boolean get(String function) {
        if (this.functions.containsKey(function)) {
            return this.functions.get(function);
        }
        if (defaultConfig.functions.containsKey(function)) {
            return defaultConfig.get(function);
        }
        return false;
    }

    public void set(String function, boolean enabled) {
        this.functions.put(function, enabled);
    }

    public String language;
}
