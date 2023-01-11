package org.kalbinvv.bewareliquids.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.kalbinvv.bewareliquids.BewareLiquids;

public class QuitEvent implements Listener{

	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		var securePlacesStorage = BewareLiquids.getSecurePlacesStorage();
		
		securePlacesStorage.removeSecurePlace(player);
	}
	
}
