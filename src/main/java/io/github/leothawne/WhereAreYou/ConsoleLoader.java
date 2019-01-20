package io.github.leothawne.WhereAreYou;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class ConsoleLoader {
	private WhereAreYouLoader plugin;
	public ConsoleLoader(WhereAreYouLoader plugin) {
		this.plugin = plugin;
	}
	private final ConsoleCommandSender newLogger() {
		return this.plugin.getServer().getConsoleSender();
	}
	private String LTIMVersion = new Version(this.plugin, this).getPluginVersion();
	private String LTIMVersion_Date = new Version(this.plugin, this).getPluginDate();
	private String Minecraft_Version = new Version(this.plugin, this).getMinecraftVersion();
	private String Java_Version = new Version(this.plugin, this).getJavaVersion();
	public final void Hello() {
		this.newLogger().sendMessage(ChatColor.AQUA + ".  .   .  .                   .            .   .      ");
		this.newLogger().sendMessage(ChatColor.AQUA + " \\  \\ /  /|                  / \\            \\ /       " + ChatColor.LIGHT_PURPLE + "  V " + this.LTIMVersion + " (Minecraft " + this.Minecraft_Version + ")");
		this.newLogger().sendMessage(ChatColor.AQUA + "  \\  \\  / |--..-..--.-.     /___\\ .--.-.     :.-..  . " + ChatColor.LIGHT_PURPLE + "  Works with Java " + this.Java_Version);
		this.newLogger().sendMessage(ChatColor.AQUA + "   \\/ \\/  |  (.-'| (.-'    /     \\| (.-'     (   |  | " + ChatColor.LIGHT_PURPLE + "  Released on " + this.LTIMVersion_Date);
		this.newLogger().sendMessage(ChatColor.AQUA + "    ' '   '  ``--'  `--'  '       '  `--'    '`-'`--`-" + ChatColor.LIGHT_PURPLE + "  Twitter @leothawne");
		this.newLogger().sendMessage("");
	}
	public final void Goodbye() {
		this.newLogger().sendMessage(ChatColor.AQUA + " .--.           ..           ");
		this.newLogger().sendMessage(ChatColor.AQUA + ":               ||           ");
		this.newLogger().sendMessage(ChatColor.AQUA + "| --..-. .-. .-.||.-..  ..-. ");
		this.newLogger().sendMessage(ChatColor.AQUA + ":   (   (   (   ||   |  (.-' ");
		this.newLogger().sendMessage(ChatColor.AQUA + " `--'`-' `-' `-'`'`-'`--|`--'");
		this.newLogger().sendMessage(ChatColor.AQUA + "                        ;    ");
		this.newLogger().sendMessage(ChatColor.AQUA + "                     `-'     ");
		this.newLogger().sendMessage("");
	}
	public final void info(String message) {
		this.newLogger().sendMessage(ChatColor.AQUA + "[Where Are You " + ChatColor.WHITE + "INFO" + ChatColor.AQUA + "] " + ChatColor.LIGHT_PURPLE + "" + message);
	}
	public final void warning(String message) {
		this.newLogger().sendMessage(ChatColor.AQUA + "[Where Are You " + ChatColor.YELLOW + "WARNING" + ChatColor.AQUA + "] " + ChatColor.LIGHT_PURPLE + "" + message);
	}
	public final void severe(String message) {
		this.newLogger().sendMessage(ChatColor.AQUA + "[Where Are You " + ChatColor.RED + "ERROR" + ChatColor.AQUA + "] " + ChatColor.LIGHT_PURPLE + "" + message);
	}
}