package move;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import dayZ.V3.main;

public class flight implements Listener{


	static Plugin plugin;
	public flight(main main) {
		// TODO Auto-generated constructor stub
		plugin = main;
	}

	public static void onFlight(boolean norden, Player p){
		Demage.logg.it.put(p.getName(), System.currentTimeMillis() + 1000*30);
		if(norden  == true){
			p.sendMessage("Nordflug");
			teleportNordFlug(p);
		}else{
			p.sendMessage("Südflug");
			teleportSuedFlug(p);
			
		}
	}
	
	public static void teleportNordFlug(Player p){
		p.sendMessage("Bist aufm Flug nach Norden");
		p.teleport(new Location(Bukkit.getWorld("world"), -250, 200, -250));
		boolean nord = true;
		fly(nord, p);
	}
	
	public static void teleportSuedFlug(Player p){
		p.sendMessage("Bist auf nem Flug nach Sueden");
		
		p.teleport(new Location(Bukkit.getWorld("world"), 250, 200, 250));
		boolean nord = false;
		fly(nord, p);
	}
	
	public static void fly(boolean norden, Player p){
		final Player pd = p;
		final boolean nord = norden;
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				if(isIn(pd) == true){
					pd.sendMessage("Du landest nun!");
					
					if(nord == true){
						landenNord(pd);
					}else{
						landenSued(pd);
					}
				}else{
					pd.sendMessage("gesprungen");
				}
			}

			private boolean isIn(Player pd) {
				// TODO Auto-generated method stub
				if(pd.getLocation().getY() >= 198){
					return true;
				}else{
				return false;
				}
			}
		}, 45*20);
	}
	
	public static void landenNord(Player p){
		final Player pd = p;
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				pd.teleport(new Location(Bukkit.getWorld("world"), -400, 48, -450));
				pd.sendMessage("gelandet");
			}
		}, 5*20);
	}
	
	public static void landenSued(Player p){
		final Player pd = p;
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				pd.teleport(new Location(Bukkit.getWorld("world"), -400, 48, -450));
				pd.sendMessage("gelandet");
			}
		}, 5*20);
	}
}
