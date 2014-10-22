package mail;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import dayZ.V3.main;

public class mailcmd implements CommandExecutor {

	Plugin DayZ;

	String prefix = ChatColor.BOLD + "" + ChatColor.DARK_RED + "["
			+ ChatColor.RESET + ChatColor.RED + "TRIBE" + ChatColor.GOLD
			+ ChatColor.BOLD + "Z" + ChatColor.DARK_RED + "]" + ChatColor.GRAY
			+ " ";

	public mailcmd(main main) {
		// TODO Auto-generated constructor stub
		DayZ = main;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(final CommandSender cs, Command arg1, String arg2,
			String[] args) {
		// TODO Auto-generated method stub
		if (args.length <= 1) {
			cs.sendMessage(prefix
					+ "Füge einen Spielernamen und die Nachricht an!");
		} else {
			final Player getmsg = Bukkit.getPlayer(args[0]);
			Player p = (Player) cs;
			if(p.getLevel() >= 1){
			if (getmsg.isOnline()) {

				cs.sendMessage(prefix + "Die Nachricht wird gesendet!");
				p.setLevel(p.getLevel() - 1);
				String msg = "";
				for (int i = 1; i < args.length; i++) {
					msg = msg + args[i] + " ";
				}
				final String msgrdy = msg;

				if (cs.hasPermission("dayz.mail.premium")) {

					Bukkit.getScheduler().scheduleSyncDelayedTask(DayZ,
							new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									getmsg.sendMessage(prefix
											+ ChatColor.STRIKETHROUGH + ""
											+ ChatColor.BOLD + ""
											+ ChatColor.DARK_GREEN
											+ "========================================");
									getmsg.sendMessage(prefix
											+ "Der Spieler "
											+ cs.getName()
											+ " hat dir eine Nachricht geschickt:");
									getmsg.sendMessage(prefix + msgrdy);

									getmsg.sendMessage(prefix
											+ ChatColor.STRIKETHROUGH + ""
											+ ChatColor.BOLD + ""
											+ ChatColor.DARK_GREEN
											+ "========================================");
									cs.sendMessage(prefix + "Deine Nachricht ist bei " + getmsg.getName() + " angekommen!");

								}
							}, 20 * 5);
				} else {

					Bukkit.getScheduler().scheduleSyncDelayedTask(DayZ,
							new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									getmsg.sendMessage(prefix
											+ ChatColor.STRIKETHROUGH + ""
											+ ChatColor.BOLD + ""
											+ ChatColor.DARK_GREEN
											+ "========================================");
									getmsg.sendMessage(prefix
											+ "Der Spieler "
											+ cs.getName()
											+ " hat dir eine Nachricht geschickt:");
									getmsg.sendMessage(prefix + msgrdy);

									getmsg.sendMessage(prefix
											+ ChatColor.STRIKETHROUGH + ""
											+ ChatColor.BOLD + ""
											+ ChatColor.DARK_GREEN
											+ "========================================");
									cs.sendMessage(prefix + "Deine Nachricht ist bei " + getmsg.getName() + " angekommen!");

								}
							}, 20 * 10);
				}

			} else {
				cs.sendMessage(prefix + "Der Spieler ist nicht Online!");
			}
			}else{
				cs.sendMessage(prefix + "Du hast nicht genügen Level!");
			}
		}
		return false;
	}

}
