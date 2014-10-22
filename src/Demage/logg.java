package Demage;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import api.ParticleEffect;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;

import dayZ.V3.main;


public class logg implements Listener {

	
	public logg(main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
		System.out.println("ASDNKAF");
	}
	
	
	String prefix = ChatColor.BOLD + "" + ChatColor.DARK_RED + "["
			+ ChatColor.RESET + ChatColor.RED + "TRIBE" + ChatColor.GOLD
			+ ChatColor.BOLD + "Z" + ChatColor.DARK_RED + "]" + ChatColor.GRAY
			+ " ";

	public static HashMap<String, Long> it = new HashMap<String, Long>();
	public static int time;
	public static int ztime;
	public static int timez = time - ztime;



	
	
	@EventHandler
	public void onDemage(EntityDamageByEntityEvent e) {
		
		if (e.getEntity() instanceof Player) {
			long stime = System.currentTimeMillis();
			Player p = (Player) e.getEntity();
			if (e.getDamager() instanceof Zombie
					|| e.getDamager() instanceof Player) {

				if (e.getDamager() instanceof Player) {
					Player pd = (Player) e.getDamager();
					if (pd == p) {
						return;
					}
					
						if (!it.containsKey(p.getName())
								|| !it.containsKey(pd.getName())) {

							
							it.put(p.getName(), stime);
							it.put(pd.getName(), stime);
							
							p.sendMessage(prefix + "Du wurdest von "
									+ pd.getDisplayName() + ChatColor.RESET
									+ ChatColor.GRAY + " angegriffen!");

						} else {
							it.put(p.getName(), stime);

							it.put(pd.getName(), stime);

						}
					

				} else {
					if (!it.containsKey(p.getName())) {

						p.sendMessage(prefix
								+ "Du wurdest von einem Zombie angegriffen!");

					}
					it.put(p.getName(), stime - timez * 1000);

				}
			}
			if (water.unset.water.get(p) == 0) {
				if (!it.containsKey(p.getName())) {
					p.sendMessage(prefix + "Du verdurstest!");
				}
				it.put(p.getName(), stime - timez * 1000);
			}
		}
		
		
		if (e.getEntity() instanceof Minecart) {
			Minecart m = (Minecart) e.getEntity();
			Bukkit.broadcastMessage("Minecraft");
			if (m.getPassenger() instanceof Player) {
				Bukkit.broadcastMessage("Spieler");
			}
		}
		
		
		
		if(e.getEntity() instanceof Player || e.getEntity() instanceof Zombie){
			
			
			Location pratikel = new Location(
					e.getEntity().getLocation().getWorld(), e.getEntity()
							.getLocation().getX() + 0.1, e.getEntity().getLocation()
							.getY() , e.getEntity().getLocation().getZ());
			ParticleEffect.DRIP_LAVA.display(pratikel, 0, 0, 0, 3, 70);
			
			e.getEntity()
	        .getLocation()
	        .getWorld()
	        .playEffect(e.getEntity().getLocation().add(0.0D, 1.0D, 0.0D), 
	        Effect.STEP_SOUND, 152);
			}

	}

	@EventHandler
	public void onShoot(WeaponDamageEntityEvent e) {
		if (e.getPlayer() instanceof Player) {
			Player p = e.getPlayer();
			long stime = System.currentTimeMillis();

			if (e.getVictim() instanceof Player) {
				Player pd = (Player) e.getVictim();
				if (pd == p) {
					return;
				}
				if (!e.isCancelled()) {
					if (!it.containsKey(p.getName())
							|| !it.containsKey(pd.getName())) {

						pd.sendMessage(prefix + "Du wurdest von "
								+ p.getDisplayName() + ChatColor.RESET
								+ ChatColor.GRAY + " angegriffen!");
						it.put(p.getName(), stime);
						it.put(pd.getName(), stime);

					} else {
						it.put(p.getName(), stime);

						it.put(pd.getName(), stime);

					}
				}

			}
		}
		org.bukkit.entity.Entity v = e.getVictim();

		/*
		 * So Lenny: Da unten is das Problem: if(pd.getHealth() -
		 * (e.getDamage()/armor) <= -1){ eine Rechnung ohne Sinn und verstand
		 * erfüllt ihren zweck Bei voller Rüstung bekommt man nur 80% von dem
		 * ganzen schaden also is da noch irgendwo ein fehler
		 */

		if (((Damageable) v).getHealth() - e.getDamage() <= -1) {
			/*if (e.getVictim() instanceof Player) {

				Player pd = (Player) e.getVictim();
				@SuppressWarnings("unused")
				double armor = getArmor(pd);

				/*
				 * if(pd.getHealth() - (e.getDamage()/armor) <= -1){
				 * 
				 * board.tod.setPKills(e.getPlayer()); board.tod.setTode(pd);
				 * Player p = e.getPlayer(); p.setLevel(p.getLevel() + 3); }
				 
			}
			*/
			if (e.getVictim() instanceof Zombie) {

				board.zombiekills.setZKills(e.getPlayer());
				Player p = (Player) e.getPlayer();
				p.setLevel(p.getLevel() + 1);
			}

		}

	}

	/*
	 * @EventHandler public void onDespawn(ItemDespawnEvent e){ if(e.getEntity()
	 * != null ){ if(e.getEntity().getLastDamageCause().getCause() ==
	 * DamageCause.ENTITY_EXPLOSION ||
	 * e.getEntity().getLastDamageCause().getCause() ==
	 * DamageCause.BLOCK_EXPLOSION ||
	 * e.getEntity().getLastDamageCause().getCause() ==
	 * DamageCause.BLOCK_EXPLOSION){ e.setCancelled(true); } } }
	 */

	@SuppressWarnings("unused")
	private double getArmor(Player pd) {
		// TODO Auto-generated method stub
		int armorpoints = 0;

		// Schuhe
		if (pd.getInventory().getBoots().getType() == Material.LEATHER_BOOTS) {
			armorpoints = armorpoints + 1;
		}
		if (pd.getInventory().getBoots().getType() == Material.IRON_BOOTS) {
			armorpoints = armorpoints + 2;
		}
		if (pd.getInventory().getBoots().getType() == Material.DIAMOND_BOOTS) {
			armorpoints = armorpoints + 3;
		}
		if (pd.getInventory().getBoots().getType() == Material.GOLD_BOOTS) {
			armorpoints = armorpoints + 1;
		}
		if (pd.getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS) {
			armorpoints = armorpoints + 1;
		}

		// Hose
		if (pd.getInventory().getLeggings().getType() == Material.LEATHER_LEGGINGS) {
			armorpoints = armorpoints + 2;
		}
		if (pd.getInventory().getLeggings().getType() == Material.CHAINMAIL_LEGGINGS) {
			armorpoints = armorpoints + 4;
		}
		if (pd.getInventory().getLeggings().getType() == Material.GOLD_LEGGINGS) {
			armorpoints = armorpoints + 3;
		}
		if (pd.getInventory().getLeggings().getType() == Material.IRON_LEGGINGS) {
			armorpoints = armorpoints + 5;
		}
		if (pd.getInventory().getLeggings().getType() == Material.DIAMOND_LEGGINGS) {
			armorpoints = armorpoints + 6;
		}

		// Brust
		if (pd.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE) {

			armorpoints = armorpoints + 3;
		}
		if (pd.getInventory().getChestplate().getType() == Material.CHAINMAIL_CHESTPLATE) {

			armorpoints = armorpoints + 5;
		}
		if (pd.getInventory().getChestplate().getType() == Material.GOLD_CHESTPLATE) {

			armorpoints = armorpoints + 5;
		}
		if (pd.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE) {

			armorpoints = armorpoints + 6;
		}
		if (pd.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE) {

			armorpoints = armorpoints + 8;
		}

		// Helm
		if (pd.getInventory().getHelmet().getType() == Material.LEATHER_HELMET) {

			armorpoints = armorpoints + 1;
		}
		if (pd.getInventory().getHelmet().getType() == Material.CHAINMAIL_HELMET) {

			armorpoints = armorpoints + 2;
		}
		if (pd.getInventory().getHelmet().getType() == Material.GOLD_HELMET) {

			armorpoints = armorpoints + 2;
		}
		if (pd.getInventory().getHelmet().getType() == Material.IRON_HELMET) {

			armorpoints = armorpoints + 2;
		}
		if (pd.getInventory().getHelmet().getType() == Material.DIAMOND_HELMET) {

			armorpoints = armorpoints + 3;
		}
		double rearmor = 0.08 * armorpoints;
		return (double) rearmor;
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (it.containsKey(e.getPlayer().getName())) {
			long infight = it.get(e.getPlayer().getName());
			if (infight + time * 1000 < System.currentTimeMillis()) {
				it.remove(e.getPlayer().getName());
				e.getPlayer().sendMessage(
						prefix + "Du bist nichtmehr im Kampf!");
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onLeft(PlayerQuitEvent e) {
		Player p = (Player) Bukkit.getOfflinePlayer(e.getPlayer().getName());
		
		if (it.containsKey(p.getName())) {
			it.remove(p.getName());
			e.getPlayer().setHealth(0);
			dropitems(e.getPlayer());
			// droparmor(e.getPlayer());

		}
		
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {

		e.setDeathMessage("");
		e.setKeepLevel(true);

		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (p.getKiller() instanceof Player) {
				Player pd = (Player) p.getKiller();
				if (it.containsKey(p.getName()) && it.containsKey(pd.getName())) {
					p.sendMessage(prefix + "Diesen Kampf hast du verloren!");
					p.sendMessage(prefix + "Du wurdest von "
							+ pd.getDisplayName() + ChatColor.RESET
							+ ChatColor.GRAY + " getötet!");
					pd.sendMessage(prefix + "Du hast " + p.getDisplayName()
							+ ChatColor.RESET + ChatColor.GRAY + " getötet!");
					it.remove(p.getName());
					it.remove(pd.getName());
				}
			} else {
				if (it.containsKey(p.getName())) {
					p.sendMessage(prefix + "Ein Zombie hat dich getötet!");
					it.remove(p.getName());

				}
			}
		}
	}

	@SuppressWarnings("unused")
	private void droparmor(Player p) {
		// TODO Auto-generated method stub
		ItemStack[] stacks = p.getInventory().getArmorContents().clone();
		p.getInventory().setArmorContents(new ItemStack[4]);
		for (ItemStack stack : stacks) {
			if (stack.getType() != Material.AIR) {
				p.getLocation().getWorld()
						.dropItemNaturally(p.getLocation(), stack);
			}

		}
	}

	private void dropitems(Player p) {
		// TODO Auto-generated method stub
		ItemStack[] stacks = p.getInventory().getContents().clone();
		p.getInventory().clear();
		for (ItemStack stack : stacks) {
			if (stack != null) {
				p.getLocation().getWorld()
						.dropItemNaturally(p.getLocation(), stack);
			}
		}
	}
}
