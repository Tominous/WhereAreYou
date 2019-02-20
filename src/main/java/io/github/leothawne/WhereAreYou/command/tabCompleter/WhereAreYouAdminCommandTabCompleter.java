package io.github.leothawne.WhereAreYou.command.tabCompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.google.common.collect.ImmutableList;

import io.github.leothawne.WhereAreYou.api.utility.TabCompleterAPI;

public class WhereAreYouAdminCommandTabCompleter extends TabCompleterAPI implements TabCompleter {
	@Override
	public final List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args){
		List<String> ReturnNothing = new ArrayList<>();
		if(sender.hasPermission("WhereAreYou.use") && sender.hasPermission("WhereAreYou.admin") && cmd.getName().equalsIgnoreCase("whereareyouadmin")) {
			if(args.length == 1) {
				ImmutableList<String> WRU = ImmutableList.of("version", "find");
				return partial(args[0], WRU);
			} else if(args.length == 2 && args[0].equalsIgnoreCase("find")) {
				return null;
			}
		}
		return ReturnNothing;
	}
}