package io.github.leothawne.WhereAreYou.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import io.github.leothawne.WhereAreYou.ConsoleLoader;
import io.github.leothawne.WhereAreYou.Version;
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
	public final boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender.hasPermission("WhereAreYou.use")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.AQUA + "=+=+=+= [Where Are You] =+=+=+=");
				sender.sendMessage(ChatColor.GREEN + "/whereareyou " + ChatColor.AQUA + "- Shows all commands for Where Are You.");
				sender.sendMessage(ChatColor.GREEN + "/whereareyou version " + ChatColor.AQUA + "- Shows plugin version.");
				sender.sendMessage(ChatColor.GREEN + "/whereareyouadmin " + ChatColor.AQUA + "- Administration commands for Where Are You.");
				sender.sendMessage(ChatColor.YELLOW + "You can also use "+ ChatColor.GREEN + "/whereareyou "+ ChatColor.YELLOW + "as "+ ChatColor.GREEN + "/wru"+ ChatColor.YELLOW + ".");
			} else if(args[0].equalsIgnoreCase("version")) {
				if(args.length < 2) {
					new Version(plugin, myLogger);
					Version.version(sender);
				} else {
					sender.sendMessage(ChatColor.AQUA + "[WRU] " + ChatColor.YELLOW + "" + language.getString("player-tma"));
				}
			} else {
				sender.sendMessage(ChatColor.AQUA + "[WRU] " + ChatColor.YELLOW + "Invalid command! Type " + ChatColor.GREEN + "/whereareyou " + ChatColor.YELLOW + "to see all available commands.");
			}
		} else {
			sender.sendMessage(ChatColor.DARK_GREEN + "[WRU] " + ChatColor.YELLOW + "" + language.getString("no-permission"));
			myLogger.warning(sender.getName() + " does not have permission [WhereAreYou.use]!");
		}
		return true;
	}
}