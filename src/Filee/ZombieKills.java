package Filee;

import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import dayZ.V3.main;

public class ZombieKills implements Listener {

	public ZombieKills(main main) {
		// TODO Auto-generated constructor stub
	}

	public static void addZombieKill(Player p, int i) {

		java.io.File f = new java.io.File("plugins/DayZ/PlayerData", p.getName().toLowerCase() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		int kills = 0;
		if(cfg.contains("Stats.Zombies")){
			kills = cfg.getInt("Stats.Zombies");
		}
		kills = kills + i;
		
		cfg.set("Stats.Zombies", kills);

		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setZombieKills(String p, int i) {
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData", p.toLowerCase() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		int kills = 0;
		
		cfg.set("Stats.Zombies", kills);
		kills = kills + i;
		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int getZombieKills(String p) {
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData", p.toLowerCase() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

		int kills = 0;
		if(cfg.contains("Stats.Zombies")){
			kills = cfg.getInt("Stats.Zombies");
		}
		return kills;

		
	}
}
