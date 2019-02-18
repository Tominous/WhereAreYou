package io.github.leothawne.WhereAreYou.command.tabCompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class WhereAreYouCommandConstructor implements TabCompleter {
	@Override
	public final List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args){
		List<String> ReturnNothing = new ArrayList<>();
		if(sender.hasPermission("WhereAreYou.use") && cmd.getName().equalsIgnoreCase("whereareyou")) {
			if(args.length == 1) {
				List<String> WRU = new ArrayList<>();
				WRU.add("version");
				return WRU;
			}
		}
		return ReturnNothing;
	}
}