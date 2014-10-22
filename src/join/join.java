package join;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import board.topscore;
import dayZ.V3.main;

public class join implements Listener {

	public static int fulllevel;

	public join(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	String prefix = ChatColor.BOLD + "" + ChatColor.DARK_RED + "["
			+ ChatColor.RESET + ChatColor.RED + "TRIBE" + ChatColor.GOLD
			+ ChatColor.BOLD + "Z" + ChatColor.DARK_RED + "]" + ChatColor.GRAY
			+ " ";

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {

		e.setJoinMessage("");
		e.setJoinMessage(null);

		e.getPlayer().sendMessage(
				prefix + "Da bist du endlich, " + e.getPlayer().getDisplayName() + ChatColor.RESET
						+ ChatColor.GRAY
						+ "!");
		e.getPlayer().sendMessage(prefix + "Du bist dazu auserwählt, die Zombies zu besiegen!");
		if (e.getPlayer().getHealth() == 0) {
			e.getPlayer().sendMessage("");
			e.getPlayer()
					.sendMessage(
							prefix
									+ "Dein Minecrafter ist in deiner Abwesenheit gestorben!");
		}

		if (!e.getPlayer().hasPermission("dayz.heal.premium")) {
			e.getPlayer().sendMessage("");
			e.getPlayer().sendMessage(
					prefix + "Falls du nicht weisst wie's geht, nutze:"
							+ ChatColor.RED + " /Anleitung");

		}

		water.unset.water.put(e.getPlayer(), fulllevel);
		water.unset.level.put(e.getPlayer(), 0);

		Filee.File.createFile(e.getPlayer());
		
		topscore.smoothTop(e.getPlayer().getName());
		
		move.vote.hasvoteReward(e.getPlayer());

	}
}
