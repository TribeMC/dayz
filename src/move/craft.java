package move;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import dayZ.V3.main;

public class craft implements Listener{

	
	public craft(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onCreft(CraftItemEvent e){
		e.setCancelled(true);
	}
}
