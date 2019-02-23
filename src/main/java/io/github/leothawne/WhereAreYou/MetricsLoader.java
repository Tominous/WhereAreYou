/*
 * Copyright (C) 2019 Murilo Amaral Nappi (murilonappi@gmail.com)
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

import io.github.leothawne.WhereAreYou.api.bStats.MetricsAPI;

public class MetricsLoader {
	private static WhereAreYouLoader plugin;
	private static ConsoleLoader myLogger;
	protected MetricsLoader(WhereAreYouLoader plugin, ConsoleLoader myLogger) {
		MetricsLoader.plugin = plugin;
		MetricsLoader.myLogger = myLogger;
	}
	protected static final void init() {
		MetricsAPI metrics = new MetricsAPI(plugin);
		if(metrics.isEnabled() == true) {
			myLogger.info("Where Are You is using bStats to collect data to improve the next versions. In case you want to know what data will be collected: [https://bstats.org/getting-started]");
		} else {
			myLogger.warning("bStats is disabled and Where Are You cannot use it. Please enable bStats! Thank you.");
		}
	}
}