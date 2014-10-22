package move;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import dayZ.V3.main;

public class fall implements Listener {

	public fall(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	public static ArrayList<String> fallmsg = new ArrayList<String>();
	String prefix = ChatColor.BOLD + "" + ChatColor.DARK_RED + "["
			+ ChatColor.RESET + ChatColor.RED + "TRIBE" + ChatColor.GOLD
			+ ChatColor.BOLD + "Z" + ChatColor.DARK_RED + "]" + ChatColor.GRAY
			+ " ";

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onFall(PlayerMoveEvent e) {
		/*
		 * if(e.getPlayer().getFallDistance() >= 19){
		 * if(!e.getPlayer().hasPermission("dayz.premium") &&
		 * fallmsg.contains(e.getPlayer().getName())){
		 * e.getPlayer().sendMessage(prefix + "Sneake um zu überleben!");
		 * fallmsg.add(e.getPlayer().getName()); }
		 * if(e.getPlayer().isSneaking()){ e.getPlayer().setFallDistance(20); }
		 * }
		 * 
		 * if(e.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD){
		 * if(e.getPlayer().getFallDistance() > 5){
		 * e.getPlayer().setVelocity(e.getPlayer().getVelocity()); } }
		 */

		if (e.getPlayer().getFallDistance() >= 2
				&& e.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD) {
			e.getPlayer().setVelocity(
					new Vector(e.getPlayer().getVelocity().getX(), e
							.getPlayer().getVelocity().getY() / 4, e
							.getPlayer().getVelocity().getZ()));
			if (!fallmsg.contains(e.getPlayer().getName())) {
				e.getPlayer().sendMessage(
						prefix + "Du nutzt nun den Fallschirm!");
				fallmsg.add(e.getPlayer().getName());
			}
			e.getPlayer().setFallDistance(2);
		}
		if (e.getPlayer().isOnGround()
				&& e.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD
				&& fallmsg.contains(e.getPlayer().getName())) {
			e.getPlayer().sendMessage(prefix + "Du bist sicher gelandet!");
			fallmsg.remove(e.getPlayer().getName());
		}

		
		if (-500 > e.getTo().getBlockX()
				|| e.getTo().getBlockX() > 500
				|| -500 > e.getTo().getBlockZ()
				|| e.getTo().getBlockZ() > 500) {

			Vector v = new Vector(0 - e.getPlayer().getLocation().getX()/450, 0.02D, 0 - e.getPlayer().getLocation().getZ()/450);
			e.getPlayer().setVelocity(v);
			e.getPlayer().playEffect(e.getPlayer().getLocation(), Effect.SMOKE, 4);
			e.getPlayer().sendMessage(e.getPlayer().getDisplayName() + ChatColor.RESET + ChatColor.ITALIC
					+ ChatColor.GOLD + "> " + ChatColor.RESET + "§c Ich kann da nicht hin...");
		}
	}
}
