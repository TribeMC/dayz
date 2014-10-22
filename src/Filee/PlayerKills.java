package Filee;

import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import dayZ.V3.main;

public class PlayerKills implements Listener{

	
	public PlayerKills(main main) {
		// TODO Auto-generated constructor stub
	}

	public static void addPlayerKill(Player p, int i){
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData", p.getName().toLowerCase() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		int kills = 0;
		if(cfg.contains("Stats.Kills")){
			kills = cfg.getInt("Stats.Kills");
		}
		kills = kills + i;
		
		cfg.set("Stats.Kills", kills);

		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setPlayerKills(String p, int i){
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData", p.toLowerCase() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		int kills = i;
		cfg.set("Stats.Kills", kills);
		
		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int getPlayerKills(String p){
		
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData", p.toLowerCase() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

		int kills = 0;
		if(cfg.contains("Stats.Kills")){
			kills = cfg.getInt("Stats.Kills");
		}
		return kills;
	}
}
