package me.astero.potioncreation.filemanager;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import lombok.Getter;
import me.astero.potioncreation.PotionCreation;

public class FileManager {
	
	public File  messagesData, potionData;
	@Getter public YamlConfiguration  modifyMessagesData, modifyPotionData;
	
	
	private PotionCreation main;
	
	public FileManager(PotionCreation main)
	{
		this.main = main;
		initiateFiles();
	
	}
	
	public void initiateFiles()
	{
		try {
			initiateYAMLFiles();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public void initiateYAMLFiles() throws IOException
	{


		messagesData = new File(Bukkit.getServer().getPluginManager()
				.getPlugin("PotionCreation").getDataFolder(), "lang.yml");
		
		potionData = new File(Bukkit.getServer().getPluginManager()
				.getPlugin("PotionCreation").getDataFolder(), "potions.yml");
		
		
		if(!potionData.exists()) // If companiondata.yml is not found.
		{
			potionData.createNewFile();
		} 


		
		if(!messagesData.exists()) // If lang.yml is not found.
		{
			main.saveResource("lang.yml", false);
		} 
		

		
				
		modifyMessagesData = YamlConfiguration.loadConfiguration(messagesData);		
		modifyPotionData = YamlConfiguration.loadConfiguration(potionData);		

	}
	
    public void reloadConfigs()
    {
    	
    	main.reloadConfig();
	
		modifyMessagesData = YamlConfiguration.loadConfiguration(messagesData);		
		modifyPotionData = YamlConfiguration.loadConfiguration(potionData);		
		
		main.getFileHandler().initiate();
    }
	
	public void saveFile()
	{
		try {
			modifyPotionData.save(potionData);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
