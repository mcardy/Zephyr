package com.minnymin.zephyr.sponge.command;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.TextBuilder;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;

import com.minnymin.zephyr.api.Zephyr;
import com.minnymin.zephyr.api.user.User;
import com.minnymin.zephyr.sponge.ZephyrPlugin;
import com.minnymin.zephyr.sponge.util.directive.ArgumentType;
import com.minnymin.zephyr.sponge.util.directive.Directive;

public class UserCommand {

	@Directive(names = { "mana" }, description = "Displays your mana", inGameOnly = true)
	public static CommandResult commandMana(CommandSource src, CommandContext context) {
		User user = Zephyr.getUserManager().getUser(((Player) src).getUniqueId());

		TextBuilder builder = Texts.builder("Mana " + user.getMana() + " / " + user.getMaximumMana() + ": [").color(
				TextColors.GRAY);
		int percent = (int) (((float) user.getMana() / (float) user.getMaximumMana()) * 10);
		TextBuilder tempBuilder = Texts.builder();
		tempBuilder.color(TextColors.AQUA);
		for (int i = 1; i <= 10; i++) {
			tempBuilder.append(Texts.of("="));
			if (i == percent) {
				builder.append(tempBuilder.build());
				tempBuilder = Texts.builder();
			}
		}
		tempBuilder.color(TextColors.DARK_GRAY);
		builder.append(tempBuilder.build());
		builder.append(Texts.of("]")).color(TextColors.GRAY);

		src.sendMessage(builder.build());
		return CommandResult.success();
	}

	@Directive(names = { "progress" }, description = "Displays your progress", inGameOnly = true)
	public static CommandResult commandProgress(CommandSource src, CommandContext context) {
		User user = Zephyr.getUserManager().getUser(((Player) src).getUniqueId());

		TextBuilder builder = Texts.builder(
				"Progress " + user.getUserData().getLevelProgress() + " / " + user.getRequiredLevelProgress() + ": [")
				.color(TextColors.GRAY);
		int percent = (int) (((float) user.getUserData().getLevelProgress() / (float) user.getRequiredLevelProgress()) * 10);
		TextBuilder tempBuilder = Texts.builder();
		tempBuilder.color(TextColors.GREEN);
		for (int i = 1; i <= 10; i++) {
			tempBuilder.append(Texts.of("="));
			if (i == percent) {
				builder.append(tempBuilder.build());
				tempBuilder = Texts.builder();
			}
		}
		tempBuilder.color(TextColors.DARK_GRAY);
		builder.append(tempBuilder.build());
		builder.append(Texts.of("] Level " + user.getUserData().getLevel())).color(TextColors.GRAY);

		src.sendMessage(builder.build());
		return CommandResult.success();
	}

	@Directive(names = { "mana.restore" }, description = "Restores your mana", inGameOnly = true, argumentLabels = { "target" }, arguments = { ArgumentType.OPTIONAL_STRING })
	public static CommandResult commandManaRestore(CommandSource src, CommandContext context) {
		User target = null;
		if (!context.getOne("target").isPresent()) {
			target = Zephyr.getUserManager().getUser(((Player) src).getUniqueId());
		} else {
			Player player = null;
			if ((player = ZephyrPlugin.getGame().getServer().getPlayer(context.<String> getOne("target").get()).get()) != null) {
				target = Zephyr.getUserManager().getUser(player.getUniqueId());
			} else {
				target = Zephyr.getUserManager().getUser(((Player) src).getUniqueId());
			}
		}

		target.setMana(target.getMaximumMana());
		target.<Player> getPlayerObject().sendMessage(Texts.builder("Mana restored!").color(TextColors.AQUA).build());

		return CommandResult.success();
	}

}
