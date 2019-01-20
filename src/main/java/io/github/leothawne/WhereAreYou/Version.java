package io.github.leothawne.WhereAreYou;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Version {
	private WhereAreYouLoader plugin;
	private ConsoleLoader myLogger;
	public Version(WhereAreYouLoader plugin, ConsoleLoader myLogger) {
		this.plugin = plugin;
		this.myLogger = myLogger;
	}
	private final int configFileVersion = 1;
	private final int english_languageFileVersion = 1;
	private final int portuguese_languageFileVersion = 1;
	private final String WRUVersion = "0.1.0";
	private final String WRUVersion_Date = "99/99/9999 (America/Sao_Paulo)";
	private final String Minecraft_Version = "1.13.X";
	private final String Minecraft_Build = "1.13-R0.1-SNAPSHOT";
	private final String Java_Version = "8+";
	public final void version(CommandSender sender) {
		sender.sendMessage(ChatColor.AQUA + "Where Are You " + ChatColor.YELLOW + "plugin " + ChatColor.GREEN + "" + this.WRUVersion + "" + ChatColor.YELLOW + " (" + ChatColor.GREEN + "" + this.WRUVersion_Date + "" + ChatColor.YELLOW + "), Minecraft " + ChatColor.GREEN + "" + this.Minecraft_Version +  "" + ChatColor.YELLOW + " (Java " + ChatColor.GREEN + "" + this.Java_Version + "" + ChatColor.YELLOW + ", build " + ChatColor.GREEN + "" + this.Minecraft_Build + "" + ChatColor.YELLOW + ").");
	}
	public final void check() {
		try {
			URLConnection allowedUrl = new URL("https://leothawne.github.io/WhereAreYou/api/" + this.WRUVersion + "/plugin.html").openConnection();
			allowedUrl.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			allowedUrl.connect();
			BufferedReader allowedReader = new BufferedReader(new InputStreamReader(allowedUrl.getInputStream(), Charset.forName("UTF-8")));
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = allowedReader.readLine()) != null) {
				sb.append(line);
			}
			if(sb.toString() != null) {
				if(sb.toString().equalsIgnoreCase("disabled")) {
					this.myLogger.severe("Hey you, stop right there! The version " + this.WRUVersion + " is not allowed anymore!");
					this.myLogger.severe("Apologies, but this plugin will now be disabled! Download a newer version to play: [https://dev.bukkit.org/projects/where-are-you]");
					this.plugin.getServer().getPluginManager().disablePlugin(plugin);
				}
			}
		} catch(Exception e) {
			this.myLogger.severe("Plugin: Is this version allowed?");
		}
	}
	public final int getConfigVersion() {
		return this.configFileVersion;
	}
	public final int getLanguageVersion(String language) {
		if(language.equalsIgnoreCase("english")) {
			return this.english_languageFileVersion;
		}
		if(language.equalsIgnoreCase("portuguese")) {
			return this.portuguese_languageFileVersion;
		}
		return 0;
	}
	public final String getPluginVersion() {
		return this.WRUVersion;
	}
	public final String getPluginDate() {
		return this.WRUVersion_Date;
	}
	public final String getMinecraftVersion() {
		return this.Minecraft_Version;
	}
	public final String getMinecraftBuild() {
		return this.Minecraft_Build;
	}
	public final String getJavaVersion() {
		return this.Java_Version;
	}
}
