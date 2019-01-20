package io.github.leothawne.WhereAreYou;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.leothawne.WhereAreYou.commands.WhereAreYouAdminCommand;
import io.github.leothawne.WhereAreYou.commands.WhereAreYouCommand;
import io.github.leothawne.WhereAreYou.commands.constructors.WhereAreYouAdminCommandConstructor;
import io.github.leothawne.WhereAreYou.commands.constructors.WhereAreYouCommandConstructor;
import io.github.leothawne.WhereAreYou.events.players.AdminVersionCheckEvent;

public class WhereAreYouLoader extends JavaPlugin {
	private final ConsoleLoader myLogger = new ConsoleLoader(this);
	public void registerEvents(WhereAreYouLoader plugin, Listener...listeners) {
		for(Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}
	private FileConfiguration configuration;
	@SuppressWarnings("unused")
	private FileConfiguration language;
	@Override
	public void onEnable() {
		for(Player player : this.getServer().getOnlinePlayers()) {
			player.sendMessage(ChatColor.AQUA + "[WhereAreYou] " + ChatColor.LIGHT_PURPLE + "Loading...");
		}
		this.myLogger.Hello();
		new MetricsLoader(this, this.myLogger).init();
		this.myLogger.info("Loading...");
		new ConfigurationLoader(this, this.myLogger).check();
		this.configuration = new ConfigurationLoader(this, this.myLogger).load();
		new LanguageLoader(this, this.myLogger, this.configuration).check();
		this.language = new LanguageLoader(this, this.myLogger, this.configuration).load();
		if(this.configuration.getBoolean("enable-plugin") == true) {
			this.getCommand("whereareyou").setExecutor(new WhereAreYouCommand(this, this.myLogger, this.language));
			this.getCommand("whereareyouadmin").setExecutor(new WhereAreYouAdminCommand(this, this.myLogger, this.language));
			this.getCommand("whereareyou").setTabCompleter(new WhereAreYouCommandConstructor());
			this.getCommand("whereareyouadmin").setTabCompleter(new WhereAreYouAdminCommandConstructor());
			this.registerEvents(this, new AdminVersionCheckEvent(this.configuration));
			new Version(this, this.myLogger).check();
			this.myLogger.warning("A permissions plugin is required! Just make sure you are using one. Permissions nodes can be found at: [https://leothawne.github.io/WhereAreYou/permissions.html]");
			for(Player player : this.getServer().getOnlinePlayers()) {
				player.sendMessage(ChatColor.AQUA + "[WhereAreYou] " + ChatColor.LIGHT_PURPLE + "Loaded!");
			}
		} else {
			this.myLogger.severe("You manually choose to disable this plugin.");
			this.getServer().getPluginManager().disablePlugin(this);
		}
	}
	@Override
	public void onDisable() {
		for(Player player : this.getServer().getOnlinePlayers()) {
			player.sendMessage(ChatColor.AQUA + "[WhereAreYou] " + ChatColor.LIGHT_PURPLE + "Unloading...");
		}
		this.myLogger.info("Unloading...");
		this.myLogger.Goodbye();
		for(Player player : this.getServer().getOnlinePlayers()) {
			player.sendMessage(ChatColor.AQUA + "[WhereAreYou] " + ChatColor.LIGHT_PURPLE + "Unloaded!");
		}
	}
}