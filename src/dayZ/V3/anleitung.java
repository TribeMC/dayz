package dayZ.V3;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class anleitung implements CommandExecutor {

	public anleitung(main main) {
		// TODO Auto-generated constructor stub
	}

	public static String prefix = ChatColor.BOLD + "" + ChatColor.DARK_RED + "["
			+ ChatColor.RESET + ChatColor.RED + "TRIBE" + ChatColor.GOLD
			+ ChatColor.BOLD + "Z" + ChatColor.DARK_RED + "]" + ChatColor.GRAY
			+ " ";

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] arg3) {
		// TODO Auto-generated method stub
		if (arg1.getName().equalsIgnoreCase("anleitung")) {
			cs.sendMessage(prefix + "   >==   ANLEITUNG   ==<");
			cs.sendMessage("");
			cs.sendMessage(prefix + "Ziel des Spiels ist es,");
			cs.sendMessage(prefix
					+ " möglichst Lange zu überleben!");
			cs.sendMessage("");
			cs.sendMessage(prefix + "Bekämpfe mit anderen die Zombiearmeen,");
			cs.sendMessage(prefix + " doch aufgepasst: Nicht alle Spieler sind freundlich!");
			cs.sendMessage(prefix + "Damit du auch gegen andere Spieler überleben kannst,");
			cs.sendMessage(prefix + " sammle Items indem du Kisten lootest!");
			
			cs.sendMessage("");
			cs.sendMessage(prefix + "Du musst immer wieder Wasser trinken,");
			cs.sendMessage(prefix + " damit du nicht verdurstest!");
			cs.sendMessage(prefix + "Damit dein Minecrafter deine abwesenheit überlebt,");
			cs.sendMessage(prefix + " nutze den Command " + ChatColor.GREEN + "/Logout" + ChatColor.GRAY + "!");

			return true;
		}
		return false;
	}

}
