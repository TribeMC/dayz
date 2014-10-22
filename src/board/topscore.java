package board;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import dayZ.V3.anleitung;
import dayZ.V3.main;

public class topscore implements Listener {

	static int topkd1;
	static int topkd2;
	static int topkd3;
	static int topkd4;
	static int topkd5;
	static String topp1;
	static String topp2;
	static String topp3;
	static String topp4;
	static String topp5;
	static String topp1a;
	static String topp2a;
	static String topp3a;
	static String topp4a;
	static String topp5a;

	File PlayerData = new File("plugins/DayZ/Stats", "PlayerData.yml");
	FileConfiguration cfg = YamlConfiguration.loadConfiguration(PlayerData);

	public topscore(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
		BoardSetUp();

		getTop();

	}

	public static void getTop() {
		topkd1 = 5;
		topkd2 = 4;
		topkd3 = 3;
		topkd4 = 2;
		topkd5 = 1;

		topp1 = "None1";
		topp2 = "None2";
		topp3 = "None3";
		topp4 = "None4";
		topp5 = "None5";

		// getTopOffline();
		// getTopOnline();

		onSetUP();

	}

	private static void set16() {
		// TODO Auto-generated method stub
		if (topp1.length() > 14 || topkd1 > 9999) {

			topp1a = topp1.substring(0, 14);

		} else {
			topp1a = topp1;
		}
		if (topp2.length() > 14 || topkd2 > 9999) {

			topp2a = topp2.substring(0, 14);

		} else {
			topp2a = topp2;
		}
		if (topp3.length() > 14 || topkd3 > 9999) {

			topp3a = topp3.substring(0, 14);

		} else {
			topp3a = topp3;
		}
		if (topp4.length() > 14 || topkd4 > 9999) {

			topp4a = topp4.substring(0, 14);

		} else {
			topp4a = topp4;

		}
		if (topp5.length() > 14 || topkd5 > 9999) {

			topp5a = topp5.substring(0, 14);

		} else {
			topp5a = topp5;
		}

	}

	@SuppressWarnings("unused")
	private static void getTopOnline() {
		// TODO Auto-generated method stub
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (topp1.equalsIgnoreCase(p.getName())
					|| topp2.equalsIgnoreCase(p.getName())
					|| topp3.equalsIgnoreCase(p.getName())
					|| topp4.equalsIgnoreCase(p.getName())
					|| topp5.equalsIgnoreCase(p.getName())) {

			} else {
				int Tode = getTode(p.getName());
				int Kills = getPKills(p.getName()) + getZKills(p.getName());
				if (Tode != 0) {
					double KDRechnen = Kills / Tode;
					int PKD = (int) KDRechnen;

					isTop(PKD, p.getName());
				}
			}
		}
	}

	@SuppressWarnings("unused")
	private static void getTopOffline() {
		// TODO Auto-generated method stub
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			int Tode = getTodeO(p);
			int Kills = getPKillsO(p) + getZKillsO(p);
			if (Tode != 0) {
				double KDRechnen = Kills / Tode;
				int PKD = (int) KDRechnen;

				isTop(PKD, p.getName());
			}

		}
	}

	private static void isTop(int pKD, String name) {
		// TODO Auto-generated method stub
		if (pKD >= topkd1) {
			topkd5 = topkd4;
			topkd4 = topkd3;
			topkd3 = topkd2;
			topkd2 = topkd1;
			topkd1 = pKD;

			topp5 = topp4;
			topp4 = topp3;
			topp3 = topp2;
			topp2 = topp1;
			topp1 = name;
			boardUpdate();
		} else {
			if (pKD >= topkd2) {
				topkd5 = topkd4;
				topkd4 = topkd3;
				topkd3 = topkd2;
				topkd2 = pKD;

				topp5 = topp4;
				topp4 = topp3;
				topp3 = topp2;
				topp2 = name;
				boardUpdate();
			} else {
				if (pKD >= topkd3) {
					topkd5 = topkd4;
					topkd4 = topkd3;
					topkd3 = pKD;

					topp5 = topp4;
					topp4 = topp3;
					topp3 = name;
					boardUpdate();
				} else {
					if (pKD >= topkd4) {
						topkd5 = topkd4;
						topkd4 = pKD;

						topp5 = topp4;
						topp4 = name;
						boardUpdate();
					} else {
						if (pKD >= topkd5) {
							topkd5 = pKD;

							topp5 = name;
							boardUpdate();
						} else {

						}
					}
				}
			}
		}
	}

	static ScoreboardManager sm = Bukkit.getScoreboardManager();
	static Scoreboard toprdy = sm.getNewScoreboard();

	public static void BoardSetUp() {

	}

	static boolean isRegistert = false;

	@SuppressWarnings("deprecation")
	public static void BoardCreate() {

		Scoreboard topboard = sm.getNewScoreboard();

		Objective score = topboard.registerNewObjective("aaa", "bbb");
		score.setDisplayName(ChatColor.RED + "     TRIBE" + ChatColor.GOLD
				+ ChatColor.BOLD + "Z" + ChatColor.RESET + ChatColor.GRAY
				+ " TOP     ");
		score.setDisplaySlot(DisplaySlot.SIDEBAR);

		Score op1 = score.getScore(Bukkit.getOfflinePlayer(ChatColor.GOLD
				+ topp1a));
		Score op2 = score.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_GRAY
				+ topp2a));
		Score op3 = score.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_GRAY
				+ topp3a));
		Score op4 = score.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_GRAY
				+ topp4a));
		Score op5 = score.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_GRAY
				+ topp5a));

		op1.setScore(topkd1);
		op2.setScore(topkd2);
		op3.setScore(topkd3);
		op4.setScore(topkd4);
		op5.setScore(topkd5);

		toprdy = topboard;
	}

	public static void setTopBoard(Player p) {
		p.setScoreboard(toprdy);
	}

	public static void smoothTop(String p) {
		if (topp1.equalsIgnoreCase(p) || topp2.equalsIgnoreCase(p)
				|| topp3.equalsIgnoreCase(p) || topp4.equalsIgnoreCase(p)
				|| topp5.equalsIgnoreCase(p)) {

		} else {
			int Tode = getTode(p);
			int Kills = getPKills(p) + getZKills(p);
			if (Tode != 0) {
				double KDRechnen = Kills / Tode;
				int PKD = (int) KDRechnen;

				isTop(PKD, p);
			}

		}

	}

	public static void boardUpdate() {
		set16();

		BoardCreate();
	}

	public static int getPKills(String p) {
		int Kills = Filee.PlayerKills.getPlayerKills(p);
		return Kills;
	}

	public static int getZKills(String p) {
		int Kills = Filee.ZombieKills.getZombieKills(p);
		return Kills;
	}

	public static int getTode(String p) {
		int Tode = Filee.Tode.getTode(p);
		return Tode;
	}

	public static int getPKillsO(OfflinePlayer p) {
		File PlayerData = new File("plugins/DayZ/Stats", "PlayerData.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(PlayerData);
		int Kills = cfg.getInt("PlayerData." + p.getName() + ".PKills");
		return Kills;
	}

	public static int getZKillsO(OfflinePlayer p) {
		File PlayerData = new File("plugins/DayZ/Stats", "PlayerData.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(PlayerData);
		int Kills = cfg.getInt("PlayerData." + p.getName() + ".ZKills");
		return Kills;
	}

	public static int getTodeO(OfflinePlayer p) {
		File PlayerData = new File("plugins/DayZ/Stats", "PlayerData.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(PlayerData);
		int Tode = cfg.getInt("PlayerData." + p.getName() + ".Tode");
		return Tode;
	}

	public static void onSetUP() {
		java.io.File files = new java.io.File("plugins/DayZ/PlayerData/");
		for (java.io.File f : files.listFiles()) {
			String name = f.getName();
			name = name.replaceAll(".yml", "");
			smoothTop(name);
		}
		Bukkit.getServer()
				.getConsoleSender()
				.sendMessage(
						"§eAuf " + anleitung.prefix + "§ehaben schon §b"
								+ files.listFiles().length + "§e User gespielt!");

	}

}
