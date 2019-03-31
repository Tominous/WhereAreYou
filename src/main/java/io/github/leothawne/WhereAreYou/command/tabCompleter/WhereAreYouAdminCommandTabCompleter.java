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
package io.github.leothawne.WhereAreYou.command.tabCompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.google.common.collect.ImmutableList;

import io.github.leothawne.WhereAreYou.WhereAreYou;
import io.github.leothawne.WhereAreYou.api.utility.TabCompleterAPI;

public class WhereAreYouAdminCommandTabCompleter extends TabCompleterAPI implements TabCompleter {
	private static WhereAreYou plugin;
	public WhereAreYouAdminCommandTabCompleter(WhereAreYou plugin) {
		WhereAreYouAdminCommandTabCompleter.plugin = plugin;
	}
	@Override
	public final List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args){
		List<String> ReturnNothing = new ArrayList<>();
		if(sender.hasPermission("WhereAreYou.use") && sender.hasPermission("WhereAreYou.admin")) {
			if(args.length == 1) {
				ImmutableList<String> WRU = ImmutableList.of("version", "find", "teleport");
				return partial(args[0], WRU);
			} else if(args.length == 2 && args[0].equalsIgnoreCase("find")) {
				List<String> WRU = new ArrayList<String>();
				WRU.add("me");
				for(Player player : plugin.getServer().getOnlinePlayers()) {
					WRU.add(player.getName());
				}
				return partial(args[1], WRU);
			} else if(args.length == 2 && args[0].equalsIgnoreCase("teleport")) {
				List<String> WRU = new ArrayList<String>();
				WRU.add("me");
				for(Player player : plugin.getServer().getOnlinePlayers()) {
					WRU.add(player.getName());
				}
				return partial(args[1], WRU);
			}
		}
		return ReturnNothing;
	}
}