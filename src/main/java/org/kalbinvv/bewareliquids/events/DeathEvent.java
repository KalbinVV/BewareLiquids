package org.kalbinvv.bewareliquids.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.kalbinvv.bewareliquids.BewareLiquids;

public class DeathEvent implements Listener{

	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		Player player = event.getEntity();

		var securePlacesStorage = BewareLiquids.getSecurePlacesStorage();

		securePlacesStorage.removeSecurePlace(player);
	}

}
