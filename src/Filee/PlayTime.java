package Filee;

import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import dayZ.V3.main;

public class PlayTime implements Listener {

	public PlayTime(main main) {
		// TODO Auto-generated constructor stub
	}

	public static void addMinute(Player p) {
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData", p
				.getName().toLowerCase() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);

		int minutes = 0;
		if (cfg.contains("Time.Minutes")) {
			minutes = cfg.getInt("Time.Minutes");
		}
		int hours = 0;
		if (cfg.contains("Time.Hours")) {
			minutes = cfg.getInt("Time.Hours");
		}
		int days = 0;
		if (cfg.contains("Time.Days")) {
			minutes = cfg.getInt("Time.Days");
		}

		if (minutes == 59) {
			cfg.set("Time.Minutes", 0);
			if (hours == 23) {
				cfg.set("Time.Hours", 0);
				cfg.set("Time.Days", days + 1);
				try {
					cfg.save(f);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				cfg.set("Time.Hours", hours + 1);
				try {
					cfg.save(f);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			cfg.set("Time.Minutes", minutes + 1);
			try {
				cfg.save(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static int getMinutes(String p) {
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData", p
				.toLowerCase() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		int min = 0;
		if(cfg.contains("Time.Minutes")){
			min = cfg.getInt("Time.Minutes");
		}
		return min;
	}

	public static int getHours(String p) {
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData", p
				.toLowerCase() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		int min = 0;
		if(cfg.contains("Time.Hours")){
			min = cfg.getInt("Time.Hours");
		}
		return min;
	}

	public static int getDays(String p) {
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData", p
				.toLowerCase() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		int min = 0;
		if(cfg.contains("Time.Days")){
			min = cfg.getInt("Time.Days");
		}
		return min;
	}
}
