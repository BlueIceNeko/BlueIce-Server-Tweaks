package top.blueicemiaow.blueiceservertweaks.procedures;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.TagParser;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.commands.CommandSourceStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class PaintingRunProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		String id = "";
		if (entity.hasPermissions(2) || new Object() {
			public int getScore(String score, Entity _ent) {
				Scoreboard _sc = _ent.level().getScoreboard();
				Objective _so = _sc.getObjective(score);
				if (_so != null)
					return _sc.getOrCreatePlayerScore(_ent.getScoreboardName(), _so).getScore();
				return 0;
			}
		}.getScore("painting", entity) == 1) {
			try {
				id = MessageArgument.getMessage(arguments, "id").getString();
			} catch (CommandSyntaxException e) {
				e.printStackTrace();
				return;
			}
			if (entity instanceof Player _player) {
				ItemStack painting = new ItemStack(Items.PAINTING);
				String paintingSnbt_ = "{EntityTag:{variant:\"" + id + "\"}}";
				CompoundTag paintingNbt_ = new CompoundTag();
				try {
					paintingNbt_ = TagParser.parseTag(paintingSnbt_);
				} catch (CommandSyntaxException e) {
					e.printStackTrace();
					return;
				}
				painting.setTag(paintingNbt_);
				painting.setCount(1);
				_player.getInventory().add(painting);
			}
			PaintingLoggerProcedure.execute(entity, id);
		}
	}
}
