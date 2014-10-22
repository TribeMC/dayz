package water;

import java.util.HashMap;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import dayZ.V3.main;

public class unset implements Listener {

	public static HashMap<Player, Integer> water = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> level = new HashMap<Player, Integer>();
	public static int updatetime;
	public static int fulllevel;


	Plugin DayZ;

	public unset(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
		DayZ = main;
		onTick();
	}

	public void onTick() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(DayZ, new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(Bukkit.getOnlinePlayers().length >= 1){
				GetAll();
				
				}
			}
		}, updatetime, updatetime);
	}

	@SuppressWarnings("deprecation")
	public void GetAll() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (level.get(p) > 300 && water.get(p) > 0) {
				water.put(p, water.get(p) - 1);
				level.put(p, 0);
			}
			

			
			if (water.get(p) > 0) {
				BarAPI.setMessage(p, ChatColor.BOLD + "" + ChatColor.BLUE
						+ "Wasserlevel: " + ChatColor.AQUA +water.get(p));
				
				// Sneaken
				if(p.isSneaking() && p.isOnGround()){
					BarAPI.setHealth(p, 50);
					level.put(p, level.get(p) + 10);
				}
				// Laufen
				if(p.isOnGround() && !p.isSprinting() && !p.isSneaking()){
					
					
					BarAPI.setHealth(p, 25);
					level.put(p, level.get(p) + 5);
				}
				// SPRINT normal
				if(p.isSprinting() && p.isOnGround()){
					BarAPI.setHealth(p, 75);
					level.put(p, level.get(p) + 15);
				}
				// SprungSprint
				if(!p.isOnGround() && p.isSprinting()){
					BarAPI.setHealth(p, 100);
					level.put(p, level.get(p) + 20);
				}
				
				
				
			} else {
				BarAPI.setMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Wassermangel");
				if (p.getGameMode() == GameMode.SURVIVAL) {

					p.damage(1);
				} else {
					water.put(p, fulllevel);

				}
			}
		}
	}
}
