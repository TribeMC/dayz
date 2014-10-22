package heal;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import dayZ.V3.main;

public class blood implements Listener {

	public static boolean trueFalse;

	public blood(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onHeal(PlayerInteractEvent e) {

		if (trueFalse == true) {
			if (e.getPlayer().getItemInHand().getType() == Material.CLAY_BALL) {
				if (e.getAction() == Action.RIGHT_CLICK_AIR
						|| e.getAction() == Action.RIGHT_CLICK_BLOCK) {

					if (e.getPlayer().hasPermission("dayz.heal.premium")) {
						e.getPlayer().addPotionEffect(
								new PotionEffect(PotionEffectType.REGENERATION,
										30, 8));
					} else {
						e.getPlayer().addPotionEffect(
								new PotionEffect(PotionEffectType.REGENERATION,
										10, 5));
						
						if(!e.getPlayer().isOp()){
							int papermenge = e.getPlayer().getItemInHand()
									.getAmount();
							ItemStack paper = new ItemStack(Material.CLAY_BALL, papermenge - 1);
							ItemMeta papermeta = paper.getItemMeta();
							papermeta.setDisplayName("BlutBeutel");
							paper.setItemMeta(papermeta);
							e.getPlayer().setItemInHand(paper);
						}
					}
				}
			}
		}
	}

}
