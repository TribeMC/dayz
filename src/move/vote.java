package move;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.vexsoftware.votifier.model.VotifierEvent;

import dayZ.V3.main;

public class vote implements Listener {

	public vote() {
		// TODO Auto-generated constructor stub
	}

	public vote(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);

	}

	@EventHandler
	public void onVote(VotifierEvent e) {
		String pname = e.getVote().getUsername();
		Player p = null;
		if (pname != null) {
			@SuppressWarnings("deprecation")
			OfflinePlayer pp = Bukkit.getOfflinePlayer(pname);
			if (pp.isOnline()) {
				p = (Player) pp;
			}
		}
		if (p != null) {

			voteReward(p, 1);
		} else {

			putData(pname);
		}
	}

	private void putData(String pname) {
		// TODO Auto-generated method stub
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData",
				pname.toLowerCase() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		int votes = 0;
		if(cfg.contains("Vote.Count")){
			votes = cfg.getInt("Vote.Count");
		}
		cfg.set("Vote.Count", votes + 1);

		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void voteReward(Player p, int i) {
		// TODO Auto-generated method stub

		p.sendMessage("Du hast Offline Votes:" + i);
	}

	public static void hasvoteReward(Player p) {
		// TODO Auto-generated method stub
		java.io.File f = new java.io.File("plugins/DayZ/PlayerData", p
				.getName().toLowerCase() + ".yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		int votes = cfg.getInt("Vote.Count");
		if (votes != 0) {
			voteReward(p, votes);
			cfg.set("Vote.Count", 0);

			try {
				cfg.save(f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
