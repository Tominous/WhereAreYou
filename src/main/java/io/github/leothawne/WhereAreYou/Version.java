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

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Version {
	private static final int configFileVersion = 1;
	private static final int english_languageFileVersion = 1;
	private static final int portuguese_languageFileVersion = 1;
	private static final String Plugin_Version = "0.1.0";
	private static final String Plugin_Date = "13/03/2019 23:30 (BRT)";
	private static final String Minecraft_Version = "1.13.x";
	private static final String Minecraft_Build = "1.13-R0.1-SNAPSHOT";
	private static final String Java_Version = "8+";
	private static final String Update_URL = "https://leothawne.github.io/WhereAreYou/api/1.13.2.html";
	private static final String Plugin_URL = "https://leothawne.github.io/WhereAreYou/api/" + Plugin_Version + "/plugin.html";
	private static final String Twitter_Username = "leonappi_";
	public static final int getConfigVersion() {
		return configFileVersion;
	}
	public static final int getLanguageVersion(String language) {
		if(language.equals("portuguese")) {
			return portuguese_languageFileVersion;
		} else if(language.equals("english")) {
			return english_languageFileVersion;
		}
		return 0;
	}
	public static final String getVersionNumber() {
		return Plugin_Version;
	}
	public static final String getVersionDate() {
		return Plugin_Date;
	}
	public static final String getMinecraftVersion() {
		return Minecraft_Version;
	}
	public static final String getMinecraftBuild() {
		return Minecraft_Build;
	}
	public static final String getJavaVersion() {
		return Java_Version;
	}
	public static final String getUpdateURL() {
		return Update_URL;
	}
	public static final String getPluginURL() {
		return Plugin_URL;
	}
	public static final String getTwitterUsername() {
		return Twitter_Username;
	}
	public static final void version(CommandSender sender) {
		sender.sendMessage(ChatColor.AQUA + "Where Are You " + ChatColor.YELLOW + "plugin " + ChatColor.GREEN + "" + Plugin_Version + "" + ChatColor.YELLOW + " (" + ChatColor.GREEN + "" + Plugin_Date + "" + ChatColor.YELLOW + "), Minecraft " + ChatColor.GREEN + "" + Minecraft_Version +  "" + ChatColor.YELLOW + " (Java " + ChatColor.GREEN + "" + Java_Version + "" + ChatColor.YELLOW + ", build " + ChatColor.GREEN + "" + Minecraft_Build + "" + ChatColor.YELLOW + ").");
	}
}
