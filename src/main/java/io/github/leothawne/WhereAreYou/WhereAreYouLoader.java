package io.github.leothawne.WhereAreYou;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.leothawne.WhereAreYou.command.WhereAreYouAdminCommand;
import io.github.leothawne.WhereAreYou.command.WhereAreYouCommand;
import io.github.leothawne.WhereAreYou.command.tabCompleter.WhereAreYouAdminCommandTabCompleter;
import io.github.leothawne.WhereAreYou.command.tabCompleter.WhereAreYouCommandConstructor;
import io.github.leothawne.WhereAreYou.event.player.AdminEvent;

public class WhereAreYouLoader extends JavaPlugin {
	private final ConsoleLoader myLogger = new ConsoleLoader(this);
	public final void registerEvents(Listener...listeners) {
		for(Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, this);
		}
	}
	private static FileConfiguration configuration;
	private static FileConfiguration language;
	@Override
	public final void onEnable() {
		myLogger.Hello();
		myLogger.info("Loading...");
		new ConfigurationLoader(this, myLogger);
		ConfigurationLoader.check();
		new ConfigurationLoader(this, myLogger);
		configuration = ConfigurationLoader.load();
		if(configuration.getBoolean("enable-plugin") == true) {
			new MetricsLoader(this, myLogger);
			MetricsLoader.init();
			new LanguageLoader(this, myLogger, configuration);
			LanguageLoader.check();
			new LanguageLoader(this, myLogger, configuration);
			language = LanguageLoader.load();
			getCommand("whereareyou").setExecutor(new WhereAreYouCommand(this, myLogger, language));
			getCommand("whereareyouadmin").setExecutor(new WhereAreYouAdminCommand(this, myLogger, language));
			getCommand("whereareyou").setTabCompleter(new WhereAreYouCommandConstructor());
			getCommand("whereareyouadmin").setTabCompleter(new WhereAreYouAdminCommandTabCompleter());
			registerEvents(new AdminEvent(configuration));
			new Version(this, myLogger);
			Version.check();
		} else {
			myLogger.severe("You manually choose to disable this plugin.");
			getServer().getPluginManager().disablePlugin(this);
		}
	}
	@Override
	public final void onDisable() {
		myLogger.info("Unloading...");
	}
}