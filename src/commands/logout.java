package commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dayZ.V3.main;

public class logout implements CommandExecutor{
	
	String prefix = ChatColor.BOLD + "" + ChatColor.DARK_RED + "["
			+ ChatColor.RESET + ChatColor.RED + "TRIBE" + ChatColor.GOLD
			+ ChatColor.BOLD + "Z" + ChatColor.DARK_RED + "]" + ChatColor.GRAY
			+ " ";

	public logout(main main) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] arg3) {
		// TODO Auto-generated method stub
		
		if(cs instanceof Player){
			Player p = (Player) cs;
			
			if(Demage.logg.it.containsKey(p.getName())){
				p.sendMessage(prefix + "Du bist noch im Kampf!");
			}else{
				p.kickPlayer(prefix + "Dein Minecrafter wird deine Abwesenheit überleben!");
			}
		}
		return false;
	}

	
}
