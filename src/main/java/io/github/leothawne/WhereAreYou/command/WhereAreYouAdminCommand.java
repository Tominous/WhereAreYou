/*
 * Copyright (C) 2019 Murilo Amaral Nappi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.leothawne.WhereAreYou.command;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.leothawne.WhereAreYou.ConsoleLoader;
import io.github.leothawne.WhereAreYou.Version;
import io.github.leothawne.WhereAreYou.WhereAreYou;
import io.github.leothawne.WhereAreYou.api.player.JSONMessageAPI;
import io.github.leothawne.WhereAreYou.api.utility.HTTP;

public class WhereAreYouAdminCommand implements CommandExecutor {
	private static WhereAreYou plugin;
	private static ConsoleLoader myLogger;
	private static FileConfiguration language;
	public WhereAreYouAdminCommand(WhereAreYou plugin, ConsoleLoader myLogger, FileConfiguration language) {
		WhereAreYouAdminCommand.plugin = plugin;
		WhereAreYouAdminCommand.myLogger = myLogger;
		WhereAreYouAdminCommand.language = language;
	}
	@Override
	public final boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender.hasPermission("WhereAreYou.use")) {
			if(sender.hasPermission("WhereAreYou.admin")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.AQUA + "=+=+=+= [Where Are You :: Admin] =+=+=+=");
					sender.sendMessage(ChatColor.GREEN + "/whereareyouadmin " + ChatColor.AQUA + "- Shows all dministration commands for Where Are You.");
					sender.sendMessage(ChatColor.GREEN + "/whereareyouadmin version " + ChatColor.AQUA + "- Checks for new updates.");
					sender.sendMessage(ChatColor.GREEN + "/whereareyouadmin find <player> " + ChatColor.AQUA + "- Finds a player's current location.");
					sender.sendMessage(ChatColor.YELLOW + "You can also use "+ ChatColor.GREEN + "/whereareyouadmin "+ ChatColor.YELLOW + "as "+ ChatColor.GREEN + "/wrua"+ ChatColor.YELLOW + ".");
				} else if(args[0].equalsIgnoreCase("version")) {
					if(args.length < 2) {
						new BukkitRunnable() {
							@Override
							public final void run() {
								String[] LocalVersion = Version.getVersionNumber().split("\\.");
								int Local_VersionNumber1 = Integer.parseInt(LocalVersion[0]);
								int Local_VersionNumber2 = Integer.parseInt(LocalVersion[1]);
								int Local_VersionNumber3 = Integer.parseInt(LocalVersion[2]);
								String[] Server1 = HTTP.getData(Version.getUpdateURL()).split("-");
								String[] Server2 = Server1[0].split("\\.");
								int Server2_VersionNumber1 = Integer.parseInt(Server2[0]);
								int Server2_VersionNumber2 = Integer.parseInt(Server2[1]);
								int Server2_VersionNumber3 = Integer.parseInt(Server2[2]);
								String updateMessage = ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + "A newer version is available: " + ChatColor.GREEN + "" + Server1[0] + "" + ChatColor.YELLOW + " (released on " + ChatColor.GREEN + "" + Server1[1] + "" + ChatColor.YELLOW + ").";
								if(Server2_VersionNumber1 > Local_VersionNumber1) {
									sender.sendMessage(updateMessage);
								} else if(Server2_VersionNumber1 == Local_VersionNumber1 && Server2_VersionNumber2 > Local_VersionNumber2) {
									sender.sendMessage(updateMessage);
								} else if(Server2_VersionNumber1 == Local_VersionNumber1 && Server2_VersionNumber2 == Local_VersionNumber2 && Server2_VersionNumber3 > Local_VersionNumber3) {
									sender.sendMessage(updateMessage);
								} else {
									sender.sendMessage(ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + "The plugin is up to date!");
								}
							}
						}.runTask(plugin);
					} else {
						sender.sendMessage(ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + "" + language.getString("player-tma"));
					}
				} else if(args[0].equalsIgnoreCase("find")) {
					if(args.length == 2) {
						if(args[1].equalsIgnoreCase("me") == false) {
							@SuppressWarnings("deprecation")
							Player findPlayer = (Player) plugin.getServer().getPlayer(args[1]);
							if(findPlayer != null) {
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
										if(player.hasPermission("minecraft.command.teleport") || (plugin.getServer().getPluginManager().isPluginEnabled("Essentials") && player.hasPermission("essentials.tp")) || player.isOp()) {
											sender.sendMessage("");
											JSONMessageAPI message = JSONMessageAPI.create("Click here to teleport to that player.");
											message.color(ChatColor.GOLD);
											message.style(ChatColor.UNDERLINE);
											message.runCommand("/tp " + findPlayer.getName());
											message.send(player);
										}
									}
								}
								sender.sendMessage("");
								sender.sendMessage("");
								sender.sendMessage("");
							} else {
								
							}
						} else {
							if(sender instanceof Player) {
								sender.sendMessage("WHAT THE H..? For God sake, just press F3.");
							} else {
								sender.sendMessage("Ok, you are not even a player. What the heck you think you're doing?");
							}
						}
						
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
				myLogger.warning(sender.getName() + " does not have permission [WhereAreYou.admin]: '/whereareyouadmin' command.");
			}
		} else {
			sender.sendMessage(ChatColor.DARK_GREEN + "[WRU :: Admin] " + ChatColor.YELLOW + "" + language.getString("no-permission"));
			myLogger.warning(sender.getName() + " does not have permission [WhereAreYou.use]: '/whereareyouadmin' command.");
		}
		return true;
	}
}