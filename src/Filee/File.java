package Filee;

import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import dayZ.V3.main;

public class File implements Listener {


	public File(main main) {
		// TODO Auto-generated constructor stub
	}

	public static boolean fileExist(String p){
		boolean exist = false;
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData", p.toLowerCase() + ".yml");

		
		if(f.exists()){
			exist = true;
		}
		
		return exist;
	}
	
	public static void createFile(Player p){
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData", p.getName().toLowerCase() + ".yml");
		if(!fileExist(p.getName())){
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public static int allPlayers(){
		int count = 0;
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData");
		count = f.listFiles().length;
		return count;
	}
}
