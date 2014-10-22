package board;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import dayZ.V3.main;

public class zombiekills implements Listener {

	public zombiekills(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onZombie(EntityDeathEvent e) {
		if (e.getEntityType() == EntityType.ZOMBIE
				&& e.getEntity().getKiller() instanceof Player) {
			Player p = (Player) e.getEntity().getKiller();
			setZKills(p);
		}
	}

	public static void setZKills(Player p) {

		Filee.ZombieKills.addZombieKill(p, 1);

	}
}
