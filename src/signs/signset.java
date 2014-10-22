package signs;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import dayZ.V3.main;

public class signset implements Listener{

	
	public signset(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void singChange(SignChangeEvent e){
		if(e.getPlayer().hasPermission("dayz.admin")){
			
			if(e.getLine(0).equalsIgnoreCase("like")){
				e.setLine(0, ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "Like" + ChatColor.DARK_GRAY + "]");
				if(e.getLine(2).isEmpty()){
					e.setLine(2, 0 + "");
				}
			}
			
			if(e.getLine(0).equalsIgnoreCase("radar")){
				e.setLine(0, ChatColor.DARK_GRAY + "[" + ChatColor.RED + "Radar" + ChatColor.DARK_GRAY + "]");
				e.setLine(1, "[Player]");
				e.setLine(2, "[X]");
				e.setLine(2, "[Y]");
			}
			
			if(e.getLine(0).equalsIgnoreCase("online")){
				e.setLine(0, ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "Online" + ChatColor.DARK_GRAY + "]");
			}
			
			if(e.getLine(0).equalsIgnoreCase("flug")){
				e.setLine(0, ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "Flug" + ChatColor.DARK_GRAY + "]");
				

				if(e.getLine(1).equalsIgnoreCase("s")){
					e.setLine(1, "");
					e.setLine(2, ChatColor.YELLOW + "[" + ChatColor.RED + "Süden" + ChatColor.YELLOW + "]");
					e.setLine(3, "");
				}
				if(e.getLine(1).equalsIgnoreCase("n")){
					e.setLine(1, "");
					e.setLine(2, ChatColor.YELLOW + "[" + ChatColor.AQUA + "Norden" + ChatColor.YELLOW + "]");
					e.setLine(3, "");
				}
			}
			
		}
	}
}
