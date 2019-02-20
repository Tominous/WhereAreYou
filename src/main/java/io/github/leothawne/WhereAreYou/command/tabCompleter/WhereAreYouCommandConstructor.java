package io.github.leothawne.WhereAreYou.command.tabCompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.google.common.collect.ImmutableList;

import io.github.leothawne.WhereAreYou.api.utility.TabCompleterAPI;

public class WhereAreYouCommandConstructor extends TabCompleterAPI implements TabCompleter {
	@Override
	public final List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args){
		List<String> ReturnNothing = new ArrayList<>();
		if(sender.hasPermission("WhereAreYou.use") && cmd.getName().equalsIgnoreCase("whereareyou")) {
			if(args.length == 1) {
				ImmutableList<String> WRU = ImmutableList.of("version");
				return partial(args[0], WRU);
			}
		}
		return ReturnNothing;
	}
}