package io.github.leothawne.WhereAreYou;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationLoader {
	private WhereAreYouLoader plugin;
	private ConsoleLoader myLogger;
	public ConfigurationLoader(WhereAreYouLoader plugin, ConsoleLoader myLogger) {
		this.plugin = plugin;
		this.myLogger = myLogger;
	}
	private File configFile;
	public final void check() {
		this.myLogger.info("Looking for config file...");
		this.configFile = new File(this.plugin.getDataFolder(), "config.yml");
		if(this.configFile.exists() == false) {
			this.myLogger.warning("Config file not found. Creating a new one...");
			this.plugin.saveDefaultConfig();
			this.myLogger.info("New config file created.");
		} else {
			this.myLogger.info("Config file found.");
		}
	}
	public final FileConfiguration load() {
		this.myLogger.info("Loading config file...");
		this.configFile = new File(this.plugin.getDataFolder(), "config.yml");
		if(this.configFile.exists()) {
			FileConfiguration configuration = this.plugin.getConfig();
			this.myLogger.info("Config file loaded.");
			if(configuration.getInt("config-version") != new Version(this.plugin, this.myLogger).getConfigVersion()) {
				this.myLogger.severe("The config.yml file is outdated! You must manually delete the config.yml file and reload the plugin.");
			}
			return configuration;
		}
		this.myLogger.severe("A config file was not found to be loaded.");
		this.myLogger.severe("Running without config file. You will face several errors from this point.");
		return null;
	}
}