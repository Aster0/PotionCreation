package me.astero.potioncreation.util;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.astero.potioncreation.PotionCreation;
import me.astero.potioncreation.data.PlayerData;


public class PageSystem {
	
	private PotionCreation main;
	
	public PageSystem(PotionCreation main)
	{
		
		this.main = main;
		
	}
	
	public void buildPageSystem(Inventory inventory, Player player, int inventorySize, int exceptObjects, ArrayList<ItemStack> arrayList)
	{
		int itemStackPerPage = inventorySize - exceptObjects; // (goBack, nextPage.. etc ItemStacks that you want to exempt.)
		
		int startValue = (PlayerData.instanceOf(player).getPageNumber() - 1) * itemStackPerPage; // if page one... = 0... 



		
		int endValue = PlayerData.instanceOf(player).getPageNumber()  * itemStackPerPage;
		
		if(endValue > arrayList.size())
		{
			endValue = arrayList.size();
		}
		
		try
		{
			for(ItemStack itemStackS : arrayList.subList(startValue, endValue))
			{
							
				    	 if(PlayerData.instanceOf(player).getPurchasedCart().get(itemStackS) == null)
				    	 {
					    	 if(!inventory.contains(itemStackS))
					    	 {
					    		 inventory.addItem(itemStackS);
					    	 }
				    	 }
				    	 else
				    	 {
				    		 inventory.addItem(main.getFileHandler().getPotions().get(
				    				 PlayerData.instanceOf(player).getPurchasedCart().get(itemStackS).getPotionType() + "-CHOOSEN").getItem());
				    		 
				    		 
				    	 }

			     
			}
			
		}
	    catch(IllegalArgumentException error)
	    {
	    	inventory.addItem(new ItemStack(Material.AIR));
	    } 
	}

}
