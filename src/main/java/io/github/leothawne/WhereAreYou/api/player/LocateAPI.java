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