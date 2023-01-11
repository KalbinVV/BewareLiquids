package org.kalbinvv.bewareliquids.storage;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface SecurePlacesStorage {

	public Location getLastSecurePlayerOrNull(Player player);
	public void setSecurePlace(Player player, Location location);
	public void removeSecurePlace(Player player);
	
}
