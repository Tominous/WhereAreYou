package io.github.leothawne.WhereAreYou;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class ConsoleLoader {
	private WhereAreYouLoader plugin;
	protected ConsoleLoader(WhereAreYouLoader plugin) {
		this.plugin = plugin;
	}
	private final ConsoleCommandSender newLogger() {
		return plugin.getServer().getConsoleSender();
	}
	private final String LTIMVersion = Version.getVersionNumber();
	private final String LTIMVersion_Date = Version.getVersionDate();
	private final String Minecraft_Version = Version.getMinecraftVersion();
	private final String Java_Version = Version.getJavaVersion();
	protected final void Hello() {
		newLogger().sendMessage(ChatColor.AQUA + ".  .   .  .                   .            .   .      ");
		newLogger().sendMessage(ChatColor.AQUA + " \\  \\ /  /|                  / \\            \\ /       " + ChatColor.LIGHT_PURPLE + "  V " + LTIMVersion + " (Minecraft " + Minecraft_Version + ")");
		newLogger().sendMessage(ChatColor.AQUA + "  \\  \\  / |--..-..--.-.     /___\\ .--.-.     :.-..  . " + ChatColor.LIGHT_PURPLE + "  Works with Java " + Java_Version);
		newLogger().sendMessage(ChatColor.AQUA + "   \\/ \\/  |  (.-'| (.-'    /     \\| (.-'     (   |  | " + ChatColor.LIGHT_PURPLE + "  Released on " + LTIMVersion_Date);
		newLogger().sendMessage(ChatColor.AQUA + "    ' '   '  ``--'  `--'  '       '  `--'    '`-'`--`-" + ChatColor.LIGHT_PURPLE + "  Twitter @leonappi_");
		newLogger().sendMessage("");
	}
	protected final void Goodbye() {
		newLogger().sendMessage(ChatColor.AQUA + " .--.           ..           ");
		newLogger().sendMessage(ChatColor.AQUA + ":               ||           ");
		newLogger().sendMessage(ChatColor.AQUA + "| --..-. .-. .-.||.-..  ..-. ");
		newLogger().sendMessage(ChatColor.AQUA + ":   (   (   (   ||   |  (.-' ");
		newLogger().sendMessage(ChatColor.AQUA + " `--'`-' `-' `-'`'`-'`--|`--'");
		newLogger().sendMessage(ChatColor.AQUA + "                        ;    ");
		newLogger().sendMessage(ChatColor.AQUA + "                     `-'     ");
		newLogger().sendMessage("");
	}
	public final void info(String message) {
		newLogger().sendMessage(ChatColor.AQUA + "[Where Are You " + ChatColor.WHITE + "INFO" + ChatColor.AQUA + "] " + ChatColor.LIGHT_PURPLE + "" + message);
	}
	public final void warning(String message) {
		newLogger().sendMessage(ChatColor.AQUA + "[Where Are You " + ChatColor.YELLOW + "WARNING" + ChatColor.AQUA + "] " + ChatColor.LIGHT_PURPLE + "" + message);
	}
	public final void severe(String message) {
		newLogger().sendMessage(ChatColor.AQUA + "[Where Are You " + ChatColor.RED + "ERROR" + ChatColor.AQUA + "] " + ChatColor.LIGHT_PURPLE + "" + message);
	}
}