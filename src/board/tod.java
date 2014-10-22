package board;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import dayZ.V3.main;

public class tod implements Listener {

	public tod(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if(e.getEntity().getKiller() instanceof Player){
			Player p = e.getEntity().getKiller();
			setPKills(p);
		}
		Player pd = (Player) e.getEntity();
		setTode(pd);

	}
	
	public static void setPKills(Player p){
		Filee.PlayerKills.addPlayerKill(p, 1);
		
	}
	
	public static void setTode(Player pd){
		Filee.Tode.addTod(pd, 1);
		
	}
}
