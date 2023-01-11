package org.kalbinvv.bewareliquids;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.kalbinvv.bewareliquids.commands.BewareLiquidsCommand;
import org.kalbinvv.bewareliquids.commands.BewareLiquidsTabCompleter;
import org.kalbinvv.bewareliquids.events.DeathEvent;
import org.kalbinvv.bewareliquids.events.MoveEvent;
import org.kalbinvv.bewareliquids.events.QuitEvent;
import org.kalbinvv.bewareliquids.storage.MapSecurePlacesStorage;
import org.kalbinvv.bewareliquids.storage.SecurePlacesStorage;

public class BewareLiquids extends JavaPlugin{
	private static JavaPlugin plugin;

	private static SecurePlacesStorage securePlacesStorage;
	private static BewareLiquidsConfiguration configuration;

	@Override
	public void onEnable() {
		plugin = this;
		
		enableMetrics();

		saveDefaultConfig();
		configuration = new BewareLiquidsConfiguration(
				new File(getDataFolder(), "config.yml")
		);

		securePlacesStorage = new MapSecurePlacesStorage();
		
		getCommand("bewareliquids").setExecutor(new BewareLiquidsCommand());
		getCommand("bewareliquids").setTabCompleter(new BewareLiquidsTabCompleter());
		
		registerEvents();
	}

	@Override
	public FileConfiguration getConfig() {
		return configuration;
	}
	
	private void registerEvents() {
		var pluginManager = getServer().getPluginManager();
		
		pluginManager.registerEvents(new MoveEvent(), plugin);
		pluginManager.registerEvents(new DeathEvent(), plugin);
		pluginManager.registerEvents(new QuitEvent(), plugin);
	}
	
	private void enableMetrics() {
		int pluginId = 17368;
		
		new Metrics(this, pluginId);
	}

	public static SecurePlacesStorage getSecurePlacesStorage() {
		return securePlacesStorage;
	}

	public static JavaPlugin getPlugin() {
		return plugin;
	}

}
