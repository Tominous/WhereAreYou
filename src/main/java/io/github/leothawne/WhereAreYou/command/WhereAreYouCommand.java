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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import io.github.leothawne.WhereAreYou.ConsoleLoader;
import io.github.leothawne.WhereAreYou.Version;

public class WhereAreYouCommand implements CommandExecutor {
	private static ConsoleLoader myLogger;
	private static FileConfiguration language;
	public WhereAreYouCommand(ConsoleLoader myLogger, FileConfiguration language) {
		WhereAreYouCommand.myLogger = myLogger;
		WhereAreYouCommand.language = language;
	}
	@Override
	public final boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(sender.hasPermission("WhereAreYou.use")) {
			if(args.length == 0) {
				sender.sendMessage(ChatColor.AQUA + "=+=+=+= [Where Are You] =+=+=+=");
				sender.sendMessage(ChatColor.GREEN + "/whereareyou " + ChatColor.AQUA + "- Shows all commands for Where Are You.");
				sender.sendMessage(ChatColor.GREEN + "/whereareyou version " + ChatColor.AQUA + "- Shows the plugin version.");
				sender.sendMessage(ChatColor.GREEN + "/whereareyouadmin " + ChatColor.AQUA + "- Shows all administration commands for Where Are You.");
				sender.sendMessage(ChatColor.YELLOW + "You can also use "+ ChatColor.GREEN + "/whereareyou "+ ChatColor.YELLOW + "as "+ ChatColor.GREEN + "/wru"+ ChatColor.YELLOW + ".");
			} else if(args[0].equalsIgnoreCase("version")) {
				if(args.length < 2) {
					Version.version(sender);
				} else {
					sender.sendMessage(ChatColor.AQUA + "[WRU] " + ChatColor.YELLOW + "" + language.getString("player-tma"));
				}
			} else {
				sender.sendMessage(ChatColor.AQUA + "[WRU] " + ChatColor.YELLOW + "Invalid command! Type " + ChatColor.GREEN + "/whereareyou " + ChatColor.YELLOW + "to see all available commands.");
			}
		} else {
			sender.sendMessage(ChatColor.DARK_GREEN + "[WRU] " + ChatColor.YELLOW + "" + language.getString("no-permission"));
			myLogger.warning(sender.getName() + " does not have permission [WhereAreYou.use]: '/whereareyou' command.");
		}
		return true;
	}
}