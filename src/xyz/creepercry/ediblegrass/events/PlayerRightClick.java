package xyz.creepercry.ediblegrass.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import xyz.creepercry.ediblegrass.EdibleGrass;

public class PlayerRightClick implements Listener {
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if (event.getClickedBlock().getType() == Material.GRASS && event.getAction() == Action.RIGHT_CLICK_BLOCK)
			if (player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR) {
				event.getClickedBlock().setType(Material.DIRT);
				player.setSaturation(player.getSaturation() + EdibleGrass.getInstance().getConfig().getInt("food.saturation"));
				player.setFoodLevel(player.getFoodLevel() + EdibleGrass.getInstance().getConfig().getInt("food.food-level"));

				final Random r = new Random();
				final int chance = r.nextInt(100);
				if (chance <= EdibleGrass.getInstance().getConfig().getInt("rewards.chance")) {
					final int picked = r.nextInt(EdibleGrass.getInstance().getConfig().getStringList("rewards.rewards").size());
					final String reward = EdibleGrass.getInstance().getConfig().getStringList("rewards.rewards").get(picked);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), reward.replace("%player%", player.getName()));
				}
			}
	}
}