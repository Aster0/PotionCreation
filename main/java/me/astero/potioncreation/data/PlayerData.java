package me.astero.potioncreation.data;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import lombok.Setter;
import me.astero.potioncreation.PotionCreation;
import me.astero.potioncreation.gui.PotionMix;
import me.astero.potioncreation.util.FormatNumbers;



public class PlayerData {
	
	private static PotionCreation main = PotionCreation.getPlugin(PotionCreation.class);
	private static final HashMap<UUID, PlayerData> players = new HashMap<>();
	
	@Getter private final HashMap<ItemStack, PurchasedData> purchasedCart = new HashMap<>(); 
	
	@Getter @Setter private int pageNumber, cartPrice, potionLevelPrice, potionDurationPrice, potionTypePrice, totalCartPrice;
	
	@Getter @Setter private boolean enoughMoney;
	
	@Getter @Setter private int potionSelectionType, levelSelection = 1;
	
	@Getter @Setter private double durationSelection = 0.10, durationSelectionVisual = 0.10;
	
	private Player player;
	
	public PlayerData(Player player)
	{
		this.player = player;
		
		cartPrice = (int) ((main.getFileHandler().getLevelSelectorIncrementPrice() 
								* levelSelection) + (durationSelection 
								* main.getFileHandler().getDurationSelectorIncrementPrice()));
		
		resetPrices();
		
	}
	
	public void updatePlayer(Player player)
	{
		this.player = player;
	}
	
	public static PlayerData instanceOf(Player player)
	{
		
		players.putIfAbsent(player.getUniqueId(), new PlayerData(player));
		
        if (players.containsKey(player.getUniqueId()))
        {
        	players.get(player.getUniqueId()).updatePlayer(player);
        }
        return players.get(player.getUniqueId());
	}
	
	public void openMenu()
	{
		PlayerData.instanceOf(player).setPageNumber(1);
		PlayerData.instanceOf(player).setPotionSelectionType(0);
		new PotionMix(main, player);
	}
	
	public int getTotalCartPrice()
	{
		int total = cartPrice + main.getFileHandler().getPotionSelector().get(potionSelectionType).getPrice() 
				+ potionLevelPrice + potionDurationPrice;
		
	
		return total;
	
	}
	
	public void clearCart()
	{
		getPurchasedCart().clear();
		cartPrice = 0;
		levelSelection = 1;
		durationSelection = 0.10;
		durationSelectionVisual = 0.10;
		resetPrices();
	}
	
	public void resetPrices()
	{
		potionLevelPrice = main.getFileHandler().getLevelSelectorIncrementPrice();
		potionDurationPrice = (int) (main.getFileHandler().getDurationSelectorIncrementPrice() * 0.1);
	}

}
