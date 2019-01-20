package io.github.leothawne.WhereAreYou;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LanguageLoader {
	private WhereAreYouLoader plugin;
	private ConsoleLoader myLogger;
	private FileConfiguration configuration;
	public LanguageLoader(WhereAreYouLoader plugin, ConsoleLoader myLogger, FileConfiguration configuration) {
		this.plugin = plugin;
		this.myLogger = myLogger;
		this.configuration = configuration;
	}
	private File languageFile;
	public final void check() {
		this.myLogger.info("Looking for language file...");
		this.languageFile = new File(this.plugin.getDataFolder(), this.configuration.getString("language") + ".yml");
		if(this.languageFile.exists() == false) {
			this.myLogger.warning("Language file not found. Extracting...");
			if(this.configuration.getString("language").equalsIgnoreCase("english") || this.configuration.getString("language").equalsIgnoreCase("portuguese")) {
				this.plugin.saveResource(configuration.getString("language") + ".yml", true);
				this.myLogger.warning(configuration.getString("language") + ".yml extracted!");
			} else {
				this.myLogger.severe(configuration.getString("language") + ".yml is not supported yet. I suggest you to manually create the language file and do manually the translation.");
			}
			
		} else {
			this.myLogger.info(configuration.getString("language") + ".yml file found.");
		}
	}
	public final FileConfiguration load() {
		this.myLogger.info("Loading language file...");
		this.languageFile = new File(this.plugin.getDataFolder(), this.configuration.getString("language") + ".yml");
		if(this.languageFile.exists()) {
			FileConfiguration languageConfig = new YamlConfiguration();
			try {
				languageConfig.load(languageFile);
				this.myLogger.info(this.configuration.getString("language") + ".yml file loaded.");
				int languageVersion = new Version(plugin, myLogger).getLanguageVersion(this.configuration.getString("language"));
				if(languageVersion != 0) {
					if(languageConfig.getInt("language-version") != languageVersion) {
						this.myLogger.severe("The " + this.configuration.getString("language") + ".yml file is outdated! You must manually delete the " + this.configuration.getString("language") + ".yml file and reload the plugin.");
					}
				}
				return languageConfig;
			} catch(IOException | InvalidConfigurationException exception) {
				exception.printStackTrace();
			}
			return null;
		}
		this.myLogger.severe("A config file was not found to be loaded.");
		this.myLogger.severe("Running without config file. You will face several errors from this point.");
		return null;
	}
}