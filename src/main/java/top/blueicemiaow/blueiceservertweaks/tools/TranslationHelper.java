package top.blueicemiaow.blueiceservertweaks.tools;

import top.blueicemiaow.blueiceservertweaks.BlueIceServerTweaks;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.api.Lists;
import top.blueicemiaow.blueiceservertweaks.api.Translation;

public class TranslationHelper {
    public static void init() {
        // Base
        add("read_config", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Config read.", "配置已读取。"}
        ));
        add("write_config", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Config written.", "配置已写入。"}
        ));
        add("register_commands", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Commands registered.", "命令已注册。"}
        ));
        add("register_events", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Events registered.", "事件已注册。"}
        ));
        add("register_tick", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Tick operations registered.", "刻操作已注册。"}
        ));
        add("register_post", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Post operations registered.", "事务后操作已注册。"}
        ));

        // Bist
        add("set", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {" is set to: ", "已设置为："}
        ));
        add("update", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {" updated config.", "已更新配置。"}
        ));
        add("get", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {" now is: ", "现在是："}
        ));
        add("experiment", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"This function is an experiment and may not run correctly.", "此功能为实验性内容且可能不会正确运行。"}
        ));
        add("require_restart", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"This function requires server restart to be fully loaded.", "此功能需要服务器重启以完整加载。"}
        ));

        // Compass
        add("location", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Location: ", "位置："}
        ));
        add("get", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {" got compass, pointing at: ", "获取了指南针，指向："}
        ));

        // Fly
        add("fly_enabled", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {" enabled fly ability.", "已启用飞行能力。"}
        ));
        add("fly_disabled", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {" disabled fly ability.", "已禁用飞行能力。"}
        ));

        // G
        add("g", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {" obtained item: ", "已获取物品："}
        ));

        // GetInfos - cpu
        add("cpu", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"==== CPU ====", "==== 中央处理器 ===="}
        ));
        add("name", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Name: ", "名称："}
        ));
        add("core", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"PP/PC/LC: ", "PP/PC/LC："}
        ));
        add("freq", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Frequency: ", "频率："}
        ));

        // GetInfos - os
        add("os", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"==== Operating System ====", "==== 操作系统 ===="}
        ));
        add("version", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Version: ", "版本："}
        ));
        add("uptime", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Uptime: ", "运行时长："}
        ));

        // GetInfos - mem
        add("mem", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"==== Memory ====", "==== 内存 ===="}
        ));
        add("max", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Max: ", "最大值："}
        ));
        add("total", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Total: ", "总计："}
        ));
        add("free", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Free: ", "空闲："}
        ));
        add("used", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Used: ", "已使用："}
        ));
        add("percentage", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Percentage: ", "百分比："}
        ));

        // GetInfos - jvm
        add("jvm", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"==== Java Virtual Machine ====", "==== Java虚拟机 ===="}
        ));
        add("vendor", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Vendor: ", "供应商："}
        ));

        // GetInfos - mc
        add("mc", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"==== Minecraft ====", "==== Minecraft ===="}
        ));

        // GetInfos - mods
        add("mods", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"==== Mods ====", "==== 模组 ===="}
        ));
        add("count", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Mod Count: ", "模组数量："}
        ));

        // GM
        add("gm", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {" set gamemode: ", "已切换游戏模式："}
        ));
        add("unknown_gamemode", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Unknown gamemode: ", "未知游戏模式："}
        ));

        // Goto
        add("goto", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {" teleported to: ", "已传送至："}
        ));

        // Head
        add("get_head", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {" got ", "获取了"}
        ));
        add("of_head", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"'s head.", "的头。"}
        ));

        // K
        add("killed", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {" killed itself.", "自杀了。"}
        ));
        add("tick", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Issue /k again in 5 seconds to kill yourself.", "在5秒内再次执行/k以自杀。"}
        ));

        // SetPermission
        add("of", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"'s ", "的"}
        ));
        add("of_all", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"'s every command", "的所有命令"}
        ));
        add("update_permission", new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {" updated permission.", "已更新权限。"}
        ));

        // Functions
        add(Config.enhanced_can_disable_shield, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Enhanced disable blocking calculation", "增强的阻止格挡计算"}
        ));
        add(Config.enhanced_is_wearing_safe_armor, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Enhanced piglin safe armor judgment", "增强的防猪灵盔甲判定"}
        ));
        add(Config.enhanced_end_portal, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Enhanced end portal activation", "增强的末地门激活"}
        ));
        add(Config.prevent_rotten_flesh_drop, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Prevent rotten flesh item entity spawn", "阻止腐肉掉落物生成"}
        ));
        add(Config.prevent_nether_portal_break, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Prevent nether portal break", "阻止地狱门破碎"}
        ));
        add(Config.prevent_straw_bed_break, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Prevent straw bed break", "阻止麦秆床破碎"}
        ));
        add(Config.prevent_copper_oxidize, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Prevent copper block family oxidize", "阻止铜块系列氧化"}
        ));
        add(Config.prevent_farmland_be_trampled, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Prevent farmland be trampled", "阻止耕地被踩坏"}
        ));
        add(Config.prevent_respawn_point_produce_fire, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Prevent bad respawn point produce fire", "阻止无效重生点起火"}
        ));
        add(Config.prevent_cushion_item_entity_produce_vibration, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Prevent cushion item entity produce vibration", "阻止坐垫物品实体震动"}
        ));
        add(Config.remove_enchantment_power_limit, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Remove enchantment power limit", "移除附魔能量上限"}
        ));
        add(Config.remove_crop_light_restriction, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Remove crop light restriction", "移除作物光照限制"}
        ));
        add(Config.remove_player_movement_bound, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Remove player movement bound", "移除玩家移动边界"}
        ));
        add(Config.tripwire_hook_fix, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Reintroduce old tripwire hook behavior", "绊线钩行为回退"}
        ));
        add(Config.respawn_immunity_fix, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Reintroduce respawn immunity time", "重生无敌时间回退"}
        ));
        add(Config.end_ring_fix, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Reintroduce old end island generation", "末地岛屿生成回退"}
        ));
        add(Config.enderman_passenger_fix, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Reintroduce old enderman passenger hurt behavior", "末影人乘客受伤行为回退"}
        ));
        add(Config.enderman_teleport_fix, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Reintroduce old enderman random teleport behavior", "末影人随机传送行为回退"}
        ));
        add(Config.shulker_teleport_fix, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Reintroduce old shulker random teleport behavior", "潜影贝随机传送行为回退"}
        ));
        add(Config.villager_major_positive_fix, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Reintroduce old villager major positive calculation", "村民major_positive计算回退"}
        ));
        add(Config.debug_gamerule, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Game rules and world settings for debugging", "用于调试的游戏规则与世界设置"}
        ));
        add(Config.fletching_table_set_stew, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Apply potion in mushroom stew", "在蘑菇煲中下药"}
        ));
        add(Config.fletching_table_tip_arrow, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"New way to make tipped arrow", "制作药箭的新方法"}
        ));
        add(Config.fletching_table_crossbow_assemble_barrel, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Waxed lightning rods as barrels of crossbows", "涂蜡的避雷针作为弩的枪管"}
        ));
        add(Config.straw_bed_explode, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Straw bed can explode in incorrect dimensions", "麦秆床可在其他维度爆炸"}
        ));
        add(Config.carpet_transmit_enchantment_power, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Wool carpet can transmit enchantment power", "羊毛地毯可传输附魔能量"}
        ));
        add(Config.larger_beacon_range, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"larger beacon effect range", "更大的信标效果范围"}
        ));
        add(Config.armor_stand_show_arms, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Armor stand show arms by default", "盔甲架默认展示手臂"}
        ));
        add(Config.powerful_bone_meal, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"More powerful bone meal", "更强大的骨粉"}
        ));
        add(Config.hoe_harvest_crop, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Crop can be harvested using hoes", "作物可用锄收获"}
        ));
        add(Config.hoe_recycle_ender_eye, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Eye of ender can be recycled using hoes", "末影之眼可用锄回收"}
        ));
        add(Config.hoe_collect_block, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Specified block can be collected using hoes", "特定方块可用锄收集"}
        ));
        add(Config.chiseled_bookshelf_provide_enchantment_power, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Chiseled bookshelf can provide enchantment power", "雕纹书架可提供附魔能量"}
        ));
        add(Config.permission_system, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Command permission system", "命令权限系统"}
        ));
        add(Config.sudo, new Translation(
                new String[] {"en_us", "zh_cn"},
                new String[] {"Super user do", "超级用户做"}
        ));
    }

    public static boolean add(String key, Translation translation) {
        if (Lists.translations.containsKey(key)) {
            return false;
        }
        Lists.translations.put(key, translation);
        return true;
    }

    public static boolean del(String key) {
        if (Lists.translations.containsKey(key)) {
            Lists.translations.remove(key);
            return true;
        }
        return false;
    }

    public static String get(String key) {
        if (Lists.translations.containsKey(key)) {
            return Lists.translations.get(key).get(BlueIceServerTweaks.config.language);
        }
        return key;
    }
}
