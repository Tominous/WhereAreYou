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
	private static FileConfiguration configuration;
	private static FileConfiguration language;
	public WhereAreYouAdminCommand(WhereAreYou plugin, ConsoleLoader myLogger, FileConfiguration configuration, FileConfiguration language) {
		WhereAreYouAdminCommand.plugin = plugin;
		WhereAreYouAdminCommand.myLogger = myLogger;
		WhereAreYouAdminCommand.configuration = configuration;
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
					sender.sendMessage(ChatColor.GREEN + "/whereareyouadmin teleport <player> " + ChatColor.AQUA + "- Teleports you to any player on the server.");
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
								String updateMessage = ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + "A newer version is available: " + ChatColor.GREEN + Server1[0] + ChatColor.YELLOW + " (released on " + ChatColor.GREEN + Server1[1] + ChatColor.YELLOW + ").";
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
						sender.sendMessage(ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + language.getString("player-tma"));
					}
				} else if(args[0].equalsIgnoreCase("find")) {
					if(args.length == 2) {
						if(args[1].equalsIgnoreCase("me") == false) {
							@SuppressWarnings("deprecation")
							Player findPlayer = (Player) plugin.getServer().getPlayer(args[1]);
							if(findPlayer != null) {
								World world = plugin.getAPI().getPlayerLocation(findPlayer).getWorld();
								int x = plugin.getAPI().getPlayerLocation(findPlayer).getBlockX();
								int y = plugin.getAPI().getPlayerLocation(findPlayer).getBlockY();
								int z = plugin.getAPI().getPlayerLocation(findPlayer).getBlockZ();
								sender.sendMessage("");
								sender.sendMessage("");
								sender.sendMessage("");
								sender.sendMessage(ChatColor.BOLD + "" + ChatColor.GOLD + language.getString("player-location") + " " + ChatColor.AQUA + findPlayer.getName() + ChatColor.GOLD + ":");
								sender.sendMessage(ChatColor.GOLD + language.getString("player-world") + ": " + ChatColor.AQUA + world.getName() + ChatColor.GOLD + ", X: " + ChatColor.AQUA + x + ChatColor.GOLD + ", Y: " + ChatColor.AQUA + y + ChatColor.GOLD + ", Z: " + ChatColor.AQUA + z + ChatColor.GOLD + ".");
								if(sender instanceof Player) {
									Player player = (Player) sender;
									JSONMessageAPI message = JSONMessageAPI.create(language.getString("player-click"));
									message.color(ChatColor.GOLD);
									message.style(ChatColor.UNDERLINE);
									message.runCommand("/whereareyouadmin teleport " + findPlayer.getName());
									message.tooltip(language.getString("player-click"));
									message.send(player);
								}
								sender.sendMessage("");
								sender.sendMessage("");
								sender.sendMessage("");
							} else {
								sender.sendMessage(ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + language.getString("player-empty"));
							}
						} else {
							if(sender instanceof Player) {
								if(configuration.getString("language").equalsIgnoreCase("english")) {
									sender.sendMessage("WHAT THE H..? For God sake, just press F3.");
								} else if(configuration.getString("language").equalsIgnoreCase("portuguese")) {
									sender.sendMessage("MAS QUE P..? Pelo amor de Deus, é só apertar o F3.");
								} else {
									sender.sendMessage("WHAT THE H..? For God sake, just press F3.");
								}
							} else {
								if(configuration.getString("language").equalsIgnoreCase("english")) {
									sender.sendMessage("Ok, you are not even a player... What the heck you think you're doing?");
								} else if(configuration.getString("language").equalsIgnoreCase("portuguese")) {
									sender.sendMessage("Ok, você nem é um jogador... Que merda é essa que você está fazendo?");
								} else {
									sender.sendMessage("Ok, you are not even a player... What the heck you think you're doing?");
								}
							}
						}
					} else if(args.length > 2) {
						sender.sendMessage(ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + language.getString("player-tma"));
					} else {
						sender.sendMessage(ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + language.getString("player-empty"));
					}
				} else if(args[0].equalsIgnoreCase("teleport")) {
					if(args.length == 2) {
						if(args[1].equalsIgnoreCase("me") == false) {
							if(sender instanceof Player) {
								Player player = (Player) sender;
								@SuppressWarnings("deprecation")
								Player findPlayer = (Player) plugin.getServer().getPlayer(args[1]);
								if(findPlayer != null) {
									plugin.getAPI().teleportPlayer(player, findPlayer);
								} else {
									sender.sendMessage(ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + language.getString("player-empty"));
								}
							} else {
								sender.sendMessage(ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + language.getString("player-error"));
							}
						} else {
							if(sender instanceof Player) {
								if(configuration.getString("language").equalsIgnoreCase("english")) {
									sender.sendMessage("WHAT THE H..?");
								} else if(configuration.getString("language").equalsIgnoreCase("portuguese")) {
									sender.sendMessage("MAS QUE P..?");
								} else {
									sender.sendMessage("WHAT THE H..?");
								}
							} else {
								if(configuration.getString("language").equalsIgnoreCase("english")) {
									sender.sendMessage("Ok, you are not even a player... What the heck you think you're doing?");
								} else if(configuration.getString("language").equalsIgnoreCase("portuguese")) {
									sender.sendMessage("Ok, você nem é um jogador... Que merda é essa que você está fazendo?");
								} else {
									sender.sendMessage("Ok, you are not even a player... What the heck you think you're doing?");
								}
							}
						}
					} else if(args.length > 2) {
						sender.sendMessage(ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + language.getString("player-tma"));
					} else {
						sender.sendMessage(ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + language.getString("player-empty"));
					}
				} else {
					sender.sendMessage(ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + "Invalid command! Type " + ChatColor.GREEN + "/whereareyouadmin " + ChatColor.YELLOW + "to see all available commands.");
				}
			} else {
				sender.sendMessage(ChatColor.DARK_GREEN + "[WRU :: Admin] " + ChatColor.YELLOW + language.getString("no-permission"));
				myLogger.warning(sender.getName() + " does not have permission [WhereAreYou.admin]: '/whereareyouadmin' command.");
			}
		} else {
			sender.sendMessage(ChatColor.DARK_GREEN + "[WRU :: Admin] " + ChatColor.YELLOW + language.getString("no-permission"));
			myLogger.warning(sender.getName() + " does not have permission [WhereAreYou.use]: '/whereareyouadmin' command.");
		}
		return true;
	}
}