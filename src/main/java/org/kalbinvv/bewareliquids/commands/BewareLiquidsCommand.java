package org.kalbinvv.bewareliquids.commands;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.kalbinvv.bewareliquids.BewareLiquids;
import org.kalbinvv.bewareliquids.BewareLiquidsConfiguration;

public class BewareLiquidsCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, 
			String[] args) {
		if(args.length < 1) {
			return false;
		}

		if(args[0].equals("reload")) {
			reloadSubcommand(sender);
		}else {
			helpSubcommand(sender);
		}

		return true;
	}

	private void helpSubcommand(CommandSender sender) {
		String helpMessage = BewareLiquids
				.getPlugin()
				.getConfig()
				.getString("messages.help");

		if(sender instanceof Player) {
			Player player = (Player) sender;

			player.sendMessage(helpMessage);
		} else {
			Logger logger = BewareLiquids
					.getPlugin()
					.getLogger();

			logger.info(removeColorCodes(helpMessage));
		}
	}

	private void reloadSubcommand(CommandSender sender) {
		String reloadMessage = BewareLiquids
				.getPlugin()
				.getConfig()
				.getString("messages.reload");

		var bewareLiquidsConfiguration = (BewareLiquidsConfiguration)
				BewareLiquids.getPlugin().getConfig();

		if(sender instanceof Player) {
			Player player = (Player) sender;

			if(!player.hasPermission("bewareliquids.reload")) {
				String permissionMessage = BewareLiquids
						.getPlugin()
						.getConfig()
						.getString("messages.permission");

				player.sendMessage(permissionMessage);
			} else {
				bewareLiquidsConfiguration.reload();

				player.sendMessage(reloadMessage);
			}
		} else {
			bewareLiquidsConfiguration.reload();

			Logger logger = BewareLiquids.getPlugin().getLogger();

			logger.info(removeColorCodes(reloadMessage));
		}
	}
	
	private String removeColorCodes(String unfilteredString) {
		return unfilteredString.replaceAll("§\\w", "");
	}
}
