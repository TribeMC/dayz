package board;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import dayZ.V3.main;

public class scoreboard implements Listener{

	public scoreboard(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	

	@SuppressWarnings("deprecation")
	public static void setscoreboard(Player p){
		
		
		ScoreboardManager sm = Bukkit.getScoreboardManager();
		Scoreboard board = sm.getNewScoreboard();
		Objective score = board.registerNewObjective("aaa", "bbb");
		score.setDisplayName(ChatColor.RED + "     TRIBE" + ChatColor.GOLD + ChatColor.BOLD + "Z" + ChatColor.RESET + ChatColor.GRAY +" Stats     ");
		score.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		Score Kills =  score.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_GRAY + "PlayerKills:"));
		Score ZKills = score.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD + "ZombieKills:"));
		Score Tode = score.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_GRAY + "Tode:"));
		
		
		
		
		int tode = getTode(p);
		
		
		Kills.setScore(getPKills(p));
		ZKills.setScore(getZKills(p));
		Tode.setScore(tode);
		
		
		
		
		
		p.setScoreboard(board);
		
	}
	
	public void join(Player p){
		
		setscoreboard(p);
	}
	
	public static int getPKills(Player p){
		int Kills = Filee.PlayerKills.getPlayerKills(p.getName());
		return Kills;
	}
	public static int getZKills(Player p){
		int Kills = Filee.ZombieKills.getZombieKills(p.getName());
		return Kills;
	}
	public static int getTode(Player p){
		int Tode = Filee.Tode.getTode(p.getName());
		return Tode;
	}


	public static void timer() {
		// TODO Auto-generated method stub
		Random r = new Random();
		int bord = r.nextInt(4);
		for(Player p : Bukkit.getOnlinePlayers()){
			if(bord != 0){
			setscoreboard(p);
			}else{
				topscore.boardUpdate();
				topscore.setTopBoard(p);
			}
		}
	}
}
