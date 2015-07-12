package com.minnymin.zephyr.sponge.command;

import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;

import com.google.common.base.Optional;
import com.minnymin.util.directive.ArgumentType;
import com.minnymin.util.directive.Directive;
import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellManager;
import com.minnymin.zephyr.sponge.spell.SpongeSpellContext;

public class SpellCommand {

	@Directive(names = { "cast" }, argumentLabels = {"spell", "args"}, arguments = {ArgumentType.STRING, ArgumentType.OPTIONAL_REMAINING}, inGameOnly = true)
	public static CommandResult onCast(CommandSource src, CommandContext context) {
		Player player = (Player) src;
		SpellManager manager = Zephyr.getSpellManager();
		Spell spell = manager.getSpell(context.<String>getOne("spell").get());
		Optional<String> options = context.<String>getOne("args");
		manager.cast(spell, new SpongeSpellContext(spell, Zephyr.getUserManager().getUser(player.getUniqueId()), options.isPresent() ? options.get().split(" ") : new String[0]));
		return CommandResult.success();
	}
	
}
