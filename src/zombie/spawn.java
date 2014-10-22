package zombie;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import dayZ.V3.main;

@SuppressWarnings("unused")
public class spawn implements Listener{

	public spawn(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);

	}
	
	@EventHandler
	public void onMobspawn(CreatureSpawnEvent e){
		if(e.getEntity() instanceof Zombie){
			Zombie z = (Zombie) e.getEntity();
			z.setCanPickupItems(false);
			
			if(z.isBaby() == true){
				e.setCancelled(true);
			}
		}else{
			e.setCancelled(true);
		}
	}
	
	
	/*
	@EventHandler
	public void onSpawn(CreatureSpawnEvent e){
		if(e.getEntity() instanceof Zombie){
			Zombie z = (Zombie) e.getEntity();
			if(z.isBaby()){
				e.setCancelled(true);
			}
			z.setCanPickupItems(false);
			e.getEntity().setRemoveWhenFarAway(true);
			
			if(e.getLocation().getChunk().getEntities().length > 3){
				e.setCancelled(true);
				
			}
			/*for(Player p : Bukkit.getOnlinePlayers()){
				if(p.getLocation().distance(e.getLocation()) < 50){
					if(p.getGameMode() == GameMode.CREATIVE){
						e.setCancelled(true);
					}else{
						e.setCancelled(false);
					}
				}else{
					e.setCancelled(true);
				}
			}
		}else{
			e.setCancelled(true);
		
			}else{
				e.setCancelled(true);
		}
	}
	
	*/

}
