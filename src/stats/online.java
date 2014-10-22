package stats;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import dayZ.V3.main;

public class online implements Listener{

	static String prefix = ChatColor.BOLD + "" + ChatColor.DARK_RED + "["
			+ ChatColor.RESET + ChatColor.RED + "TRIBE" + ChatColor.GOLD
			+ ChatColor.BOLD + "Z" + ChatColor.DARK_RED + "]" + ChatColor.GRAY
			+ " ";
	Plugin dayZ;
	public online(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
		
		dayZ = main;
		
		onTimer();
	}

	public void onTimer(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(dayZ, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				allOnline();
				//board.topscore.getTop();
			}
		}, 20*60, 20*60);
	}
	
	public void allOnline(){
		
		for(Player p : Bukkit.getOnlinePlayers()){
			Filee.PlayTime.addMinute(p);
		}
	}

	
	
	
}
