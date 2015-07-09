package com.minnymin.zephyr.sponge.spell;

import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;

import com.google.common.base.Optional;
import com.minnymin.util.directive.ArgumentType;
import com.minnymin.util.directive.Directive;
import com.minnymin.zephyr.Zephyr;
import com.minnymin.zephyr.spell.Spell;
import com.minnymin.zephyr.spell.SpellContext;
import com.minnymin.zephyr.spell.SpellManager;
import com.minnymin.zephyr.sponge.SpongeUser;

public class SpellCommand {

	@Directive(names = { "cast" }, argumentLabels = {"spell", "args"}, arguments = {ArgumentType.STRING, ArgumentType.OPTIONAL_REMAINING})
	public static CommandResult onCast(CommandSource src, CommandContext context) {
		SpellManager manager = Zephyr.getSpellManager();
		Spell spell = manager.getSpell(context.<String>getOne("spell").get());
		Optional<String> options = context.<String>getOne("args");
		spell.cast(new SpellContext(new SpongeUser(src), options.isPresent() ? options.get().split(" ") : new String[0]));
		return CommandResult.success();
	}
	
}
