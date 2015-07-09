package com.minnymin.util.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandContext {

	private CommandSender sender;
	private Command command;
	private String label;
	private String[] options;
	
	public CommandContext(CommandSender sender, Command command, String label, String[] options) {
		this.sender = sender;
		this.command = command;
		this.label = label;
		this.options = options;
	}
	
	public String getLabel() {
		return this.label;
	}

	public String[] getOptions() {
		return this.options;
	}
	
	public void setOptions(String label) {
		int subLength = label.split("\\.").length - 1;
		String[] newOptions = new String[options.length - subLength];
		for (int i = 0; i < options.length - subLength; i++) {
			newOptions[i] = options[i + subLength];
		}
		options = newOptions;
	}

	public CommandSender getSender() {
		return sender;
	}
	
	public Command getCommand() {
		return command;
	}
	
	public boolean isPlayer() {
		return this.getSender() instanceof Player;
	}
	
}
