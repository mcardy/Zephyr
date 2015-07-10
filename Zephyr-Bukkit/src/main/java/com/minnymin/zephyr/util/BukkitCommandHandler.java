package com.minnymin.zephyr.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.help.GenericCommandHelpTopic;
import org.bukkit.help.HelpTopic;
import org.bukkit.help.HelpTopicComparator;
import org.bukkit.help.IndexHelpTopic;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;

public class BukkitCommandHandler implements CommandExecutor {

	protected Map<String, BukkitExecutor> cmdMap;
	protected Plugin plugin;
	protected CommandMap map;

	public BukkitCommandHandler(Plugin plugin) {
		cmdMap = new HashMap<String, BukkitExecutor>();
		this.plugin = plugin;

		if (plugin.getServer().getPluginManager() instanceof SimplePluginManager) {
			SimplePluginManager manager = (SimplePluginManager) plugin
					.getServer().getPluginManager();
			try {
				Field field = SimplePluginManager.class
						.getDeclaredField("commandMap");
				field.setAccessible(true);
				map = (CommandMap) field.get(manager);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void addCommand(String label, BukkitExecutor executor) {
		cmdMap.put(label, executor);
		cmdMap.put(this.plugin.getName() + ':' + label, executor);
		
		String baseLabel = label.replace(".", ",").split(",")[0].toLowerCase();
		if (map.getCommand(baseLabel) == null) {
			org.bukkit.command.Command cmd = new BukkitCommandWrapper(baseLabel, this, this.plugin);
			map.register(this.plugin.getName(), cmd);
		}
		if (!executor.getCmd().description().equalsIgnoreCase("") && baseLabel == label) {
			map.getCommand(baseLabel).setDescription(executor.getCmd().description());
		}
		if (!executor.getCmd().usage().equalsIgnoreCase("") && baseLabel == label) {
			map.getCommand(baseLabel).setUsage(executor.getCmd().usage());
		}
	}
	
	public void addCommand(String[] aliases, BukkitExecutor executor) {
		for (int i = 0; i < aliases.length; i++) {
			addCommand(aliases[i], executor);
		}
	}

	public BukkitExecutor getExecutor(Method m) {
		return new BukkitExecutor(m);
	}

	public BukkitExecutor getExecutor(Method m, Object o) {
		return new BukkitExecutor(m, o);
	}

	public Class<? extends CommandContext> getContextClass() {
		return CommandContext.class;
	}
	
	public void execute(CommandContext args) {
		for (int i = args.getOptions().length; i >= 0; i--) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(args.getLabel().toLowerCase());
			for (int x = 0; x < i; x++) {
				buffer.append("." + args.getOptions()[x].toLowerCase());
			}
			
			String label = buffer.toString();
			
			if (cmdMap.containsKey(label)) {
				args.setOptions(label);
				cmdMap.get(label).execute(args);
				return;
			}
		}
	}
	
	public void registerCommands(Class<?> c) {
		for (int i = 0; i < c.getMethods().length; i++) {
			Method m = c.getMethods()[i];
			Cmd cmd = m.getAnnotation(Cmd.class);
			if (cmd != null) {
				if (m.getParameterTypes().length == 1 && m.getParameterTypes()[0] == getContextClass()) {
					BukkitExecutor executor = getExecutor(m);
					addCommand(cmd.label(), executor);
					addCommand(cmd.aliases(), executor);
				} else {
					System.out.println("WARNING: Commands in " + c.getName() + " are unable to be registered:");
					System.out.println("Method context argument is not of type " + getContextClass().getName());
				}
			}
		}
	}
	
	public void registerCommands(Object obj) {
		Class<?> c = obj.getClass();
		for (int i = 0; i < c.getMethods().length; i++) {
			Method m = c.getMethods()[i];
			Cmd cmd = m.getAnnotation(Cmd.class);
			if (cmd != null) {
				if (m.getParameterTypes().length == 1 && m.getParameterTypes()[0] == getContextClass()) {
					BukkitExecutor executor = getExecutor(m, obj);
					addCommand(cmd.label(), executor);
					addCommand(cmd.aliases(), executor);
				} else {
					System.out.println("WARNING: Commands in " + c.getName() + " are unable to be registered:");
					System.out.println("Command " + m.getName() + "'s context argument is not of type " + getContextClass().getName());
				}
			}
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		CommandContext context = new CommandContext(sender, command, label, args);
		this.execute(context);
		return true;
	}
	
	public void registerHelp() {
		Set<HelpTopic> help = new TreeSet<HelpTopic>(HelpTopicComparator.helpTopicComparatorInstance());
		for (String s : this.cmdMap.keySet()) {
			if (!s.contains(".")) {
				org.bukkit.command.Command cmd = map.getCommand(s);
				HelpTopic topic = new GenericCommandHelpTopic(cmd);
				help.add(topic);
			}
		}
		IndexHelpTopic topic = new IndexHelpTopic(plugin.getName(), "All commands for " + plugin.getName(), null, help,
				"Below is a list of all " + plugin.getName() + " commands:");
		Bukkit.getServer().getHelpMap().addTopic(topic);
	}

	protected class BukkitCommandWrapper extends org.bukkit.command.Command {

		private final Plugin owningPlugin;
		private CommandExecutor executor;

		/**
		 * A slimmed down PluginCommand
		 * 
		 * @param name
		 * @param owner
		 */
		protected BukkitCommandWrapper(String label, CommandExecutor executor, Plugin owner) {
			super(label);
			this.executor = executor;
			this.owningPlugin = owner;
			this.usageMessage = "";
		}

		@Override
		public boolean execute(CommandSender sender, String commandLabel,
				String[] args) {
			boolean success = false;
			if (!owningPlugin.isEnabled()) {
				return false;
			}
			if (!testPermission(sender)) {
				return true;
			}
			try {
				success = executor.onCommand(sender, this, commandLabel, args);
			} catch (Throwable ex) {
				throw new CommandException(
						"Unhandled exception executing command '"
								+ commandLabel + "' in plugin "
								+ owningPlugin.getDescription().getFullName(),
						ex);
			}
			if (!success && usageMessage.length() > 0) {
				for (String line : usageMessage.replace("<command>",
						commandLabel).split("\n")) {
					sender.sendMessage(line);
				}
			}
			return success;
		}

		/*@Override
		public java.util.List<String> tabComplete(CommandSender sender,
				String alias, String[] args) throws CommandException,
				IllegalArgumentException {
			Validate.notNull(sender, "Sender cannot be null");
			Validate.notNull(args, "Arguments cannot be null");
			Validate.notNull(alias, "Alias cannot be null");

			List<String> completions = null;
			try {
				if (completer != null) {
					completions = completer.onTabComplete(sender, this, alias,
							args);
				}
				if (completions == null && executor instanceof TabCompleter) {
					completions = ((TabCompleter) executor).onTabComplete(
							sender, this, alias, args);
				}
			} catch (Throwable ex) {
				StringBuilder message = new StringBuilder();
				message.append(
						"Unhandled exception during tab completion for command '/")
						.append(alias).append(' ');
				for (String arg : args) {
					message.append(arg).append(' ');
				}
				message.deleteCharAt(message.length() - 1)
						.append("' in plugin ")
						.append(owningPlugin.getDescription().getFullName());
				throw new CommandException(message.toString(), ex);
			}

			if (completions == null) {
				return super.tabComplete(sender, alias, args);
			}
			return completions;
		}*/

	}

}
