package org.kalbinvv.bewareliquids.events;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.kalbinvv.bewareliquids.BewareLiquids;
import org.kalbinvv.bewareliquids.BewareLiquidsConfiguration;

public class MoveEvent implements Listener{

	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		if(!player.hasPermission("bewareliquids.use")) {
			return;
		}
		
		BewareLiquidsConfiguration configuration = (BewareLiquidsConfiguration) 
				BewareLiquids.getPlugin().getConfig();
		
		if(player.isInsideVehicle()) {
			if(player.getVehicle() instanceof Boat) {
				if(configuration.getBoolean("allowBoats")) {
					return;
				}
			}
		}
		
		List<String> worlds = configuration.getStringList("worlds");
		
		if(!worlds.contains(player.getWorld().getName())) {
			return;
		}

		var securePlacesStorage = BewareLiquids.getSecurePlacesStorage();

		if(((Entity)player).isOnGround()) {
			securePlacesStorage.setSecurePlace(player, player.getLocation());
		}else {
			Location location = player.getLocation();

			if(configuration.isForbiddenLiquid(location.getBlock())) {
				Location lastSecurePlace = securePlacesStorage
						.getLastSecurePlayerOrNull(player);

				if(lastSecurePlace != null) {
					player.setFallDistance(0);
					player.teleport(lastSecurePlace);
					
					if(configuration.getBoolean("removeFire")) {
						player.setFireTicks(0);
					}
				}
			}

		}
	}

}
