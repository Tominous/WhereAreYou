package io.github.leothawne.WhereAreYou.commands.constructors;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class WhereAreYouCommandConstructor implements TabCompleter {
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args){
		List<String> ReturnNothing = new ArrayList<>();
		
		return ReturnNothing;
	}
}