package io.github.leothawne.WhereAreYou;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.leothawne.WhereAreYou.command.WhereAreYouAdminCommand;
import io.github.leothawne.WhereAreYou.command.WhereAreYouCommand;
import io.github.leothawne.WhereAreYou.command.tabCompleter.WhereAreYouAdminCommandTabCompleter;
import io.github.leothawne.WhereAreYou.command.tabCompleter.WhereAreYouCommandConstructor;
import io.github.leothawne.WhereAreYou.event.player.AdminEvent;

public class WhereAreYouLoader extends JavaPlugin {
	private final ConsoleLoader myLogger = new ConsoleLoader(this);
	public void registerEvents(Listener...listeners) {
		for(Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, this);
		}
	}
	private FileConfiguration configuration;
	private FileConfiguration language;
	@Override
	public final void onEnable() {
		for(Player player : getServer().getOnlinePlayers()) {
			player.sendMessage(ChatColor.AQUA + "[WhereAreYou] " + ChatColor.LIGHT_PURPLE + "Loading...");
		}
		myLogger.Hello();
		new MetricsLoader(this, myLogger);
		MetricsLoader.init();
		myLogger.info("Loading...");
		new ConfigurationLoader(this, myLogger);
		ConfigurationLoader.check();
		new ConfigurationLoader(this, myLogger);
		configuration = ConfigurationLoader.load();
		new LanguageLoader(this, myLogger, configuration);
		LanguageLoader.check();
		new LanguageLoader(this, myLogger, configuration);
		language = LanguageLoader.load();
		if(configuration.getBoolean("enable-plugin") == true) {
			getCommand("whereareyou").setExecutor(new WhereAreYouCommand(this, myLogger, language));
			getCommand("whereareyouadmin").setExecutor(new WhereAreYouAdminCommand(this, myLogger, language));
			getCommand("whereareyou").setTabCompleter(new WhereAreYouCommandConstructor());
			getCommand("whereareyouadmin").setTabCompleter(new WhereAreYouAdminCommandTabCompleter());
			registerEvents(new AdminEvent(configuration));
			new Version(this, myLogger);
			Version.check();
			myLogger.warning("A permissions plugin is required! Just make sure you are using one. Permissions nodes can be found at: [https://leothawne.github.io/WhereAreYou/permissions.html]");
			for(Player player : getServer().getOnlinePlayers()) {
				player.sendMessage(ChatColor.AQUA + "[WhereAreYou] " + ChatColor.LIGHT_PURPLE + "Loaded!");
			}
		} else {
			myLogger.severe("You manually choose to disable this plugin.");
			getServer().getPluginManager().disablePlugin(this);
		}
	}
	@Override
	public final void onDisable() {
		for(Player player : getServer().getOnlinePlayers()) {
			player.sendMessage(ChatColor.AQUA + "[WhereAreYou] " + ChatColor.LIGHT_PURPLE + "Unloading...");
		}
		myLogger.info("Unloading...");
		myLogger.Goodbye();
		for(Player player : getServer().getOnlinePlayers()) {
			player.sendMessage(ChatColor.AQUA + "[WhereAreYou] " + ChatColor.LIGHT_PURPLE + "Unloaded!");
		}
	}
}