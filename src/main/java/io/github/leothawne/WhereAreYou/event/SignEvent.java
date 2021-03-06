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
package io.github.leothawne.WhereAreYou.event;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import io.github.leothawne.WhereAreYou.WhereAreYou;

public class SignEvent implements Listener {
	private static WhereAreYou plugin;
	private static FileConfiguration language;
	public SignEvent(WhereAreYou plugin, FileConfiguration language) {
		SignEvent.plugin = plugin;
		SignEvent.language = language;
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public static final void placeSign(SignChangeEvent event) {
		Player player = (Player) event.getPlayer();
		if(player.hasPermission("WhereAreYou.admin")) {
			String[] lines = event.getLines();
			if(lines[0].equalsIgnoreCase("[Find]")) {
				if(!lines[1].equals("")) {
					Player getPlayer = plugin.getServer().getPlayer(lines[1]);
					if(getPlayer != null) {
						event.setLine(0, ChatColor.DARK_BLUE + "" + "[Find]");
						event.setLine(1, getPlayer.getName());
					} else {
						event.setLine(0, ChatColor.DARK_RED + "" + "[Find]");
						player.sendMessage(ChatColor.DARK_RED + language.getString("not-found"));
					}
				} else {
					event.setLine(0, ChatColor.DARK_RED + "" + "[Find]");
					player.sendMessage(ChatColor.DARK_RED + language.getString("empty-name"));
				}
			}
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public static final void clickSign(PlayerInteractEvent event) {
		Player player = (Player) event.getPlayer();
		if(player.hasPermission("WhereAreYou.admin")) {
			if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				Block block = event.getClickedBlock();
				if((block.getType() == Material.SIGN) || (block.getType() == Material.WALL_SIGN)) {
					Sign sign = (Sign) block.getState();
					String[] lines = sign.getLines();
					if(lines[0].equals(ChatColor.DARK_BLUE + "" + "[Find]")) {
						if(!lines[1].equals("")) {
							Player getPlayer = plugin.getServer().getPlayer(lines[1]);
							if(getPlayer != null) {
								if(player.isSneaking() == true) {
									player.performCommand("whereareyouadmin teleport " + getPlayer.getName());
								} else {
									player.performCommand("whereareyouadmin find " + getPlayer.getName());
								}
							}
						}
					}
				}
			}
		}
	}
}