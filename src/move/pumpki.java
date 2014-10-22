package move;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import dayZ.V3.main;

public class pumpki implements Listener{


	public pumpki(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInter(PlayerInteractEvent e){
		if(e.getPlayer().hasPermission("dayz.premium")){
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
				if(e.getClickedBlock().getType() == Material.PUMPKIN){
					
					World w = e.getClickedBlock().getLocation().getWorld();
					w.getBlockAt(e.getClickedBlock().getLocation()).setType(Material.getMaterial(91));
					return;
				}
				if(e.getClickedBlock().getType() == Material.getMaterial(91)){
					World w = e.getClickedBlock().getLocation().getWorld();
					w.getBlockAt(e.getClickedBlock().getLocation()).setType(Material.getMaterial(86));
					return;
				}
			}
		}
	}
	

}
