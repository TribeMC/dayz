package stats;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import dayZ.V3.main;

public class onlinecmd implements CommandExecutor {

	static String prefix = ChatColor.BOLD + "" + ChatColor.DARK_RED + "["
			+ ChatColor.RESET + ChatColor.RED + "TRIBE" + ChatColor.GOLD
			+ ChatColor.BOLD + "Z" + ChatColor.DARK_RED + "]" + ChatColor.GRAY
			+ " ";

	public onlinecmd(main main) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		// TODO Auto-generated method stub
		if(cs.hasPermission("dayz.admin")){
		if (args.length == 1) {
			
			if (Filee.File.fileExist(args[0]) == false) {

				cs.sendMessage(prefix + "Dieser Spieler hat noch nicht gespielt!");
			} else {
				int days = Filee.PlayTime.getDays(args[0]);
				int hours = Filee.PlayTime.getHours(args[0]);
				int minutes = Filee.PlayTime.getMinutes(args[0]);
				cs.sendMessage(prefix + "Der Spieler " + args[0]
						+ " hat bereits " + days
						+ ((days == 1) ? "Tag, " : "Tage, ") + hours
						+ ((hours == 1) ? "Stunde und " : "Stunden und ")
						+ minutes + ((minutes == 1) ? "Minute" : "Minuten")
						+ " gespielt!");
			}

		} else {

		}
		}else{
			cs.sendMessage(prefix + "Du darfst nicht die Spielerzeit anderer Abfragen!");
		}
		return false;
	}

}
