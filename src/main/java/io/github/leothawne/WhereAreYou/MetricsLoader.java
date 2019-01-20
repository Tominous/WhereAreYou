package io.github.leothawne.WhereAreYou;

import io.github.leothawne.WhereAreYou.api.bStats.Metrics;

public class MetricsLoader {
	private WhereAreYouLoader plugin;
	private ConsoleLoader myLogger;
	public MetricsLoader(WhereAreYouLoader plugin, ConsoleLoader myLogger) {
		this.plugin = plugin;
		this.myLogger = myLogger;
	}
	public final void init() {
		Metrics metrics = new Metrics(this.plugin);
		if(metrics.isEnabled() == true) {
			this.myLogger.info("Where Are You is using bStats to collect data to improve the next versions. In case you want to know what data will be collected: [https://bstats.org/getting-started]");
		} else {
			this.myLogger.warning("bStats is disabled and Where Are You cannot use it. Please enable bStats! Thank you.");
		}
	}
}