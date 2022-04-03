package me.astero.potioncreation.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.astero.potioncreation.PotionCreation;
import me.astero.potioncreation.data.PlayerData;
import me.astero.potioncreation.gui.PotionMix;
import net.md_5.bungee.api.ChatColor;

public class PotionCreationCommand implements CommandExecutor {
	
	private PotionCreation main;
	
	public PotionCreationCommand(PotionCreation main)
	{
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if(args.length < 1)
			{
				PlayerData.instanceOf(player).openMenu();
				
			}
			else
			{
				if(args[0].equalsIgnoreCase("reload"))
				{
					if(player.hasPermission("potioncreation.admin.reload"))
					{
						main.getFileManager().reloadConfigs();
						
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getFileHandler().getPrefix() + 
								main.getFileHandler().getPluginReloadMessage()));
					}
					else
					{
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getFileHandler().getPrefix() + 
								main.getFileHandler().getNoPermissionMessage()));
					}
				}
			}
		}
		else
		{
			System.out.println(ChatColor.RED + "You must be a player to use this command!");
		}
		// TODO Auto-generated method stub
		return false;
	}

}
