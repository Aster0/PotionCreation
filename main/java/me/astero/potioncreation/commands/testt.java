package me.astero.potioncreation.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.astero.potioncreation.PotionCreation;
import me.astero.potioncreation.gui.PotionMix;

public class testt implements CommandExecutor {
	
	private PotionCreation main;
	public testt(PotionCreation main)
	{
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


		Player player = (Player) sender;
		
		new PotionMix(main, player);
		
		player.getInventory().addItem(main.getFileHandler().getPotions().get("SPEED-CHOOSEN").getItem());
		System.out.println(main.getFileHandler().getPotions().get("SPEED-CHOOSEN").getDescription());
		System.out.println(main.getFileHandler().getPotions().get("SPEED").getDescription());
		return false;
	}

}
