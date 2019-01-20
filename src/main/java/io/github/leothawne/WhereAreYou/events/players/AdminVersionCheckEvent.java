package io.github.leothawne.WhereAreYou.events.players;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AdminVersionCheckEvent implements Listener {
	private FileConfiguration configuration;
	public AdminVersionCheckEvent(FileConfiguration configuration) {
		this.configuration = configuration;
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = (Player) event.getPlayer();
		if(player.hasPermission("WhereAreYou.use") && player.hasPermission("WhereAreYou.admin")) {
			if(this.configuration.getBoolean("update.check") == true) {
				player.performCommand("whereareyouadmin version");
			}
		}
	}
}