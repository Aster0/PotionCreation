package me.astero.potioncreation.filemanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import lombok.Getter;
import me.astero.potioncreation.PotionCreation;
import me.astero.potioncreation.potion.PotionMaker;
import me.astero.potioncreation.potiondata.PotionDetail;
import me.astero.potioncreation.potiondata.PotionSelectionDetail;
import me.astero.potioncreation.util.ItemBuilderUtil;

public class FileHandler {
	
	private PotionCreation main;
	@Getter private final HashMap<String, PotionDetail> potions = new HashMap<>();
	@Getter private final ArrayList<ItemStack> potionItems = new ArrayList<>();
	@Getter private final ArrayList<PotionSelectionDetail> potionSelector = new ArrayList<>();
	
	private List<String> nulledPotions = Arrays.asList("UNCRAFTABLE", "WATER", "MUNDANE",
			"THICK", "AWKWARD");
	
	@Getter private boolean potionTypeRandom, levelSelectionRandom, durationSelectionRandom;
	
	@Getter private String potionMixingTitle, goBackName, nextPageName, choosenPotionName, cartName,
	potionSelectorName, emptyCartName, levelSelectorName, durationSelectorName, potionBrewedName, mixSound,
	noPermissionMessage, prefix, pluginReloadMessage;
	
	@Getter private int potionMixingSize, goBackSlot, goBackAmount, nextPageSlot, nextPageAmount, cartAmount,
	cartSlot, potionSelectorAmount, potionSelectorSlot, emptyCartSlot, emptyCartAmount, levelSelectorAmount, 
	levelSelectorSlot, levelSelectorIncrementPrice, maxPotionLevel, durationSelectorAmount, durationSelectorSlot,
	durationSelectorIncrementPrice, maxPotionDuration, potionFailRate, potionPassColor, potionFailColor;
	
	@Getter private List<String> goBackDescription, nextPageDescription, cartDescriptionEmpty,
	cartDescriptionNotEmpty, potionSelectorDescription, potionSelectorTypes, emptyCartDescription,
	levelSelectorDescription, durationSelectorDescription, potionPassDescription, potionFailDescription, potionFailEffects,
	cartNotEnoughMoneyDescription;
	
	
	@Getter private ItemStack goBackItem, nextPageItem, cartEmptyItem, cartNotEmptyItem, emptyCartItem,
	levelSelectorItem, durationSelectorItem, cartNotEnoughMoneyItem;

	
	public FileHandler(PotionCreation main)
	{
		this.main = main;
		initiate();
	}
	
	public void initiate()
	{
		
		noPermissionMessage = main.getFileManager().getModifyMessagesData().getString("messages.noPermission");
		prefix = main.getFileManager().getModifyMessagesData().getString("messages.prefix");
		pluginReloadMessage = main.getFileManager().getModifyMessagesData().getString("messages.pluginReloaded");
				
		potionMixingTitle = main.getConfig().getString("gui.potionMixingMenu.title");
		potionMixingSize = main.getConfig().getInt("gui.potionMixingMenu.size");
		
		potionFailRate = main.getConfig().getInt("settings.freshlyBrewedPotion.fail.rate");
		potionBrewedName = main.getConfig().getString("settings.freshlyBrewedPotion.name");
		
		potionPassDescription = main.getConfig().getStringList("settings.freshlyBrewedPotion.pass.description");
		potionFailDescription = main.getConfig().getStringList("settings.freshlyBrewedPotion.fail.description");
		
		potionFailEffects = main.getConfig().getStringList("settings.freshlyBrewedPotion.fail.effects");
		
		potionPassColor = main.getConfig().getInt("settings.freshlyBrewedPotion.pass.color");
		potionFailColor = main.getConfig().getInt("settings.freshlyBrewedPotion.fail.color");
		
		mixSound = main.getConfig().getString("settings.freshlyBrewedPotion.sound");
		
		potionTypeRandom = main.getConfig().getBoolean("gui.potionSelectorItem.random");
		levelSelectionRandom = main.getConfig().getBoolean("gui.levelSelectorItem.random");
		durationSelectionRandom = main.getConfig().getBoolean("gui.durationSelectorItem.random");
		
		goBackName = main.getConfig().getString("gui.goBackItem.name");
		goBackAmount = main.getConfig().getInt("gui.goBackItem.amount");
		goBackSlot = main.getConfig().getInt("gui.goBackItem.slot");
		goBackDescription = main.getConfig().getStringList("gui.goBackItem.description");
		
		levelSelectorName = main.getConfig().getString("gui.levelSelectorItem.name");
		levelSelectorAmount = main.getConfig().getInt("gui.levelSelectorItem.amount");
		levelSelectorSlot = main.getConfig().getInt("gui.levelSelectorItem.slot");
		levelSelectorIncrementPrice = main.getConfig().getInt("settings.potionLevelIncrementPrice");
		levelSelectorDescription = main.getConfig().getStringList("gui.levelSelectorItem.description");
		maxPotionLevel = main.getConfig().getInt("settings.maxPotionLevel");
		
		durationSelectorName = main.getConfig().getString("gui.durationSelectorItem.name");
		durationSelectorAmount = main.getConfig().getInt("gui.durationSelectorItem.amount");
		durationSelectorSlot = main.getConfig().getInt("gui.durationSelectorItem.slot");
		durationSelectorIncrementPrice = main.getConfig().getInt("settings.potionDurationIncrementPrice");
		durationSelectorDescription = main.getConfig().getStringList("gui.durationSelectorItem.description");
		maxPotionDuration = main.getConfig().getInt("settings.maxPotionDuration");
		
		emptyCartName = main.getConfig().getString("gui.emptyCartItem.name");
		emptyCartAmount = main.getConfig().getInt("gui.emptyCartItem.amount");
		emptyCartSlot = main.getConfig().getInt("gui.emptyCartItem.slot");
		emptyCartDescription = main.getConfig().getStringList("gui.emptyCartItem.description");
		
		
		
		
		cartName = main.getConfig().getString("gui.cartItem.name");
		cartAmount = main.getConfig().getInt("gui.cartItem.amount");
		cartSlot = main.getConfig().getInt("gui.cartItem.slot");
		cartDescriptionEmpty = main.getConfig().getStringList("gui.cartItem.description.empty");
		cartDescriptionNotEmpty = main.getConfig().getStringList("gui.cartItem.description.notEmpty");
		cartNotEnoughMoneyDescription = main.getConfig().getStringList("gui.cartItem.description.notEnoughMoney");
		
		potionSelectorName = main.getConfig().getString("gui.potionSelectorItem.name");
		potionSelectorAmount = main.getConfig().getInt("gui.potionSelectorItem.amount");
		potionSelectorSlot = main.getConfig().getInt("gui.potionSelectorItem.slot");
		potionSelectorDescription = main.getConfig().getStringList("gui.potionSelectorItem.description");
		potionSelectorTypes = main.getConfig().getStringList("gui.potionSelectorItem.type");
		
		System.out.println();
		
		for(String type : potionSelectorTypes)
		{

			
			 try
			 {
				 
				 PotionSelectionDetail psd = new PotionSelectionDetail();
				 
				 List<String> lore = new ArrayList<>();
				 

				 
				 ItemBuilderUtil ibu = new ItemBuilderUtil(
						 Material.valueOf(type),
						 potionSelectorName,
						 potionSelectorAmount);
				 
				 if(main.getFileManager().getModifyPotionData().getString("potions." + type.toLowerCase() + ".price") == null)
				 {
					 main.getFileManager().getModifyPotionData().set("potions." + type.toLowerCase() + ".price", 250);
				 }
				 
				 psd.setPrice(main.getFileManager().getModifyPotionData().getInt("potions." + type.toLowerCase() + ".price"));
						 
				 
				 String typeReplace;
				 
				 if(!potionTypeRandom)
				 {
					typeReplace = type.replace("_", " ");
				 }
				 else
				 {
					 typeReplace = "RANDOM";
				 }
				 
				 for(String description : potionSelectorDescription)
				 {
					 lore.add(description.replace("%potiontype%", typeReplace)
							 .replace("%price%", String.valueOf(psd.getPrice())));
				 }
				 
				 
				ItemStack potionType = ibu.setLore(lore).build();
				
				 
				 psd.setPotionBaseName(type);
				 psd.setPotionBaseType(potionType);
				 

					 
					
				
				 potionSelector.add(psd);
				 
				 System.out.println(ChatColor.GOLD + "  ... " + ChatColor.YELLOW + type.replace("_", " ")
						 + ChatColor.GOLD + " have been successfully"
				 		+ " loaded up as a potion base!");
			 }
			 catch(IllegalArgumentException notValidType)
			 {
				 
			 }
		}
		

		
		 try
		 {
			 cartNotEmptyItem = new ItemBuilderUtil(
					 Material.valueOf(main.getConfig().getString("gui.cartItem.type.notEmpty")),
					 cartName,
					 cartAmount)
					 .setLore(cartDescriptionNotEmpty)
					 .build();
			 
		 }
		 catch(IllegalArgumentException itemNotFound)
		 {
			 cartNotEmptyItem = new ItemBuilderUtil(
					 Material.STONE,
					 cartName,
					 cartAmount)
					 .setLore(cartDescriptionNotEmpty)
					 .build();
		 }
		 
		 
		 try
		 {
			 levelSelectorItem = new ItemBuilderUtil(
					 Material.valueOf(main.getConfig().getString("gui.levelSelectorItem.type")),
					 levelSelectorName,
					 levelSelectorAmount)
					 .setLore(levelSelectorDescription)
					 .build();
			 
		 }
		 catch(IllegalArgumentException itemNotFound)
		 {
			 levelSelectorItem = new ItemBuilderUtil(
					 Material.STONE,
					 levelSelectorName,
					 levelSelectorAmount)
					 .setLore(levelSelectorDescription)
					 .build();
		 }
		 
		 try
		 {
			 durationSelectorItem = new ItemBuilderUtil(
					 Material.valueOf(main.getConfig().getString("gui.durationSelectorItem.type")),
					 durationSelectorName,
					 durationSelectorAmount)
					 .setLore(durationSelectorDescription)
					 .build();
		 }
		 catch(IllegalArgumentException itemNotFound)
		 {
			 durationSelectorItem = new ItemBuilderUtil(
					 Material.STONE,
					 durationSelectorName,
					 durationSelectorAmount)
					 .setLore(durationSelectorDescription)
					 .build();
		 }
		
		
		 try
		 {
			 cartEmptyItem = new ItemBuilderUtil(
					 Material.valueOf(main.getConfig().getString("gui.cartItem.type.empty")),
					 cartName,
					 cartAmount)
					 .setLore(cartDescriptionEmpty)
					 .build();
		 }
		 catch(IllegalArgumentException itemNotFound)
		 {
			 cartEmptyItem = new ItemBuilderUtil(
					 Material.STONE,
					 cartName,
					 cartAmount)
					 .setLore(cartNotEnoughMoneyDescription)
					 .build();
		 }
		 
		 try
		 {
			 cartNotEnoughMoneyItem = new ItemBuilderUtil(
					 Material.valueOf(main.getConfig().getString("gui.cartItem.type.notEnoughMoney")),
					 cartName,
					 cartAmount)
					 .setLore(cartNotEnoughMoneyDescription)
					 .build();
			 
		 }
		 catch(IllegalArgumentException itemNotFound)
		 {
			 cartNotEnoughMoneyItem = new ItemBuilderUtil(
					 Material.STONE,
					 cartName,
					 cartAmount)
					 .setLore(cartDescriptionNotEmpty)
					 .build();
		 }
		 
		 
		
		
		 try
		 {
			 goBackItem = new ItemBuilderUtil(
					 Material.valueOf(main.getConfig().getString("gui.goBackItem.type")),
					 goBackName,
					 goBackAmount)
					 .setLore(goBackDescription)
					 .build();
		 }
		 catch(IllegalArgumentException itemNotFound)
		 {
			 goBackItem = new ItemBuilderUtil(
					 Material.STONE,
					 goBackName,
					 goBackAmount)
					 .setLore(goBackDescription)
					 .build();
		 }
		 
	 
		nextPageName = main.getConfig().getString("gui.nextPageItem.name");
		nextPageAmount = main.getConfig().getInt("gui.nextPageItem.amount");
		nextPageSlot = main.getConfig().getInt("gui.nextPageItem.slot");
		nextPageDescription = main.getConfig().getStringList("gui.nextPageItem.description");
		
		
		 try
		 {
			 nextPageItem = new ItemBuilderUtil(
					 Material.valueOf(main.getConfig().getString("gui.nextPageItem.type")),
					 nextPageName,
					 nextPageAmount)
					 .setLore(nextPageDescription)
					 .build();
		 }
		 catch(IllegalArgumentException itemNotFound)
		 {
			 nextPageItem = new ItemBuilderUtil(
					 Material.STONE,
					 nextPageName,
					 nextPageAmount)
					 .setLore(nextPageDescription)
					 .build();
		 }
		 
		 
		 try
		 {
			 emptyCartItem = new ItemBuilderUtil(
					 Material.valueOf(main.getConfig().getString("gui.emptyCartItem.type")),
					 emptyCartName,
					 emptyCartAmount)
					 .setLore(emptyCartDescription)
					 .build();
		 }
		 catch(IllegalArgumentException itemNotFound)
		 {
			 emptyCartItem = new ItemBuilderUtil(
					 Material.STONE,
					 nextPageName,
					 nextPageAmount)
					 .setLore(nextPageDescription)
					 .build();
		 }
	
		
			 
	 
		if(main.getFileManager().getModifyPotionData().getString("potions.choosen.name") == null)
		{
			
			main.getFileManager().getModifyPotionData().set("potions.choosen.gui.color",
					16777215);
			

			
			main.getFileManager().getModifyPotionData().set("potions.choosen.gui.description", 
					Arrays.asList("", "&fPotion has already been added to cart.", "", "&6> &7RIGHT CLICK TO REMOVE FROM THE &eCART&7." ));
			
			main.getFileManager().getModifyPotionData().set("potions.choosen.gui.amount", 
					1);
			
		}
				
				
		for(PotionEffectType potion : PotionEffectType.values())
		{
			try
			{
				if(!nulledPotions.contains(potion.toString()))
				{
					
					PotionDetail potionDetail = new PotionDetail();
					
					String path = "potions." + potion.getName().toLowerCase();
					String pricePath = path + ".price";
					
					
					
	
					
					
					if(main.getFileManager().getModifyPotionData().getString(pricePath) == null)
					{
						
						String potionNameEdited;
						
						
						
						if(potion.getName().indexOf("_") != -1)
							
							potionNameEdited = potion.getName().substring(0,1).toUpperCase() +
									potion.getName().substring(1, potion.getName().indexOf("_")).toLowerCase() + " " +
									potion.getName().substring(potion.getName().indexOf("_") + 1, potion.getName().indexOf("_") + 2).toUpperCase() +
									potion.getName().substring(potion.getName().indexOf("_") + 2).toLowerCase();
						
						
						else
							potionNameEdited = potion.getName().substring(0,1).toUpperCase() + 
									potion.getName().substring(1).toLowerCase();
						
						
						main.getFileManager().getModifyPotionData().set(pricePath,
								new Random().nextInt(500));
						
						try
						{
							main.getFileManager().getModifyPotionData().set(path + ".gui.color",
									potion.getColor().asRGB());
						}
						catch(NoSuchMethodError olderVersion) {}
						
	
						main.getFileManager().getModifyPotionData().set(path + ".gui.name", 
								"&6" + potionNameEdited.replace("_", " ") + "&7&l - $%price%");
		
						
						main.getFileManager().getModifyPotionData().set(path + ".gui.description", 
								Arrays.asList("", "&6> &7LEFT CLICK TO ADD TO THE &eCART&7.", ""));
						
						
						main.getFileManager().getModifyPotionData().set(path + ".gui.amount", 
								1);
						
						
						
					}
					
					
					// UPDATES THE ITEM STACK IF THE YML IS UPDATED
					potionDetail.setColor(main.getFileManager().getModifyPotionData().getInt(path + ".gui.color"));
					potionDetail.setPrice(main.getFileManager().getModifyPotionData().getInt(pricePath));
					potionDetail.setName(main.getFileManager().getModifyPotionData().getString(path + ".gui.name").replace("%price%",
							String.valueOf(potionDetail.getPrice())));
					potionDetail.setAmount(main.getFileManager().getModifyPotionData().getInt(path + ".gui.amount"));
					potionDetail.setType(potion);
					potionDetail.setDescription(main.getFileManager().getModifyPotionData().getStringList(path + ".gui.description"));
					
					
					PotionMaker potionMaker = new PotionMaker(potionDetail.getName(), potionDetail.getAmount());
					
					
					
					ItemStack item = potionMaker
							.setLore(potionDetail.getDescription())
							.setColor(potionDetail.getColor())
							.setPotionEffects(potion, 0, 0)
							.make();
					
					potionDetail.setItem(item);
					
					
					potions.put(potion.getName(), potionDetail);
					potionItems.add(potionDetail.getItem());
					
					String potionTypeName = potionDetail.getName();
					
					
					
					
					PotionDetail potionDetail2 = new PotionDetail();
					
					PotionMaker potionMaker2 = new PotionMaker(potionTypeName, potionDetail.getAmount());
					
					potionDetail2.setColor(main.getFileManager().getModifyPotionData().getInt("potions.choosen.gui.color"));
					
					potionDetail2.setPrice(main.getFileManager().getModifyPotionData().getInt(pricePath));
					potionDetail2.setName(potionTypeName);
					potionDetail2.setAmount(main.getFileManager().getModifyPotionData().getInt("potions.choosen.gui.amount"));
					potionDetail2.setDescription(main.getFileManager().getModifyPotionData().getStringList("potions.choosen.gui.description"));
					
					
					
					
					potionDetail2.setItem(potionMaker2.setLore(potionDetail2.getDescription())
							.setColor(potionDetail2.getColor())
							.make());
					potionDetail2.setMainItem(item);
					
					
					
					potions.put(potion.getName() + "-CHOOSEN", potionDetail2);
					
					
	
	
				}
			}
			catch(NullPointerException nullError) {}
			
			
			
			//versionPotions.add(potion.toString());
		}
		
		System.out.println("\n" + ChatColor.GOLD + "              >--------------------------<");
		System.out.println(ChatColor.GOLD + "              A total of " + ChatColor.YELLOW + potionItems.size() + ChatColor.GOLD + " potion have");
		System.out.println(ChatColor.GOLD + "                    been loaded up." );
		System.out.println(ChatColor.GOLD + "              >--------------------------< \n");
		
		main.getFileManager().saveFile();
		

		
		
	}

}
