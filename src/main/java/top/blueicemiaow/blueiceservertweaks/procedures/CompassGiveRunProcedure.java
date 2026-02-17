package top.blueicemiaow.blueiceservertweaks.procedures;

import top.blueicemiaow.blueiceservertweaks.Translation;
import top.blueicemiaow.blueiceservertweaks.Lists;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.TagParser;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class CompassGiveRunProcedure {
	public CompassGiveRunProcedure() {
		String[] languages = {"en_us", "zh_cn"};
		String[] values = {"Location", "位置"};
		Translation compass_location = new Translation(languages, values);
		Lists.addTranslation("compass_location", compass_location);
	}

	public static void execute(LevelAccessor world, double x, double y, double z, CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		String dimension = "";
		String locationX = "";
		String locationY = "";
		String locationZ = "";
		String compassName = "";
		String compassSnbt_ = "";
		CompoundTag compassNbt_ = null;
		if (entity.hasPermissions(2) || new Object() {
			public int getScore(String score, Entity _ent) {
				Scoreboard _sc = _ent.level().getScoreboard();
				Objective _so = _sc.getObjective(score);
				if (_so != null)
					return _sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).getScore();
				return 0;
			}
		}.getScore("compass", entity) == 1) {
			if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.COMPASS) {
				if ((entity.level().dimension()) == Level.END) {
					dimension = "minecraft:the_end";
				} else if ((entity.level().dimension()) == Level.NETHER) {
					dimension = "minecraft:the_nether";
				} else {
					dimension = "minecraft:overworld";
				}
				locationX = "" + new Object() {
					public double getX() {
						try {
							return BlockPosArgument.getLoadedBlockPos(arguments, "location").getX();
						} catch (CommandSyntaxException e) {
							e.printStackTrace();
							return 0;
						}
					}
				}.getX();
				locationY = "" + new Object() {
					public double getY() {
						try {
							return BlockPosArgument.getLoadedBlockPos(arguments, "location").getY();
						} catch (CommandSyntaxException e) {
							e.printStackTrace();
							return 0;
						}
					}
				}.getY();
				locationZ = "" + new Object() {
					public double getZ() {
						try {
							return BlockPosArgument.getLoadedBlockPos(arguments, "location").getZ();
						} catch (CommandSyntaxException e) {
							e.printStackTrace();
							return 0;
						}
					}
				}.getZ();
				locationX = locationX.substring(0, locationX.length() - 2);
				locationY = locationY.substring(0, locationY.length() - 2);
				locationZ = locationZ.substring(0, locationZ.length() - 2);
				try {
					compassName = MessageArgument.getMessage(arguments, "name").getString();
				} catch (CommandSyntaxException e) {
					e.printStackTrace();
				}
				compassSnbt_ = "{LodestoneDimension:\"" + dimension + "\",LodestonePos:{X:" + locationX + ",Y:" + locationY + ",Z:" + locationZ + "},LodestoneTracked:0b,display:{Lore:[\"{\\\"text\\\":\\\""
						+ Lists.getTranslation("compass_location").get(Translation.serverLanguage) + ": " + locationX + ", " + locationY + ", " + locationZ + "\\\"}\"],Name:\"{\\\"text\\\":\\\"" + compassName + "\\\"}\"}}";
				try {
					compassNbt_ = TagParser.parseTag(compassSnbt_);
				} catch (CommandSyntaxException e) {
					e.printStackTrace();
				}
				if (entity instanceof LivingEntity _entity) {
					_entity.getMainHandItem().setTag(compassNbt_);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
				CompassLoggerProcedure.execute(entity, compassSnbt_);
			}
		}
	}
}
