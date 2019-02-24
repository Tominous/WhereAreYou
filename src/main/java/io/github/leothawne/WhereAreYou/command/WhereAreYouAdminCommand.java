/*
 * Copyright (C) 2019 Murilo Amaral Nappi (murilonappi@gmail.com)
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import io.github.leothawne.WhereAreYou.ConsoleLoader;
import io.github.leothawne.WhereAreYou.Version;
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
					if(args.length < 2) {
						try {
							URLConnection newUpdateURL = new URL("https://leothawne.github.io/WhereAreYou/api/version.html").openConnection();
							newUpdateURL.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
							newUpdateURL.connect();
							BufferedReader newUpdateReader = new BufferedReader(new InputStreamReader(newUpdateURL.getInputStream(), Charset.forName("UTF-8")));
							StringBuilder sb = new StringBuilder();
							String line;
							while((line = newUpdateReader.readLine()) != null) {
								sb.append(line);
							}
							if(sb.toString() != null) {
								String[] LocalVersion = Version.getVersionNumber().split("\\.");
								int Local_VersionNumber1 = Integer.parseInt(LocalVersion[0]);
								int Local_VersionNumber2 = Integer.parseInt(LocalVersion[1]);
								int Local_VersionNumber3 = Integer.parseInt(LocalVersion[2]);
								String[] Server1 = sb.toString().split("-");
								String[] Server2 = Server1[0].split("\\.");
								int Server2_VersionNumber1 = Integer.parseInt(Server2[0]);
								int Server2_VersionNumber2 = Integer.parseInt(Server2[1]);
								int Server2_VersionNumber3 = Integer.parseInt(Server2[2]);
								sender.sendMessage(ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + "Running: " + ChatColor.GREEN + "" + Version.getVersionNumber() + "" + ChatColor.YELLOW + " (released on " + ChatColor.GREEN + "" + Version.getVersionDate() + "" + ChatColor.YELLOW + ").");
								String updateMessage = ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + "A newer version is available: " + ChatColor.GREEN + "" + Server1[0] + "" + ChatColor.YELLOW + " (released on " + ChatColor.GREEN + "" + Server1[1] + "" + ChatColor.YELLOW + ").";
								if(Server2_VersionNumber1 > Local_VersionNumber1) {
									sender.sendMessage(updateMessage);
								} else if(Server2_VersionNumber1 == Local_VersionNumber1 && Server2_VersionNumber2 > Local_VersionNumber2) {
									sender.sendMessage(updateMessage);
								} else if(Server2_VersionNumber1 == Local_VersionNumber1 && Server2_VersionNumber2 == Local_VersionNumber2 && Server2_VersionNumber3 > Local_VersionNumber3) {
									sender.sendMessage(updateMessage);
								}
							} else {
								sender.sendMessage(ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + "Error while checking for new updates: Server did not respond correctly.");
							}
						} catch(Exception e) {
							myLogger.severe("Error while checking for new updates: " + e.getMessage());
							sender.sendMessage(ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + "Error while checking for new updates.");
						}
					} else {
						sender.sendMessage(ChatColor.AQUA + "[WRU :: Admin] " + ChatColor.YELLOW + "" + language.getString("player-tma"));
					}
				} else if(args[0].equalsIgnoreCase("find")) {
					if(args.length == 2) {
						if(args[1].equalsIgnoreCase("me") == false) {
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
				myLogger.warning(sender.getName() + " does not have permission [WhereAreYou.admin]!");
			}
		} else {
			sender.sendMessage(ChatColor.DARK_GREEN + "[WRU :: Admin] " + ChatColor.YELLOW + "" + language.getString("no-permission"));
			myLogger.warning(sender.getName() + " does not have permission [WhereAreYou.use]!");
		}
		return true;
	}
}