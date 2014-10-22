package Filee;

import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import dayZ.V3.main;

public class Tode implements Listener{
	
	
	public Tode(main main) {
		// TODO Auto-generated constructor stub
	}

	public static void addTod(Player p, int i){
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData", p.getName().toLowerCase() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		int kills = 0;
		if(cfg.contains("Stats.Tode")){
			kills = cfg.getInt("Stats.Tode");
		}
		kills = kills + i;
		
		cfg.set("Stats.Tode", kills);
		
		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setTode(String p, int i){
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData", p.toLowerCase() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		int kills = i;
		
		
		cfg.set("Stats.Tode", kills);
		
		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int getTode(String p){
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData", p.toLowerCase() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

		int kills = 0;
		if(cfg.contains("Stats.Tode")){
			kills = cfg.getInt("Stats.Tode");
		}
		return kills;
	}

}
