package water;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dayZ.V3.main;

public class drinky implements Listener {

	public static int fulllevel;

	public drinky(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	String prefix = ChatColor.BOLD + "" + ChatColor.DARK_RED + "["
			+ ChatColor.RESET + ChatColor.RED + "TRIBE" + ChatColor.GOLD
			+ ChatColor.BOLD + "Z" + ChatColor.DARK_RED + "]" + ChatColor.GRAY
			+ " ";

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR
				|| e.getAction() == Action.RIGHT_CLICK_BLOCK) {

			if (e.getPlayer().getItemInHand().getData().toString()
					.contains("POTION(0)")) {

				// if(watermeta.getDisplayName().equalsIgnoreCase("Trinkflasche")){
				e.setCancelled(true);
				e.getPlayer().getInventory()
						.setItemInHand(new ItemStack(Material.GLASS_BOTTLE, 1));
				;

				e.getPlayer().sendMessage(
						prefix + "Du hast nun wieder " + fulllevel
								+ " Wasserlevel!");
				unset.water.put(e.getPlayer(), fulllevel);
				// }
			}

			if (e.getPlayer().getItemInHand().getType() == Material.DIAMOND) {
				repairArmor(e.getPlayer());

				int papermenge = e.getPlayer().getItemInHand().getAmount();
				ItemStack paper = new ItemStack(Material.DIAMOND,
						papermenge - 1);
				ItemMeta papermeta = paper.getItemMeta();
				papermeta.setDisplayName("Rüstungsreperierer");
				paper.setItemMeta(papermeta);
				e.getPlayer().setItemInHand(paper);
			}

		}
	}

	@SuppressWarnings("deprecation")
	private void repairArmor(Player p) {
		// TODO Auto-generated method stub
		if (p.getInventory().getBoots() != null) {
			if (p.getInventory().getBoots().getDurability() <= 50) {
				p.getInventory().getBoots().setDurability((short) 0);
			} else {
				p.getInventory()
						.getBoots()
						.setDurability(
								(short) (p.getInventory().getBoots()
										.getDurability() - 50));
			}
		}
		
		if (p.getInventory().getLeggings() != null) {
			if (p.getInventory().getLeggings().getDurability() <= 50) {
				p.getInventory().getLeggings().setDurability((short) 0);
			} else {
				p.getInventory()
						.getLeggings()
						.setDurability(
								(short) (p.getInventory().getLeggings()
										.getDurability() - 50));
			}
		}
		if (p.getInventory().getChestplate() != null) {
			if (p.getInventory().getChestplate().getDurability() <= 50) {
				p.getInventory().getChestplate().setDurability((short) 0);
			} else {
				p.getInventory()
						.getChestplate()
						.setDurability(
								(short) (p.getInventory().getChestplate()
										.getDurability() - 50));
			}
		}
		if (p.getInventory().getHelmet() != null) {
			if (p.getInventory().getHelmet().getDurability() <= 50) {
				p.getInventory().getHelmet().setDurability((short) 0);
			} else {
				p.getInventory()
						.getHelmet()
						.setDurability(
								(short) (p.getInventory().getHelmet()
										.getDurability() - 50));
			}
		}

		p.updateInventory();
	}
}
