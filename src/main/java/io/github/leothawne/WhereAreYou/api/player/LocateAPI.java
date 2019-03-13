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
package io.github.leothawne.WhereAreYou.api.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class LocateAPI {
	public static final Location getPlayerLocation(Player player) {
		return player.getLocation();
	}
	public static final Object getPlayerLocation(Player player, boolean toString) {
		if(toString == true) {
			return player.getLocation().getX() + "," + player.getLocation().getY() + "," + player.getLocation().getZ();
		} else {
			return getPlayerLocation(player);
		}
	}
	protected static final Object getPlayerLocation(Player player, boolean toString, boolean inBlocks) {
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
	public static final Object getPlayerLocation(Player player, boolean toString, boolean inBlocks, boolean asVector) {
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
}