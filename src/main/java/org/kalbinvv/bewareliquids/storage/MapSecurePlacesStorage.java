package org.kalbinvv.bewareliquids.storage;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MapSecurePlacesStorage implements SecurePlacesStorage{

	private final Map<String, Location> securePlaces;
	
	public MapSecurePlacesStorage() {
		this.securePlaces = new HashMap<String, Location>();
	}
	
	@Override
	public Location getLastSecurePlayerOrNull(Player player) {
		String username = player.getName();
		
		if(securePlaces.containsKey(username)) {
			return securePlaces.get(username);
		}else {
			return null;
		}
	}

	@Override
	public void setSecurePlace(Player player, Location location) {
		securePlaces.put(player.getName(), location);
	}

	@Override
	public void removeSecurePlace(Player player) {
		securePlaces.remove(player.getName());
	}

}
