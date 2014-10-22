package heal;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dayZ.V3.main;

public class paper implements Listener {

	public static boolean trueFalse;

	public paper(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	String prefix = ChatColor.BOLD + "" + ChatColor.DARK_RED + "["
			+ ChatColor.RESET + ChatColor.RED + "TRIBE" + ChatColor.GOLD
			+ ChatColor.BOLD + "Z" + ChatColor.DARK_RED + "]" + ChatColor.GRAY
			+ " ";
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onHeal(PlayerInteractEvent e) {
		if (trueFalse == true) {
			if (e.getPlayer().getItemInHand().getType() == Material.PAPER) {
				if (e.getAction() == Action.RIGHT_CLICK_AIR
						|| e.getAction() == Action.RIGHT_CLICK_BLOCK) {

					
					if (e.getPlayer().getHealth() == 20) {

						if (!e.getPlayer().hasPermission("dayz.heal.premium")) {
							e.getPlayer()
									.sendMessage(
											prefix + "Heile mehr Leben wenn du dir das Upgrade kaufst!");
						}
					} else {
						int papermenge = e.getPlayer().getItemInHand()
								.getAmount();
						ItemStack paper = new ItemStack(Material.PAPER, papermenge - 1);
						ItemMeta papermeta = paper.getItemMeta();
						papermeta.setDisplayName("Bandage");
						paper.setItemMeta(papermeta);
						e.getPlayer().setItemInHand(paper);

						if (e.getPlayer().hasPermission("dayz.heal.premium")) {
							if (e.getPlayer().getHealth() >= 10) {
								e.getPlayer().setHealth(20);
							} else {
								e.getPlayer().setHealth(
										e.getPlayer().getHealth() + 10);
							}
						} else {
							if (e.getPlayer().getHealth() >= 12) {
								e.getPlayer().setHealth(20);
							} else {
								e.getPlayer().setHealth(
										e.getPlayer().getHealth() + 8);
							}
						}

					}
					e.setCancelled(true);
					e.getPlayer().updateInventory();
				}else{
					e.setCancelled(false);
				}
			}
		}else{
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void otherHEal(PlayerInteractEntityEvent e){
		if (trueFalse == true) {
			if (e.getPlayer().getItemInHand().getType() == Material.PAPER && e.getRightClicked() instanceof Player) {
				Player pd = (Player) e.getRightClicked();
				if(!(pd.getHealth() == 20)){
					int papermenge = e.getPlayer().getItemInHand()
							.getAmount();
					ItemStack paper = new ItemStack(Material.PAPER, papermenge - 1);
					ItemMeta papermeta = paper.getItemMeta();
					papermeta.setDisplayName("Bandage");
					paper.setItemMeta(papermeta);
					e.getPlayer().setItemInHand(paper);
					
					if (pd.getHealth() >= 13) {
						pd.setHealth(20);
					} else {
						pd.setHealth(
								pd.getHealth() + 8);
					}
				}
			}
		}
	}

}
