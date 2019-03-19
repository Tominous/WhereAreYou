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

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.leothawne.WhereAreYou.api.bStats.MetricsAPI;
import io.github.leothawne.WhereAreYou.command.WhereAreYouAdminCommand;
import io.github.leothawne.WhereAreYou.command.WhereAreYouCommand;
import io.github.leothawne.WhereAreYou.command.tabCompleter.WhereAreYouAdminCommandTabCompleter;
import io.github.leothawne.WhereAreYou.command.tabCompleter.WhereAreYouCommandTabCompleter;
import io.github.leothawne.WhereAreYou.event.AdminEvent;
import io.github.leothawne.WhereAreYou.event.SignEvent;
import io.github.leothawne.WhereAreYou.task.VersionTask;

/**
 * Main class.
 * 
 * @author leothawne
 *
 */
public class WhereAreYou extends JavaPlugin {
	private final ConsoleLoader myLogger = new ConsoleLoader(this);
	private final void registerEvents(Listener...listeners) {
		for(Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, this);
		}
	}
	private static FileConfiguration configuration;
	private static FileConfiguration language;
	private static MetricsAPI metrics;
	private static BukkitScheduler scheduler;
	private static int versionTask;
	/**
	 * 
	 * @deprecated Not for public use.
	 * 
	 */
	@Override
	public final void onEnable() {
		myLogger.Hello();
		myLogger.info("Loading...");
		ConfigurationLoader.check(this, myLogger);
		configuration = ConfigurationLoader.load(this, myLogger);
		if(configuration.getBoolean("enable-plugin") == true) {
			MetricsLoader.init(this, myLogger, metrics);
			LanguageLoader.check(this, myLogger, configuration);
			language = LanguageLoader.load(this, myLogger, configuration);
			getCommand("whereareyou").setExecutor(new WhereAreYouCommand(myLogger, language));
			getCommand("whereareyouadmin").setExecutor(new WhereAreYouAdminCommand(this, myLogger, configuration, language));
			getCommand("whereareyou").setTabCompleter(new WhereAreYouCommandTabCompleter());
			getCommand("whereareyouadmin").setTabCompleter(new WhereAreYouAdminCommandTabCompleter(this));
			scheduler = getServer().getScheduler();
			versionTask = scheduler.scheduleAsyncRepeatingTask(this, new VersionTask(this, myLogger, Version.getVersionNumber(), Version.getPluginURL()), 0, 20 * 1800);
			registerEvents(new AdminEvent(configuration), new SignEvent(this, language));
		} else {
			myLogger.severe("You choose to disable this plugin.");
			getServer().getPluginManager().disablePlugin(this);
		}
	}
	/**
	 * 
	 * @deprecated Not for public use.
	 * 
	 */
	@Override
	public final void onDisable() {
		myLogger.info("Unloading...");
		if(scheduler.isCurrentlyRunning(versionTask)) {
			scheduler.cancelTask(versionTask);
		}
	}
}