package zombie;

import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import dayZ.V3.main;

public class skull implements Listener{

	public skull(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onZombieDeath(EntityDeathEvent e){
		e.setDroppedExp(0);
		if(e.getEntity() instanceof Zombie){
			 DamageCause cause = e.getEntity().getLastDamageCause().getCause();
			 
	            if (cause == DamageCause.ENTITY_ATTACK && e.getEntity().getKiller() != null){
	            	Player killer = e.getEntity().getKiller();
	            	killer.setLevel(killer.getLevel() + 1);
	            }
			
			
		}
	}
}
