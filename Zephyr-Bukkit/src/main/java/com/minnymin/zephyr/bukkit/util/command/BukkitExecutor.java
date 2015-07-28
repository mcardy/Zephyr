package com.minnymin.zephyr.bukkit.util.command;

import java.lang.reflect.Method;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.minnymin.zephyr.bukkit.util.command.SenderSpecification.SenderType;

class BukkitExecutor {

	private Method executor;
	private Object owner;

	BukkitExecutor(Method m) {
		this.executor = m;
	}

	BukkitExecutor(Method m, Object o) {
		this.executor = m;
		this.owner = o;
	}

	void execute(CommandContext context) {
		Permission perm = this.executor.getAnnotation(Permission.class);
		if (perm != null) {
			if (!context.getSender().hasPermission(perm.permission())) {
				context.getSender().sendMessage(perm.message());
				return;
			}
		}

		SenderSpecification senderSpec = this.executor.getAnnotation(SenderSpecification.class);
		if (senderSpec != null) {
			if ((senderSpec.type() == SenderType.PLAYER && !(context.getSender() instanceof Player))
					|| (senderSpec.type() == SenderType.CONSOLE && !(context.getSender() instanceof ConsoleCommandSender))) {
				context.getSender().sendMessage(senderSpec.message());
				return;
			}
		}

		try {
			this.executor.invoke(this.owner, context);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	Cmd getCmd() {
		return this.executor.getAnnotation(Cmd.class);
	}

}
