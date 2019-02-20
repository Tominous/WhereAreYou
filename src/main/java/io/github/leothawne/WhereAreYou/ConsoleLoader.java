package io.github.leothawne.WhereAreYou;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class ConsoleLoader {
	private WhereAreYouLoader plugin;
	protected ConsoleLoader(WhereAreYouLoader plugin) {
		this.plugin = plugin;
	}
	private final ConsoleCommandSender getConsoleSender() {
		return plugin.getServer().getConsoleSender();
	}
	public final void Hello() {
		getConsoleSender().sendMessage(ChatColor.AQUA + "__          _______  _    _ ");
		getConsoleSender().sendMessage(ChatColor.AQUA + "\\ \\        / |  __ \\| |  | |");
		getConsoleSender().sendMessage(ChatColor.AQUA + " \\ \\  /\\  / /| |__) | |  | |" + ChatColor.WHITE + "  V: " + Version.getVersionNumber() + " (Minecraft: " + Version.getMinecraftVersion() + ")");
		getConsoleSender().sendMessage(ChatColor.AQUA + "  \\ \\/  \\/ / |  _  /| |  | |" + ChatColor.WHITE + "  Requires Java: " + Version.getJavaVersion());
		getConsoleSender().sendMessage(ChatColor.AQUA + "   \\  /\\  /  | | \\ \\| |__| |" + ChatColor.WHITE + "  Released on: " + Version.getVersionDate());
		getConsoleSender().sendMessage(ChatColor.AQUA + "    \\/  \\/   |_|  \\_\\\\____/ " + ChatColor.WHITE + "  My Twitter: @leonappi_");
	}
	public final void info(String message) {
		getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "WRU " + ChatColor.GREEN + "I" + ChatColor.WHITE + "] " + message);
	}
	public final void warning(String message) {
		getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "WRU " + ChatColor.YELLOW + "W" + ChatColor.WHITE + "] " + message);
	}
	public final void severe(String message) {
		getConsoleSender().sendMessage(ChatColor.WHITE + "[" + ChatColor.AQUA + "WRU " + ChatColor.RED + "E" + ChatColor.WHITE + "] " + message);
	}
}