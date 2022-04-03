package me.astero.potioncreation.listener;

import java.util.Random;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.astero.potioncreation.PotionCreation;
import me.astero.potioncreation.data.PlayerData;
import me.astero.potioncreation.data.PurchasedData;
import me.astero.potioncreation.economy.EconomyHandler;
import me.astero.potioncreation.gui.PotionMix;
import me.astero.potioncreation.potion.PotionMaker;
import me.astero.potioncreation.potiondata.PotionDetail;
import net.md_5.bungee.api.ChatColor;

public class PotionMixListener implements Listener {
	
	private PotionCreation main;
	
	public PotionMixListener(PotionCreation main)
	{
		this.main = main;
	}
	
	@EventHandler
	public void onClick (InventoryClickEvent e)
	{
		Player player = (Player) e.getWhoClicked();
		
		try
		{
			
			boolean mainMenu = ChatColor.translateAlternateColorCodes('&', 
					e.getView().getTitle()).equals(ChatColor.translateAlternateColorCodes('&', 
							main.getFileHandler().getPotionMixingTitle()));
			
			
			if(mainMenu)
			{
				if(e.getCurrentItem() != null)
				{
					e.setCancelled(true);		
					
					String getCurrent = e.getCurrentItem().getItemMeta().getDisplayName();
					
					

					if(getCurrent.equals(ChatColor.translateAlternateColorCodes('&', main.getFileHandler().getGoBackName())))
					{
						
						if(PlayerData.instanceOf(player).getPageNumber() != 1)
						{
							PlayerData.instanceOf(player).setPageNumber(PlayerData.instanceOf(player).getPageNumber() - 1);
							new PotionMix(main, player);
						}
						else
							player.closeInventory();
					
					}
					else if(getCurrent.equals(ChatColor.translateAlternateColorCodes('&', main.getFileHandler().getNextPageName())))
					{
						PlayerData.instanceOf(player).setPageNumber(PlayerData.instanceOf(player).getPageNumber() + 1);
						new PotionMix(main, player);
					}
					else if(getCurrent.equals(ChatColor.translateAlternateColorCodes('&', main.getFileHandler().getPotionSelectorName())))
					{
						if(!main.getFileHandler().isPotionTypeRandom())
						{
							if(PlayerData.instanceOf(player).getPotionSelectionType() + 1 
									== main.getFileHandler().getPotionSelector().size())
							{
								PlayerData.instanceOf(player).setPotionSelectionType(0);
								
							}
							else
							{
								PlayerData.instanceOf(player).setPotionSelectionType(PlayerData.instanceOf(player).getPotionSelectionType() + 1);
							}
					
							
	
							
							new PotionMix(main, player);
						}
					}
					else if(getCurrent.equals(ChatColor.translateAlternateColorCodes('&', main.getFileHandler().getEmptyCartName())))
					{
						if(!PlayerData.instanceOf(player).getPurchasedCart().isEmpty())
						{
							PlayerData.instanceOf(player).clearCart();
							new PotionMix(main, player);
						}
					}
					else if(getCurrent.equals(ChatColor.translateAlternateColorCodes('&', main.getFileHandler().getLevelSelectorName())))
					{
						if(!main.getFileHandler().isLevelSelectionRandom())
						{
							if(PlayerData.instanceOf(player).getLevelSelection() != main.getFileHandler().getMaxPotionLevel())
							{
								PlayerData.instanceOf(player).setLevelSelection(PlayerData.instanceOf(player).getLevelSelection() + 1);
								
								
							}
							else
							{
								PlayerData.instanceOf(player).setLevelSelection(1);
							}
							
							PlayerData.instanceOf(player).setPotionLevelPrice(main.getFileHandler().getLevelSelectorIncrementPrice() 
									* PlayerData.instanceOf(player).getLevelSelection());
							
							new PotionMix(main, player);
						}
					}
					else if(getCurrent.equals(ChatColor.translateAlternateColorCodes('&', main.getFileHandler().getDurationSelectorName())))
					{
						if(!main.getFileHandler().isDurationSelectionRandom())
						{
							if(!(PlayerData.instanceOf(player).getDurationSelectionVisual() >= main.getFileHandler().getMaxPotionDuration()))
							{
								double addition;
								
								if(String.valueOf(PlayerData.instanceOf(player).getDurationSelectionVisual()).contains(".5"))
								{
									addition = 0.5;
								}
								else
								{
									addition = 0.1;
								}
								
								
								PlayerData.instanceOf(player).setDurationSelectionVisual(Double.valueOf(String.format("%.2f", 
										PlayerData.instanceOf(player).getDurationSelectionVisual() + addition)));
								
								PlayerData.instanceOf(player).setDurationSelection(Double.valueOf(String.format("%.2f", 
										PlayerData.instanceOf(player).getDurationSelection() + 0.10)));
							}
							else
							{
								PlayerData.instanceOf(player).setDurationSelection(0.10);
								PlayerData.instanceOf(player).setDurationSelectionVisual(0.10);
							}
							
							PlayerData.instanceOf(player).setPotionDurationPrice((int) (PlayerData.instanceOf(player).getDurationSelection() 
									* main.getFileHandler().getDurationSelectorIncrementPrice()));
							
							new PotionMix(main, player);
						}
					}
					else if(getCurrent.equals(ChatColor.translateAlternateColorCodes('&', main.getFileHandler().getCartName())))
					{
						if(PlayerData.instanceOf(player).isEnoughMoney() && !PlayerData.instanceOf(player).getPurchasedCart().isEmpty())
						{
							
							
							
							Random rand = new Random();
							
							int rate = rand.nextInt(101);
							
							String potionBase;
							
							if(!main.getFileHandler().isPotionTypeRandom())
								potionBase = main.getFileHandler().getPotionSelector()
									.get(PlayerData.instanceOf(player).getPotionSelectionType()).getPotionBaseName();
							else
								potionBase = main.getFileHandler()
										.getPotionSelector().get(rand.nextInt(main.getFileHandler().getPotionSelector().size())).getPotionBaseName();
							
							PotionMaker pm = new PotionMaker(main.getFileHandler().getPotionBrewedName(), 1, potionBase);
							
							
							
							if(rate >= main.getFileHandler().getPotionFailRate())
							{
								
								
								
								
								
								pm.setColor(main.getFileHandler().getPotionPassColor());
								pm.setLore(main.getFileHandler().getPotionPassDescription());
								
								int duration, level;
								
								if(!main.getFileHandler().isDurationSelectionRandom())
								
									duration = (int) (PlayerData.instanceOf(player).getDurationSelection() * 100) * 20;
								else
									duration = (int) (0.1 + (3 - 0.1) * rand.nextDouble() * 100);
								
								if(!main.getFileHandler().isLevelSelectionRandom())
									level = PlayerData.instanceOf(player).getLevelSelection() - 1;
								else
									level = rand.nextInt(main.getFileHandler().getMaxPotionLevel() + 1);
								
								
								for(PurchasedData pd : PlayerData.instanceOf(player).getPurchasedCart().values())
								{
									pm.setPotionEffects(pd.getPotionType(), 
											duration,
											level);
								}
								
								
							}
							else if(rate <= main.getFileHandler().getPotionFailRate())
							{
								for(String effects : main.getFileHandler().getPotionFailEffects())
								{
									
									try
									{
										String[] args = effects.split(";");
										
										pm.setPotionEffects(args[0], Integer.valueOf(args[1]) * 20, Integer.valueOf(args[2]) - 1);
									}
									catch(IllegalArgumentException potionError) {}
								}
								pm.setColor(main.getFileHandler().getPotionFailColor());
								pm.setLore(main.getFileHandler().getPotionFailDescription());
							}
							
							player.getInventory().addItem(pm.make());
							
							EconomyHandler.econ.withdrawPlayer(player, PlayerData.instanceOf(player).getTotalCartPrice());
							PlayerData.instanceOf(player).clearCart();
							
							
							try
							{
								player.playSound(player.getLocation(), 
										Sound.valueOf(main.getFileHandler().getMixSound()), 1.0F, 1.0F);
							}
							 catch(IllegalArgumentException soundNotFound)
							 {
								 System.out.println(ChatColor.GOLD + "POTIONCREATION → " + ChatColor.RED + "Mix sound - " + ChatColor.YELLOW + 
										 main.getFileHandler().getMixSound()+ ChatColor.RED +" is not found.");
							 }
							
							new PotionMix(main, player);
						}
					}
					
					for(PotionDetail pd : main.getFileHandler().getPotions().values())
					{
						
						
						if(getCurrent.equals(ChatColor.translateAlternateColorCodes('&', pd.getName())))
						{
							
							
							if(pd.getMainItem() == null)
							{
								if(e.getClick().isLeftClick())
								{
	
									if(PlayerData.instanceOf(player).getPurchasedCart().get(pd.getItem()) == null) // So it only runs when it's not added yet.
									{
										PurchasedData pud = new PurchasedData();
										
										pud.setPotionType(pd.getType().getName());
										pud.setPotionName(pd.getName());
										PlayerData.instanceOf(player).getPurchasedCart().put(pd.getItem(), pud);
										PlayerData.instanceOf(player).setCartPrice(
												PlayerData.instanceOf(player).getCartPrice() + pd.getPrice());
									}
										
									
									
									
								}
							}
							else
							{
								if(e.getClick().isRightClick())
								{
									if(PlayerData.instanceOf(player).getPurchasedCart().get(pd.getMainItem()) != null) // So it only runs when it's added before.
									{
										PlayerData.instanceOf(player).getPurchasedCart().remove(pd.getMainItem());
										
										PlayerData.instanceOf(player).setCartPrice(
												PlayerData.instanceOf(player).getCartPrice() - pd.getPrice());
									}
								}
								
								
							}	
							
							new PotionMix(main, player);
				

						}

					}
					
					
				}
			}
				
		}
		catch(NullPointerException e1)
		{
			
		}
	}


}
