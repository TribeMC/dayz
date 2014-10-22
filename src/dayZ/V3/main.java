package dayZ.V3;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{

	
	
	
	File Spawns = new File("plugins/DayZ", "Spawns.yml");
	
	File data = new File("plugins/DayZ/Stats" , "data.yml");
	FileConfiguration ds = YamlConfiguration.loadConfiguration(data);
	
	java.io.File files = new java.io.File("plugins/DayZ/PlayerData/");
	
	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		super.onEnable();
		System.out.println("[DayZ] Erstellt von V3lop5!");
		
		new heal.paper(this);
		new heal.blood(this);
		// new heal.spend(this);
		
		
		new Demage.logg(this);
		
		new protection.reSpawn(this);

		System.out.println("[DayZ]"+ " AntiLogg + Heals geladen");
		
		loadConfig();
		
		/* ItemStack helm = new ItemStack(Material.DIAMOND_HELMET);
		ItemStack brust = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemStack hosee = new ItemStack(Material.DIAMOND_LEGGINGS);
		ItemStack schuhe = new ItemStack(Material.DIAMOND_BOOTS);
		
		ShapedRecipe helmcraft = new ShapedRecipe(helm);
		ShapedRecipe brustcraft = new ShapedRecipe(brust);
		ShapedRecipe hosecraft = new ShapedRecipe(hosee);
		ShapedRecipe schuhecraft = new ShapedRecipe(schuhe);
		
		helmcraft.shape("AB");
		helmcraft.setIngredient('A', Material.DIAMOND);
		helmcraft.setIngredient('B', Material.DIAMOND_HELMET);
		
		brustcraft.shape("AB");
		brustcraft.setIngredient('A', Material.DIAMOND);
		brustcraft.setIngredient('B', Material.DIAMOND_CHESTPLATE);
		
		hosecraft.shape("AB", "CC");
		hosecraft.setIngredient('A', Material.DIAMOND);
		hosecraft.setIngredient('B', Material.DIAMOND_LEGGINGS);
		
		schuhecraft.shape("AB");
		schuhecraft.setIngredient('A', Material.DIAMOND);
		schuhecraft.setIngredient('B', Material.DIAMOND_BOOTS);
		
		Bukkit.addRecipe(helmcraft);
		Bukkit.addRecipe(brustcraft);
		Bukkit.addRecipe(hosecraft);
		Bukkit.addRecipe(schuhecraft);
		*/
		
		new move.fall(this);
		new move.craft(this);
		new move.pumpki(this);
		new move.flight(this);
		
		System.out.println("[DayZ]"+ " Fallschirm + CraftProtect geladen!");
		
		new join.join(this);
		new join.quit(this);
		
		System.out.println("[DayZ]"+ " JoinQuit geladen");
		
		getCommand("anleitung").setExecutor(new anleitung(this));
		getCommand("mail").setExecutor(new mail.mailcmd(this));
		getCommand("survive").setExecutor(new commands.logout(this));
		getCommand("spawn").setExecutor(new commands.setspawn(this));
		getCommand("playtime").setExecutor(new stats.onlinecmd(this));
		
		System.out.println("[DayZ]"+ " CMDs geladen");
		
		new world.time(this);
		
		
		
		
		new zombie.skull(this);
		new zombie.stark(this);
		new zombie.spawn(this);
		
		new water.unset(this);
		new water.drinky(this);
		
		System.out.println("[DayZ]"+ " Wasser + Welt geladen!");
		if(!files.exists()){
			try {
				files.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
			
			
		}
		
		new Filee.File(this);
		new Filee.PlayerKills(this);
		new Filee.PlayTime(this);
		new Filee.Tode(this);
		new Filee.ZombieKills(this);
		
		
	
		
		
		if(!Spawns.exists()){
			try {
				Spawns.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("[DayZ]"+ " DataFiles fertig geladen");
		new board.scoreboard(this);
		new board.tod(this);
		new board.zombiekills(this);
		new board.topscore(this);
		
		new stats.online(this);
		
		System.out.println("[DayZ]"+ " Scoreboard geladen");
		
		new signs.sign(this);
		new signs.signset(this);
		System.out.println("[DayZ]"+ " Signs geladen");
		
		// new stats.top(this);
		// getCommand("top5").setExecutor(new stats.topcmd(this));
		
		// new scoreboard.BoardUpdate(this);
		// new scoreboard.data(this);
		// new scoreboard.tod(this);
		// new scoreboard.zombiekills(this);
		
		new move.vote(this);
		
		//Load Bukkit Channels
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
	}
	
	
	private void loadConfig() {
		// TODO Auto-generated method stub
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		heal.blood.trueFalse = getConfig().getBoolean("Config.BloodHeal.TrueFalse");
		heal.paper.trueFalse = getConfig().getBoolean("Config.PaperHeal.TrueFalse");
		heal.spend.trueFalse = getConfig().getBoolean("Config.SpendBlood.TrueFalse");
		heal.spend.time = getConfig().getInt("Config.SpendBlood.Time");
		
		protection.reSpawn.trueFalse = getConfig().getBoolean("Config.Protection.TrueFalse");
		protection.reSpawn.time = getConfig().getInt("Config.Protection.Time");
		
		world.time.time = getConfig().getInt("Config.world.time");
		world.time.resettime = getConfig().getInt("Config.world.resettime");
		
		
		
		Demage.logg.time = getConfig().getInt("Config.Logg.time");
		Demage.logg.ztime = getConfig().getInt("Config.Logg.ztime");
		
		water.unset.updatetime = getConfig().getInt("Config.Water.updatetime");
		water.unset.fulllevel = getConfig().getInt("Config.Water.waterfull");
		join.join.fulllevel = getConfig().getInt("Config.Water.waterfull");
		water.drinky.fulllevel = getConfig().getInt("Config.Water.waterfull");
		
		System.out.println("[DayZ]"+ " Config geladen");
		
	}


	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		File PlayerData = new File("plugins/DayZ/Stats", "PlayerData.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(PlayerData);
		try {
			cfg.save(PlayerData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.onDisable();
	}
	
	
}
