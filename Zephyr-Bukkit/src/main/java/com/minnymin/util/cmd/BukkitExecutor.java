package com.minnymin.util.cmd;

import java.lang.reflect.Method;

import org.bukkit.entity.Player;

public class BukkitExecutor {

	private Method executor;
	private Object owner;
	
	public BukkitExecutor(Method m) {
		this.executor = m;
	}
	
	public BukkitExecutor(Method m, Object o) {
		this.executor = m;
		this.owner = o;
	}
	
	public void execute(CommandContext args) {
		if (args instanceof CommandContext) {
			CommandContext context = (CommandContext) args;
			
			Permission perm = this.executor.getAnnotation(Permission.class);
			if (perm != null) {
				if (!context.getSender().hasPermission(perm.permission())) {
					context.getSender().sendMessage(perm.message());
					return;
				}
			}
			
			InGameOnly inGame = this.executor.getAnnotation(InGameOnly.class);
			if (inGame != null) {
				if (!(context.getSender() instanceof Player)) {
					context.getSender().sendMessage(inGame.message());
					return;
				}
			}
			
			ConsoleOnly console = this.executor.getAnnotation(ConsoleOnly.class);
			if (console != null) {
				if (context.getSender() instanceof Player) {
					context.getSender().sendMessage(console.message());
					return;
				}
			}
			
			try {
				this.executor.invoke(this.owner, (CommandContext) args);
			} catch (Exception ex) {
				
			}
		}
	}
	
	public Cmd getCmd() {
		return this.executor.getAnnotation(Cmd.class);
	}

}
