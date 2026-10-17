package top.blueicemiaow.blueiceservertweaks.tools;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLevelEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.world.InteractionResult;
import top.blueicemiaow.blueiceservertweaks.api.Config;
import top.blueicemiaow.blueiceservertweaks.commands.lvl0.*;
import top.blueicemiaow.blueiceservertweaks.commands.lvl2.*;
import top.blueicemiaow.blueiceservertweaks.commands.lvl3.*;
import top.blueicemiaow.blueiceservertweaks.gameplay.*;

public class Register {
    public static void commands() {
        // OP level 3
        CommandRegistrationCallback.EVENT.register(BIST::register);
        CommandRegistrationCallback.EVENT.register(GetInfos::register);
        CommandRegistrationCallback.EVENT.register(LoggerCommand::register);
        CommandRegistrationCallback.EVENT.register(SetPermission::register);

        //OP level 2
        CommandRegistrationCallback.EVENT.register(G::register);
        CommandRegistrationCallback.EVENT.register(GM::register);

        // Free
        CommandRegistrationCallback.EVENT.register(Compass::register);
        CommandRegistrationCallback.EVENT.register(Craft::register);
        CommandRegistrationCallback.EVENT.register(Fly::register);
        CommandRegistrationCallback.EVENT.register(Goto::register);
        CommandRegistrationCallback.EVENT.register(Hat::register);
        CommandRegistrationCallback.EVENT.register(Head::register);
        CommandRegistrationCallback.EVENT.register(K::register);
        CommandRegistrationCallback.EVENT.register(Name::register);
        CommandRegistrationCallback.EVENT.register(Painting::register);

        if (CommandHelper.isSudoEnabled()) {
            CommandRegistrationCallback.EVENT.register(Sudo::register);
        }
    }

    public static void events() {
        UseBlockCallback.EVENT.register(FletchingTable::tipArrow);
        UseBlockCallback.EVENT.register(FletchingTable::setStew);
        UseBlockCallback.EVENT.register(FletchingTable::modifyCrossbow);
        UseBlockCallback.EVENT.register(Hoe::harvestCrop);
        UseBlockCallback.EVENT.register(Hoe::recycleEye);
        UseBlockCallback.EVENT.register(Hoe::collectBlock);
        UseBlockCallback.EVENT.register(BoneMeal::perform);
    }

    public static void tick() {
        ServerTickEvents.END_LEVEL_TICK.register(Tick::tick);
    }

    public static void post() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            if (ConfigHelper.get(Config.villager_major_positive_fix)) {
                Post.majorPositive(true);
            }
        });
        ServerLevelEvents.LOAD.register((server, level) -> {
            if (ConfigHelper.get(Config.debug_gamerule)) {
                Post.debugGamerule(level, true);
            }
        });
    }
}
