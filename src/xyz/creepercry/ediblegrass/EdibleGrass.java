package xyz.creepercry.ediblegrass;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.creepercry.ediblegrass.events.PlayerRightClick;

public class EdibleGrass extends JavaPlugin {

	private static EdibleGrass instance;
	
	@Override
	public void onEnable() {
		instance = this;
		
		saveDefaultConfig();
		
		Bukkit.getPluginManager().registerEvents(new PlayerRightClick(), this);
	}
	
	@Override
	public void onDisable() {
		instance = null;
	}
	
	public static EdibleGrass getInstance() {
		return instance;
	}
}
