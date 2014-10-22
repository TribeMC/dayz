package commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import dayZ.V3.main;

public class setspawn implements CommandExecutor{
	String prefix = ChatColor.BOLD + "" + ChatColor.DARK_RED + "["
			+ ChatColor.RESET + ChatColor.RED + "TRIBE" + ChatColor.GOLD
			+ ChatColor.BOLD + "Z" + ChatColor.DARK_RED + "]" + ChatColor.GRAY
			+ " ";

	public setspawn(main main) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String arg2,
			String[] args) {
		// TODO Auto-generated method stub
		if(cs instanceof Player && cs.hasPermission("dayz.admin")){
			Player p = (Player) cs;
			
		if(args.length == 0){
			p.sendMessage(prefix + "Spawns setzen:");
			p.sendMessage(prefix + "/spawn set");
			p.sendMessage(prefix + "/spawn get [ZAHL]");
			p.sendMessage(prefix + "Fehler werden nicht geduldet :D");
		}
		if(args.length == 1 && args[0].equalsIgnoreCase("set")){
			setloc(p);
		}
		if(args.length == 2 && args[0].equalsIgnoreCase("get")){
			teleport(p, args[1].toString());
		}
		}else{
			cs.sendMessage(prefix + "Nur Spieler mit der Permission können Spawns setzen!");
		}
		return false;
	}

	private void teleport(Player p, String endspawn) {
		// TODO Auto-generated method stub
		File Spawns = new File("plugins/DayZ", "Spawns.yml");
		FileConfiguration spawn = YamlConfiguration.loadConfiguration(Spawns);
		
		
		
		World w = Bukkit.getWorld(spawn.getString("Spawn." + endspawn + ".world"));
		double x = spawn.getDouble("Spawn." + endspawn + ".x");
		double y = spawn.getDouble("Spawn." + endspawn + ".y");
		double z = spawn.getDouble("Spawn." + endspawn + ".z");
		float yaw = (float) spawn.getDouble("Spawn." + endspawn + ".yaw");
		float pitch = (float) spawn.getDouble("Spawn." + endspawn + ".pitch");
		
		Location tpLoc = new Location(w, x, y, z, yaw, pitch);
		p.teleport(tpLoc);
	}

	private void setloc(Player p) {
		// TODO Auto-generated method stub
		File Spawns = new File("plugins/DayZ", "Spawns.yml");
		FileConfiguration spawn = YamlConfiguration.loadConfiguration(Spawns);
		int allspawns = 0;
		for(int i = 0; i < 99 ; i++){
			if(spawn.contains("Spawn." + i + ".world")){
				allspawns = allspawns + 1;
			}else{
				break;
			}
			
		}
		spawn.set("Spawn." + allspawns + ".world", p.getLocation().getWorld().getName());
		spawn.set("Spawn." + allspawns + ".x", p.getLocation().getX());
		spawn.set("Spawn." + allspawns + ".y", p.getLocation().getY());
		spawn.set("Spawn." + allspawns + ".z", p.getLocation().getZ());
		spawn.set("Spawn." + allspawns + ".yaw", p.getLocation().getYaw());
		spawn.set("Spawn." + allspawns + ".pitch", p.getLocation().getPitch());
		
		try {
			spawn.save(Spawns);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p.sendMessage(prefix + "Spawn gesetzt!");
		
		
	}

}
