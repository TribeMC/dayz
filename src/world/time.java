package world;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import dayZ.V3.main;

public class time implements Listener{

	public static int time;
	public static int resettime;
	World w = Bukkit.getWorld("world");
	Plugin DayZ;
	
	public time(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
		DayZ = main;
		reMake();
	}

	public void reMake(){
		Bukkit.getScheduler().scheduleSyncRepeatingTask(DayZ, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				FreeZe();
				board.scoreboard.timer();
			}
		}, 20*resettime, 20*resettime);
	}
	
	public void FreeZe(){
		w.setTime(time);
		w.setStorm(false);
	}
	
	
	
}
