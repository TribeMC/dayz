package zombie;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;

public class remove implements Listener {

	ArrayList<Zombie> save = new ArrayList<Zombie>();

	public void getSaved() {
		World w = Bukkit.getWorld("world");
		for (LivingEntity e : w.getLivingEntities()) {
			if (e instanceof Zombie) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.getLocation().distance(e.getLocation()) < 50) {

					}
				}
				
				
			}
		}

	}
}
