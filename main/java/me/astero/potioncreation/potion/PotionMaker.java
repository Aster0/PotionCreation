package me.astero.potioncreation.potion;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class PotionMaker {
	
	private ItemStack potion;
	private PotionMeta potionMeta;
	
	public PotionMaker(String itemName, int stackAmount)
	{
		potion = new ItemStack(Material.POTION, stackAmount);
		
		potionMeta = (PotionMeta) potion.getItemMeta();
		
		potionMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName));
		
	}
	
	public PotionMaker(String itemName, int stackAmount, String potionBase)
	{
		potion = new ItemStack(Material.valueOf(potionBase), stackAmount);
		
		potionMeta = (PotionMeta) potion.getItemMeta();
		
		potionMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName));
		
	}
	
	
	public PotionMaker setColor(int rgb)
	{
		try
		{
			potionMeta.setColor(Color.fromRGB(rgb));
			
			
		}
		catch(NoSuchMethodError olderVersion) {}
		
		return this;
	}
	
	public PotionMaker setLore(List<String> lore)
	{
		final ArrayList<String> setLore = new ArrayList<>();
		
		for(String description : lore)
		{
			setLore.add(ChatColor.translateAlternateColorCodes('&', description));
		}
		
		potionMeta.setLore(setLore);
		
		return this;
	}
	
	public PotionMaker setPotionEffects(PotionEffectType type, int duration, int level)
	{
		
		potionMeta.addCustomEffect(new PotionEffect(type, duration, level), false);
		return this;
	}
	
	public PotionMaker setPotionEffects(String type, int duration, int level)
	{
		
		potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.getByName(type), duration, level), false);
		return this;
	}
	
	public ItemStack make()
	{
		potion.setItemMeta(potionMeta);
		
		return potion;
	}
	

	
	

}
