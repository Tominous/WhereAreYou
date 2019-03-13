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
package io.github.leothawne.WhereAreYou;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LanguageLoader {
	public static final void check(WhereAreYou plugin, ConsoleLoader myLogger, FileConfiguration configuration) {
		myLogger.info("Looking for language file...");
		File languageFile = new File(plugin.getDataFolder(), configuration.getString("language") + ".yml");
		if(languageFile.exists() == false) {
			myLogger.warning("Language file not found. Extracting...");
			if(configuration.getString("language").equalsIgnoreCase("english") || configuration.getString("language").equalsIgnoreCase("portuguese")) {
				plugin.saveResource(configuration.getString("language") + ".yml", true);
				myLogger.warning(configuration.getString("language") + ".yml extracted!");
			} else {
				myLogger.severe(configuration.getString("language") + ".yml is not supported yet. I suggest you to manually create the language file and do manually the translation.");
			}
		} else {
			myLogger.info(configuration.getString("language") + ".yml file found.");
		}
	}
	public static final FileConfiguration load(WhereAreYou plugin, ConsoleLoader myLogger, FileConfiguration configuration) {
		myLogger.info("Loading language file...");
		File languageFile = new File(plugin.getDataFolder(), configuration.getString("language") + ".yml");
		if(languageFile.exists()) {
			FileConfiguration languageConfig = new YamlConfiguration();
			try {
				languageConfig.load(languageFile);
				myLogger.info(configuration.getString("language") + ".yml file loaded.");
				int languageVersion = 0;
				if(configuration.getString("language").equalsIgnoreCase("english") || configuration.getString("language").equalsIgnoreCase("portuguese")) {
					languageVersion = Version.getLanguageVersion(configuration.getString("language"));
				}
				if(languageVersion != 0) {
					if(languageConfig.getInt("language-version") != languageVersion) {
						myLogger.severe("The " + configuration.getString("language") + ".yml file is outdated! You must manually delete the " + configuration.getString("language") + ".yml file and reload the plugin.");
					}
				}
				return languageConfig;
			} catch(IOException | InvalidConfigurationException exception) {
				exception.printStackTrace();
			}
			return null;
		}
		myLogger.severe("A config file was not found to be loaded.");
		myLogger.severe("Running without config file. You will face several errors from this point.");
		return null;
	}
}