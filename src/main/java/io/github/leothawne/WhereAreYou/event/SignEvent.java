package io.github.leothawne.WhereAreYou.event;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import io.github.leothawne.WhereAreYou.WhereAreYouLoader;

public class SignEvent implements Listener {
	private static WhereAreYouLoader plugin;
	public SignEvent(WhereAreYouLoader plugin) {
		SignEvent.plugin = plugin;
	}
	@EventHandler(priority = EventPriority.MONITOR)
	public static final void placeSign(SignChangeEvent event) {
		Player player = (Player) event.getPlayer();
		if(player.hasPermission("WhereAreYou.admin")) {
			String[] lines = event.getLines();
			if(lines[0].equalsIgnoreCase("[Find]")) {
				if(!lines[1].equals("")) {
					@SuppressWarnings("deprecation")
					Player getPlayer = plugin.getServer().getPlayer(lines[1]);
					if(getPlayer != null) {
						event.setLine(0, ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "[Find]");
						event.setLine(1, getPlayer.getName());
					} else {
						event.setLine(0, ChatColor.DARK_RED + "" + ChatColor.BOLD + "[Find]");
						player.sendMessage(ChatColor.DARK_RED + "Player not found!");
					}
				} else {
					event.setLine(0, ChatColor.DARK_RED + "" + ChatColor.BOLD + "[Find]");
					player.sendMessage(ChatColor.DARK_RED + "Name cannot be empty.");
				}
			}
		}
	}
	@EventHandler(priority = EventPriority.MONITOR)
	public static final void clickSign(PlayerInteractEvent event) {
		Player player = (Player) event.getPlayer();
		if(player.hasPermission("WhereAreYou.admin")) {
			if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				Block block = event.getClickedBlock();
				if((block.getType() == Material.SIGN) || (block.getType() == Material.WALL_SIGN)) {
					Sign sign = (Sign) block.getState();
					String[] lines = sign.getLines();
					if(lines[0].equals(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "[Find]")) {
						if(!lines[1].equals("")) {
							@SuppressWarnings("deprecation")
							Player getPlayer = plugin.getServer().getPlayer(lines[1]);
							if(getPlayer != null) {
								if(player.isSneaking() == true) {
									if(player.hasPermission("minecraft.command.teleport") || (plugin.getServer().getPluginManager().isPluginEnabled("Essentials") && player.hasPermission("essentials.tp")) || player.isOp()) {
										player.performCommand("tp " + getPlayer.getName());
									}
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