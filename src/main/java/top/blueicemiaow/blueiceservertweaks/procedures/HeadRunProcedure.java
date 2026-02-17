package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.TagParser;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

public class HeadRunProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		String skullOwner = "";
		if (entity.hasPermissions(2) || new Object() {
			public int getScore(String score, Entity _ent) {
				Scoreboard _sc = _ent.level().getScoreboard();
				Objective _so = _sc.getObjective(score);
				if (_so != null)
					return _sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).getScore();
				return 0;
			}
		}.getScore("head", entity) == 1) {
			skullOwner = StringArgumentType.getString(arguments, "playerName");
			if (entity instanceof Player _player) {
				ItemStack head = new ItemStack(Blocks.PLAYER_HEAD);
				String headSnbt_ = "{SkullOwner:" + skullOwner + "}";
				CompoundTag headNbt_ = new CompoundTag();
				try {
					headNbt_ = TagParser.parseTag(headSnbt_);
				} catch (CommandSyntaxException e) {
					e.printStackTrace();
				}
				head.setTag(headNbt_);
				head.setCount(1);
				_player.getInventory().add(head);
			}
			HeadLoggerProcedure.execute(entity, skullOwner);
		}
	}
}
