package zombie;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import dayZ.V3.main;

public class stark implements Listener{

	
	public stark(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);

	}

	@EventHandler
	public void onTarget(EntityTargetLivingEntityEvent e){
		if(e.getEntity() instanceof Zombie && e.getTarget() instanceof Player){
			Zombie z = (Zombie) e.getEntity();
			Player p = (Player) e.getTarget();
			int armor = getArmorPoints(p);
			
			
			if(armor >= 19 && z.getMaxHealth() == 20){
				z.setMaxHealth(36);
				z.setHealth(36);

			}
			if(armor >= 9 && z.getMaxHealth() == 20){
				z.setMaxHealth(27);
				z.setHealth(27);
				
			}
			
			if(armor >= 0 && z.getMaxHealth() == 20){
				z.setMaxHealth(16);
				z.setHealth(16);
				
			}
			
		}
	}

	private int getArmorPoints(Player pd) {
		// TODO Auto-generated method stub
		int armorpoints = 0;

		// Schuhe
		if(pd.getInventory().getBoots() != null){
			
		if (pd.getInventory().getBoots().getType() == Material.LEATHER_BOOTS) {
			armorpoints = armorpoints + 1;
		}
		if (pd.getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS) {
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
		}

		// Hose
		if(pd.getInventory().getLeggings() != null && pd.getInventory().getLeggings().getType() != Material.AIR){
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

		}
		// Brust
		if(pd.getInventory().getChestplate() != null){
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
		}

		// Helm
		if(pd.getInventory().getHelmet() != null && pd.getInventory().getHelmet().getType() != Material.AIR){

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
		}
		
		return armorpoints;
		
	}
}
