package join;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import dayZ.V3.main;

public class quit implements Listener {
	Plugin plugin;

	public quit(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
		plugin = main;
	}

	@EventHandler
	public void onDisconnat(PlayerQuitEvent e) {
		e.setQuitMessage("");
		e.setQuitMessage(null);

		water.unset.level.remove(e.getPlayer());
		water.unset.water.remove(e.getPlayer());
		move.fall.fallmsg.remove(e.getPlayer().getName());

	}

	@EventHandler
	public void onKick(PlayerKickEvent e) {
		e.setCancelled(true);

		Player p = e.getPlayer();
		p.sendMessage(ChatColor.YELLOW
				+ "Du wirst in die Lobby verschoben! Grund:");
		p.sendMessage(e.getReason());

		move(p);
	}

	private void move(Player p) {
		// Move zur Lobby
		
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream d = new DataOutputStream(b);
		
		try{
			d.writeUTF("Connect");
			d.writeUTF("lobby"); //?
			p.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
			b.close();
			d.close();
		}catch(IOException ex){
			p.sendMessage(ChatColor.DARK_RED + "Fehler! Nutze bitte 'Verlassen'");
		}
		
	}
}
