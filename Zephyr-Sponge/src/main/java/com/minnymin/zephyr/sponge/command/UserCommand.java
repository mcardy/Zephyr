package com.minnymin.zephyr.sponge.command;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.TextBuilder;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;

import com.minnymin.util.directive.Directive;
import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.user.User;

public class UserCommand {

	@Directive(names = { "mana" }, description = "Displays your mana", inGameOnly = true)
	public static CommandResult commandMana(CommandSource src, CommandContext context) {
		User user = Zephyr.getUserManager().getUser(((Player)src).getUniqueId());

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

}
