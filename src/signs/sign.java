package signs;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import dayZ.V3.main;

public class sign implements Listener {

	String prefix = ChatColor.BOLD + "" + ChatColor.DARK_RED + "["
			+ ChatColor.RESET + ChatColor.RED + "TRIBE" + ChatColor.GOLD
			+ ChatColor.BOLD + "Z" + ChatColor.DARK_RED + "]" + ChatColor.GRAY
			+ " ";

	public HashMap<String, Long> radaruser = new HashMap<String, Long>();

	public sign(main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
		// TODO Auto-generated constructor stub
	}

	@EventHandler
	public void onInteractWithSign(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getClickedBlock().getState() instanceof Sign) {
				Sign s = (Sign) e.getClickedBlock().getState();

				if (s.getLine(0).equalsIgnoreCase(
						ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "Like"
								+ ChatColor.DARK_GRAY + "]")) {

					if (e.getPlayer().getLevel() >= 1) {
						String like = s.getLine(2);

						int likes = 0;
						try {
							likes = Integer.parseInt(like);

						} catch (NumberFormatException error) {
							System.out.println("Fehler");
						}
						e.getPlayer().playNote(e.getPlayer().getLocation(),
								Instrument.BASS_DRUM, Note.natural(1, Tone.G));
						s.setLine(2, likes + 1 + "");
						s.update();
						e.getPlayer().setLevel(e.getPlayer().getLevel() - 1);
					} else {
						e.getPlayer()
								.sendMessage(
										prefix
												+ "Um das Schild zu nutzen brauchst du 1 Level!");
					}
				}

				if (s.getLine(0).equalsIgnoreCase(
						ChatColor.DARK_GRAY + "[" + ChatColor.RED + "Radar"
								+ ChatColor.DARK_GRAY + "]")) {

					if (radaruser.containsKey(e.getPlayer().getName())) {
						long systemtime = System.currentTimeMillis();
						e.getPlayer()
								.sendMessage(
										prefix
												+ "Du musst warten, bevor du die Radarstation wieder nutzen kannst!");
						long settime = radaruser.get(e.getPlayer().getName());
						if (settime + 5000 < systemtime) {
							radaruser.remove(e.getPlayer().getName());

						}

					} else {
						if (e.getPlayer().getLevel() >= 5) {

							int online = Bukkit.getOnlinePlayers().length;
							Random r = new Random();
							int target = r.nextInt(online);

							Player p = Bukkit.getOnlinePlayers()[target];

							s.setLine(1, p.getName());
							s.setLine(2, (int) p.getLocation().getX() + "");
							s.setLine(3, (int) p.getLocation().getZ() + "");
							s.update();
							long systemtime = System.currentTimeMillis();
							radaruser.put(e.getPlayer().getName(), systemtime);
							e.getPlayer()
									.setLevel(e.getPlayer().getLevel() - 5);

						} else {
							e.getPlayer()
									.sendMessage(
											prefix
													+ "Um das Schild zu nutzen brauchst du 5 Level!");

						}

					}
				}

				if (s.getLine(0).equalsIgnoreCase(
						ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "Online"
								+ ChatColor.DARK_GRAY + "]")) {
					if (e.getPlayer().getLevel() >= 3) {
						

						int days = Filee.PlayTime.getDays(e.getPlayer().getName());
						int hours = Filee.PlayTime.getHours(e.getPlayer().getName());
						int minutes = Filee.PlayTime.getMinutes(e.getPlayer().getName());

						s.setLine(1, e.getPlayer().getName() + ":");
						s.setLine(2, days + "Tage " + hours + "h");
						s.setLine(3, "und " + minutes + "min!");
						s.update();

						e.getPlayer().setLevel(e.getPlayer().getLevel() - 3);
					} else {
						e.getPlayer()
								.sendMessage(
										prefix
												+ "Um das Schild zu nutzen brauchst du mindestens 3 Level!");

					}

				}

				if (s.getLine(0).equals(ChatColor.DARK_GRAY + "[" + ChatColor.BLUE + "Flug" + ChatColor.DARK_GRAY + "]")) {
					if (e.getPlayer().getLevel() >= 100) {

						if (s.getLine(2).equals( ChatColor.YELLOW + "[" + ChatColor.RED + "Süden" + ChatColor.YELLOW + "]")) {
							e.getPlayer().setLevel(e.getPlayer().getLevel() - 100);
							flyNord(e.getPlayer());
						}
						if (s.getLine(2).equals(ChatColor.YELLOW + "[" + ChatColor.AQUA + "Norden" + ChatColor.YELLOW + "]")) {
							e.getPlayer().setLevel(e.getPlayer().getLevel() - 100);
							flySued(e.getPlayer());
						}

						
					} else {
						e.getPlayer()
								.sendMessage(
										prefix
												+ "Um das Schild zu nutzen brauchst du mindestens 100 Level!");

					}
				}
			}
		}
	}

	private void flySued(Player p) {
		// TODO Auto-generated method stub
		if (!Demage.logg.it.containsKey(p.getName())) {
			boolean nord = false;
			move.flight.onFlight(nord, p);
		} else {
			p.sendMessage(prefix
					+ "Du kannst während eines Kampfes nicht wegfliegen!");
		}
	}

	private void flyNord(Player p) {
		// TODO Auto-generated method stub
		if (!Demage.logg.it.containsKey(p.getName())) {
			boolean nord = true;
			move.flight.onFlight(nord, p);
		} else {
			p.sendMessage(prefix
					+ "Du kannst während eines Kampfes nicht wegfliegen!");
		}
	}

}
