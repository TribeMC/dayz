package protection;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

import dayZ.V3.main;

public class reSpawn implements Listener {

	public static boolean trueFalse;
	public static int time;
	HashMap<Player, Long> god = new HashMap<Player, Long>();
	Plugin DayZ;

	String prefix = ChatColor.BOLD + "" + ChatColor.DARK_RED + "["
			+ ChatColor.RESET + ChatColor.RED + "TRIBE" + ChatColor.GOLD
			+ ChatColor.BOLD + "Z" + ChatColor.DARK_RED + "]" + ChatColor.GRAY
			+ " ";
	
	public reSpawn(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
		DayZ = main;
		remove();
	}

	private void remove() {
		// TODO Auto-generated method stub
		Bukkit.getScheduler().scheduleSyncRepeatingTask(DayZ, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(Player p : Bukkit.getOnlinePlayers()){
					if(god.containsKey(p)){
						if(god.get(p) + time*1000 < System.currentTimeMillis()){
							god.remove(p);
							p.sendMessage(prefix + "Dein Respawnschutz ist nun vorbei!");
						}
					}
				}
			}
		}, 20, 20);
	}

	@EventHandler
	public void onReSpawn(final PlayerRespawnEvent e) {
		if (trueFalse == true) {
			//TEleport
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(DayZ, new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					teleport(e.getPlayer());
					e.getPlayer().sendMessage(prefix + "Du hast nun " + time + "s Respawnschutz!");
				}
			}, 10);
			

			long systemtime = System.currentTimeMillis();
			god.put(e.getPlayer(), systemtime);

		
		}
	}
	
	private void teleport(Player p) {
		// TODO Auto-generated method stub
		
		File Spawns = new File("plugins/DayZ", "Spawns.yml");
		FileConfiguration spawn = YamlConfiguration.loadConfiguration(Spawns);
		int allspawns = -1;
		
		
		for(int i = 0; i < 99 ; i++){
			if(spawn.contains("Spawn." + i + ".world")){
				allspawns++;
			}else{
				break;
			}
		}
		Random r = new Random();
		
		int endspawn = r.nextInt(allspawns);
		
		World w = Bukkit.getWorld(spawn.getString("Spawn." + endspawn + ".world"));
		double x = spawn.getDouble("Spawn." + endspawn + ".x");
		double y = spawn.getDouble("Spawn." + endspawn + ".y");
		double z = spawn.getDouble("Spawn." + endspawn + ".z");
		float yaw = (float) spawn.getDouble("Spawn." + endspawn + ".yaw");
		float pitch = (float) spawn.getDouble("Spawn." + endspawn + ".pitch");
		
		Location tpLoc = new Location(w, x, y, z, yaw, pitch);
		p.teleport(tpLoc);
	}

	@EventHandler
	public void onEDMG(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player && god.containsKey((Player)e.getEntity())){
			e.setCancelled(true);
			if(e.getDamager() instanceof Player){
				Player p = (Player) e.getEntity();
				Player pd = (Player) e.getDamager();
				pd.sendMessage(prefix + "Der Spieler " + p.getDisplayName() + ChatColor.RESET + ChatColor.GRAY + " ist im Respawnschutz!");
			}
		}
		if(e.getDamager() instanceof Player && god.containsKey((Player)e.getDamager())){
			Player p = (Player)e.getDamager();
			if(e.getEntity() instanceof Player){
			p.sendMessage(prefix + "Du bist noch im Respawnschutz!");
			e.setCancelled(true);
			}
			
		}
	}
	
	@EventHandler
	public void onBDMG(EntityDamageByBlockEvent e){
		if(e.getEntity() instanceof Player && god.containsKey((Player)e.getEntity())){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e){
		if(god.containsKey(e.getPlayer())){
			e.getPlayer().setFallDistance(0);
		}
	}
	
	
}
