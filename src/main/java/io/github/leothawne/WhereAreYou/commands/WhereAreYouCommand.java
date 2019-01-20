package io.github.leothawne.WhereAreYou.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import io.github.leothawne.WhereAreYou.ConsoleLoader;
import io.github.leothawne.WhereAreYou.WhereAreYouLoader;

public class WhereAreYouCommand implements CommandExecutor {
	private WhereAreYouLoader plugin;
	private ConsoleLoader myLogger;
	private FileConfiguration language;
	public WhereAreYouCommand(WhereAreYouLoader plugin, ConsoleLoader myLogger, FileConfiguration language) {
		this.plugin = plugin;
		this.myLogger = myLogger;
		this.language = language;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender.hasPermission("WhereAreYou.use")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.AQUA + "=+=+=+= [Where Are You] =+=+=+=");
				sender.sendMessage(ChatColor.GREEN + "/whereareyou " + ChatColor.AQUA + "- Show all commands for LT Item Mail.");
				sender.sendMessage(ChatColor.GREEN + "/itemmail version " + ChatColor.AQUA + "- Show plugin version.");
				sender.sendMessage(ChatColor.GREEN + "/sendbox <player> " + ChatColor.AQUA + "- Send items to players.");
				sender.sendMessage(ChatColor.GREEN + "/itemmailadmin " + ChatColor.AQUA + "- Administration commands for LT Item Mail.");
				sender.sendMessage(ChatColor.YELLOW + "You can also use "+ ChatColor.GREEN + "/itemmail "+ ChatColor.YELLOW + "as "+ ChatColor.GREEN + "/ima"+ ChatColor.YELLOW + ".");
				sender.sendMessage(ChatColor.YELLOW + "You can also use "+ ChatColor.GREEN + "/sendbox "+ ChatColor.YELLOW + "as "+ ChatColor.GREEN + "/sbx"+ ChatColor.YELLOW + ".");
			}
		} else {
			sender.sendMessage(ChatColor.DARK_GREEN + "[Where Are You] " + ChatColor.YELLOW + "" + language.getString("no-permission"));
			this.myLogger.warning(sender.getName() + " does not have permission [LTItemMail.use]!");
		}
		return true;
	}
}