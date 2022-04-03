package me.astero.potioncreation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import me.astero.potioncreation.commands.PotionCreationCommand;
import me.astero.potioncreation.commands.testt;
import me.astero.potioncreation.economy.EconomyHandler;
import me.astero.potioncreation.filemanager.FileHandler;
import me.astero.potioncreation.filemanager.FileManager;
import me.astero.potioncreation.listener.PotionMixListener;

public class PotionCreation extends JavaPlugin{

	@Getter private FileManager fileManager;
	@Getter private FileHandler fileHandler;
	
	@Override
	public void onEnable()
	{
		System.out.println("\n" + ChatColor.GOLD + "PotionCreation" + ChatColor.GRAY + " by Astero" + ChatColor.GOLD + " is loading up...\n");
		
		getConfig().options().copyDefaults();
		saveDefaultConfig();
		
		fileManager = new FileManager(this);
		System.out.println(ChatColor.GOLD + ">" + ChatColor.GRAY + " YAML files are loaded up!");
		
		fileHandler = new FileHandler(this);
		System.out.println(ChatColor.GOLD + ">" + ChatColor.GRAY + " Caching files is done!");
		
		
		new EconomyHandler(this);
		System.out.println(ChatColor.GOLD + ">" + ChatColor.GRAY + " Misc files are loaded up!");
		

		getCommand("potioncreation").setExecutor(new PotionCreationCommand
				(this));
		System.out.println(ChatColor.GOLD + ">" + ChatColor.GRAY + " Commands are loaded up!");
		
		
		Bukkit.getPluginManager().registerEvents(new PotionMixListener(this), this);
		System.out.println(ChatColor.GOLD + ">" + ChatColor.GRAY + " Event Listeners are loaded up!");
		
		System.out.println();
		
		System.out.println(ChatColor.GOLD + "PotionCreation" + ChatColor.GRAY + " by Astero" + ChatColor.GOLD + " has been sucessfully loaded up!\n");
		
	}
}
