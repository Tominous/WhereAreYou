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
package io.github.leothawne.WhereAreYou.api;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import io.github.leothawne.WhereAreYou.ConsoleLoader;
import io.github.leothawne.WhereAreYou.WhereAreYou;
import io.github.leothawne.WhereAreYou.api.bStats.MetricsAPI;

/**
 * 
 * The API class.
 * 
 * @author leothawne
 * 
 */
public class WhereAreYouAPI {
	private static WhereAreYou plugin;
	private static ConsoleLoader myLogger;
	private static MetricsAPI metrics;
	/**
	 * 
	 * @deprecated There is no need to manually create
	 * an object with this constructor when
	 * you can easily use {@link WhereAreYou#getAPI()}.
	 * 
	 */
	public WhereAreYouAPI(WhereAreYou plugin, ConsoleLoader myLogger, MetricsAPI metrics) {
		WhereAreYouAPI.plugin = plugin;
		WhereAreYouAPI.myLogger = myLogger;
		WhereAreYouAPI.metrics = metrics;
	}
	/**
	 * 
	 * Returns a ConsoleLoader type value that can
	 * be used to log messages on the server console.
	 * 
	 * @return A ConsoleLoader type value.
	 * 
	 */
	public final ConsoleLoader getLogger() {
		return myLogger;
	}
	/**
	 * 
	 * Returns a boolean type value that can be used to determine
	 * if the plugin is currently using bStats.
	 * 
	 * @return A boolean type value.
	 * 
	 */
	public final boolean isMetricsEnabled() {
		return metrics.isEnabled();
	}
	/**
	 * 
	 * Returns the current location of a player.
	 * 
	 * @param player The Player type variable.
	 * 
	 * @return A Location type value.
	 * 
	 */
	public final Location getPlayerLocation(Player player) {
		return player.getLocation();
	}
	/**
	 * 
	 * Returns the current location of a player.
	 * 
	 * @param playerUUID The player's unique id.
	 * 
	 * @return A Location type value.
	 * 
	 */
	public final Location getPlayerLocation(UUID playerUUID) {
		return getPlayerLocation(plugin.getServer().getPlayer(playerUUID));
	}
	/**
	 * 
	 * Returns the current location of a player.
	 * 
	 * @param playerName The player's name.
	 * 
	 * @return A Location type value.
	 * 
	 */
	public final Location getPlayerLocation(String playerName) {
		return getPlayerLocation(plugin.getServer().getPlayer(playerName));
	}
	/**
	 * 
	 * Returns the current location of a player.
	 * 
	 * @param player The Player type variable.
	 * @param toString Determines if return value must be String or not.
	 * 
	 * @return A Location type value or a String type value.
	 * 
	 */
	public final Object getPlayerLocation(Player player, boolean toString) {
		if(toString == true) {
			return player.getLocation().getX() + "," + player.getLocation().getY() + "," + player.getLocation().getZ();
		} else {
			return getPlayerLocation(player);
		}
	}
	/**
	 * 
	 * Returns the current location of a player.
	 * 
	 * @param playerUUID The player's unique id.
	 * @param toString Determines if return value must be String or not.
	 * 
	 * @return A Location type value or a String type value.
	 * 
	 */
	public final Object getPlayerLocation(UUID playerUUID, boolean toString) {
		return getPlayerLocation(plugin.getServer().getPlayer(playerUUID), toString);
	}
	/**
	 * 
	 * Returns the current location of a player.
	 * 
	 * @param playerName The player's name.
	 * @param toString Determines if return value must be String or not.
	 * 
	 * @return A Location type value or a String type value.
	 * 
	 */
	public final Object getPlayerLocation(String playerName, boolean toString) {
		return getPlayerLocation(plugin.getServer().getPlayer(playerName), toString);
	}
	/**
	 * 
	 * Returns the current location of a player.
	 * 
	 * @param player The Player type variable.
	 * @param toString Determines if return value must be String or not.
	 * @param inBlocks Determines if return value (Location or String) must be made with Integer values or not (Double values instead). If true, toString must be true.
	 * 
	 * @return A Location type value or a String type value.
	 * 
	 */
	public final Object getPlayerLocation(Player player, boolean toString, boolean inBlocks) {
		if(toString == true) {
			if(inBlocks == true) {
				return player.getLocation().getBlockX() + "," + player.getLocation().getBlockY() + "," + player.getLocation().getBlockZ();
			} else {
				return getPlayerLocation(player, toString);
			}
		} else {
			return getPlayerLocation(player);
		}
	}
	/**
	 * 
	 * Returns the current location of a player.
	 * 
	 * @param playerUUID The player's unique id.
	 * @param toString Determines if return value must be String or not.
	 * @param inBlocks Determines if return value (Location or String) must be made with Integer values or not (Double values instead). If true, toString must be true.
	 * 
	 * @return A Location type value or a String type value.
	 * 
	 */
	public final Object getPlayerLocation(UUID playerUUID, boolean toString, boolean inBlocks) {
		return getPlayerLocation(plugin.getServer().getPlayer(playerUUID), toString, inBlocks);
	}
	/**
	 * 
	 * Returns the current location of a player.
	 * 
	 * @param playerName The player's name.
	 * @param toString Determines if return value must be String or not.
	 * @param inBlocks Determines if return value (Location or String) must be made with Integer values or not (Double values instead). If true, toString must be true.
	 * 
	 * @return A Location type value or a String type value.
	 * 
	 */
	public final Object getPlayerLocation(String playerName, boolean toString, boolean inBlocks) {
		return getPlayerLocation(plugin.getServer().getPlayer(playerName), toString, inBlocks);
	}
	/**
	 * 
	 * Returns the current location of a player.
	 * 
	 * @param player The Player type variable.
	 * @param toString Determines if return value must be String or not.
	 * @param inBlocks Determines if return value (Location or String) must be made with Integer values or not (Double values instead). If true, toString must be true.
	 * @param asVector Determines if return value must be Vector or not. If true, toString and inBlocks must be false.
	 * 
	 * @return A Location type value, a String type value or a Vector type value.
	 * 
	 */
	public final Object getPlayerLocation(Player player, boolean toString, boolean inBlocks, boolean asVector) {
		if(toString == true) {
			if(inBlocks == true) {
				return getPlayerLocation(player, toString, inBlocks);
			} else {
				return getPlayerLocation(player, toString);
			}
		} else {
			if(inBlocks == true) {
				if(asVector == true) {
					return new Vector(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
				} else {
					return getPlayerLocation(player);
				}
			} else {
				if(asVector == true) {
					return new Vector(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
				} else {
					return getPlayerLocation(player);
				}
			}
		}
	}
	/**
	 * 
	 * Returns the current location of a player.
	 * 
	 * @param playerUUID The player's unique id.
	 * @param toString Determines if return value must be String or not.
	 * @param inBlocks Determines if return value (Location or String) must be made with Integer values or not (Double values instead). If true, toString must be true.
	 * @param asVector Determines if return value must be Vector or not. If true, toString and inBlocks must be false.
	 * 
	 * @return A Location type value, a String type value or a Vector type value.
	 * 
	 */
	public final Object getPlayerLocation(UUID playerUUID, boolean toString, boolean inBlocks, boolean asVector) {
		return getPlayerLocation(plugin.getServer().getPlayer(playerUUID), toString, inBlocks, asVector);
	}
	/**
	 * 
	 * Returns the current location of a player.
	 * 
	 * @param playerName The player's name.
	 * @param toString Determines if return value must be String or not.
	 * @param inBlocks Determines if return value (Location or String) must be made with Integer values or not (Double values instead). If true, toString must be true.
	 * @param asVector Determines if return value must be Vector or not. If true, toString and inBlocks must be false.
	 * 
	 * @return A Location type value, a String type value or a Vector type value.
	 * 
	 */
	public final Object getPlayerLocation(String playerName, boolean toString, boolean inBlocks, boolean asVector) {
		return getPlayerLocation(plugin.getServer().getPlayer(playerName), toString, inBlocks, asVector);
	}
	/**
	 * 
	 * Teleports a player to another player's location.
	 * 
	 * @param from The Player type variable.
	 * @param to The Player type variable.
	 * 
	 * @return A Boolean type value.
	 * 
	 */
	public final boolean teleportPlayer(Player from, Player to) {
		Location destination = new Location(to.getLocation().getWorld(), to.getLocation().getX(), to.getLocation().getY(), to.getLocation().getZ(), to.getLocation().getYaw(), to.getLocation().getPitch());
		return from.teleport(destination);
	}
	/**
	 * 
	 * Teleports a player to another player's location.
	 * 
	 * @param fromUUID The player's unique id.
	 * @param toUUID The player's unique id.
	 * 
	 * @return A Boolean type value.
	 * 
	 */
	public final boolean teleportPlayer(UUID fromUUID, UUID toUUID) {
		return teleportPlayer(plugin.getServer().getPlayer(fromUUID), plugin.getServer().getPlayer(toUUID));
	}
	/**
	 * 
	 * Teleports a player to another player's location.
	 * 
	 * @param fromName The player's name.
	 * @param toName The player's name.
	 * 
	 * @return A Boolean type value.
	 * 
	 */
	public final boolean teleportPlayer(String fromName, String toName) {
		return teleportPlayer(plugin.getServer().getPlayer(fromName), plugin.getServer().getPlayer(toName));
	}
}