package io.github.leothawne.WhereAreYou.command;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import io.github.leothawne.WhereAreYou.ConsoleLoader;
import io.github.leothawne.WhereAreYou.WhereAreYouLoader;
import io.github.leothawne.WhereAreYou.api.player.JSONMessageAPI;

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
	public final boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender.hasPermission("WhereAreYou.use")) {
			if(sender.hasPermission("WhereAreYou.admin")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.AQUA + "=+=+=+= [Where Are You :: Admin] =+=+=+=");
					sender.sendMessage(ChatColor.GREEN + "/whereareyouadmin " + ChatColor.AQUA + "- Administration commands for Where Are You.");
					sender.sendMessage(ChatColor.GREEN + "/whereareyouadmin version " + ChatColor.AQUA + "- Checks for new updates.");
					sender.sendMessage(ChatColor.GREEN + "/whereareyouadmin find <player> " + ChatColor.AQUA + "- Finds a player's current location.");
					sender.sendMessage(ChatColor.YELLOW + "You can also use "+ ChatColor.GREEN + "/whereareyouadmin "+ ChatColor.YELLOW + "as "+ ChatColor.GREEN + "/wrua"+ ChatColor.YELLOW + ".");
				} else if(args[0].equalsIgnoreCase("version")) {
					
				} else if(args[0].equalsIgnoreCase("find")) {
					if(args.length == 2) {
						@SuppressWarnings("deprecation")
						Player findPlayer = (Player) plugin.getServer().getPlayer(args[1]);
						World world = findPlayer.getLocation().getWorld();
						int x = findPlayer.getLocation().getBlockX();
						int y = findPlayer.getLocation().getBlockY();
						int z = findPlayer.getLocation().getBlockZ();
						sender.sendMessage("");
						sender.sendMessage("");
						sender.sendMessage("");
						sender.sendMessage(ChatColor.BOLD + "" + ChatColor.AQUA + "" + findPlayer.getName() + "" + ChatColor.GOLD + "" + "'s Location:");
						sender.sendMessage(ChatColor.GOLD + "" + "World: " + "" + ChatColor.AQUA + "" + world.getName() + "" + ChatColor.GOLD + "" + ", X: " + "" + ChatColor.AQUA + "" + x + "" + ChatColor.GOLD + "" + ", Y: " + "" + ChatColor.AQUA + "" + y + "" + ChatColor.GOLD + "" + ", Z: " + "" + ChatColor.AQUA + "" + z + "" + ChatColor.GOLD + "" + ".");
						if(sender instanceof Player) {
							Player player = (Player) sender;
							if(player.getLocation().getWorld().equals(world)) {
								if(player.hasPermission("minecraft.command.teleport") || player.isOp()) {
									sender.sendMessage("");
									JSONMessageAPI message = JSONMessageAPI.create("Click here to teleport to that player.");
									message.color(ChatColor.GOLD);
									message.style(ChatColor.UNDERLINE);
									message.runCommand("/minecraft:teleport " + sender.getName() + " " + x + " " + y + " " + z);
									message.send(player);
								}
							}
						}
						sender.sendMessage("");
						sender.sendMessage("");
						sender.sendMessage("");
					} else if(args.length > 2) {
						sender.sendMessage(ChatColor.AQUA + "[WRU] " + ChatColor.YELLOW + "" + language.getString("player-tma"));
					} else {
						sender.sendMessage(ChatColor.AQUA + "[WRU] " + ChatColor.YELLOW + "" + language.getString("player-empty"));
					}
				} else {
					sender.sendMessage(ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + "Invalid command! Type " + ChatColor.GREEN + "/whereareyouadmin " + ChatColor.YELLOW + "to see all available commands.");
				}
			} else {
				sender.sendMessage(ChatColor.DARK_GREEN + "[WRU :: Admin] " + ChatColor.YELLOW + "" + language.getString("no-permission"));
				myLogger.warning(sender.getName() + " does not have permission [WhereAreYou.admin]!");
			}
		} else {
			sender.sendMessage(ChatColor.DARK_GREEN + "[WRU :: Admin] " + ChatColor.YELLOW + "" + language.getString("no-permission"));
			myLogger.warning(sender.getName() + " does not have permission [WhereAreYou.use]!");
		}
		return true;
	}
}