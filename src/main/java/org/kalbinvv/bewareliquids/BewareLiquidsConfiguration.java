package org.kalbinvv.bewareliquids;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class BewareLiquidsConfiguration extends YamlConfiguration{

	private final File configurationFile;
	private Set<Material> forbiddenLiquids;
	
	private final static String FORBIDEN_LIQUIDS_PATH = "forbiddenLiquids";
	
	public BewareLiquidsConfiguration(File configurationFile) {
		this.configurationFile = configurationFile;
		
		var configuration = YamlConfiguration.loadConfiguration(configurationFile);
		
		loadFromConfiguration(configuration);
		
		initForbiddenLiquids();
	}
	
	public void reload() {
		var configuration = YamlConfiguration.loadConfiguration(configurationFile);
		
		loadFromConfiguration(configuration);
		
		initForbiddenLiquids();
	}
	
	public void initForbiddenLiquids() {
		forbiddenLiquids = new HashSet<Material>();
		
		Logger logger = BewareLiquids.getPlugin().getLogger();
		
		for(String liquidName : getStringList(FORBIDEN_LIQUIDS_PATH)) {
			try {
				var material = Material.valueOf(liquidName.toUpperCase());
				
				forbiddenLiquids.add(material);
			} catch (IllegalArgumentException e) {
				logger.warning(String.format(
						"Can't load %s liquid: %s",
						liquidName,
						e.getMessage()));
			}
		}
	}
	
	private void loadFromConfiguration(FileConfiguration configuration) {
		try {
			loadFromString(configuration.saveToString());
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isForbiddenLiquid(Block block) {
		return forbiddenLiquids.contains(block.getType());
	}
	
}
