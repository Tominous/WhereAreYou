package io.github.leothawne.WhereAreYou.command.tabCompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class WhereAreYouAdminCommandTabCompleter implements TabCompleter {
	@Override
	public final List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args){
		List<String> ReturnNothing = new ArrayList<>();
		if(sender.hasPermission("WhereAreYou.use") && sender.hasPermission("WhereAreYou.admin") && cmd.getName().equalsIgnoreCase("whereareyouadmin")) {
			if(args.length == 1) {
				List<String> WRU = new ArrayList<>();
				WRU.add("version");
				if(sender.hasPermission("WhereAreYou.find")) {
					WRU.add("find");
				}
				return WRU;
			} else if(args.length == 2 && args[0].equalsIgnoreCase("find")) {
				return null;
			}
		}
		return ReturnNothing;
	}
}