package me.astero.potioncreation.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class ItemBuilderUtil {
	
	private ItemStack itemStack;
	private ItemMeta itemMeta;
	private ArrayList<String> setLore = new ArrayList<>();
	
	public ItemBuilderUtil(Material material, String displayName, int stackAmount)
	{

		this.itemStack = new ItemStack(material, stackAmount);

	
		itemMeta = this.itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		
		
		
		
	}
	
	public ItemBuilderUtil(ItemStack itemStack, String displayName)
	{

		this.itemStack = itemStack;

	
		ItemMeta itemMeta = this.itemStack.getItemMeta();
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
		
		this.itemStack.setItemMeta(itemMeta);
		
		
	}
	
	public ItemBuilderUtil setLore(List<String> list)
	{

		for(String lore : list)
		{
			setLore.add(ChatColor.translateAlternateColorCodes('&', lore));
		}
		itemMeta.setLore(setLore);
		
		
		
		return this;
	}
	
	public ItemBuilderUtil setLore(String... lore)
	{	
		itemMeta.setLore(Arrays.asList(lore));
		
		
		return this;
	}
	
	
	
	public ItemStack build()
	{
		this.itemStack.setItemMeta(itemMeta);
		
		
		return this.itemStack;
	}

}
