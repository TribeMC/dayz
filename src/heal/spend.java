package heal;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import dayZ.V3.main;

public class spend implements Listener {

	public static boolean trueFalse;

	public static int time;

	String prefix = ChatColor.BOLD + "" + ChatColor.DARK_RED + "["
			+ ChatColor.RESET + ChatColor.RED + "TRIBE" + ChatColor.GOLD
			+ ChatColor.BOLD + "Z" + ChatColor.DARK_RED + "]" + ChatColor.GRAY
			+ " ";

	Plugin pl;
	ArrayList<Player> healerd = new ArrayList<Player>();

	public spend(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	@EventHandler
	public void onLook(EntityTargetLivingEntityEvent e) {
		if (trueFalse == true) {
			if (e.getEntity() instanceof Player) {

				if (e.getTarget() instanceof Player) {
					Player target = (Player) e.getTarget();
					final Player healer = (Player) e.getEntity();
					if (healerd.contains(healer)) {
						if (target.isSneaking()
								&& healer.isSneaking()
								&& healer.getItemInHand().getType() == Material.SLIME_BALL && !healerd.contains(healer)) {
							target.addPotionEffect(new PotionEffect(
									PotionEffectType.REGENERATION, 24000, 4));
							target.sendMessage(prefix + "Der Spieler "
									+ healer.getDisplayName() + ChatColor.GRAY
									+ " hat dich geheilt!");
							int papermenge = healer.getItemInHand().getAmount();
							healer.setItemInHand(new ItemStack(
									Material.SLIME_BALL, papermenge - 1));
							healerd.add(healer);
							healer.sendMessage(prefix + "Du hast den Spieler"
									+ target.getDisplayName() + ChatColor.GRAY
									+ " geheilt!");

							Bukkit.getScheduler().scheduleSyncDelayedTask(pl,
									new Runnable() {

										@Override
										public void run() {
											// TODO Auto-generated method stub
											healerd.remove(healer);
											healer.sendMessage(prefix
													+ "Du kannst wieder Spieler heilen!");

										}
									}, 20 * time);
						}
					}
				}
			}
		}
	}
}
