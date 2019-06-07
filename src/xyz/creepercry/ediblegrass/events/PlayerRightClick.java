package xyz.creepercry.ediblegrass.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import xyz.creepercry.ediblegrass.EdibleGrass;

public class PlayerRightClick implements Listener {
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		final Player player = e.getPlayer();
		final ItemStack item = player.getItemInHand();
		final Block block = e.getClickedBlock();
		if (block.getType() == Material.GRASS && e.getAction() == Action.RIGHT_CLICK_BLOCK)
			if (item == null || item.getType() == Material.AIR) {
				block.setType(Material.DIRT);
				player.setSaturation(player.getSaturation() + EdibleGrass.getInstance().getConfig().getInt("food.saturation"));
				player.setFoodLevel(player.getFoodLevel() + EdibleGrass.getInstance().getConfig().getInt("food.food-level"));
				if (EdibleGrass.getInstance().getConfig().getBoolean("rewards.disabled"))
					return;
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