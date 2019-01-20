package io.github.leothawne.WhereAreYou.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import io.github.leothawne.WhereAreYou.ConsoleLoader;
import io.github.leothawne.WhereAreYou.WhereAreYouLoader;

public class WhereAreYouAdminCommand implements CommandExecutor {
	private WhereAreYouLoader plugin;
	private ConsoleLoader myLogger;
	private FileConfiguration language;
	public WhereAreYouAdminCommand(WhereAreYouLoader plugin, ConsoleLoader myLogger, FileConfiguration language) {
		this.plugin = plugin;
		this.myLogger = myLogger;
		this.language = language;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		return true;
	}
}