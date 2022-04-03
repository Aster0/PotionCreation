package me.astero.potioncreation.gui;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.astero.potioncreation.PotionCreation;
import me.astero.potioncreation.data.PlayerData;
import me.astero.potioncreation.data.PurchasedData;
import me.astero.potioncreation.economy.EconomyHandler;
import me.astero.potioncreation.util.FormatNumbers;
import me.astero.potioncreation.util.InventoryBuilder;
import me.astero.potioncreation.util.PageSystem;
import net.md_5.bungee.api.ChatColor;

public class PotionMix {
	
	
	private PotionCreation main;
	private Player player;
	
	public PotionMix(PotionCreation main, Player player)
	{
		this.main = main;
		this.player = player;
		
		if(player.hasPermission("potioncreation.player.menu"))
		{
			buildInventory();
		}
		else
		{
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getFileHandler().getPrefix() + 
					main.getFileHandler().getNoPermissionMessage()));
		}
	}
	
	private void buildInventory()
	{
		
		
		

		ItemStack cart;
		
		if(PlayerData.instanceOf(player).getPurchasedCart().isEmpty())
		{
			cart = main.getFileHandler().getCartEmptyItem();
		}
		else
		{
			if(EconomyHandler.econ.has(player, PlayerData.instanceOf(player).getTotalCartPrice()))
			{
				PlayerData.instanceOf(player).setEnoughMoney(true);
				cart = main.getFileHandler().getCartNotEmptyItem();
				
				ItemMeta cartMeta = cart.getItemMeta();
				
				final ArrayList<String> lore = new ArrayList<>();

				
				for(String description : main.getFileHandler().getCartDescriptionNotEmpty())
				{
					lore.add(ChatColor.translateAlternateColorCodes('&', 
							description.replace("%price%", FormatNumbers.format(PlayerData.instanceOf(player).getTotalCartPrice()))
							.replace("%potions%", "")));
					
					
					
					if(description.equals("%potions%"))
					{
						for(PurchasedData pd : PlayerData.instanceOf(player).getPurchasedCart().values())
						{
							lore.add(ChatColor.translateAlternateColorCodes('&', pd.getPotionName()));
							
						}
					}
				}
				
				
				cartMeta.setLore(lore);
				
				cart.setItemMeta(cartMeta);
			}
			else
			{
				PlayerData.instanceOf(player).setEnoughMoney(false);
				cart = main.getFileHandler().getCartNotEnoughMoneyItem();
			}
			
		}
		
		
		ItemStack levelSelectorItem = main.getFileHandler().getLevelSelectorItem();
		ItemMeta levelSelectorItemMeta = levelSelectorItem.getItemMeta();
		
		final ArrayList<String> lore = new ArrayList<>();
		
		String potionLevel; 
		
		
		if(!main.getFileHandler().isLevelSelectionRandom())
			potionLevel = String.valueOf(PlayerData.instanceOf(player).getLevelSelection());
		else
			potionLevel = "RANDOM";
		
		for(String description : main.getFileHandler().getLevelSelectorDescription())
		{
			lore.add(ChatColor.translateAlternateColorCodes('&', description
					.replace("%potionlevel%", potionLevel)
					.replace("%price%",
							FormatNumbers.format(main.getFileHandler().getLevelSelectorIncrementPrice() 
									* PlayerData.instanceOf(player).getLevelSelection()))));
		}
		

		
		levelSelectorItemMeta.setLore(lore);
		levelSelectorItem.setItemMeta(levelSelectorItemMeta);
		
		
		ItemStack durationSelectorItem = main.getFileHandler().getDurationSelectorItem();
		ItemMeta durationSelectorItemMeta = durationSelectorItem.getItemMeta();
		
		lore.clear();
		
		String potionDuration; 
		
		
		if(!main.getFileHandler().isDurationSelectionRandom())
			potionDuration = String.valueOf(PlayerData.instanceOf(player).getDurationSelectionVisual()).replace(".", ":") + "0";
		else
			potionDuration = "RANDOM";
		
		for(String description : main.getFileHandler().getDurationSelectorDescription())
		{
			lore.add(ChatColor.translateAlternateColorCodes('&', description
					.replace("%potionduration%", potionDuration)
					.replace("%price%",
							FormatNumbers.format((int) (main.getFileHandler().getDurationSelectorIncrementPrice()
									* PlayerData.instanceOf(player).getDurationSelection())))));
		}
		

		
		durationSelectorItemMeta.setLore(lore);
		durationSelectorItem.setItemMeta(durationSelectorItemMeta);
		
		Inventory menu = new InventoryBuilder(main.getFileHandler().getPotionMixingSize()
				, main.getFileHandler().getPotionMixingTitle())
				.setItem(main.getFileHandler().getGoBackSlot(), main.getFileHandler().getGoBackItem())
				.setItem(main.getFileHandler().getNextPageSlot(), main.getFileHandler().getNextPageItem())
				.setItem(main.getFileHandler().getCartSlot(), cart)
				.setItem(main.getFileHandler().getPotionSelectorSlot(), 
						main.getFileHandler().getPotionSelector().get(PlayerData.instanceOf(player).getPotionSelectionType()).getPotionBaseType())
				.setItem(main.getFileHandler().getEmptyCartSlot(), main.getFileHandler().getEmptyCartItem())
				.setItem(main.getFileHandler().getLevelSelectorSlot(), levelSelectorItem)
				.setItem(main.getFileHandler().getDurationSelectorSlot(), durationSelectorItem)
				.build();
		

		
				new PageSystem(main).buildPageSystem(menu, player, main.getFileHandler().getPotionMixingSize(),
						4, main.getFileHandler().getPotionItems());

			
			
		
		
	
		

		
		openInventory(menu);
	}
	
	private void openInventory(Inventory menu)
	{
		player.openInventory(menu);
	}

}
